package chat.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import chat.client.method.MemLogic;

@SuppressWarnings("serial")
public class Profile extends JDialog{
	
	MemLogic ml = new MemLogic();
	JPanel profile = new JPanel();
	JLabel jl_profile = new JLabel();
	
	ImageIcon img5 = new ImageIcon(MemLogic.mvo.getPath());
	Image p = img5.getImage();
	Image img_profile = p.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon_profile = new ImageIcon(img_profile);
	
	JLabel jl_name = new JLabel(MemLogic.mvo.getM_name());
	JLabel jl_phone = new JLabel(MemLogic.mvo.getM_phone());
	JLabel jl_email = new JLabel(MemLogic.mvo.getM_email());
	JButton jb_chat = new JButton();
	JButton jb_best = new JButton();
	JLabel jl_chat = new JLabel("1¥Î1 √§∆√");
	JLabel jl_best = new JLabel("¡Ò∞‹√£±‚");

	public Profile() {
		
		profile.setLayout(null);
		
		jl_profile.setBounds(65, 50, icon_profile.getIconWidth(), icon_profile.getIconHeight());
		jl_profile.setIcon(icon_profile);
		profile.add(jl_profile);
		
		jl_name.setBounds(120, 240, 100, 30);
		jl_name.setForeground(Color.WHITE);
		jl_name.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.PLAIN,15));
		profile.add(jl_name);
		
		jl_phone.setBounds(95, 270, 100, 30);
		jl_phone.setForeground(Color.WHITE);
		jl_phone.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.PLAIN,15));
		profile.add(jl_phone);
		
		jl_email.setBounds(80, 300, 200, 30);
		jl_email.setForeground(Color.WHITE);
		jl_email.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.PLAIN,15));
		profile.add(jl_email);
		
		jl_chat.setBounds(50, 400, 200, 30);
		jl_chat.setForeground(Color.WHITE);
		jl_chat.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.PLAIN,15));
		profile.add(jl_chat);
		
		jl_best.setBounds(170, 400, 200, 30);
		jl_best.setForeground(Color.WHITE);
		jl_best.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.PLAIN,15));
		profile.add(jl_best);
		
		profile.setBackground(new Color(248, 136, 137));
		add("Center",profile);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300,500);
		setVisible(true);
		
	}

}
