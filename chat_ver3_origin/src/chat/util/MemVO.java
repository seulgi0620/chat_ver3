package chat.util;

public class MemVO {
	
	private String path = null;
	private String m_ID = null;
	private String m_pw = null;
	private String m_name = null;
	private String m_email = null;
	private String m_phone = null; 
	
	public MemVO() {}
	
	public MemVO(String m_ID, String m_pw,String m_name,String m_email,String m_phone) {
		
		setM_ID(m_ID);
		setM_pw(m_pw);
		setM_name(m_name);
		setM_email(m_email);
		setM_phone(m_phone);
		
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getM_ID() {
		return m_ID;
	}
	
	public void setM_ID(String m_ID) {
		this.m_ID = m_ID;
	}
	
	public String getM_name() {
		return m_name;
	}
	
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public String getM_email() {
		return m_email;
	}
	
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	
	public String getM_phone() {
		return m_phone;
	}
	
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	
	public String getM_pw() {
		return m_pw;
	}
	
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	
}
