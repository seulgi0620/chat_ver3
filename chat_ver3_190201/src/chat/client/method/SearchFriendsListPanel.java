package chat.client.method;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chat.client.view.FriendsPage;
import chat.util.SearchTableVO;

public class SearchFriendsListPanel{

	int rows = 10;

	public FriendsPage fp = null;
	public JLabel jl_friendID = null;
	Vector<SearchTableVO> stbvo = null;
	public JCheckBox jcb_friendID = null;

	public SearchFriendsListPanel() {
		stbvo = new Vector<SearchTableVO>();
	}

	public SearchFriendsListPanel(Vector<SearchTableVO> stbvo) {
		this.stbvo = stbvo;
	}

	public void initDisplay(FriendsPage fp) {
		this.fp = fp;

		Vector<SearchTableVO> vv = null;

		if (stbvo.size() != 0) {
			vv = stbvo;
		} else {
			vv = new Vector<SearchTableVO>();
		}

		// fp.friends_list.removeAllElements();
		FriendsPage.searchFriends_map.clear();
		fp.jp_myfriends.removeAll();

		for (int i = 0; i < stbvo.size(); i++) {

			JPanel jp_friendsList = new JPanel();
			jp_friendsList.setLayout(new FlowLayout());
			jp_friendsList.setBackground(Color.pink);
			Border bd = new LineBorder(Color.BLACK);
			jp_friendsList.setBorder(bd);
			jl_friendID = new JLabel();
			jp_friendsList.add(jl_friendID);
			
			jcb_friendID = new JCheckBox();
			jp_friendsList.add(jcb_friendID);

			jl_friendID.setText(String.valueOf(vv.get(i).getUser_id()));
			jl_friendID.setOpaque(true);
			
			FriendsPage.searchFriends_map.put(vv.get(i).getUser_id(), jp_friendsList);
			fp.friends_list.add(vv.get(i).getUser_id());
			fp.jp_myfriends.add(FriendsPage.searchFriends_map.get(vv.get(i).getUser_id()));
			if (stbvo.size() > 6) {
				rows = stbvo.size();
			}
		}
		fp.jp_myfriends.setLayout(new GridLayout(rows, 1));
	}
	
	

	

}
