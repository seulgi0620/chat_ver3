package chat.client.notused;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class Test1  {

	JFrame jf = new JFrame();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	JButton jbtn = new JButton("생성");
	
	JLabel jlb = new JLabel("chat");

	String[] col = new String[1];
	String[][] data = new String[0][1];
	DefaultTableModel jtm = new DefaultTableModel(data,col);
	JTable jt = new JTable(jtm);
	
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
	JTextPane jtp = new JTextPane(sd_display);
	JScrollPane sp = new JScrollPane(jtp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	Test2 t2 = null;
	
	 JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		 JLabel lblProfile = new JLabel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JLabel lblChatmember = new JLabel("chatmember");
		JLabel lblChat = new JLabel("chat");
		
		public void initDisplay(Test2 t2) {

			this.t2 = t2;
			
		lblProfile.setIcon(new ImageIcon("C:/오리프사.png"));
		
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		t2.jtp.insertComponent(panel);
		try {
			t2.sd_display.insertString(t2.sd_display.getLength(),
					   "\n", null
					    );
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		panel.add(panel_1, BorderLayout.WEST);
		
		panel_1.add(lblProfile);
		
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panel_3.setBackground(Color.PINK);
		
		panel_2.add(panel_3);
		
		panel_3.add(lblChatmember);
		panel_4.setBackground(Color.ORANGE);
		
		panel_2.add(panel_4);
		
		panel_4.add(lblChat);

		}
		
	
		
}
