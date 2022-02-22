package etc.bda.utils.httpcall.simple;

import com.google.api.client.util.Key;

public class ResponseDTO<T extends Object> {
	private boolean error = false;
	private String exception = null;
	
	@Key(value="data")
	private String data = null;
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
