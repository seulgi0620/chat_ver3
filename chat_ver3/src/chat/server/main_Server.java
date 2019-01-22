package chat.server;

import chat.server.view.TalkServer;

public class main_Server {
	public static void main(String[] args) {
		
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		new Thread(ts).start();
		
	}
}