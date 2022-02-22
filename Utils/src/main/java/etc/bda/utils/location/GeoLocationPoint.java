package etc.bda.utils.location;

public class GeoLocationPoint {

	private double latitude;
	private double longitude;

	public GeoLocationPoint() {

	}
	public GeoLocationPoint(Location location) {
		this.latitude = location.getLat();
		this.longitude = location.getLng();
	}
	
	public GeoLocationPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLatitudeInRadians() {
		return Math.toRadians(latitude);
	}

	public double getLatitudeSinInRadians() {
		return Math.sin(this.getLatitudeInRadians());
	}

	public double getLatitudeCosInRadians() {
		return Math.cos(this.getLatitudeInRadians());
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLongitudeInRadians() {
		return Math.toRadians(longitude);
	}

	public double getLongitudeSinInRadians() {
		return Math.sin(this.getLongitudeInRadians());
	}

	public double getLongitudeCosInRadians() {
		return Math.cos(this.getLongitudeInRadians());
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double distance(GeoLocationPoint to) {
		return 6378.137 * Math.acos(this.getLatitudeCosInRadians() * to.getLatitudeCosInRadians()
				* Math.cos(to.getLongitudeInRadians() - this.getLongitudeInRadians())
				+ this.getLatitudeSinInRadians() * to.getLatitudeSinInRadians());
	}

}