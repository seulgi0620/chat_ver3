package chat.util;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class InviteListVO implements Serializable{

	String chatroom_code = "";
	
	Vector<String> vc = null;
	
	public Vector<String> getVc() {
		return vc;
	}
	public void setVc(Vector<String> vc) {
		this.vc = vc;
	}
	public String getChatroom_code() {
		return chatroom_code;
	}
	public void setChatroom_code(String chatroom_code) {
		this.chatroom_code = chatroom_code;
	}
	
}
