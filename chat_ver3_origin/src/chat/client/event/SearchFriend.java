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
					System.out.println("이벤트감지1");
					
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
