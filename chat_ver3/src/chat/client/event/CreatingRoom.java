package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import chat.client.method.CreateChatroom;
import chat.client.method.CreatePanel;
import chat.client.view.FriendsPage;
import chat.client.view.TalkClient;
import chat.util.Protocol;

public class CreatingRoom implements ActionListener {

	FriendsPage fp = null;
	String friend_id = null;
	List<String> friend_ids = null;
	
	public CreatingRoom(FriendsPage fp) {
		this.fp = fp;
	}
	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if(fp != null) {
			if(src == fp.jbtn_start_chat) {
				int[] row = fp.jt_myfriends.getSelectedRows();
				
				friend_ids = new ArrayList<String>();
				
				for(int i=0;i<fp.jt_myfriends.getSelectedRows().length;i++) {
					friend_ids.add(String.valueOf(fp.jt_myfriends.getValueAt(row[i], 0)));
				}
				
				String chatroom_code = null;
				
				CreateChatroom ccr = new CreateChatroom();
				
				for(int i=0;i<friend_ids.size();i++) {
					chatroom_code = ccr.create_chatroom(fp.user_id, "100", "님이 입장하셨습니다.", friend_ids.get(i));
				}
				
				TalkClient tc = new TalkClient(fp.umf, chatroom_code, fp.user_id, fp.user_name);
				fp.umf.chatroompage.tc_map.put(chatroom_code, tc);

				fp.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.create_room, fp.user_id, "접속쓰"));
				
				Protocol.onair_chatroom.add(chatroom_code);

				fp.umf.chatroompage.tp = new CreatePanel();
				fp.umf.chatroompage.tp.initDisplay(fp.umf.chatroompage);
				fp.umf.chatroompage.sp.setViewportView(fp.umf.chatroompage.jp);
				fp.umf.repaint();
				fp.umf.revalidate();
				
			}
		}
		
	}
	
}
