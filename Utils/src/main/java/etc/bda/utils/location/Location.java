package etc.bda.utils.location;

import com.google.api.client.util.Key;

public class Location {
    @Key(value = "lat")
	private double lat;
    
    @Key(value = "lng")
    private double lng;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
    
}
