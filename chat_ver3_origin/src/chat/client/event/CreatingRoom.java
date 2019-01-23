package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import chat.client.view.FriendsPage;
import chat.util.Protocol;

public class CreatingRoom implements ActionListener {

	FriendsPage fp = null;
	String friend_id = null;
	Vector<String> friend_ids = null;
	
	public CreatingRoom(FriendsPage fp) {
		this.fp = fp;
	}
	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if(fp != null) {
			if(src == fp.jbtn_start_chat) {
				int[] row = fp.jt_myfriends.getSelectedRows();
				
				friend_ids = new Vector<String>();
				
				for(int i=0;i<fp.jt_myfriends.getSelectedRows().length;i++) {
					friend_ids.add(String.valueOf(fp.jt_myfriends.getValueAt(row[i], 0)));
				}
				
				fp.umf.mnr.send(Protocol.msg(friend_ids));

			}
		}
		
	}
	
}
