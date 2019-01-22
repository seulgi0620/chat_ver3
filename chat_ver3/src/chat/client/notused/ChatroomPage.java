package chat.client.notused;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class ChatroomPage extends JPanel{
	
	private ChatroomPage panel;
	
	JLabel jl_search = new JLabel("°Ë»ö");
	JTextField tf_search = new JTextField();
	
	String cols[] = {""};
	String data[][] = new String[0][1];
	DefaultTableModel dtm_lr = new DefaultTableModel(data,cols);
	JTable jt_loan = new JTable(dtm_lr);
	JScrollPane lrScroll = new JScrollPane(jt_loan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JTableHeader header = jt_loan.getTableHeader();
	
	ImageIcon img = new ImageIcon("C:\\dev_kosmo20181101\\semi_project\\src\\book\\image\\logout.png");
	Font f = new Font("¸¼Àº °íµñ",Font.PLAIN,13);
	
	public ChatroomPage(){
		
		panel = this;
		panel.setLayout(null);
		
		lrScroll.setBounds(10,50,365,440);
		
		header.setReorderingAllowed(false);
		
		add(jl_search);
		add(tf_search);	      
		add(lrScroll);
		
		setBackground(Color.WHITE);
	
	}
}
						
