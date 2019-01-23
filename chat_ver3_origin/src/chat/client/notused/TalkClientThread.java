package chat.client.notused;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import chat.client.view.TalkClient;
import chat.util.Protocol;

public class TalkClientThread extends Thread {
	
	public boolean isStop = false;
	
	public TalkClient tc      = null;
	
	ObjectInputStream ois  = null;
	ObjectOutputStream oos = null;
	
	public TalkClientThread(TalkClient tc) {
		
		this.tc = tc;
		
		//SelectLog sl = new SelectLog();
		//sl.sel_log(this.tc);
		
		//SelectParticipentList spcl = new SelectParticipentList();
		//tc.jl_list.setListData(spcl.selectChatList(tc.chatroom_code));
		
	}
	
	public void send(String msg) {
		try {
			oos.writeObject(msg);			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void run() {
		while(!isStop) {
			try {
				String msg = null;
				String chatroom_code = null;
				String protocol = null;
				String user_id = null;
				String message = null;
				String log_time = null;
				
				if(ois != null) {
					try {
						msg = (String) ois.readObject();
						
						StringTokenizer st = null;
						if(msg != null) {
							st = new StringTokenizer(msg, Protocol.seperator);
							
							chatroom_code = st.nextToken(); // CHATROOM_CODE IN
							protocol = st.nextToken(); // PROTOCOL IN
							
							if(tc != null) {
								
								if(!tc.chatroom_code.equals(chatroom_code)) {
									// ONLY SAME CHATROOM GRANTED
								} else {
									if(protocol.equals(Protocol.enter_room) || protocol.equals(Protocol.create_room)) {

										user_id = st.nextToken(); // ENTER - USER_ID IN

										//SelectParticipentList spcl = new SelectParticipentList();
										//tc.jl_list.setListData(spcl.selectChatList(tc.chatroom_code));
										
										message = st.nextToken(); // ENTER - MSG IN
										tc.jta_display.append(user_id + message + "\n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									} else if(protocol.equals(Protocol.plain_text)) {

										user_id = st.nextToken(); // PLAIN - USER_ID IN
										message  = st.nextToken(); // PLAIN - MSG IN
										try {
											log_time = st.nextToken();
										} catch(NoSuchElementException e) {
											log_time = "0";
										}
										tc.jta_display.append(user_id +" : "+ message + " (" + log_time + ") \n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									} else if(protocol.equals(Protocol.exit_room)) {

										user_id = st.nextToken(); // EXIT - USER_ID IN
										
										//SelectParticipentList spcl = new SelectParticipentList();
										tc.jl_list.removeAll();
										//tc.jl_list.setListData(spcl.selectChatList(tc.chatroom_code));
										tc.jl_list.revalidate();
										
										message = st.nextToken(); // EXIT - MSG IN
										tc.jta_display.append(user_id + message+"\n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									}
									
								}
								
							}
							
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SocketException e) {
						System.out.println("화면종료 및 방나가기 외에 이 메시지가 출력되는 경우 에러.");
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
