package chat.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chat.client.method.MemLogic;
import chat.client.method.loginMethod;
import chat.util.DBConnectionMgr;

@SuppressWarnings("serial")
public class LoginView2 extends JFrame {
	
	MemLogic ml = new MemLogic();
	JoinView jv = new JoinView();

	JButton jb_fid = new JButton("아이디 찾기");
	JButton jb_fpw = new JButton("비밀번호 찾기");

	JFrame login = new JFrame();

	JPanel jp_login = new JPanel();
	JPanel jp_join = new JPanel();
	JTextField jtf_input = new JTextField();
	JButton jb_login = new JButton("로그인");
	JButton jb_join = new JButton("회원가입");

	JLabel jl_id = new JLabel("아이디를 입력하세요");
	JLabel jl_pw = new JLabel("비밀번호를 입력하세요");
	JTextField tf_id = new JTextField();
	JPasswordField tf_pw = new JPasswordField();

	JLabel imgBox;
	ImageIcon img = new ImageIcon("src/chat/imgs/logo2.png");
	JLabel jl_logo = new JLabel("PEACH");
	JLabel jl_logo2 = new JLabel("TALK");

	public LoginView2() {
		Ldisplay();
	}
	
	public void Ldisplay() {
		
		jb_join.addActionListener(actionListener);
		jb_login.addActionListener(actionListener);
		tf_id.addActionListener(actionListener);
		tf_pw.addActionListener(actionListener);
		
		jp_login.setLayout(null);
		
		String path = "src/chat/imgs/";
		ImageIcon bookIcon = new ImageIcon(path + "title_icon.png");
		login.setIconImage(bookIcon.getImage());
		login.setTitle("Ver0.1");
		
		jl_logo.setBounds(140, 80, 150, 45);
		jl_logo.setFont(new Font("HY엽서L", Font.BOLD, 30));
		jl_logo.setForeground(new Color(248, 136, 137));
		jp_login.add(jl_logo);
		jl_logo2.setBounds(155, 110, 100, 45);
		jl_logo2.setFont(new Font("HY엽서L", Font.BOLD, 30));
		jl_logo2.setForeground(new Color(248, 136, 137));
		jp_login.add(jl_logo2);
		imgBox = new JLabel(img);
		imgBox.setBounds(120, 50, img.getIconWidth(), img.getIconHeight());
		jp_login.add(imgBox);
		
		jp_login.setBackground(new Color(248, 136, 137));

		jl_id.setBounds(85, 230, 250, 45);
		jl_pw.setBounds(85, 280, 250, 45);
		jp_login.add(jl_id);
		jp_login.add(jl_pw);
		jl_id.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		jl_id.setForeground(Color.LIGHT_GRAY);
		jl_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		jl_pw.setForeground(Color.LIGHT_GRAY);
		tf_id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf_id.setBounds(75, 230, 250, 45);
		tf_pw.setBounds(75, 280, 250, 45);

		jp_login.add(tf_id);
		jp_login.add(tf_pw);

		jb_login.setBorderPainted(false);
		jb_login.setBounds(75, 330, 250, 45);
		jp_login.add(jb_login);
		jb_login.setForeground(Color.GRAY);
		jb_login.setBackground(Color.WHITE);

		jb_join.setBorderPainted(false);
		jb_join.setFocusPainted(false);
		jb_join.setContentAreaFilled(false);
		jb_join.setBounds(110, 380, 170, 60);
		jp_login.add(jb_join);
		jb_join.setForeground(Color.WHITE);

		jb_fid.setBackground(new Color(49, 133, 156));
		jb_fid.setBorderPainted(false);
		jb_fid.setFocusPainted(false);
		jb_fid.setContentAreaFilled(false);
		jb_fid.setBounds(75, 500, 120, 35);
		jb_fid.setForeground(Color.WHITE);
		jp_login.add(jb_fid);
		
		jb_fpw.setBackground(new Color(49, 133, 156));
		jb_fpw.setBorderPainted(false);
		jb_fpw.setFocusPainted(false);
		jb_fpw.setContentAreaFilled(false);
		jb_fpw.setBounds(175, 500, 120, 35);
		jb_fpw.setForeground(Color.WHITE);
		jp_login.add(jb_fpw);
		
		add("Center", jp_login);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setVisible(true);
		
	}

	private ActionListener actionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			DBConnectionMgr dbMgr = new DBConnectionMgr();
			Object obj = e.getSource();
			
			if(obj == jb_login || obj == tf_pw || obj == tf_id){ 
				Connection con = null;
				try{
					con = dbMgr.getConnection("chat_ver2");
					if(con !=null){
						if(ml.Login(tf_id.getText(), String.valueOf(tf_pw.getPassword()))==1) { 
							dispose();
							loginMethod lm = new loginMethod();
							String str = lm.accessMain(tf_id.getText(), String.valueOf(tf_pw.getPassword()));
							UserMainFrame mf = new UserMainFrame(tf_id.getText(), str);
							mf.setVisible(true);
						} else if(ml.Login(tf_id.getText(), String.valueOf(tf_pw.getPassword()))==2) {
							JOptionPane.showMessageDialog(login, "비밀번호가 잘못되었습니다."," ",JOptionPane.INFORMATION_MESSAGE);
						} else if(ml.Login(tf_id.getText(),String.valueOf(tf_pw.getPassword()))==0){
							JOptionPane.showMessageDialog(login, "존재 하지 않는 아이디입니다"," ",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch(Exception ea) {
					ea.printStackTrace();
				}
			} else if(obj ==jb_join) {
				jv.Jdisplay();
			} else if(obj ==jb_fid) {
				
			}
		}
	};
	
}
