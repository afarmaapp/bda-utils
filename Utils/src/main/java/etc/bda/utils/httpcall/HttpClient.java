package etc.bda.utils.httpcall;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

//import org.apache.http.HttpRequest;
//import org.apache.http.HttpRequestFactory;
//import org.apache.http.client.HttpResponseException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;

import etc.bda.utils.Util;

public class HttpClient {

	private HttpRequestFactory httpRequestFactory;
	private HttpRequestInitializer authInitializer;
	
	@Inject
	private HttpCallConfig config;
	
	@Inject
	private HttpLoggerBC httpLoggerBC;

	public HttpClient() {
		super();
		if (config == null) { // a principio nao inicializou com o CDI, iniciando manualmente
			config = new HttpCallConfig();
			initialize();
		}
	}
	
	@PostConstruct
	private void initialize() {
		HttpTransport httpTransport = new NetHttpTransport();
//		this.authInitializer = new RequestInitializer(httpTransport, config);
		this.httpRequestFactory = httpTransport.createRequestFactory(r -> {
			r.setParser(HttpUtils.DEFAULT_RESPONSE_PARSER);
//			authInitializer.initialize(r);
			r.setConnectTimeout(config.getConnectTimeout());
			r.setReadTimeout(config.getReadTimeout());
		});		
	}

	private interface NoContentHttpRequestSupplier {
		public HttpRequest get(GenericUrl url) throws IOException;
	}

	private interface HttpRequestSupplier {
		public HttpRequest get(GenericUrl url, HttpContent content) throws IOException;
	}

	private interface HttpRequestSupplierWithMethod {
		public HttpRequest get(String method, GenericUrl url, HttpContent content) throws IOException;
	}

	private <T> T executeRequest(HttpRequest request, Class<T> responseType) {
//		String requestId = UUID.randomUUID().toString();
		Exception exceptionToLog = null;
		HttpResponse response = null;
		String stringResult = null;
		try {
//			request.getHeaders().put(this.orusConfig.getParameterRequestIdName(), requestId);
		request.getHeaders().put("ApplicationV", "integrator/6");
		request.getHeaders().put("Cache-Control", "no-cache");
		request.getHeaders().put("Content-Type", "application/json");
		} catch (Throwable error) {
		}
		long before = System.currentTimeMillis();
		try {
			request.getHeaders().setContentLength(null);
			response = request.execute();
			if (responseType != null) {
				stringResult = new String(response.parseAsString().getBytes(config.getCharsetAsString()));
				return request.getParser().parseAndClose(new StringReader(stringResult), responseType);
			} else {
				return null;
			}
		} catch (HttpResponseException httpError) {
			exceptionToLog = httpError;
			throw new HttpException("Erro ao invocar servicos", httpError, config.getCharset());
		} catch (IOException e) {
			exceptionToLog = e;
			throw new RuntimeException("Erro IO", e);
		} finally {
			final HttpLoggerEntity httpLogger = HttpLogger.buildOrusHttpLogger(request, response, stringResult,
					System.currentTimeMillis() - before, exceptionToLog, config.getCharset());
			if (this.httpLoggerBC != null) {
		        CompletableFuture.runAsync(() -> {
					this.httpLoggerBC.persist(httpLogger);
		        }, Util.EXECUTOR_SERVICE_POOL);
			} else {
				System.out.println(httpLogger.toString());
			}
			if (response != null) {
				try {
					response.disconnect();
				} catch (IOException e) {
				}
			}
		}
	}

	private HttpRequest buildHttpRequest(NoContentHttpRequestSupplier supplier, GenericUrl url) {
		try {
			return supplier.get(url);
		} catch (IOException e) {
			throw new RuntimeException("HTTP Error", e);
		}
	}

	private HttpRequest buildHttpRequest(HttpRequestSupplier supplier, GenericUrl url, HttpContent content) {
		try {
			return supplier.get(url, content);
		} catch (IOException e) {
			throw new RuntimeException("HTTP Error", e);
		}
	}

	private HttpRequest buildHttpRequest(HttpRequestSupplierWithMethod supplier, String method, GenericUrl url,
			HttpContent content) {
		try {
			return supplier.get(method, url, content);
		} catch (IOException e) {
			throw new RuntimeException("HTTP Error", e);
		}
	}

//	public Credential getOrusCredential() {
//		if (authInitializer instanceof RequestInitializer) {
//			return ((RequestInitializer) authInitializer).authorize();
//		} else {
//			return null;
//		}
//	}

	public <T> T execGet(GenericUrl url, Class<T> responseType) {
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildGetRequest, url), responseType);
	}

	public <T, R> R execPost(GenericUrl url, T body, Class<R> responseType) {
		JsonHttpContent j = (body != null ? new JsonHttpContent(HttpUtils.JSON_FACTORY, body) : null);
		System.out.println(j.getData().toString());
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildPostRequest, url,
				j), responseType);
	}

	public <R> R execPost(GenericUrl url, HttpContent body, Class<R> responseType) {
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildPostRequest, url, body), responseType);
	}

	public <T, R> R execPatch(GenericUrl url, T body, Class<R> responseType) {
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildPatchRequest, url,
				(body != null ? new JsonHttpContent(HttpUtils.JSON_FACTORY, body) : null)), responseType);
	}

	public <R> R execPatch(GenericUrl url, HttpContent body, Class<R> responseType) {
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildPatchRequest, url, body), responseType);
	}

	public void execDelete(GenericUrl url) {
		executeRequest(buildHttpRequest(this.httpRequestFactory::buildDeleteRequest, url), null);
	}

	public <T, R> R execDelete(GenericUrl url, T body, Class<R> responseType) {
		return executeRequest(buildHttpRequest(this.httpRequestFactory::buildRequest, "DELETE", url,
				(body != null ? new JsonHttpContent(HttpUtils.JSON_FACTORY, body) : null)), responseType);
	}
}