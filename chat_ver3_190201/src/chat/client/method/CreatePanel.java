package chat.client.method;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import chat.client.event.EnteringRoom;
import chat.client.view.MyChatroomList2;
import chat.util.ChatroomListVO;

import javax.swing.JLabel;

public class CreatePanel {
	
	int rows = 0;
	
	MyChatroomList2 mcl2 = null;
	
	Vector<ChatroomListVO> clvo = null;
	
	public CreatePanel() {
		clvo = new Vector<ChatroomListVO>();
	}
	
	public CreatePanel(Vector<ChatroomListVO> clvo) {
		this.clvo = clvo;
	}
	
	public void initDisplay(MyChatroomList2 mcl2) {
		
		this.mcl2 = mcl2;
		
		Vector<ChatroomListVO> vc = null;
		
		if(clvo.size() != 0) {
			vc = clvo;
		} else {
			vc = new Vector<ChatroomListVO>();
		}

		mcl2.chatroom_list.removeAllElements();
		MyChatroomList2.chatroom_map.clear();
		mcl2.jp.removeAll();
		
		for(int i=0;i<clvo.size();i++) {
			
		    JPanel jp_chatlist = new JPanel();
		    jp_chatlist.setName("outer_panel");
		    
		    JPanel jp_west = new JPanel();
		    jp_west.setName("inner1_panel_west");
		    JPanel jp_center = new JPanel();
		    jp_center.setName("inner1_panel_center");
		    JPanel jp_east = new JPanel();
		    jp_east.setName("inner1_panel_east");
		    
		    JPanel jp_profile = new JPanel();
		    jp_profile.setName("inner2_panel_profile");
		    JPanel jp_chatmem = new JPanel();
		    jp_chatmem.setName("inner2_panel_chatmem");
		    JPanel jp_chatchat = new JPanel();
		    jp_chatchat.setName("inner2_panel_chatchat");
		    JPanel jp_time = new JPanel();
		    jp_time.setName("inner2_panel_time");
		    JPanel jp_noread_no = new JPanel();
		    jp_noread_no.setName("inner2_panel_noreadno");
		    
		    JLabel jlb_profile = new JLabel();
		    jlb_profile.setName("inner3_label_profile");
		    JLabel jlb_chatmem = new JLabel("참여자목록");
		    jlb_chatmem.setName("inner3_label_chatmem");
		    JLabel jlb_chatchat = new JLabel("채팅내용");
		    jlb_chatchat.setName("inner3_label_chatchat");
		    JLabel jlb_time = new JLabel("시간");
		    jlb_time.setName("inner3_label_time");
		    JLabel jlb_noread_no = new JLabel("숫자");
		    jlb_noread_no.setName("inner3_label_noreadno");
			
		    jp_chatlist.setLayout(new BorderLayout(0,0));
		    
			jp_chatlist.add(jp_west, BorderLayout.WEST);
			jp_west.add(jp_profile);
			jp_west.setLayout(new GridLayout(0, 1, 0, 0));
			jp_profile.add(jlb_profile);
			jlb_profile.setIcon(new ImageIcon("src/chat/imgs/chat.PNG"));
			
			jp_chatlist.add(jp_center, BorderLayout.CENTER);
			jp_center.setLayout(new GridLayout(0, 1, 0, 0));
			
			jp_center.add(jp_chatmem);
			jp_center.add(jp_chatchat);
			
			jp_chatmem.add(jlb_chatmem);
			jp_chatchat.add(jlb_chatchat);
		
			jp_chatlist.add(jp_east, BorderLayout.EAST);
			jp_east.setLayout(new GridLayout(0, 1, 0, 0));
			
			jp_east.add(jp_time);
			jp_east.add(jp_noread_no);
			
			jp_time.add(jlb_time);
			jp_noread_no.add(jlb_noread_no);

			jlb_noread_no.setText(String.valueOf(vc.get(i).getMsg_not_read()));
			jlb_chatchat.setText(vc.get(i).getChatroom_code());
			jlb_chatmem.setText(vc.get(i).getMax_msg());
			jlb_time.setText(vc.get(i).getMax_log_time());
			
		    jp_chatlist.setOpaque(true);
		    
		    jp_west.setOpaque(true);
		    jp_center.setOpaque(true);
		    jp_east.setOpaque(true);
		    
		    jp_profile.setOpaque(true);
		    jp_chatmem.setOpaque(true);
		    jp_chatchat.setOpaque(true);
		    jp_time.setOpaque(true);
		    jp_noread_no.setOpaque(true);
		    
		    jp_chatlist.setBackground(Color.WHITE);
		    
		    jp_west.setBackground(Color.WHITE);
		    jp_center.setBackground(Color.WHITE);
		    jp_east.setBackground(Color.WHITE);
		    
		    jp_profile.setBackground(Color.WHITE);
		    jp_chatmem.setBackground(Color.WHITE);
		    jp_chatchat.setBackground(Color.WHITE);
		    jp_time.setBackground(Color.WHITE);
		    jp_noread_no.setBackground(Color.WHITE);
			
			jlb_noread_no.setOpaque(true);
			jlb_noread_no.setBackground(Color.WHITE);
			jlb_chatchat.setOpaque(true);
			jlb_chatchat.setBackground(Color.WHITE);
			jlb_chatmem.setOpaque(true);
			jlb_chatmem.setBackground(Color.WHITE);
			jlb_time.setOpaque(true);
			jlb_time.setBackground(Color.WHITE);

			EnteringRoom er = new EnteringRoom(mcl2, jlb_chatchat);
			jp_chatlist.addMouseListener(er);
			
			MyChatroomList2.chatroom_map.put(vc.get(i).getChatroom_code(), jp_chatlist);
			
			mcl2.chatroom_list.add(vc.get(i).getChatroom_code());

			mcl2.jp.add(MyChatroomList2.chatroom_map.get(vc.get(i).getChatroom_code()));
			rows++;
		}
		
		mcl2.jp.setLayout(new GridLayout(rows, 1));
		
	}
	
}

