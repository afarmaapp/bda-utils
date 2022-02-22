package etc.bda.utils.httpcall.simple;

import com.google.api.client.util.Key;

public class RequestDTO {

	@Key
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
	
}
