package etc.bda.utils.httpcall.simple;

import com.google.api.client.http.GenericUrl;

import etc.bda.utils.httpcall.HttpCallConfig;
import etc.bda.utils.httpcall.HttpClient;
import etc.bda.utils.location.GoogleResponse;

public class GenericServiceCall {
    
	private HttpClient client;
    private HttpCallConfig config;

    public HttpClient getHttpClient() {
        if (this.client == null) {
            this.client = new HttpClient();
        }
        return this.client;
    }

    public HttpCallConfig getConfig() {
        if (this.config == null) {
            config = new HttpCallConfig();
        }
        return this.config;
    }

    public GenericUrl getUrl(String path) {
        return new GenericUrl(this.getConfig().getUrl()+path);
    }
    
    private  String execGet2(GenericUrl url, Class<String> responseType) {

		String response = getHttpClient().execGet(url,  responseType);
		System.out.println(response);
		return response;
	}
    
    public  <R extends Object> R execGet(GenericUrl url, Class<R> responseType) {

		R response = getHttpClient().execGet(url,  responseType);
		System.out.println(response);
		return response;
	}
    
    private <R extends ResponseDTO> R call(RequestDTO request, Class<R> responseType) {

		R response = getHttpClient().execPost(getUrl(""), request, responseType);
		
		return response;
	}
    
}