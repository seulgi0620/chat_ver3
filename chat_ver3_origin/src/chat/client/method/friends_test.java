package chat.client.method;

import javax.swing.JFrame;

public class friends_test extends JFrame{
	
	public void method() {
		this.setLayout(null);
		
		this.setSize(500,500);
		MyfriendsListPanel mm = new MyfriendsListPanel();
		this.add(mm);
//		mm.setBounds(0, 0, 500, 100);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		friends_test ff = new friends_test();
		ff.method();
	}
}
