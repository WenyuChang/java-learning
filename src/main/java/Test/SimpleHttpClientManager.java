/**
 * 
 */
package Test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author sj
 *
 */
public class SimpleHttpClientManager {
	
	// 连接超时时间，默认10秒
	private static int socketTimeout = 10000;

	// 传输超时时间，默认30秒
	private static int connectTimeout = 30000;
	
	public static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
			.setConnectTimeout(connectTimeout).build();
	
	private static class SingletonPoolManager {
		private static final int maxTotal = 100;
		public final static PoolingHttpClientConnectionManager poolHttpClient = new PoolingHttpClientConnectionManager();
		static {
			poolHttpClient.setMaxTotal(maxTotal);
		}
	}

	private static PoolingHttpClientConnectionManager getInstance() {
		return SingletonPoolManager.poolHttpClient;
	}

	public static CloseableHttpClient getCloseableHttpClient(){
		
		return HttpClients.custom().setConnectionManager(SimpleHttpClientManager.getInstance()).build();
	}
	

}
