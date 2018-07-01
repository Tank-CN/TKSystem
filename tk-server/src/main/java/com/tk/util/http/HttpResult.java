package com.tk.util.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.util.HashMap;

/**
 * HttpResult
 *
 */
public class HttpResult {

	private HttpEntity httpEntity;

	private HashMap<String, Header> headerAll;

	private int statusCode;

	private String body;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setHeaders(Header[] headers) {
		headerAll = new HashMap<String, Header>();
		for (Header header : headers) {
			headerAll.put(header.getName(), header);
		}
	}

	public HashMap<String, Header> getHeaderAll() {
		return headerAll;
	}

	public void setHttpEntity(HttpEntity entity) {
		this.httpEntity = entity;
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
