package chat.client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import chat.client.method.LastRead;
import chat.util.Protocol;

@SuppressWarnings("serial")
public class TalkClient extends JFrame implements ActionListener {
	
	LastRead lr = new LastRead();
	
	TalkClient me = null;
	
	public UserMainFrame umf    = null;
	public ChatInvite ci = null;
	
	public String user_id       = null;
	public String user_name     = null;
	public String chatroom_code = null;
	
	public Vector<String> participent_list   = null;
	public JList<String> jl_list             = null;
	public JScrollPane js_list               = new JScrollPane();
	
	public JTextArea jta_display 	= new JTextArea();
	public JScrollPane js_display   = new JScrollPane();
	
	JTextField jtf_msg 		= new JTextField();
	JButton jbtn_send 		= new JButton("Àü¼Û");
	JButton jbtn_exit       = new JButton("³ª°¡±â");
	JButton jbtn_invite     = new JButton("ÃÊ´ëÇÏ±â");

	public TalkClient (UserMainFrame umf, String chatroom_code, String user_id, String user_name) {
		
		this.umf           = umf;
		
		this.user_id       = user_id;
		this.user_name     = user_name;
		this.chatroom_code = chatroom_code;
		
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		
		initDisplay();

	}

	public void initDisplay() {
		
		jbtn_invite.addActionListener(this);
		
		this.setLayout(new GridLayout(3, 2));
		this.setTitle(user_id+"´ÔÀÇ ÇÇÄ¡Åå : "+chatroom_code);
	    this.setSize(500,500);
	    this.setVisible(true);
		
		js_display.setViewportView(jta_display);

		jl_list = new JList<String>();
		js_list.setViewportView(jl_list);
		
		this.add(js_display);
		this.add(js_list);
		this.add(jtf_msg);
		this.add(jbtn_exit);
		this.add(jbtn_invite);
		
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

	public void actionPerformed(ActionEvent arg0) {
		
		Object src = arg0.getSource();
		
		if(src == jtf_msg) {

			umf.mnr.send(Protocol.msg(chatroom_code, Protocol.plain_text, user_id, jtf_msg.getText()));
			
			jtf_msg.setText("");
			jta_display.setCaretPosition(jta_display.getDocument().getLength());
			
		} else if(src == jbtn_exit) {

			umf.mnr.send(Protocol.msg(chatroom_code, Protocol.exit_room, user_id, "´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù."));
			
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
			
		}
		
	}

}
