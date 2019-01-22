package chat.client.method;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chat.client.view.LoginView2;
import chat.client.view.MyChatroomList2;
import chat.client.view.TalkClient;
import chat.client.view.UserMainFrame;
import chat.util.Protocol;

public class MessagesNotRead extends Thread{

	MessagesNotRead_Call mnr_c = new MessagesNotRead_Call();
	LastRead lr = new LastRead();
	LoginView2 lv2 = null;
	
	public boolean isStop = false;
	
	public UserMainFrame umf = null;
	
	ObjectInputStream ois  = null;
	ObjectOutputStream oos = null;
	
	int message_not_read = 0;
	
	String user_id = "";
	TalkClient tc = null;
	
	public MessagesNotRead(UserMainFrame umf) {
		
		this.umf = umf;
		this.oos = umf.oos;
		this.ois = umf.ois;
		
		this.user_id = umf.user_id;
		
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
				
				try {
					
					msg = (String) ois.readObject();
					
					StringTokenizer st = null;
					if(msg != null) {
						st = new StringTokenizer(msg, Protocol.seperator);
						
						chatroom_code = st.nextToken();
						protocol = st.nextToken();

						if(Protocol.exit_room.equals(protocol)) {
							umf.chatroompage.tp = new CreatePanel();
							umf.chatroompage.tp.initDisplay(umf.chatroompage);
							umf.chatroompage.sp.setViewportView(umf.chatroompage.jp);
							umf.repaint();
							umf.revalidate();
						} else if(Protocol.create_room.equals(protocol)) {
							umf.chatroompage.tp = new CreatePanel();
							umf.chatroompage.tp.initDisplay(umf.chatroompage);
							umf.chatroompage.sp.setViewportView(umf.chatroompage.jp);
							umf.repaint();
							umf.revalidate();
						}
						
						//////////////////
						else if (Protocol.overlap.equals(protocol)) {
							try {
								isStop = true;
								ois.close();
								oos.close();
								//umf.mySocket.close();
								//tct.stop();
								umf.dispose();
								lv2 = new LoginView2();
								JOptionPane.showMessageDialog(umf,"이미 로그인 되어있는 아이디","에러",JOptionPane.ERROR_MESSAGE);	
							} catch (IOException e) {
								e.printStackTrace();
							}
						break;	
						}
						
						//////////////////
						
						user_id = st.nextToken();
						message = st.nextToken();
						try {
							log_time = st.nextToken();
						} catch(NoSuchElementException e) {
							//e.printStackTrace();
							log_time = "0";
						}
						
						SelectChatList scl = new SelectChatList();
						Vector<String> chatroom_list = scl.selectChatList(umf.user_id);
						
						for(int i=0;i<chatroom_list.size();i++) {
							if(chatroom_list.get(i).equals(chatroom_code)) {
								if(Protocol.chatroom_on.isEmpty()) {
									JPanel jp_outer = MyChatroomList2.chatroom_map.get(chatroom_code);
									JPanel jp_inner1_cnt = (JPanel) jp_outer.getComponent(2);
									JPanel jp_inner2_cnt = (JPanel) jp_inner1_cnt.getComponent(1);
									JLabel jl_inner3_cnt = (JLabel) jp_inner2_cnt.getComponent(0);
									int cnt = Integer.parseInt(jl_inner3_cnt.getText());
									jl_inner3_cnt.setText(String.valueOf(cnt+1));
									
									JPanel jp_inner1_msg = (JPanel) jp_outer.getComponent(1);
									JPanel jp_inner2_msg = (JPanel) jp_inner1_msg.getComponent(0);
									JLabel jl_inner3_msg = (JLabel) jp_inner2_msg.getComponent(0);
									jl_inner3_msg.setText(user_id+" : "+message);
									
									JPanel jp_inner1_time = (JPanel) jp_outer.getComponent(2);
									JPanel jp_inner2_time = (JPanel) jp_inner1_time.getComponent(0);
									JLabel jl_inner3_time = (JLabel) jp_inner2_time.getComponent(0);
									
									Date dateFormat = null;
									String stringFormat = null;
									try {
										dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(log_time);
										stringFormat = new SimpleDateFormat("HH:mm").format(dateFormat);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									jl_inner3_time.setText(stringFormat);
									
									jp_outer.getParent().add(jp_outer, 0);
									umf.repaint();
									umf.revalidate();
									
								} else if(!Protocol.chatroom_on.isEmpty()) {
									if(Protocol.chatroom_on.get(0).equals(chatroom_code)) {
										JPanel jp_outer = MyChatroomList2.chatroom_map.get(chatroom_code);
										JPanel jp_inner1 = (JPanel) jp_outer.getComponent(2);
										JPanel jp_inner2 = (JPanel) jp_inner1.getComponent(1);
										JLabel jl_inner3 = (JLabel) jp_inner2.getComponent(0);
										jl_inner3.setText("0");
										
										JPanel jp_inner1_msg = (JPanel) jp_outer.getComponent(1);
										JPanel jp_inner2_msg = (JPanel) jp_inner1_msg.getComponent(0);
										JLabel jl_inner3_msg = (JLabel) jp_inner2_msg.getComponent(0);
										jl_inner3_msg.setText(user_id+" : "+message);
										
										JPanel jp_inner1_time = (JPanel) jp_outer.getComponent(2);
										JPanel jp_inner2_time = (JPanel) jp_inner1_time.getComponent(0);
										JLabel jl_inner3_time = (JLabel) jp_inner2_time.getComponent(0);
										
										Date dateFormat = null;
										String stringFormat = null;
										try {
											dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(log_time);
											stringFormat = new SimpleDateFormat("HH:mm").format(dateFormat);
										} catch (ParseException e) {
											e.printStackTrace();
										}
										jl_inner3_time.setText(stringFormat);
																				
									}
								}
								
								if(umf.chatroompage.tc_map.get(chatroom_code) != null) {
									
									TalkClient tc = umf.chatroompage.tc_map.get(chatroom_code);
									
									if(protocol.equals(Protocol.enter_room) || protocol.equals(Protocol.create_room)) {

										SelectParticipentList spcl = new SelectParticipentList();
										tc.jl_list.setListData(spcl.selectChatList(tc.chatroom_code));

										tc.jta_display.append(user_id + message + "\n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									} else if(protocol.equals(Protocol.plain_text)) {

										tc.jta_display.append(user_id +" : "+ message + " (" + log_time + ") \n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									} else if(protocol.equals(Protocol.exit_room)) {

										SelectParticipentList spcl = new SelectParticipentList();
										tc.jl_list.removeAll();
										tc.jl_list.setListData(spcl.selectChatList(tc.chatroom_code));
										tc.jl_list.revalidate();

										tc.jta_display.append(user_id + message+"\n");
										tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
										
									}
								}
								
							}
							
						}

					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}
