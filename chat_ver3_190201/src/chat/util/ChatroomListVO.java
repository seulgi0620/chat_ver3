package chat.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChatroomListVO implements Serializable{

	String chatroom_code = "";
	String max_msg = "";
	String max_log_time = "";
	int msg_not_read = 0;
	int last_code = 0;
	
	public String getChatroom_code() {
		return chatroom_code;
	}
	
	public void setChatroom_code(String chatroom_code) {
		this.chatroom_code = chatroom_code;
	}
	
	public String getMax_msg() {
		return max_msg;
	}
	
	public void setMax_msg(String max_msg) {
		this.max_msg = max_msg;
	}
	
	public String getMax_log_time() {
		return max_log_time;
	}
	
	public void setMax_log_time(String max_log_time) {
		this.max_log_time = max_log_time;
	}
	
	public int getMsg_not_read() {
		return msg_not_read;
	}
	
	public void setMsg_not_read(int msg_not_read) {
		this.msg_not_read = msg_not_read;
	}
	
	public int getlast_code() {
		return last_code;
	}
	
	public void setlast_code(int last_code) {
		this.last_code = last_code;
	}
	
}
