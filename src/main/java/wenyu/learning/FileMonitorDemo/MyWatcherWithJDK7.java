package wenyu.learning.FileMonitorDemo;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;


public class MyWatcherWithJDK7 {

	private Path path = null;
	private WatchService watcher = null;
	private String fileName = null;
	
	public MyWatcherWithJDK7(Path path, String fileName) {
		this.path = path;
		this.fileName = fileName;
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			WatchKey key = path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void watchEvents() {
		while (true) {
			// wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            
            System.out.println("size: " + key.pollEvents().size());
            for (WatchEvent<?> event: key.pollEvents()) {
                Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                	System.out.println("over flow!");
                    continue;
                }

                @SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path name = ev.context();
                //System.out.println("name: " + name.toString());
                //if (name.toString().equals(fileName)) {
	                Path child = path.resolve(name);
	                System.out.format("%s: %s\n",child, event.kind().name());
                //}
            }
            key.reset();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "c:/aaa";
		Path path = Paths.get(filePath);
		
		MyWatcherWithJDK7 watcher = new MyWatcherWithJDK7(path, "aaa.txt");
		watcher.watchEvents();
	}

}
