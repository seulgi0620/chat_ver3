package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.client.view.FriendsPage;
import chat.server.method.SearchAddFriend;
import chat.util.Protocol;

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
				//new SearchTable(fp, fp.tf_search.getText());
				try {
					fp.umf.mnr.send(Protocol.msg("search_friend", Protocol.search_friend, fp.user_id, fp.tf_search.getText()));
					
					
					
					fp.remove(fp.myfriendsScroll);
					fp.add(fp.searchScroll);
					
					int row = fp.dtm_search.getRowCount();
					if (row > 0) {
						for (int i = row - 1; i >= 0; i--) {
							fp.dtm_search.removeRow(i);
						}
					}
					System.out.println(Protocol.msg("search_friend", Protocol.search_friend, fp.user_id, fp.tf_search.getText()));
					
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
