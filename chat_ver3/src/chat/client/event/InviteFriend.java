package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import chat.client.view.ChatInvite;
import chat.util.DBConnectionMgr;
import chat.util.Protocol;

public class InviteFriend implements ActionListener, MouseListener {
	
	ChatInvite ci = null;
	
	CallableStatement cstmt = null;
	Connection con = null;
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public InviteFriend(ChatInvite ci) {
		
		this.ci = ci;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		ci.choice = ci.jl_invite.getSelectedValue();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			con = dbMgr.getConnection("chat_ver2");
			
			cstmt = con.prepareCall("{Call proc_chatinv2(?,?)}");
			cstmt.setString(1, ci.choice);
			cstmt.setString(2, ci.chatroom_code);
			cstmt.executeUpdate();

			ci.setVisible(false);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		ci.tc.initDisplay();

		ci.tc.umf.mnr.send(Protocol.msg(ci.chatroom_code, Protocol.create_room, ci.choice, "님이 초대되었습니다."));
		
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	
}
