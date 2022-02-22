package etc.bda.utils.location;

import com.google.api.client.http.GenericUrl;

import etc.bda.utils.httpcall.simple.GenericServiceCall;

public class GoogleGeocode {
	private static String GOOGLE_API_KEY = "AIzaSyBo7_55U2lnF4BI8tH7VfuUvcMWukPsPEM";
	private static String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
	private String googleApiUrl;
	private String googleApiKey;
	
	public GoogleGeocode() {
		this.googleApiUrl = GOOGLE_API_URL;
		this.googleApiKey = GOOGLE_API_KEY;
				
	}
	
	public GoogleGeocode(String googleApiUrl, String googleApiKey) {
		this.googleApiUrl = googleApiUrl;
		this.googleApiKey = googleApiKey;
				
	}
	public GoogleGeoCodeResponse geocode(String cep) {
		String url = this.googleApiUrl.concat("address=").concat(cep).concat("&key=").concat(this.googleApiKey); 
    	GoogleResponse gr = new GenericServiceCall().execGet(new GenericUrl(url), GoogleResponse.class);
		if (gr == null || gr.getResults().isEmpty())
			return null;
		else return gr.getResults().get(0);
    	
	}
}
