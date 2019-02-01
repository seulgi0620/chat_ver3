package chat.server.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import chat.server.method.TalkServerThread;

@SuppressWarnings("serial")
public class TalkServer extends JFrame implements Runnable {
	
	Connection con;
	CallableStatement cstmt1 = null;
	int resInt = 0;
	ResultSet srs = null;
	
	ServerSocket server = null;
	Socket client 		= null;
	public JTextArea jta_log 	= new JTextArea();
	JScrollPane js = new JScrollPane();
	Vector<TalkServerThread> chatList = null;
	
	public TalkServer() {

	}
	
	public void initDisplay() {
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					if(server!=null)
						server.close();
					System.exit(0);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    js.setViewportView(jta_log);
	    jta_log.setEditable(false);
		this.add("Center", js);
		this.setTitle("서버 로그 출력....");
	    this.setSize(500,500);
	    this.setVisible(true);
	}

	public void run() {

		chatList = new Vector<TalkServerThread>();
		boolean isStop = false;
		
		try {
			server = new ServerSocket(30001);
			
			while(!isStop) {
				jta_log.append("Waiting\n");
				client = server.accept();
				jta_log.append(client.toString() + "\n");
				TalkServerThread tst = new TalkServerThread(this, client, chatList);
				chatList.add(tst);
				tst.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
