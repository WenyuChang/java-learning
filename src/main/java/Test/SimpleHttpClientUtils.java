/**
 * 
 */
package Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * @author sj
 *
 */
public class SimpleHttpClientUtils {

	public static String excuteSimplePost(String uri, Map<String, Object> paramMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);
		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}

		HttpPost httpPost = new HttpPost(uriBuilder.build());
		CloseableHttpResponse response = null;
		String result = null;

		try {
			response = closeableHttpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			response.close();
		}
		return result;

	}

	public static String excuteSimpleGet(String uri, Map<String, Object> paramMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);

		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}

		HttpGet httpGet = new HttpGet(uriBuilder.build());

		CloseableHttpResponse response = null;
		String result = null;

		try {
			response = closeableHttpClient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			response.close();
		}
		return result;

	}

	public static String formatPrefixUrl(String url) {
		Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w.]+\\/?)\\S*");
		Matcher matcher = pattern.matcher(url);
		if (!matcher.matches()) {
			return "http://" + url;
		}
		return url;

	}
}
