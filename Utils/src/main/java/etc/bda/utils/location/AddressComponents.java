package etc.bda.utils.location;

import java.util.List;

import com.google.api.client.util.Key;

public class AddressComponents {
	@Key(value = "long_name")
    private String longName;
	
	@Key(value = "short_name")
	private String shortName;
	
	@Key(value = "types")
    private List<String> types;

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
