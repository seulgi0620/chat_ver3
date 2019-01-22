package chat.client.notused;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chat.client.method.SelectChatList;
import chat.client.view.UserMainFrame;

@SuppressWarnings("serial")
public class MyChatroomList extends JPanel {

	public UserMainFrame umf     = null;
	
	public String user_id        = null;
	public String user_name      = null;
	String user_email            = null;
	String user_phone            = null;
	
	Vector<String> chatroom_list = null;
	public JList<String> jl_list = null;
	JScrollPane js_list          = new JScrollPane();
	
	public JButton jbtn_sel      = new JButton("º±≈√");
	
	public MyChatroomList(UserMainFrame umf, String user_id, String user_name) {
		
		this.umf       = umf;
		
		this.user_id   = user_id;
		this.user_name = user_name;
		
		SelectChatList scl = new SelectChatList();
		chatroom_list      = scl.selectChatList(this.user_id);
		jl_list            = new JList<String>();
		jl_list.setListData(chatroom_list);
		
		initDisplay();
		
		//EnteringRoom er = new EnteringRoom(this);
		//jbtn_sel.addActionListener(er);
		
	}
	
	public void initDisplay() {
		this.setLayout(new GridLayout(2, 1));
	    this.setSize(500,500);
	    this.setVisible(true);
	    
	    this.add(js_list);
	    js_list.setViewportView(jl_list);
	    
	    this.add(jbtn_sel);
	    
		this.repaint();
		this.revalidate();
		
	}
	
}
