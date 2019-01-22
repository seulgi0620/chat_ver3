package chat.client.view;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chat.client.event.InviteFriend;
import chat.util.DBConnectionMgr;

@SuppressWarnings("serial")
public class ChatInvite extends JDialog {

	public TalkClient tc = null;
	InviteFriend ifd = null;
	
	JPanel jp_invite = new JPanel();
	JPanel jp_invite_south = new JPanel();
	JButton jbtn_invite = new JButton("초대하기");
	
	CallableStatement cstmt = null;
	Connection con = null;
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public String user_id = null;
	public String chatroom_code = null;
	public String choice = null;

	Vector<String> v = null;
	public JList<String> jl_invite = null;
	JScrollPane jsp = null;
	
	public ChatInvite(TalkClient tc) {
		
		this.tc = tc;
		this.chatroom_code = tc.chatroom_code; 
		this.user_id = tc.user_id;
		ifd = new InviteFriend(this);
		
		jp_invite_south.add(jbtn_invite);
		
		jbtn_invite.addActionListener(ifd);
		
	}
	
	public void initDisplay() {
		
		this.setSize(600, 600);
		this.setVisible(true);
		this.add("Center",jp_invite);
		this.add("South", jp_invite_south);
		
	}
	
	public void invitelist() {
		
		Vector<String> v = new Vector<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select friend_id from chat_friend where user_id = ? ");
		sql.append("  minus  "); 
		sql.append("  select user_id from participent_list where chatroom_code = ? ");

		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, chatroom_code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				v.add(rs.getString("friend_id"));
			}
			
			if(jl_invite != null) {
				this.remove(jl_invite);
				jl_invite = null;
			}
			if(jsp != null) {
				this.remove(jsp);
				jsp = null;
			}
			
			jl_invite = new JList<String>(v);
			jsp = new JScrollPane(jl_invite, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(jsp);

			this.repaint();
			this.revalidate();
			
			jl_invite.addMouseListener(ifd);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
