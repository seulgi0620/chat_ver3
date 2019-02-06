package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JDialog;

import chat.client.method.MyfriendsListPanel;
import chat.client.view.FriendsPage;
import chat.util.MyfriendsListVO;

public class CreatingRoom implements ActionListener {

	FriendsPage fp = null;
	String friend_id = null;
	Vector<String> friend_ids = null;
	MyFriendsDoubleClick mfdc = null;
	MyfriendsListPanel mflp = null;
	public JDialog jdl_friendList = null;
	
	public CreatingRoom(FriendsPage fp) {
		this.fp = fp;
	
	}
	public CreatingRoom(MyFriendsDoubleClick mfdc) {
		this.mfdc = mfdc;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if(fp != null) {
			if(src == fp.jbtn_start_chat) {
				jdl_friendList = new JDialog();
				jdl_friendList.setSize(500,500);
				jdl_friendList.setVisible(true);
				mflp = new MyfriendsListPanel();
				mflp.initDisplay(this.fp, this);
				
				
				
//				int[] row = fp.jt_myfriends.getSelectedRows();
//				
//				friend_ids = new Vector<String>();
//				
//				for(int i=0;i<fp.jt_myfriends.getSelectedRows().length;i++) {
//					friend_ids.add(String.valueOf(fp.jt_myfriends.getValueAt(row[i], 0)));
//				}
//				
//				fp.umf.mnr.send(Protocol.msg(friend_ids));

			}
//			
//			if(src == mfdc.jbt_chatStart) {
//				System.out.println("³ª¿À³ª");
//				
//				friend_ids = new Vector<String>();
//				
//					friend_ids.add(mfdc.jl_friendName.getText());
//				
//				fp.umf.mnr.send(Protocol.msg(friend_ids));
//
//			}
			
		}
		
	}
	
}
