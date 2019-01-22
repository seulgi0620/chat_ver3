package chat.client.notused;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogSaver {
	
	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
	
	public void log_write(String filepath, String msg) {
		
        try{
 
            File file = new File(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            
            if(file.isFile() && file.canWrite()){
                bufferedWriter.append(msg);
                bufferedWriter.newLine();
                
                bufferedWriter.close();
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }

	}
	
	public void log_read(String filepath, TalkClientThread tct) {

		File file = new File(filepath);
		boolean isExists = file.exists();
		
		if(!isExists) {
			tct.tc.jta_display.append("내용이 없습니다.\n");
		} else {
	        Path path = Paths.get(filepath);
	        Charset cs = StandardCharsets.UTF_8;
	        List<String> list = new ArrayList<String>();
	        
	        try{
	            list = Files.readAllLines(path, cs);
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	        
	        for(String readLine : list){
	        	tct.tc.jta_display.append(readLine+"\n");
	        }
		}

	}

}
