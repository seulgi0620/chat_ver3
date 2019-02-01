package chat.client.event;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import chat.client.method.MyfriendsListPanel;

public class MyFriendsDoubleClick implements MouseListener {
	MyfriendsListPanel mflp = null;
	JLabel jl_friendName = null;
	JButton jbt_chatStart = null;

	public MyFriendsDoubleClick(MyfriendsListPanel mflp, JLabel jl_friendName) {
		this.mflp = mflp;
		this.jl_friendName = jl_friendName;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JDialog jd_friend = new JDialog();
			jbt_chatStart = new JButton("채팅하기");
			jd_friend.setLayout(new FlowLayout());
			jd_friend.add(jl_friendName);
			jd_friend.add(jbt_chatStart);
			
			CreatingRoom er = new CreatingRoom(this);
			jbt_chatStart.addActionListener(er);
			
			jd_friend.setSize(300, 100);
			jd_friend.setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
