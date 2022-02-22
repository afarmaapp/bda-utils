package etc.bda.utils.location;

import com.google.api.client.util.Key;

public class Geometry {

	@Key(value = "location")
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Key(value = "location_type")
	private String locationType;
	@Key(value = "viewport")
	private Viewport viewport;

	@Key(value = "bounds")
	private Viewport bounds;

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
}
