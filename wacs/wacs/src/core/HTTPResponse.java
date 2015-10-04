package core;

import enums.RequestMethod;

public class HTTPResponse {

	private String path;
	private RequestMethod requestMethod;
	private int responseCode;
	private String responseContent;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public RequestMethod getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}
}


