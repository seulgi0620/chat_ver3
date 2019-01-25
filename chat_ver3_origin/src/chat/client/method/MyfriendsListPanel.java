package chat.client.method;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chat.client.view.FriendsPage;
import chat.util.MyfriendsListVO;

public class MyfriendsListPanel extends JPanel{
	
	FriendsPage fp = null;
	
	Vector<MyfriendsListVO> mflvo = null;

	public MyfriendsListPanel() {
		mflvo = new Vector<MyfriendsListVO>();
		initDisplay();
	}
	
	public MyfriendsListPanel(Vector<MyfriendsListVO> mflvo) {
		this.mflvo = mflvo;
	}
	public void initDisplay() {
		this.fp = fp;
		
		Vector<MyfriendsListVO> vv = null;
		
		if(mflvo.size() != 0) {
			vv = mflvo;
		} else {
			vv = new Vector<MyfriendsListVO>();
		}
		
		//fp.friends_list.removeAllElements();
		
		///
		
		JLabel jl_friendName = new JLabel("친구이름1");
		this.add(jl_friendName);
		this.setSize(365,30);
		this.setBackground(Color.cyan);
		
	}
	public static void main(String[] args) {
		new MyfriendsListPanel();
	}
}
