package chat.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChatLogVO implements Serializable{

	String chatroom_code = "";
	String user_id = "";
	String msg = "";
	String tt = "";
	
	public String getChatroom_code() {
		return chatroom_code;
	}
	public void setChatroom_code(String chatroom_code) {
		this.chatroom_code = chatroom_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}

}
