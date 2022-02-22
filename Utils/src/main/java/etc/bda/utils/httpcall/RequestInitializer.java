package etc.bda.utils.httpcall;

import java.io.IOException;
import java.util.ArrayList;


import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.PasswordTokenRequest;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class RequestInitializer implements HttpRequestInitializer {

	private final HttpTransport httpTransport;

	private Credential credential;

	private HttpCallConfig config;

	public RequestInitializer(HttpTransport httpTransport, final HttpCallConfig orusConfig) {
		super();
		this.httpTransport = httpTransport;
		this.config = orusConfig;
	}

	@Override
	public void initialize(HttpRequest request) throws IOException {
		authorize();
		credential.initialize(request);
	}

	public Credential authorize() {
		if (credential == null) {
			TokenRequest request = new PasswordTokenRequest(httpTransport, new JacksonFactory(),
					new GenericUrl(config.getUrl() + "v1/login"), config.getUsername(),
					config.getPassword());
			BasicAuthentication basicAuthentication = new BasicAuthentication(config.getUsername(),
					config.getPassword());
			request.setScopes(new ArrayList<String>());
			try {
				TokenResponse response = request.execute();
				credential = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
						.setJsonFactory(HttpUtils.JSON_FACTORY).setTransport(httpTransport)
						.setTokenServerUrl(request.getTokenServerUrl()).setClientAuthentication(basicAuthentication)
						.build().setFromTokenResponse(response);
			} catch (IOException e) {
				credential = null;
				throw new RuntimeException("Error authorize", e);
			}
		}
		return credential;
	}
}
