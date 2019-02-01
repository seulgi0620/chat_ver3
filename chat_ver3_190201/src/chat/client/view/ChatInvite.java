package chat.client.view;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chat.client.event.InviteFriend;
import chat.util.DBConnectionMgr;
import chat.util.Protocol;

@SuppressWarnings("serial")
public class ChatInvite extends JDialog {

	public TalkClient2 tc = null;
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

	public JList<String> jl_invite = null;
	JScrollPane jsp = null;
	
	ChatInvite me = null;
	
	public ChatInvite(TalkClient2 tc) {
		
		this.tc = tc;
		this.chatroom_code = tc.chatroom_code; 
		this.user_id = tc.user_id;
		ifd = new InviteFriend(this);
		
		jp_invite_south.add(jbtn_invite);
		
		jbtn_invite.addActionListener(ifd);
		
		me = this;
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				me.dispose();
			}
			
		});
		
	}
		
	public void initDisplay() {
		
		this.setSize(600, 600);
		this.setVisible(true);
		this.add("Center",jp_invite);
		this.add("South", jp_invite_south);
		
	}
	
	public void invitelist() {
		
		jl_invite = new JList<String>();
		jsp = new JScrollPane(jl_invite, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jsp);

		tc.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.invite_user, user_id, "request_list"));
	
		jl_invite.addMouseListener(ifd);

	}
	
}
