package chat.client.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chat.client.method.CreatePanel;

@SuppressWarnings("serial")
public class MyChatroomList2 extends JPanel {

	public static HashMap<String, JPanel> chatroom_map = new HashMap<String, JPanel>();
	
	//public HashMap<String, TalkClient> tc_map = new HashMap<String, TalkClient>();
	public HashMap<String, TalkClient2> tc_map = new HashMap<String, TalkClient2>();
	
	public UserMainFrame umf     = null;
	
	public String user_id        = null;
	public String user_name      = null;
	String user_email            = null;
	String user_phone            = null;
	
	public Vector<String> chatroom_list = null;
	
	public JPanel jp = null;
	public JScrollPane sp = null;
	
	public CreatePanel tp = null;
	
	public MyChatroomList2(UserMainFrame umf, String user_id, String user_name) {

		this.umf       = umf;
		
		this.user_id   = user_id;
		this.user_name = user_name;
		
		jp = new JPanel(new GridLayout(1, 1));
		sp = new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		chatroom_list = new Vector<String>();
		
		tp = new CreatePanel();
		
		initDisplay();
		
		this.repaint();
		this.revalidate();
		
	}
	
	public void initDisplay() {
		
		this.setLayout(new GridLayout(1, 1));
	    this.setSize(400,600);
	    this.setVisible(true);
	    
	    this.add(sp);
	    sp.setViewportView(jp);

	    this.setOpaque(true);
	    this.setBackground(Color.WHITE);
	    
		this.repaint();
		this.revalidate();
		
	}
	
}
