package chat.util;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class MyfriendsListVO implements Serializable{
	String friendName = "";
	Vector<String> v_s = null;
	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public Vector<String> getV_s() {
		return v_s;
	}

	public void setV_s(Vector<String> v_s) {
		this.v_s = v_s;
	}
}
