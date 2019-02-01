package chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import chat.client.method.MemLogic;
import chat.util.DBConnectionMgr;

@SuppressWarnings("serial")
public class JoinView extends JFrame {
	
	boolean isIdCheck =false;
	
	MemLogic ml = new MemLogic();
	
	JFrame join = new JFrame();
	JPanel jp = new JPanel();
	
	JLabel jb_title = new JLabel("회원가입");
	JButton jb_exit = new JButton("종료");
		
	ImageIcon img2 = new ImageIcon("src/chat/imgs/exit.png");
	
	JLabel jl_id = new JLabel("아이디 : ",JLabel.CENTER);
	JTextField tf_id = new JTextField();
	
	JButton jb_idcheck = new JButton("중복검사");
	JLabel idok = new JLabel("사용가능한 아이디 입니다.");
	JLabel idno = new JLabel("중복된 아이디 입니다.");
	JLabel jl_pw = new JLabel("비밀번호 : ",JLabel.CENTER);
	JPasswordField tf_pw = new JPasswordField();
	JLabel jl_pwcheck = new JLabel("비밀번호 확인 : ",JLabel.CENTER);
	JPasswordField tf_pwcheck = new JPasswordField();
	JLabel jl_name = new JLabel("이름 : ",JLabel.CENTER);
	JTextField tf_name = new JTextField();
	JLabel jl_hp = new JLabel("전화번호 : ",JLabel.CENTER);
	JTextField tf_hp = new JTextField();
	JLabel jl_email = new JLabel("E-mail : ",JLabel.CENTER);
	JTextField tf_email = new JTextField(); 
	
	JButton jb_join = new JButton("가입");
	JButton jb_cancle = new JButton("취소");
	
	JPanel jpj = new JPanel();
	JMenuBar jmb = new JMenuBar();
	
	public void Jdisplay() {
		
		jpj.setLayout(null);
		jmb.setLayout(null);
		
		jb_exit.addActionListener(actionListener);	
		jb_cancle.addActionListener(actionListener);	
		jb_idcheck.addActionListener(actionListener);		
		jb_join.addActionListener(actionListener);	

		jmb.setPreferredSize(new Dimension(400, 55));

		jmb.setBackground(new Color(248, 136, 137));
		jpj.setBackground(Color.WHITE);
		
		jb_title.setBounds(10, 10 , 170, 35);
		jb_title.setFont(new Font("맑은 고딕",Font.BOLD,20));
		jb_title.setForeground(Color.WHITE);
		jmb.add(jb_title);

		jb_exit.setBorderPainted(false);
		jb_exit.setFocusPainted(false); 
		jb_exit.setContentAreaFilled(false);
		jb_exit.setIcon(img2);
		jb_exit.setBounds(400, 10 , 90, 35);
		jb_exit.setForeground(Color.WHITE);
		jmb.add(jb_exit);

		jmb.add(jb_exit);

		jl_id.setBounds(50, 40 , 50, 35);
		jpj.add(jl_id);
		tf_id.setBounds(110, 40 , 250, 40);
		jpj.add(tf_id);
		jb_idcheck.setBounds(370, 40 , 90, 40);
		jpj.add(jb_idcheck);
		jb_idcheck.setForeground(Color.WHITE);
		jb_idcheck.setBackground(new Color(248, 136, 137));

		idok.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		idok.setForeground(Color.RED);
		idok.setBounds(110, 75 , 350, 40);
		jpj.add(idok);
		idok.setVisible(false);
		idno.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		idno.setForeground(Color.RED);
		idno.setBounds(110, 75 , 350, 40);
		jpj.add(idno);
		idno.setVisible(false);

		jl_name.setBounds(50, 110 , 70, 35);
		jpj.add(jl_name);
		tf_name.setBounds(110, 110 , 250, 40);
		jpj.add(tf_name);

		jl_pw.setBounds(35, 160 , 70, 35);
		jpj.add(jl_pw);
		tf_pw.setBounds(110, 160 , 250, 40);
		jpj.add(tf_pw);

		jl_pwcheck.setBounds(10, 210 , 90, 35);
		jpj.add(jl_pwcheck);
		tf_pwcheck.setBounds(110, 210, 250, 40);
		jpj.add(tf_pwcheck);

		jl_hp.setBounds(35, 260 , 70, 35);
		jpj.add(jl_hp);
		tf_hp.setBounds(110, 260 , 250, 40);
		jpj.add(tf_hp);

		jl_email.setBounds(45, 310 , 70, 35);
	    jpj.add(jl_email);
	    tf_email.setBounds(110, 310 , 250, 40);
	    jpj.add(tf_email);

		jb_join.setBounds(130, 370, 90, 40);
		jpj.add(jb_join);
		jb_join.setForeground(Color.WHITE);
		jb_join.setBackground(new Color(248, 136, 137));
		
		jb_cancle.setBounds(230, 370, 90, 40);
		jpj.add(jb_cancle);
		jb_cancle.setForeground(Color.WHITE);
		jb_cancle.setBackground(new Color(89,89,89));
				
		add("Center",jpj);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 550);
		setJMenuBar(jmb);
		setVisible(true);
		
	}
	
	private ActionListener actionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			DBConnectionMgr dbMgr = new DBConnectionMgr();
			Object obj = e.getSource();
			if(obj == jb_idcheck){
				Connection con = null;
				try {
					con = dbMgr.getConnection("chat_ver2");
					if(con !=null){
						if(ml.memIdCheck(tf_id.getText())) {
							idok.setVisible(false);
							idno.setVisible(true);
							isIdCheck=false;
						} else if(!(ml.memIdCheck(tf_id.getText()))) {
							idno.setVisible(false);
							idok.setVisible(true);
							isIdCheck=true;
						}
					}
				} catch(Exception ea) {
					ea.printStackTrace();
				}
			} else if(obj == jb_join) {
				Connection con = null;
				if(isIdCheck==false) {
					JOptionPane.showMessageDialog(join, "아이디 중복검사가 필요합니다.", " ",JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						con = dbMgr.getConnection("chat_ver2");
						if(con !=null){
							int result = ml.MemJoin(tf_id.getText(), String.valueOf(tf_pw.getPassword()),tf_name.getText(), tf_hp.getText(),tf_email.getText(),"src/imgs/default.png");
							if(result==1){
								JOptionPane.showMessageDialog(join, "가입이 완료되었습니다.", " ",JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else if(result ==0) {
								JOptionPane.showMessageDialog(join, "가입 실패", " ",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} catch(Exception ea) {
						ea.printStackTrace();
					}
				}
			} else if(obj== jb_cancle) {
				dispose();
			} else if(obj ==jb_exit) {
				dispose();
			}
		}
	};
	
}

