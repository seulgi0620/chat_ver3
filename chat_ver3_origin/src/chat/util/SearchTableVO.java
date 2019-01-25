package chat.util;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class SearchTableVO implements Serializable{
	
	String search_text = "";
	Vector<String> v_s = null;
	String user_id = null;
	String user_name = null;
	
	
	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
	public Vector<String> getV_s() {
		return v_s;
	}
	public void setV_s(Vector<String> v_s) {
		this.v_s = v_s;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
