package chat.client.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import chat.client.view.MyChatroomList2;
import chat.client.view.TalkClient2;
import chat.util.Protocol;

public class EnteringRoom implements MouseListener{

	MyChatroomList2 mcl2 = null;
	JLabel jl_chatchat = null;
	
	String chatroom_code = null;

	public EnteringRoom(MyChatroomList2 mcl2, JLabel jl_chatchat) {
		this.mcl2 = mcl2;
		this.jl_chatchat = jl_chatchat;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {		
			chatroom_code = jl_chatchat.getText();
			
			boolean isit = false;
			
			if(Protocol.onair_chatroom.size() != 0) {
				for(int i=0;i<Protocol.onair_chatroom.size();i++) {
					if(Protocol.onair_chatroom.get(i).equals(chatroom_code)) {
						isit = true;
					}
				}
				if(isit) {
					mcl2.umf.chatroompage.tc_map.get(chatroom_code).toFront();
				} else if(!isit) {
					//TalkClient tc = new TalkClient(mcl2.umf, chatroom_code, mcl2.user_id, mcl2.user_name);
					TalkClient2 tc = new TalkClient2(mcl2.umf, chatroom_code, mcl2.user_id, mcl2.user_name);
					mcl2.umf.chatroompage.tc_map.put(chatroom_code, tc);
					Protocol.onair_chatroom.add(chatroom_code);
					mcl2.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.enter_room, mcl2.user_id, "log"));
					mcl2.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.enter_room, mcl2.user_id, "participent"));
				}
			} else {
				//TalkClient tc = new TalkClient(mcl2.umf, chatroom_code, mcl2.user_id, mcl2.user_name);
				TalkClient2 tc = new TalkClient2(mcl2.umf, chatroom_code, mcl2.user_id, mcl2.user_name);
				mcl2.umf.chatroompage.tc_map.put(chatroom_code, tc);
				Protocol.onair_chatroom.add(chatroom_code);
				mcl2.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.enter_room, mcl2.user_id, "log"));
				mcl2.umf.mnr.send(Protocol.msg(chatroom_code, Protocol.enter_room, mcl2.user_id, "participent"));
			}
			
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
