package etc.bda.utils.location;

import com.google.api.client.util.Key;

public class Results {
	
	@Key
	private String formatted_address;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
}
