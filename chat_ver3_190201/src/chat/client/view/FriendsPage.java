package chat.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import chat.client.event.CreatingRoom;
import chat.client.event.SearchFriend;
import chat.client.method.MemLogic;
import chat.client.method.MyfriendsListPanel;
import chat.util.DBConnectionMgr;
import chat.util.Protocol;

@SuppressWarnings("serial")
public class FriendsPage extends JPanel implements ActionListener {

	
	public UserMainFrame umf = null;
	public MyfriendsListPanel mflp = null;

	public String user_id = null;
	public String user_name = null;

	DBConnectionMgr dbMgr = new DBConnectionMgr();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public JButton jbtn_start_chat = new JButton("방만들기");

	JLabel jl_icon = new JLabel();

	ImageIcon icon_search = new ImageIcon("src/chat/imgs/search.png");
	JLabel jl_search = new JLabel("이름검색");
	public JTextField tf_search = new JTextField();

	JButton jl_profile = new JButton();
	ImageIcon img5 = new ImageIcon(MemLogic.mvo.getPath());
	Image p = img5.getImage();
	Image img_profile = p.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon_profile = new ImageIcon(img_profile);
	JLabel jl_myName = new JLabel(MemLogic.mvo.getM_name());

	String cols_friends[] = {"내 친구들 ㅎㅎ"};
	String data_friends[][] = new String[0][1];
	public DefaultTableModel dtm_myfriends = new DefaultTableModel(data_friends, cols_friends);
	public JTable jt_myfriends = new JTable(dtm_myfriends);
	public JScrollPane myfriendsScroll = new JScrollPane(jt_myfriends, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JTableHeader header = jt_myfriends.getTableHeader();
	
	public JPanel jp_myfriends = null;
	public JScrollPane jsp_myfriends = null;
	public static HashMap<String, JPanel> friends_map = new HashMap<String, JPanel>();
	public Vector<String> friends_list = null;

	String cols_search[] = {"검색된 아이디","이름"};
	String data_search[][] = new String[0][1];
	public DefaultTableModel dtm_search = new DefaultTableModel(data_search, cols_search);
	public JTable jt_search = new JTable(dtm_search);
	public JScrollPane searchScroll = new JScrollPane(jt_search, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JTableHeader header_search = jt_search.getTableHeader();
	public JButton jbt_add_friend = new JButton("친구추가!");

	Font f = new Font("맑은 고딕", Font.PLAIN, 13);

	public FriendsPage(UserMainFrame umf, String m_ID) {

		this.umf = umf;
		this.user_id = m_ID;
		
		jp_myfriends = new JPanel(new GridLayout());
		jsp_myfriends = new JScrollPane(jp_myfriends,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		jl_profile.addActionListener(this);

		friends_list = new Vector<String>();
		mflp = new MyfriendsListPanel();
		
		try {
			System.out.println(Protocol.msg("myfriends", Protocol.myfriend, m_ID, "친구 찾아줘"));
			this.umf.mnr.send(Protocol.msg("myfriends", Protocol.myfriend, m_ID, "친구 찾아줘"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		initDisplay();
		
		this.repaint();
		this.revalidate();

		CreatingRoom er = new CreatingRoom(this);
		jbtn_start_chat.addActionListener(er);
		SearchFriend sf = new SearchFriend (this);
		jbt_add_friend.addActionListener(sf);
		tf_search.addActionListener(sf);
		

	}

	public void initDisplay() {
		this.setLayout(null);
		this.setBackground(Color.white);
		jbtn_start_chat.setBounds(190, 440, 120, 30);

		jl_search.setBounds(40, 10, 160, 30);
		jl_search.setFont(f);
		jl_search.setForeground(Color.LIGHT_GRAY);

		tf_search.setBounds(40, 10, 300, 30);

		jl_profile.setBorderPainted(false);
		jl_profile.setFocusPainted(false);
		jl_profile.setContentAreaFilled(false);
		jl_profile.setBounds(20, 55, icon_profile.getIconWidth(), icon_profile.getIconHeight());
		jl_profile.setIcon(icon_profile);

		jl_myName.setBounds(85, 65, 60, 30);
		jl_myName.setFont(f);

		//myfriendsScroll.setBounds(10, 120, 365, 320);
		
		//jp_myfriends.setLayout(new GridLayout(6,1));
		jsp_myfriends.setBounds(10, 120, 365, 320);
		this.setSize(400,600);
		this.setVisible(true);
		this.add(jsp_myfriends);
		jsp_myfriends.setViewportView(jp_myfriends);
		
		
		searchScroll.setBounds(10, 120, 365, 320);
		jbt_add_friend.setBounds(60, 440, 120, 30);
		header.setReorderingAllowed(false);
		this.add(jbtn_start_chat);
		this.add(jl_search);
		this.add(tf_search);
		this.add(jl_profile);
		this.add(jbt_add_friend);
		this.add(jl_myName);
	}

	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == jl_profile || obj == jl_myName) {

			Profile p = new Profile();
			p.setVisible(true);

		} 
		else if (obj == jbtn_start_chat) {

		}

	}
}
