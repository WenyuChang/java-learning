package wenyu.learning.FileMonitorDemo;

import org.apache.commons.vfs.FileChangeEvent;
import org.apache.commons.vfs.FileListener;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.impl.DefaultFileMonitor;


public class MyWatcherWithVFS implements FileListener  {

	public void startListening(DefaultFileMonitor fileMonitor) throws FileSystemException, InterruptedException {
		FileSystemManager fsManager = VFS.getManager();
		FileObject fileObject = fsManager.resolveFile("c:/aaa/aaa.txt");
		if (fileMonitor == null) {
			fileMonitor = new DefaultFileMonitor(this);
			fileMonitor.addFile(fileObject);
			fileMonitor.start();
		}
		synchronized (fileMonitor) {
			fileMonitor.wait(); //wait fileChange
			startListening(fileMonitor); //recursive call
		}
		 
	}
	
	public void fileChanged(FileChangeEvent arg0) throws Exception {
		System.out.println("File Changed...");
	}

	public void fileCreated(FileChangeEvent arg0) throws Exception {
		System.out.println("File Created...");
	}

	public void fileDeleted(FileChangeEvent arg0) throws Exception {
		System.out.println("File Deleted...");
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws FileSystemException 
	 */
	public static void main(String[] args) throws FileSystemException, InterruptedException {
		MyWatcherWithVFS watcher = new MyWatcherWithVFS();
		watcher.startListening(null);
	}
	
	
}
