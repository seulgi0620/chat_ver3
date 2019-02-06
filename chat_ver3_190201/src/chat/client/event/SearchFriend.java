package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.client.method.SearchAddFriend;
import chat.client.view.FriendsPage;
import chat.util.Protocol;

public class SearchFriend implements ActionListener {

	FriendsPage fp = null;
	
	public SearchFriend(FriendsPage fp) {
		this.fp = fp;
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(fp!=null) {
			if (src == fp.tf_search) {
				try {
					fp.umf.mnr.send(Protocol.msg("SearchFriends", Protocol.search_friend, fp.user_id, fp.tf_search.getText()));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			else if (src == fp.jbt_add_friend) {
				new SearchAddFriend(fp);
			}
		}
	}

}
