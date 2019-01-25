package chat.client.method;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chat.client.view.FriendsPage;
import chat.client.view.MyChatroomList2;
import chat.util.MyfriendsListVO;

public class MyfriendsListPanel {
	
	int rows = 0;
	
	FriendsPage fp = null;
	 
	public JLabel jl_friendName = new JLabel();
	Vector<MyfriendsListVO> mflvo = null;

	public MyfriendsListPanel() {
		mflvo = new Vector<MyfriendsListVO>();
	}
	
	public MyfriendsListPanel(Vector<MyfriendsListVO> mflvo) {
		this.mflvo = mflvo;
	}
	public void initDisplay(FriendsPage fp) {
		this.fp = fp;
		
		Vector<MyfriendsListVO> vv = null;
		
		if(mflvo.size() != 0) {
			vv = mflvo;
		} else {
			vv = new Vector<MyfriendsListVO>();
		}
		
		fp.friends_list.removeAllElements();
		FriendsPage.friends_map.clear();
		fp.jp_myfriends.removeAll();
		
		for(int i=0;i<mflvo.size();i++) {
			JPanel jp_friendsList = new JPanel();
			new JPanel();
			jp_friendsList.add(jl_friendName);
			jp_friendsList.setSize(365,30);
			jp_friendsList.setBackground(Color.cyan);
			
			jl_friendName.setText(String.valueOf(vv.get(i).getFriendName()));
			jl_friendName.setOpaque(true);
			
			FriendsPage.friends_map.put(vv.get(i).getFriendName(), jp_friendsList);
			System.out.println("¿©±â" + vv.get(0).getFriendName());
			fp.friends_list.add(vv.get(i).getFriendName());

			fp.jp_myfriends.add(FriendsPage.friends_map.get(vv.get(i).getFriendName()));
			rows++;
		}
		fp.jp_myfriends.setLayout(new GridLayout(rows, 1));
	}
	
}
