/*package com.ways.utils.utils;

import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Header;
import javax.mail.internet.ContentType;
import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.omg.CORBA.NameValuePair;
import org.springframework.http.HttpEntity;

import com.google.common.collect.ImmutableBiMap.Builder;

*//**
 * HttpClientUtils, 使用 HttpClient 4.x<br>
 * 
 *//*
public class HttpUtils {

	private static HttpClient client = null;
	private static String default_charset = "utf-8";
	public static String cookie = "";
	public static Map<String, String> cookieMap = new HashMap<String, String>();;

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm).build();
	}

	*//**
	 * 发送一个 Post 请求, 使用指定的字符集编码.
	 * 
	 * @param url
	 * @param body
	 *            RequestBody
	 * @param mimeType
	 *            例如 application/xml
	 * @param charset
	 *            编码
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return ResponseBody, 使用指定的字符集编码.
	 * 
	 * @throws ConnectTimeoutException
	 *             建立链接超时异常
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 *//*
	public static String post(String url, String body, String mimeType, String charset, Integer connTimeout,
			Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException, Exception {
		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		String result = "";
		try {
			if (StringUtils.isNotBlank(body)) {
				HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
				post.setEntity(entity);
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());

			HttpResponse res;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = HttpUtils.client;
				res = client.execute(post);
			}
			result = IOUtils.toString(res.getEntity().getContent(), charset);
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	*//**
	 * 提交form表单
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param session
	 * @param baseClient
	 * @return
	 * @throws ConnectTimeoutException
	 * @throws SocketTimeoutException
	 * @throws Exception
	 *//*
	public static String postForm(String url, Map<String, String> params, Map<String, String> headers)
			throws ConnectTimeoutException, SocketTimeoutException, Exception {
		return postForm(url, params, headers, 30000, 30000);
	}

	public static String postForm(String url, Map<String, String> params, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout)
					throws ConnectTimeoutException, SocketTimeoutException, Exception {
		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
				post.setEntity(entity);

			}
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = HttpUtils.client;
				res = client.execute(post);
			}
			setCookieStore(res);
			return IOUtils.toString(res.getEntity().getContent(), getCharsetFromResponse(res));
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
	}

	*//**
	 * 发送一个 GET 请求，获取图片
	 * 
	 * @param url
	 * @param headers
	 * @return
	 * @throws Exception
	 *//*
	public static byte[] getImg(String url, Map<String, String> headers) throws Exception {
		HttpClient client = null;
		HttpGet get = new HttpGet(url);
		boolean isSecure = url.startsWith("https");
		try {
			HttpResponse res = getResponse(client, get, isSecure, headers, null, null);
			setCookieStore(res);
			return IOUtils.toByteArray(res.getEntity().getContent());
		} finally {
			get.releaseConnection();
			if (isSecure && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
	}

	*//**
	 * 发送一个 GET 请求，获取HTML
	 * 
	 * @param url
	 * @param headers
	 * @param session
	 * @param baseClient
	 * @return
	 * @throws Exception
	 *//*
	public static String getHtml(String url, Map<String, String> headers) throws Exception {
		return getHtml(url, headers, 30000, 30000);
	}

	public static String getHtml(String url, Map<String, String> headers, Integer connTimeout, Integer readTimeout)
			throws Exception {
		HttpClient client = null;
		HttpGet get = new HttpGet(url);
		boolean isSecure = url.startsWith("https");
		try {
			HttpResponse res = getResponse(client, get, isSecure, headers, connTimeout, readTimeout);
			setCookieStore(res);
			return IOUtils.toString(res.getEntity().getContent(), getCharsetFromResponse(res));
		} finally {
			get.releaseConnection();
			if (isSecure && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
	}

	*//**
	 * 发送一个 GET 请求
	 * 
	 * @param client
	 * @param get
	 * @param isSecure
	 * @param headers
	 * @param connTimeout
	 * @param readTimeout
	 * @return
	 * @throws Exception
	 *//*
	public static HttpResponse getResponse(HttpClient client, HttpGet get, boolean isSecure,
			Map<String, String> headers, Integer connTimeout, Integer readTimeout) throws Exception {

		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				get.addHeader(entry.getKey(), entry.getValue());
			}
		}
		// 设置参数
		Builder customReqConf = RequestConfig.custom();
		if (connTimeout != null) {
			customReqConf.setConnectTimeout(connTimeout);
		}
		if (readTimeout != null) {
			customReqConf.setSocketTimeout(readTimeout);
		}
		get.setConfig(customReqConf.build());

		HttpResponse res = null;

		if (isSecure) {
			// 执行 Https 请求.
			client = createSSLInsecureClient();
			res = client.execute(get);
		} else {
			// 执行 Http 请求.
			client = HttpUtils.client;
			res = client.execute(get);
		}
		return res;

	}

	*//**
	 * 从 response 里获取 charset
	 * 
	 * @param ressponse
	 * @return
	 *//*
	private static String getCharsetFromResponse(HttpResponse response) {
		// Content-Type:text/html; charset=GBK
		String charset = default_charset;
		if (response.getEntity() != null && response.getEntity().getContentType() != null
				&& response.getEntity().getContentType().getValue() != null) {
			String contentType = response.getEntity().getContentType().getValue();
			if (contentType.contains("charset=")) {
				charset = contentType.substring(contentType.indexOf("charset=") + 8);
			}
		}
		return charset;
	}

	static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	@SuppressWarnings("rawtypes")
	public static void setCookieStore(HttpResponse httpResponse) {
		cookie = "";
		Header[] allHeaders = httpResponse.getHeaders("Set-Cookie");
		for (int i = 0; i < allHeaders.length; i++) {
			String headerStr = allHeaders[i].getValue();
			if (!headerStr.contains("=")) {
				continue;
			}
			String key = findBetween(headerStr, "", "=");
			cookieMap.put(key, headerStr + ";");
		}

		for (Map.Entry entry : cookieMap.entrySet()) {
			cookie += (String) entry.getValue();
		}
	}

	public static String findBetween(String str, String top, String foot) {
		Pattern pattern = Pattern.compile(top + ".+?" + foot);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group(0).substring(getLength(top), getLength(matcher.group(0)) - getLength(foot));
		} else {
			return null;
		}

	}

	private static int getLength(String str) {
		if (str.contains("\\")) {
			return str.length() - 1;
		} else {
			return str.length();
		}
	}
}*/