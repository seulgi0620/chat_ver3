package chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import chat.client.method.MemLogic;
import chat.client.method.MessagesNotRead;
import chat.util.MemVO;
import chat.util.Protocol;

@SuppressWarnings("serial")
public class UserMainFrame extends JFrame implements ActionListener {

	public String user_id;

	public String user_name = null;

	MemLogic ml = new MemLogic();

	public JMenuBar jmb;
	public FriendsPage friendspage;
	public MyChatroomList2 chatroompage;
	public SettingPage settingpage;
	public JButton jb_friend;
	public JButton jb_chat;
	public JButton jb_setting;
	public JButton jb_logout;

	Font f = new Font("¸¼Àº °íµñ", Font.PLAIN, 13);

	ImageIcon img = new ImageIcon("src/chat/imgs/friend.png");
	ImageIcon img2 = new ImageIcon("src/chat/imgs/chat.png");
	ImageIcon img3 = new ImageIcon("src/chat/imgs/setting.png");
	ImageIcon img4 = new ImageIcon("src/chat/imgs/exit.png");

	Socket mySocket = null;

	public ObjectInputStream ois = null;
	public ObjectOutputStream oos = null;

	public MessagesNotRead mnr = null;
	public String chatroom_code = null;
	LoginView2 lv2 = null;

	String ip = "192.168.0.8";
	int port = 30001;

	public void access_network() {
		try {

			mySocket = new Socket(ip, port);

			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());

			oos.writeObject(Protocol.msg("No_chatroom_code", Protocol.user_connect, user_id, "·Î±×ÀÎ¾²"));

			mnr = new MessagesNotRead(this);
			mnr.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserMainFrame(String user_id, String user_name) {

		this.user_id = user_id;
		this.user_name = user_name;

		friendspage = new FriendsPage(this, user_id);
		chatroompage = new MyChatroomList2(this, user_id, user_name);
		settingpage = new SettingPage();
		jb_friend = new JButton();
		jb_chat = new JButton();
		jb_setting = new JButton();
		jb_logout = new JButton();

		jb_friend.addActionListener(this);
		jb_chat.addActionListener(this);
		jb_setting.addActionListener(this);
		jb_logout.addActionListener(this);

		jmb = new JMenuBar();
		jmb.setLayout(null);
		jmb.setBackground(new Color(248, 136, 137));
		jmb.setPreferredSize(new Dimension(400, 60));

		jb_logout.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jb_logout.setFocusPainted(false);
		jb_logout.setContentAreaFilled(false);
		jb_logout.setIcon(img4);
		jb_logout.setBounds(330, 12, 40, 40);
		jmb.add(jb_logout);

		jb_friend.setBorderPainted(false);
		jb_friend.setFocusPainted(false);
		jb_friend.setContentAreaFilled(false);
		jb_friend.setIcon(img);
		jb_friend.setBounds(30, 20, img.getIconWidth(), img.getIconHeight());
		jmb.add(jb_friend);

		jb_chat.setBorderPainted(false);
		jb_chat.setFocusPainted(false);
		jb_chat.setContentAreaFilled(false);
		jb_chat.setIcon(img2);
		jb_chat.setBounds(90, 20, img2.getIconWidth(), img2.getIconHeight());
		jmb.add(jb_chat);

		jb_setting.setBorderPainted(false);
		jb_setting.setFocusPainted(false);
		jb_setting.setContentAreaFilled(false);
		jb_setting.setIcon(img3);
		jb_setting.setBounds(150, 20, img3.getIconWidth(), img3.getIconHeight());
		jmb.add(jb_setting);

		setTitle("peachTalk");
		setJMenuBar(jmb);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 650);
		setVisible(true);

		access_network();

		add(settingpage);
		add(chatroompage);
		add(friendspage);

		friendspage.setVisible(true);
		chatroompage.setVisible(false);
		settingpage.setVisible(false);

		this.setBackground(Color.WHITE);

		repaint();
		revalidate();

	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jb_friend) {
			remove(friendspage);
			add(friendspage);
			chatroompage.setVisible(false);
			settingpage.setVisible(false);
			friendspage.setVisible(true);
			repaint();
			revalidate();
		} else if (obj == jb_chat) {
			friendspage.setVisible(false);
			settingpage.setVisible(false);
			chatroompage.setVisible(true);
			repaint();
			revalidate();
		} else if (obj == jb_setting) {
			remove(settingpage);
			add(settingpage);
			friendspage.setVisible(false);
			chatroompage.setVisible(false);
			settingpage.setVisible(true);
			repaint();
			revalidate();
		} else if (obj == jb_logout) {
			try {
				mnr.isStop = true;
				ois.close();
				oos.close();
				// mySocket.close();
				System.out.println(Protocol.onair_chatroom);
				
				//if (Protocol.onair_chatroom.size() > 0) {
					for (int i = 0; i < Protocol.onair_chatroom.size(); i++) {
						TalkClient tc = chatroompage.tc_map.get(Protocol.onair_chatroom.get(i));
						tc.dispose(); 
					}
				//	}
					this.dispose();
					lv2 = new LoginView2();
					MemLogic.mvo = new MemVO();
					Protocol.onair_chatroom = new Vector<String>();
				
//				else {
//					this.dispose();
//					lv2 = new LoginView2();
//					MemLogic.mvo = new MemVO();
//				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

}
