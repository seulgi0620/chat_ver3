package chat.server.method;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import chat.client.method.InsertLog;
import chat.server.view.TalkServer;
import chat.util.Protocol;

public class TalkServerThread extends Thread {
	
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	String user_id = null;
	
	String chatroom_code = null;
	
	Socket client = null;
	Vector<TalkServerThread> chatList = null;
	
	TalkServer ts = null;
	
	public TalkServerThread(TalkServer ts, Socket client, Vector<TalkServerThread> chatList) {
		
		this.ts = ts;
		
		this.client = client;
		this.chatList = chatList;

		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

			String enter_msg = (String)ois.readObject();
			
			StringTokenizer st = null;
			
			if(enter_msg != null) {
				st = new StringTokenizer(enter_msg, Protocol.seperator);
				chatroom_code = st.nextToken();
				st.nextToken();
				user_id = st.nextToken();
				String msg = st.nextToken();
				
				if(!chatroom_code.equals("No_chatroom_code"))
					broadCasting(enter_msg, chatroom_code);
			/////
				for(TalkServerThread tst : chatList) {
					if(user_id.equals(tst.user_id)){
						System.out.println(user_id);
						this.send(Protocol.msg(chatroom_code, Protocol.overlap, user_id, msg));
					}
				} ////////
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void broadCasting(String msg, String chatroom_code) {
		for(int i=0;i<chatList.size();i++) {
			if(chatroom_code.equals(chatList.get(i).chatroom_code)) {
				chatList.elementAt(i).send(msg);
			}
		}
	}
	
	public void broadCasting(String msg) {
		for(int i=0;i<chatList.size();i++) {
			chatList.elementAt(i).send(msg);
		}
	}
	
	private void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				try {
					String msg = null;
					String chatroom_code = null;
					String protocol = null;
					String user_id = null;
					String message = null;
					String log_time = null;
					
					try {
						
						msg = (String)ois.readObject();
						StringTokenizer st = null;
						if(msg != null) {
							st = new StringTokenizer(msg, Protocol.seperator);
							
							chatroom_code = st.nextToken();
							protocol = st.nextToken();
							user_id = st.nextToken();
							message = st.nextToken();
						
							
							
							try {
								log_time = st.nextToken();
							} catch(NoSuchElementException e) {
								log_time = "0";
							}

							InsertLog il = new InsertLog();
							il.ins_log(chatroom_code, protocol, user_id, message, log_time);
						}
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
										
					broadCasting(msg);

				} catch (IOException e) {
					try {
						ois.close();
						oos.close();
						client.close();
						chatList.removeElement(this);
						ts.jta_log.append(user_id+" is down\n");
						ts.jta_log.append("Remain : " + chatList.size()+"\n");
						break;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch(NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}
}
