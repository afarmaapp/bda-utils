package etc.bda.utils.httpcall;

import java.nio.charset.Charset;
import java.util.Set;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;

public class HttpLogger {

    public static HttpLoggerEntity buildOrusHttpLogger(HttpRequest request, HttpResponse response, String stringResult, Long duration, Exception exceptionToLog, Charset charset) {
        HttpLoggerEntity result = new HttpLoggerEntity();
        String method = request.getRequestMethod();
        result.setUrl(request.getUrl().build());
        if (method != null) {
            result.setMethod(method);
        }
        if (response != null) {
            result.setResponseCode(response.getStatusCode());
        }
        if (exceptionToLog != null && exceptionToLog instanceof HttpResponseException) {
            result.setResponseCode(((HttpResponseException)exceptionToLog).getStatusCode());
        }
        if (duration != null) {
            result.setDuration(duration);
        }
        result.setRequest(HttpLogger.mountRequest(request));
        result.setResponse(HttpLogger.mountResponse(response, stringResult, exceptionToLog, charset));
        return result;
    }

    private static HttpObject mountRequest(HttpRequest request) {
        HttpObject result = new HttpObject();
        if (request != null) {
            try {
                HttpContent content = request.getContent();
                if (content != null && content instanceof JsonHttpContent) {
                    JsonHttpContent jsonContent = (JsonHttpContent) content;
                    Object object = jsonContent.getData();
                    JsonFactory jsonFactory = jsonContent.getJsonFactory();
                    if (object != null && jsonFactory != null) {
                        result.setPayload(jsonFactory.toPrettyString(object));
                    }
                }
                result.setHeaders(HttpLogger.headersToString(request.getHeaders()));
            } catch (Throwable error) {
                error.printStackTrace();
            }
        }
        return result;
    }
    
    private static HttpObject mountResponse(HttpResponse response, String stringResult, Exception exceptionToLog, Charset charset) {
        HttpObject result = new HttpObject();
        if (response != null) {
            try {
                result.setPayload(new String(stringResult.getBytes(charset)));
                result.setHeaders(HttpLogger.headersToString(response.getHeaders()));
            } catch (Throwable error) {
                error.printStackTrace();
            }            
        } else {
            if (exceptionToLog != null) {
                if (exceptionToLog instanceof HttpResponseException) {
                    result.setPayload(new String(((HttpResponseException)exceptionToLog).getContent().getBytes(charset)));
                    result.setHeaders(HttpLogger.headersToString(((HttpResponseException)exceptionToLog).getHeaders()));
                } else {
                    result.setPayload(exceptionToLog.getMessage());
                }
            }
        }
        return result;
    }

    private static String headersToString(HttpHeaders headers) {
        Set<String> chaves = headers.keySet();
        if (chaves != null && !chaves.isEmpty()) {
            StringBuffer stringHeader = new StringBuffer();
            for (String header : chaves) {
                stringHeader.append(header).append(": ").append(headers.get(header)).append("\n");
            }
            return stringHeader.toString();
        }
        return null;
    }

}