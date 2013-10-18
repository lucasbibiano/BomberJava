package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedThreadPool {
	private static ExecutorService es;
	
	private SharedThreadPool() {}
	
	public static synchronized ExecutorService getES() {
		if (es == null) {
			es = Executors.newCachedThreadPool();
		}
		
		return es;
	}
}
