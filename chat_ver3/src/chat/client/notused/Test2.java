package chat.client.notused;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Test2  {
	
	JFrame jf = new JFrame();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	JButton jbtn = new JButton("»ý¼º");
	
	JLabel jlb = new JLabel("chat");

	String[] col = new String[1];
	String[][] data = new String[0][1];
	DefaultTableModel jtm = new DefaultTableModel(data,col);
	JTable jt = new JTable(jtm);
	
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
	JTextPane jtp = new JTextPane(sd_display);
	JScrollPane sp = new JScrollPane(jtp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public void initDisplay() {
		jf.setSize(500, 300);
		
		jtp.setEditable(false);
		jtp.setFocusable(false);
		
		jf.getContentPane().add(sp, BorderLayout.CENTER);
		jf.getContentPane().add(jbtn, BorderLayout.SOUTH);
		
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test1 t1 = new Test1();
				t1.initDisplay(Test2.this);
			}
		});

		jf.setVisible(true);
		
	}

	public static void main(String[] args) {
		Test2 t2 = new Test2();
		t2.initDisplay();
	}
}
