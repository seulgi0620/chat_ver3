package chat.server.method;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import chat.server.view.TalkServer;
import chat.util.ChatroomListVO;
import chat.util.MyfriendsListVO;
import chat.util.Protocol;

public class TalkServerThread extends Thread {
	
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	public String user_id = null;
	
	public Vector<String> vc_chatroom_code = null;
	public String chatroom_code = null;
	
	public Vector<String> v_friendsName = null;
	
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
				st.nextToken();
				chatroom_code = st.nextToken();
				user_id = st.nextToken();
				st.nextToken();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		SelectChatList scl = new SelectChatList();
		MessagesNotRead_Call mnr_c = new MessagesNotRead_Call();
		
		Vector<ChatroomListVO> vc = mnr_c.selectMsgCount(user_id, scl.selectChatList(user_id));
		
		vc_chatroom_code = new Vector<String>();
		for(int p=0;p<vc.size();p++) {
			vc_chatroom_code.add(vc.get(p).getChatroom_code());
		}
		
		send(Protocol.msg(vc));

	}

	public void broadCasting(String msg) {
		for(int i=0;i<chatList.size();i++) {
			chatList.elementAt(i).send(msg);
		}
	}
	
	private void send(Vector<?> vc) {
		try {
			oos.writeObject(vc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
			try {
				try {
					
					Object obj = null;
					
					String msg = null;
					String chatroom_code = null;
					String protocol = null;
					String user_id = null;
					String message = null;
					String log_time = null;
					
					Vector<String> vc_str = null;
					
					try {
						
						obj = ois.readObject();
						
						if(obj instanceof String) {
							
							msg = (String) obj;
							
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
								
								if(Protocol.enter_room.equals(protocol)) {
									if(message.equals("log")) {
										this.chatroom_code = chatroom_code;
										SelectLog sl = new SelectLog();
										send(Protocol.msg(sl.sel_log(this)));
									} else if(message.equals("participent")) {
										SelectParticipentList spcl = new SelectParticipentList();
										send(Protocol.msg(spcl.selectChatList(chatroom_code)));
									}
								} else if(Protocol.exit_room.equals(protocol)) {
									
									if(message.equals("participent")) {
										SelectParticipentList spcl = new SelectParticipentList();
										send(Protocol.msg(spcl.selectChatList(chatroom_code)));
									} else {
										ExitChatroom ecr = new ExitChatroom();
										ecr.exit_chatroom(user_id, chatroom_code);
										ecr.delete_chatroom(chatroom_code);
																				
										for(int j=0;j<chatList.size();j++) {
											for(int k=0;k<chatList.elementAt(j).vc_chatroom_code.size();k++) {
												if(chatroom_code.equals(chatList.elementAt(j).vc_chatroom_code.get(k))) {
													chatList.elementAt(j).send(Protocol.msg(chatroom_code, Protocol.exit_room, this.user_id, "now_exited"));
												}
											}
										}
										
										SelectChatList scl = new SelectChatList();
										MessagesNotRead_Call mnr_c = new MessagesNotRead_Call();
										Vector<ChatroomListVO> vc = mnr_c.selectMsgCount(user_id, scl.selectChatList(user_id));
										send(Protocol.msg(vc));

									}

								} else if(Protocol.invite_user.equals(protocol)) {
									
									if(message.equals("request_list")) {
										InviteList il = new InviteList();
										send(Protocol.msg(il.invite_friend_list(user_id, chatroom_code)));
									} else if(message.equals("now_invite")) {
										InviteList il = new InviteList();
										il.invite_friend(user_id, chatroom_code); // USER_ID == SELECTED USER
										
										for(int j=0;j<chatList.size();j++) {
											
											boolean flag = false;
											
											for(int k=0;k<chatList.elementAt(j).vc_chatroom_code.size();k++) {
												if(user_id.equals(chatList.elementAt(j).user_id)) {
													
													flag = true;
													SelectChatList scl = new SelectChatList();
													MessagesNotRead_Call mnr_c = new MessagesNotRead_Call();
													Vector<ChatroomListVO> vc = mnr_c.selectMsgCount(chatList.elementAt(j).user_id, scl.selectChatList(chatList.elementAt(j).user_id));
													chatList.elementAt(j).send(Protocol.msg(vc));
													
												} else if(chatroom_code.equals(chatList.elementAt(j).vc_chatroom_code.get(k))) {
													chatList.elementAt(j).send(Protocol.msg(chatroom_code, Protocol.invite_user, chatList.elementAt(j).user_id, "now_invite"));
												}
											}
											
											if(flag) {
												chatList.elementAt(j).vc_chatroom_code.add(chatroom_code);
											}
											
										}

									}
									
								}
								else if(Protocol.search_friend.equals(protocol)) {
									SearchTable stb = new SearchTable();
									this.send(stb.search(user_id, message));
								}
								
								else if(Protocol.myfriend.equals(protocol)) {
									FriendsTable ft = new FriendsTable();
									Vector<MyfriendsListVO> v_flvo = ft.search(user_id);
									v_friendsName = new Vector<String>();
									for(int p=0;p<v_flvo.size();p++) {
										v_friendsName.add(v_flvo.get(p).getFriendName());
									}
									send(Protocol.msg(v_flvo));
								break;}
								
								else {
									InsertLog il = new InsertLog();
									il.ins_log(chatroom_code, protocol, user_id, message, log_time);
									
									broadCasting(msg);
								}

							}
							
						} else if(obj instanceof Vector<?>) {
							
							Vector<?> raw = (Vector<?>) obj;
							
							if(raw.firstElement() instanceof String) {
								
								vc_str = (Vector<String>) raw;
								
								CreateChatroom ccr = new CreateChatroom();
								
								for(int i=0;i<vc_str.size();i++) {
									chatroom_code = ccr.create_chatroom(this.user_id, Protocol.enter_room, "´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.", vc_str.get(i));
									for(int j=0;j<chatList.size();j++) {
										if(chatList.elementAt(j).user_id.equals(this.user_id) || chatList.elementAt(j).user_id.equals(vc_str.elementAt(i))) {
											if(chatList.elementAt(j).user_id.equals(this.user_id)) {
												chatList.elementAt(j).send(Protocol.msg(chatroom_code, Protocol.create_room, this.user_id, "me"));
											}
											
											chatList.elementAt(j).vc_chatroom_code.add(chatroom_code);
											SelectChatList scl = new SelectChatList();
											MessagesNotRead_Call mnr_c = new MessagesNotRead_Call();
											Vector<ChatroomListVO> vc = mnr_c.selectMsgCount(chatList.elementAt(j).user_id, scl.selectChatList(chatList.elementAt(j).user_id));
											chatList.elementAt(j).send(Protocol.msg(vc));
											
										}
									}
								}
								
							}
							
						}
						
						
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

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
