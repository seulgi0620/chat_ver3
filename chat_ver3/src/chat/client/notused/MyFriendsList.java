package chat.client.notused;

import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import chat.client.method.SelectFriendsList;
import chat.client.view.UserMainFrame;

@SuppressWarnings("serial")
public class MyFriendsList extends JPanel {

	public UserMainFrame umf     = null;
	
	public String user_id        = null;
	public String user_name      = null;
	String user_email            = null;
	String user_phone            = null;
	
	Vector<String> friends_list  = null;
	public JList<String> jl_list = null;
	JScrollPane js_list          = new JScrollPane();
	
	public JButton jbtn_sel      = new JButton("방만들기");
	
	public MyFriendsList(UserMainFrame umf, String user_id) {
		
		this.umf       = umf;
		
		this.user_id   = user_id;
		
		SelectFriendsList scl = new SelectFriendsList();
		friends_list          = scl.selectFriList(this.user_id);
		jl_list               = new JList<String>();
		jl_list.setListData(friends_list);
		
		initDisplay();
		
		//CreatingRoom er = new CreatingRoom(this);
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
