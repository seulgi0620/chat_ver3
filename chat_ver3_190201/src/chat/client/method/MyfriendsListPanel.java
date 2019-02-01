package chat.client.method;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chat.client.event.MyFriendsDoubleClick;
import chat.client.view.FriendsPage;
import chat.util.MyfriendsListVO;

public class MyfriendsListPanel{

	int rows = 10;

	public FriendsPage fp = null;
	public MyFriendsDoubleClick mfdc = null;
	public JLabel jl_friendName = null;
	Vector<MyfriendsListVO> mflvo = null;

	public MyfriendsListPanel() {
		mflvo = new Vector<MyfriendsListVO>();
		// initDisplay(fp);
	}

	public MyfriendsListPanel(Vector<MyfriendsListVO> mflvo) {
		this.mflvo = mflvo;
	}

	public void initDisplay(FriendsPage fp) {
		this.fp = fp;

		Vector<MyfriendsListVO> vv = null;

		if (mflvo.size() != 0) {
			vv = mflvo;
		} else {
			vv = new Vector<MyfriendsListVO>();
		}

		// fp.friends_list.removeAllElements();
		FriendsPage.friends_map.clear();
		fp.jp_myfriends.removeAll();

		for (int i = 0; i < mflvo.size(); i++) {

			JPanel jp_friendsList = new JPanel();
			jp_friendsList.setLayout(new FlowLayout());
			jl_friendName = new JLabel();
			jp_friendsList.add(jl_friendName);

			jl_friendName.setText(String.valueOf(vv.get(i).getFriendName()));
			jl_friendName.setOpaque(true);
			
			mfdc = new MyFriendsDoubleClick(this,jl_friendName);
			jp_friendsList.addMouseListener(mfdc);
			
			FriendsPage.friends_map.put(vv.get(i).getFriendName(), jp_friendsList);
			fp.friends_list.add(vv.get(i).getFriendName());
			fp.jp_myfriends.add(FriendsPage.friends_map.get(vv.get(i).getFriendName()));
			if (mflvo.size() > 6) {
				rows = mflvo.size();
			}
		}
		fp.jp_myfriends.setLayout(new GridLayout(rows, 1));
	}

	

}
