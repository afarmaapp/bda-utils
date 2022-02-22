package etc.bda.utils.httpcall;

import java.nio.charset.Charset;

import java.nio.charset.Charset;
import com.google.api.client.http.HttpResponseException;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 6530418517126600248L;

    private int statusCode;
    private String errorCode;
    private String description;

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Exception cause) {
        super(message, cause);
    }
    
    public HttpException(String message, HttpResponseException cause, Charset charset) {
        super(message, cause);
        if (cause != null) {
            this.setStatusCode(cause.getStatusCode());
            if (cause.getContent() != null) {
                ErrorMessage errorMessage = new Gson().fromJson(new String(cause.getContent().getBytes(charset)), ErrorMessage.class);
                this.setDescription(errorMessage.getDescription());
                this.setErrorCode(errorMessage.getErrorCode());        
            }
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

class ErrorMessage {
    private String status;
    @SerializedName("error-code") 
    private String errorCode;
    @SerializedName("error-description") 
    private String description;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}