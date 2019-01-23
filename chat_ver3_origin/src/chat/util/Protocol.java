package chat.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Protocol {
	
	public static Vector<String> chatroom_on = new Vector<String>();
	public static Vector<String> onair_chatroom = new Vector<String>();
	
	final public static String seperator = "|";
	final public static String enter_room = "100";
	final public static String plain_text = "200";
	final public static String exit_room  = "300";
	final public static String close_room = "400";
	final public static String create_room = "500";
	final public static String user_connect = "111";
	final public static String invite_user = "600";
	final public static String search_friend = "222";
	
	public static String msg(String chatroom_code, String protocol, String user_id, String msg) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String str = chatroom_code +seperator+ protocol +seperator+ user_id +seperator+ msg +seperator+ dateFormat.format(date);
		return str;
	}
	
	public static Vector<?> msg(Vector<?> vc) {
		
		return vc;
		
	}
	
}