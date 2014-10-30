package wenyu.learning.Lock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class VirtualJob {
	
	public String name;
	public String filePath = "src/main/java/wenyu/demo/Lock/number";
	
	public VirtualJob(String jobName) {
		name = jobName;
	}
	
	private String readFile() {
        FileReader fileReader = null;
        String content = null;
        try {
            fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
            	content = line + "\n";
            }
            
            fileReader.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private boolean writeFile(String content) {
        File writefile;
        try {
            writefile = new File(filePath);

            if (writefile.exists() == false) {
                return false;
            }
            FileWriter fw = new FileWriter(writefile, false);
            fw.write(content);
            fw.flush();
            fw.close();
            
            return true;
            
        } catch (Exception d) {
            System.out.println(d.getMessage());
            return false;
        }
	}
	
	public void excute() throws InterruptedException {
		
		String content = readFile();
		if(content != null) {
			content = content.substring(0, content.length()-1);
			System.out.println(content);
			
			// TODO job excute
			int number = Integer.valueOf(content);
			number++;
			content = String.valueOf(number);
		}
		
		System.out.println(name + "'s content has already been " + content);
		Thread.sleep(1000);
		if(writeFile(content)) {
			System.out.println(name + " success...");
		} else {
			System.out.println(name + " failed...");
		}
	}
}
