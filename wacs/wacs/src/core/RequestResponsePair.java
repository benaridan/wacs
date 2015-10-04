package core;

public class RequestResponsePair {

	private HTTPRequest httpRequest;
	private HTTPResponse httpResponse;
	
	public HTTPRequest getHttpRequest() {
		return httpRequest;
	}
	public void setHttpRequest(HTTPRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	public HTTPResponse getHttpResponse() {
		return httpResponse;
	}
	public void setHttpResponse(HTTPResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
}
