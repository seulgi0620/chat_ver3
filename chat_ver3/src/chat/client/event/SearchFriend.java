package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.client.method.SearchAddFriend;
import chat.client.method.SearchTable;
import chat.client.view.FriendsPage;

public class SearchFriend implements ActionListener {

	FriendsPage fp = null;
	
	public SearchFriend(FriendsPage fp) {
		this.fp = fp;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(fp!=null) {
			if (src == fp.tf_search) {
				new SearchTable(fp, fp.tf_search.getText());
			}
			
			else if (src == fp.jbt_add_friend) {
				new SearchAddFriend(fp);
			}
		}
	}

}
