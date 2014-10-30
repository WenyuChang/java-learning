package wenyu.learning.FileMonitorDemo;

import java.io.File;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitorWithCommonsIO {

    private static final String FILE_TO_MONITOR = "C:/aaa";

    class FAListener implements FileAlterationListener {

        public void onDirectoryChange(File arg0) {
        	System.out.println("onDirectoryChange : "+arg0);
        }

        public void onDirectoryCreate(File arg0) {
        	System.out.println("onDirectoryCreate : "+arg0);

        }

        public void onDirectoryDelete(java.io.File arg0) {
        	System.out.println("onDirectoryDelete : "+arg0);
        }

        public void onFileChange(File arg0) {
        	System.out.println("onFileChange : "+arg0);
        }

        public void onFileCreate(File arg0) {
                System.out.println("onFileCreate : "+arg0);
        }

        public void onFileDelete(File arg0) {
        	System.out.println("onFileDelete : "+arg0);
        }

		public void onStart(FileAlterationObserver arg0) {
			System.out.println("onStart : "+arg0);
		}

		public void onStop(FileAlterationObserver arg0) {
			System.out.println("onStop : "+arg0);
		}
    }

    private IOFileFilter initFileFilter() {
    	// Create a FileFilter
        IOFileFilter filter = FileFilterUtils.and(FileFilterUtils.fileFileFilter(),FileFilterUtils.suffixFileFilter(".txt"));
        return filter;
    }
    
    // public FileAlterationListener
    private void demoEntry() {
        System.out.println("begin sample-> observer=" + FILE_TO_MONITOR);
        File directory = new File(FILE_TO_MONITOR);
        FileAlterationObserver observer = new FileAlterationObserver(directory, initFileFilter());
        observer.addListener(new FAListener());
       
        long interval = 1000;
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        try {
        	monitor.start();
        	//monitor.stop();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        FileMonitorWithCommonsIO nt = new FileMonitorWithCommonsIO();
        nt.demoEntry();
    }
}
