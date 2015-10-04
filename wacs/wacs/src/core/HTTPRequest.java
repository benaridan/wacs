package core;

public class HTTPRequest {

	private String path;
	private int responseCode;
	private String response;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		//todo validate path
		
		this.path = path;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
