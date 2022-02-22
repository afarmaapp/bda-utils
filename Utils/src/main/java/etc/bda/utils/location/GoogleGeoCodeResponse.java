package etc.bda.utils.location;

import java.util.List;

import com.google.api.client.util.Key;

public class GoogleGeoCodeResponse {
	@Key(value = "address_components")
	private List<AddressComponents> addressComponents;

	public List<AddressComponents> getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(List<AddressComponents> addressComponents) {
		this.addressComponents = addressComponents;
	}
	
	@Key(value = "formatted_address")
	private String formattedAddress;
	
	@Key(value = "geometry")
	private Geometry geometry;
	
	@Key(value = "place_id")
    private String placeId;
	
	@Key(value = "types")
    private List<String> types;
	
	@Key(value = "status")
    private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

}
