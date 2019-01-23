package chat.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import chat.client.method.MemLogic;

@SuppressWarnings("serial")
public class SettingPage extends JPanel {

	MemLogic ml = new MemLogic();

	private SettingPage panel;

	JButton jb_change;
	String folderPath;
	Font f = new Font("맑은 고딕", Font.PLAIN, 13);

	JLabel jl_profile;
	ImageIcon img5 = new ImageIcon(MemLogic.mvo.getPath());
	Image p = img5.getImage();
	Image img_profile = p.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon_profile = new ImageIcon(img_profile);

	JLabel jl_name = new JLabel("이름");
	JLabel jl_phone = new JLabel("전화번호");
	JLabel jl_email = new JLabel("Email");

	JTextField tf_name = new JTextField(MemLogic.mvo.getM_name());
	JTextField tf_phone = new JTextField(MemLogic.mvo.getM_phone());
	JTextField tf_email = new JTextField(MemLogic.mvo.getM_email());
	JLabel jl_tficon;

	ImageIcon img6 = new ImageIcon("src/chat/imgs/tf.png");
	Image p1 = img6.getImage();
	Image img_textfield = p1.getScaledInstance(15, 30, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon_textfield = new ImageIcon(img_textfield);

	ImageIcon img7 = new ImageIcon("src/chat/imgs/imgupd.jpg");
	Image p2 = img7.getImage();
	Image img_change = p2.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon_change = new ImageIcon(img_change);

	JButton jb_upd = new JButton("적용하기");

	public SettingPage() {
		
		panel = this;
		panel.setLayout(null);

		jb_change = new JButton();

		jb_change.addActionListener(actionListener);

		jl_profile = new JLabel();
		jl_profile.setIcon(icon_profile);
		jl_profile.setBounds(100, 50, icon_profile.getIconWidth(), icon_profile.getIconHeight());
		add(jl_profile);
		jb_change.setBorderPainted(false);
		jb_change.setFocusPainted(false);
		jb_change.setContentAreaFilled(false);
		jb_change.setBounds(250, 170, icon_change.getIconWidth(), icon_change.getIconHeight());
		jb_change.setIcon(icon_change);
		add(jb_change);

		jl_name.setBounds(65, 210, 100, 40);
		add(jl_name);
		tf_name.setBounds(100, 210, 150, 40);
		add(tf_name);
		jl_tficon = new JLabel(icon_textfield);
		jl_tficon.setBounds(260, 215, icon_textfield.getIconWidth(), icon_textfield.getIconHeight());
		add(jl_tficon);

		jl_phone.setBounds(40, 260, 100, 40);
		add(jl_phone);
		tf_phone.setBounds(100, 260, 150, 40);
		add(tf_phone);
		jl_tficon = new JLabel(icon_textfield);
		jl_tficon.setBounds(260, 265, icon_textfield.getIconWidth(), icon_textfield.getIconHeight());
		add(jl_tficon);

		jl_email.setBounds(60, 310, 100, 40);
		add(jl_email);
		tf_email.setBounds(100, 310, 150, 40);
		add(tf_email);
		jl_tficon = new JLabel(icon_textfield);
		jl_tficon.setBounds(260, 315, icon_textfield.getIconWidth(), icon_textfield.getIconHeight());
		add(jl_tficon);

		jb_upd.setBounds(125, 370, 90, 30);
		jb_upd.setBackground(new Color(248, 136, 137));
		jb_upd.setForeground(Color.WHITE);
		add(jb_upd);

		setBackground(Color.WHITE);
	}

	public String finder() {
		
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		chooser.setCurrentDirectory(new File("내 PC"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setDialogTitle("타이틀");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Png File", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			folderPath = "";
		}
		
		return folderPath;
		
	}

	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();
			
			if (obj == jb_change) {
				finder();
			}
			
			ml.ProfileUpd(MemLogic.mvo.getM_ID(), folderPath);
			MemLogic.mvo.setPath(folderPath);
			ImageIcon img8 = new ImageIcon(MemLogic.mvo.getPath());
			p = img8.getImage();
			img_profile = p.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			icon_profile = new ImageIcon(img_profile);
			jl_profile.setIcon(icon_profile);

		}
	};
	
}