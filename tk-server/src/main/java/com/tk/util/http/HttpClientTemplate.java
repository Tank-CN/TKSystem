package com.tk.util.http;

import com.tk.util.JSONUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * HttpClientTemplate
 * 网络请求工具类
 */

public class HttpClientTemplate {

	private static PoolingHttpClientConnectionManager connMgr;

	private static RequestConfig requestConfig;

	private static final int MAX_TIMEOUT = 5000;

	private static final int MAX_TOTAL = 100;

	private static final String CHAR_SET = "UTF-8";

	private static final String HTTP = "HTTP";

	private static final String HTTPS = "HTTPS";

	private static HttpClientTemplate instance;

	static {
		connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(MAX_TOTAL);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		configBuilder.setStaleConnectionCheckEnabled(true);//测试连接
		requestConfig = configBuilder.build();
	}

	public static HttpClientTemplate instance() throws Exception {
		if (instance == null) {
			instance = new HttpClientTemplate();
		}
		return instance;
	}

	private static HttpClient getHttpClient(String type) throws Exception {
		if (type.equals(HTTP)) {
			return HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		}
		if (type.equals(HTTPS)) {
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme(HTTPS, 443, getSSLSocketFactory()));
			return httpClient;
		} else {
			throw new IOException("current type[" + type + "] request is not supported.");
		}
	}

	private HttpResult executeWithHttpClient(String type, HttpClientCallBack callback) throws Exception {
		HttpClient httpClient = getHttpClient(type);
		HttpResponse response = null;
		try {
			HttpRequestBase httpBase = callback.doInHttpClient();
			response = httpClient.execute(httpBase);
			httpBase.releaseConnection();
			return buildResult(response);
		} catch (IOException e) {
			throw new IOException("error in http invoke.", e);
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * post (default http : key-value)
	 */

	public HttpResult post(String url, Map<String, Object> params) throws Exception {
		return doPost(HTTP, url, null, params, CHAR_SET);
	}

	public HttpResult post(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
		return doPost(HTTP, url, headers, params, CHAR_SET);
	}

	public HttpResult doPost(String type, final String url, final Map<String, String> headers, final Map<String, Object> params, final String charset) throws Exception {
		return new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpPost doInHttpClient() throws Exception {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeaders(parseHeader(headers));
				List<NameValuePair> pairList = new ArrayList<>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
					pairList.add(pair);
				}
				httpPost.setEntity(new UrlEncodedFormEntity(pairList, charset));
				return httpPost;
			}
		});
	}

	/**
	 * post (default http : json)
	 */

	public HttpResult post(String url, Object json) throws Exception {
		return doPost(HTTP, url, null, json, CHAR_SET);
	}

	public HttpResult post(String url, Map<String, String> headers, Object json) throws Exception {
		return doPost(HTTP, url, headers, json, CHAR_SET);
	}

	public HttpResult doPost(String type, final String url, final Map<String, String> headers, final Object json, final String charset) throws Exception {
		return new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpPost doInHttpClient() throws Exception {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeaders(parseHeader(headers));
				StringEntity stringEntity = new StringEntity(JSONUtils.writeValueAsString(json), charset);
				stringEntity.setContentEncoding(charset);
				stringEntity.setContentType("application/json");
				httpPost.setEntity(stringEntity);
				return httpPost;
			}
		});
	}

	/**
	 * get (default http : key-value)
	 */

	public HttpResult get(String url, Map<String, String> params) throws Exception {
		return doGet(HTTP, url, null, params);
	}

	public HttpResult get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
		return doGet(HTTP, url, headers, params);
	}

	public HttpResult doGet(String type, final String url, final Map<String, String> headers, final Map<String, String> params) throws Exception {
		return new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpGet doInHttpClient() throws Exception {
				String realUrl = (null == params ? url : url + "?" + parseParams(params));
				HttpGet httpGet = new HttpGet(realUrl);
				httpGet.setHeaders(parseHeader(headers));
				return httpGet;
			}
		});
	}

	/**
	 * delete (default http)
	 */
	public HttpResult delete(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
		return doDelete(HTTP, url, headers, params);
	}

	public HttpResult doDelete(String type, final String url, final Map<String, String> headers, final Map<String, String> params) throws Exception {
		return new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpDelete doInHttpClient() throws Exception {
				String realUrl = (null == params ? url : url + "?" + parseParams(params));
				HttpDelete httpDelete = new HttpDelete(realUrl);
				httpDelete.setHeaders(parseHeader(headers));
				return httpDelete;
			}
		});
	}

	/**
	 * download (default http)
	 */
	public File downLoad(String url, Map<String, String> headers, File localPath) throws Exception {
		return doDownLoad(HTTP, url, headers, localPath);
	}

	public File doDownLoad(String type, final String url, final Map<String, String> headers, File localPath) throws Exception {
		HttpResult result = new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpGet doInHttpClient() throws Exception {
				HttpGet httpGet = new HttpGet(url);
				httpGet.setHeaders(parseHeader(headers));
				return httpGet;
			}
		});
		HttpEntity entity = result.getHttpEntity();
		InputStream in = entity.getContent();

		FileOutputStream fos = new FileOutputStream(localPath);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) != -1) {
			fos.write(buffer, 0, length);
		}
		fos.close();
		return localPath;
	}

	/**
	 * upload (default http)
	 */
	public HttpResult upload(String url, Map<String, String> headers, HttpEntity httpEntity) throws Exception {
		return doUpload(HTTP, url, headers, httpEntity);
	}

	public HttpResult doUpload(String type, final String url, final Map<String, String> headers, final HttpEntity httpEntity) throws Exception {
		return new HttpClientTemplate().executeWithHttpClient(type, new HttpClientCallBack() {

			@Override
			public HttpPost doInHttpClient() throws Exception {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeaders(parseHeader(headers));
				httpPost.setEntity(httpEntity);
				return httpPost;
			}
		});
	}

	/**
	 * **********************************************************************
	 */

	public static HttpResult buildResult(HttpResponse response) throws Exception {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			return null;
		}
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			return null;
		}
		HttpResult result = new HttpResult();
		result.setStatusCode(statusCode);
		result.setHeaders(response.getAllHeaders());
		result.setHttpEntity(entity);
		result.setBody(EntityUtils.toString(entity));
		return result;
	}

	private static Header[] parseHeader(Map<String, String> headers) {
		if (null == headers || headers.isEmpty()) {
			return getDefaultHeaders();
		}
		Header[] allHeader = new BasicHeader[headers.size()];
		int i = 0;
		for (String str : headers.keySet()) {
			allHeader[i] = new BasicHeader(str, headers.get(str));
			i++;
		}
		return allHeader;
	}

	private static Header[] getDefaultHeaders() {
		Header[] allHeader = new BasicHeader[2];
		allHeader[0] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
		allHeader[1] = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/Chrome/Safari");
		return allHeader;
	}

	private static String parseParams(Map<String, String> params) {
		if (null == params || params.isEmpty()) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String key : params.keySet()) {
			sb.append(key + "=" + params.get(key) + "&");
		}
		return sb.substring(0, sb.length() - 1);
	}

	private static SSLSocketFactory getSSLSocketFactory() throws Exception {
		X509TrustManager tm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
		return socketFactory;
	}

	interface HttpClientCallBack {

		HttpRequestBase doInHttpClient() throws Exception;
	}

}