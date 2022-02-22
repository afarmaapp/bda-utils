package etc.bda.utils.location;

import com.google.api.client.util.Key;

public class Viewport {
	@Key(value = "northeast")
	private Location northeast;
	
	@Key(value = "southwest")
	private Location southwest;

	public Location getNortheast() {
		return northeast;
	}

	public void setNortheast(Location northeast) {
		this.northeast = northeast;
	}

	public Location getSouthwest() {
		return southwest;
	}

	public void setSouthwest(Location southwest) {
		this.southwest = southwest;
	}
	
}
