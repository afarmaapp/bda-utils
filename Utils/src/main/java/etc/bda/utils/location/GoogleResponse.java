package etc.bda.utils.location;

import java.util.List;

import com.google.api.client.util.Key;

import etc.bda.utils.httpcall.simple.ResponseDTO;

public class GoogleResponse extends ResponseDTO<Object>{
	
	@Key(value = "results")
	private List<GoogleGeoCodeResponse> results;

	public List<GoogleGeoCodeResponse> getResults() {
		return results;
	}

	public void setResults(List<GoogleGeoCodeResponse> results) {
		this.results = results;
	}	
}
