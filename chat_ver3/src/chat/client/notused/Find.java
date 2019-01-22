package chat.client.notused;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class Find extends JFileChooser {

	public static void main(String[] args) {
		
		 String folderPath = "";
	     
	     JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // ���丮 ����
	     chooser.setCurrentDirectory(new File("/")); // ���� ��� ���丮�� ����
	     chooser.setAcceptAllFileFilterUsed(true);   // Fileter ��� ���� ���� 
	     chooser.setDialogTitle("Ÿ��Ʋ"); // â�� ����
	     chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ���� ���� ���
	     
	     FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary File", "cd11"); // filter Ȯ���� �߰�
	     chooser.setFileFilter(filter); // ���� ���͸� �߰�
	     
	     int returnVal = chooser.showOpenDialog(null); // ����� â ����
	     
	     if(returnVal == JFileChooser.APPROVE_OPTION) { // ���⸦ Ŭ�� 
	         folderPath = chooser.getSelectedFile().toString();
	     }else if(returnVal == JFileChooser.CANCEL_OPTION){ // ��Ҹ� Ŭ��
	         System.out.println("cancel"); 
	         folderPath = "";
	     }
	     System.out.println(folderPath); 
	}

}