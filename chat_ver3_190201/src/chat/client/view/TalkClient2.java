package chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import chat.client.method.LastRead;
import chat.client.method.TalkPanel2;
import chat.util.Protocol;

@SuppressWarnings("serial")
public class TalkClient2 extends JFrame implements ActionListener {
LastRead lr = new LastRead();
	
	TalkClient2 me = null;
	
	public UserMainFrame umf    = null;
	public ChatInvite ci = null;
	
	public String user_id       = null;
	public String user_name     = null;
	public String chatroom_code = null;
	
	public Vector<String> participent_list   = null;
	public JList<String> jl_list = null;
	public JScrollPane js_list               = new JScrollPane();

	public JPanel jp_chat = new JPanel();
	public JScrollPane sp = new JScrollPane(jp_chat,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public JTextArea jta_display 	= new JTextArea();
	public JScrollPane js_display   = new JScrollPane();

	JPanel jp_north = new JPanel();
	JPanel jp_center = new JPanel();
	JPanel jp_south = new JPanel();
	JPanel jp_south_center = new JPanel();
	JPanel jp_south_east = new JPanel();
	JPanel jp_south_south = new JPanel();
	
	JTextField jta = new JTextField();
	JButton jbtn_send = new JButton("전송");
	JButton jbtn_invite = new JButton("초대");
	JButton jbtn_exit = new JButton("나가기");
	JButton jbtn_list = new JButton("참여자목록");
	
	JButton jbtn_imnoticon = new JButton("이모티콘");
	Color b=new Color(178,199,217);
	
	JDialog jdl_list = new JDialog();
	JPanel jp_jdl = new JPanel();
	
	public int grid = 0;
	public int min_grid = 1;
	
	Font blank = new Font("굴림체",Font.BOLD,5);
	
	String namecheck = null;
	
	int check = 0;
	Vector<JLabel> timecheck = new Vector<JLabel>();
	
	String path = "C:\\웨딩피치img\\";
	
	public TalkClient2 (UserMainFrame umf, String chatroom_code, String user_id, String user_name) {
		
		this.umf           = umf;
		
		this.user_id       = user_id;
		this.user_name     = user_name;
		this.chatroom_code = chatroom_code;
		
		jta.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_invite.addActionListener(this);
		jbtn_list.addActionListener(this);
		
		initDisplay();
		
	}
	
	public void initDisplay() {

		jdl_list.setVisible(false);
		jdl_list.setSize(200, 400);
		jdl_list.setLayout(new BorderLayout());
		jdl_list.add("Center",jp_jdl);
		jp_jdl.setLayout(new BorderLayout());
		
		jl_list = new JList<String>();
		js_list = new JScrollPane(jl_list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		jp_jdl.add("Center",js_list);
		
		this.setTitle(user_id+"님의 피치톡: "+chatroom_code);
		this.setVisible(true);
		this.setSize(450, 600);

		this.add("North",jp_north);
		this.add("Center", jp_center);
		this.add("South", jp_south);
		
		jp_north.setLayout(new GridLayout(0, 3));
		jp_north.add(jbtn_invite);
		jp_north.add(jbtn_exit);
		jp_north.add(jbtn_list);

		jp_south.setLayout(new BorderLayout());
		jp_south.add("Center",jp_south_center);
		jp_south.add("South",jp_south_south);
		jp_south.add("East",jp_south_east);

		jp_south_center.setLayout(new BorderLayout());
		jp_south_center.add("Center",jta);
		
		jp_south_east.setLayout(new BorderLayout());
		jp_south_east.add(jbtn_send);
		
		jp_south_south.setLayout(new FlowLayout());
		jp_south_south.add(jbtn_imnoticon);
		
		jp_center.setLayout(new BorderLayout());
		jp_center.add("Center",sp);
		
		jp_chat.setBackground(b);
		
		me = this;

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				
				Protocol.onair_chatroom.remove(chatroom_code);
				lr.updateLastRead(umf.user_id, chatroom_code);

				dispose();
			}
			
			public void windowActivated(java.awt.event.WindowEvent windowEvent) {
				
				Protocol.chatroom_on.add(chatroom_code);
				
				for(int i=0;i<umf.chatroompage.chatroom_list.size();i++) {
					
					if(umf.chatroompage.chatroom_list.get(i).equals(chatroom_code)) {
						lr.updateLastRead(umf.user_id, chatroom_code);
						
						JPanel jp_outer = MyChatroomList2.chatroom_map.get(chatroom_code);
						JPanel jp_inner1 = (JPanel) jp_outer.getComponent(2);
						JPanel jp_inner2 = (JPanel) jp_inner1.getComponent(1);
						JLabel jl_inner3 = (JLabel) jp_inner2.getComponent(0);
						jl_inner3.setText("0");
					}
					
				}
				
			}
			public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
				Protocol.chatroom_on.remove(chatroom_code);
				lr.updateLastRead(umf.user_id, chatroom_code);
			}
			
		});

		this.repaint();
		this.revalidate();
		
	}
	
	public void addp(String p_user_id, String text, String time) {
		TalkPanel2 c2 = new TalkPanel2();
		
		if(grid<6) {
			jp_chat.setLayout(new GridLayout(7,0));
		}
		else {
			jp_chat.setLayout(new GridLayout(grid+1,0));
		}
		
		grid++;
		
		if(!user_id.equals(p_user_id)) {
			
			//jp1의 레이아웃설정
			c2.jp1.setLayout(new BorderLayout());
			//jp1에 왼쪽과 센터박기
			c2.jp1.add("West",c2.jp2);
			c2.jp1.add("Center",c2.jp3);
			c2.jp3.setBackground(Color.WHITE);
			c2.jp2.setLayout(new BorderLayout());
			//jp2에 왼쪽에 프로필박기
			c2.jp2.add("West",c2.jp4);
			c2.jp4.setLayout(new BorderLayout());
			c2.jp4.add("North",c2.jp11);
			c2.jp11.setLayout(new BorderLayout());
			c2.jp11.add("West",c2.jlb_profile);
			c2.jp11.add("East",c2.jlb_chatimg);  
			//jp2 센터에 패널박기 +
			c2.jp2.add("Center",c2.jp5);
			c2.jp5.setLayout(new BorderLayout());
			c2.jp5.add("North",c2.jp6);
			c2.jp6.setLayout(new BorderLayout());
			c2.jp6.add("West",c2.jlb_name);
			c2.jp5.add("Center",c2.jp7);
			//텍스트붙이기
			c2.jp7.setLayout(new BorderLayout());
			c2.jp7.add("West",c2.jp8);
			///////
			c2.jp7.add("Center",c2.jp9);
			c2.jp9.setLayout(new BorderLayout());
			c2.jp9.add("South",c2.jp10);
			c2.jp10.setLayout(new GridLayout(2, 0));
			c2.jp10.add(c2.jlb_count);
			c2.jp10.add(c2.jlb_time);
	
			c2.jp10.setOpaque(true);
			c2.jp10.setBackground(Color.blue);
			c2.jp4.setBackground(b);
			c2.jp10.setBackground(b);
			c2.jp6.setBackground(b);
			c2.jp11.setBackground(b);
			c2.jp9.setBackground(b);
			c2.jp3.setBackground(b);
			c2.jp8.setBackground(Color.WHITE);
			
		} else if(user_id.equals(p_user_id)) {
			
			c2.jp1.setLayout(new BorderLayout());
			c2.jp1.add("Center",c2.jp2);
			c2.jp1.add("East",c2.jp3);
			c2.jp3.setLayout(new BorderLayout());
			c2.jp3.add("East",c2.jlb_chatimg2);
			c2.jp3.add("West",c2.jp9);
			c2.jp9.setLayout(new BorderLayout());
			c2.jp9.add("South",c2.jp10);
			c2.jp10.setLayout(new GridLayout(2, 0));
			c2.jp10.add(c2.jp11);
			c2.jp10.add(c2.jp12);
			c2.jp11.setLayout(new BorderLayout());
			c2.jp12.setLayout(new BorderLayout());
			c2.jp11.add("East",c2.jlb_count);
			c2.jp12.add("East",c2.jlb_time);

			c2.jp3.add("Center",c2.jp8);
			c2.jp1.add("North",c2.jlb_blank2);
			
			this.check = 1;
			c2.jp11.setBackground(b);
			c2.jp12.setBackground(b);
			c2.jp3.setBackground(b);
			c2.jp1.setBackground(b);
			c2.jp10.setBackground(b);
			c2.jp2.setBackground(b);
			c2.jp8.setBackground(Color.YELLOW);
			c2.jp9.setBackground(b);
			c2.jp4.setBackground(b);
			
			c2.jp1.add("South",c2.jp_bottom);
			c2.jp_bottom.setBackground(b);
			c2.jp_bottom.setLayout(new BorderLayout());
			c2.jp_bottom.add("North",c2.jlb_blank);
			c2.jlb_blank.setFont(blank);
			
			c2.jlb_chatimg2.setIcon(new ImageIcon("C:/말풍반대2.png"));
			
		}

		if(text.length()>180) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,100);
			String text6 = text.substring(100,120);
			String text7 = text.substring(120,140);
			String text8 = text.substring(140,160);
			String text9 = text.substring(160,180);
			String text10 = text.substring(180,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			c2.jlb_text6.setText(text6);
			c2.jlb_text7.setText(text7);
			c2.jlb_text8.setText(text8);
			c2.jlb_text9.setText(text9);
			c2.jlb_text9.setText(text10);
			
			c2.jp8.setLayout(new GridLayout(10, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			c2.jp8.add(c2.jlb_text6);
			c2.jp8.add(c2.jlb_text7);
			c2.jp8.add(c2.jlb_text8);
			c2.jp8.add(c2.jlb_text9);
			c2.jp8.add(c2.jlb_text10);
			
		} else if(text.length()>160) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,100);
			String text6 = text.substring(100,120);
			String text7 = text.substring(120,140);
			String text8 = text.substring(140,160);
			String text9 = text.substring(160,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			c2.jlb_text6.setText(text6);
			c2.jlb_text7.setText(text7);
			c2.jlb_text8.setText(text8);
			c2.jlb_text9.setText(text9);
			
			c2.jp8.setLayout(new GridLayout(9, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			c2.jp8.add(c2.jlb_text6);
			c2.jp8.add(c2.jlb_text7);
			c2.jp8.add(c2.jlb_text8);
			c2.jp8.add(c2.jlb_text9);
			
		} else if(text.length()>140) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,100);
			String text6 = text.substring(100,120);
			String text7 = text.substring(120,140);
			String text8 = text.substring(140,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			c2.jlb_text6.setText(text6);
			c2.jlb_text7.setText(text7);
			c2.jlb_text8.setText(text8);
			
			c2.jp8.setLayout(new GridLayout(8, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			c2.jp8.add(c2.jlb_text6);
			c2.jp8.add(c2.jlb_text7);
			c2.jp8.add(c2.jlb_text8);
			
		} else if(text.length()>120) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,100);
			String text6 = text.substring(100,120);
			String text7 = text.substring(120,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			c2.jlb_text6.setText(text6);
			c2.jlb_text7.setText(text7);
			
			c2.jp8.setLayout(new GridLayout(7, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			c2.jp8.add(c2.jlb_text6);
			c2.jp8.add(c2.jlb_text7);
			
		} else if(text.length()>100) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,100);
			String text6 = text.substring(100,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			c2.jlb_text6.setText(text6);
			
			c2.jp8.setLayout(new GridLayout(6, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			c2.jp8.add(c2.jlb_text6);
			
		} else if(text.length()>80) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,80);
			String text5 = text.substring(80,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			c2.jlb_text5.setText(text5);
			
			c2.jp8.setLayout(new GridLayout(5, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
			c2.jp8.add(c2.jlb_text5);
			
		} else if(text.length()>60) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,60);
			String text4 = text.substring(60,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			c2.jlb_text4.setText(text4);
			
			c2.jp8.setLayout(new GridLayout(4, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			c2.jp8.add(c2.jlb_text4);
		} else if(text.length()>40) {
		
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,40);
			String text3 = text.substring(40,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			c2.jlb_text3.setText(text3);
			
			c2.jp8.setLayout(new GridLayout(3, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			c2.jp8.add(c2.jlb_text3);
			
		} else if(text.length()>20) {
			
			String text1 = text.substring(0,20);
			String text2 = text.substring(20,text.length());
			
			c2.jlb_text1.setText(text1);
			c2.jlb_text2.setText(text2);
			
			c2.jp8.setLayout(new GridLayout(2, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jp8.add(c2.jlb_text2);
			
		} else if(text.length()>0) {
			
			String text1 = text.substring(0,text.length());

			c2.jp8.setLayout(new GridLayout(1, 0));
			c2.jp8.add(c2.jlb_text1);
			c2.jlb_text1.setText(text1+"   ");
			
		}
		
		c2.jlb_name.setText(p_user_id);
		c2.jlb_time.setText(time);
		
		jp_chat.add(c2.jp1);
		
		sp.getVerticalScrollBar().setValue(jp_chat.getY());	
		
		if(!p_user_id.equals(namecheck)) {
			
			if(check==0) {
				c2.jlb_chatimg.setIcon(new ImageIcon(path+"말풍선3.png"));
			} else if(check==1) {
				c2.jlb_chatimg2.setIcon(new ImageIcon(path+"말풍반대1.png"));
			}

			c2.jlb_profile.setIcon(new ImageIcon(path+"디폴트프사.png"));
			c2.jp1.add("South",c2.jp_bottom);
			c2.jp_bottom.setBackground(b);
			c2.jp_bottom.setLayout(new BorderLayout());
			c2.jp_bottom.add("North",c2.jlb_blank);
			c2.jlb_blank.setFont(blank);
			
			if(p_user_id.equals("test1")||p_user_id.equals("test2")||p_user_id.equals("test3")||p_user_id.equals("aoor")||p_user_id.equals("sg1")){
				c2.jlb_profile.setIcon(new ImageIcon(path+p_user_id+".png"));
			}
			
		} else if(p_user_id.equals(namecheck)) {
			
			c2.jlb_profile.setIcon(new ImageIcon(path+"공백.png"));
			c2.jlb_chatimg.setIcon(new ImageIcon(path+"말풍선공백.png"));
			c2.jlb_name.setText("  ");
			c2.jlb_chatimg2.setIcon(new ImageIcon(path+"말풍2.png"));
			
		}
		
		this.namecheck = p_user_id;
		this.check = 0;
		
		timecheck.add(c2.jlb_time);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		Object src = arg0.getSource();
		
		if(src == jta||src == jbtn_send) {
			
			if(jta.getText().length()!=0) {
				umf.mnr.send(Protocol.msg(chatroom_code, Protocol.plain_text, user_id, jta.getText()));
				jta.setText("");
			}
			
		} else if(src == jbtn_exit) {
			
			umf.mnr.send(Protocol.msg(chatroom_code, Protocol.exit_room, user_id, "님이 퇴장하셨습니다."));
			this.dispose();
			
		} else if(src == jbtn_invite) {
			
			if(ci == null) {
				ChatInvite ci = new ChatInvite(this);
				this.ci = ci;
			} else if(ci != null) {
				this.remove(ci);
				this.ci = null;
				ChatInvite ci = new ChatInvite(this);
				this.ci = ci;
			}
			
			ci.initDisplay();
			ci.invitelist();
			
		} else if(src == jbtn_list) {
			jdl_list.setVisible(true);
		}
		
	}

}
