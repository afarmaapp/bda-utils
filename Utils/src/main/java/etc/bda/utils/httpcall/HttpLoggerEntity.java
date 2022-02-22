package etc.bda.utils.httpcall;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "HttpLogger", schema = "logger")
@Entity(name = "HttpLogger")
public class HttpLoggerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orus_httplogger_id_seq")
	@SequenceGenerator(name = "orus_httplogger_id_seq", sequenceName = "orus_httplogger_id_seq", allocationSize = 1)
	private Long id;

	@Column(length = 5000)
	private String url;

	@Column
	private Date date;

	@Column
	private String method;

	@Column
	private Integer responseCode;

	@Column
	private Long duration;

	@Transient
	private HttpObject request;

	@Transient
	private HttpObject response;
	
	public HttpLoggerEntity() {
		this.date = new Date();
	}

	public HttpObject getRequest() {
		return request;
	}

	public void setRequest(HttpObject request) {
		this.requestHeaders = request!=null?request.getHeaders():this.requestHeaders;
		this.requestPayload = request!=null?request.getPayload():this.requestPayload;
		this.request = request;
	}

	public HttpObject getResponse() {
		return response;
	}

	public void setResponse(HttpObject response) {
		this.responseHeaders = response!=null?response.getHeaders():this.responseHeaders;
		this.responsePayload = response!=null?response.getPayload():this.responsePayload; 
		this.response = response;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("URL: ").append(this.getUrl()).append("\n");
		buffer.append("Method: ").append(this.getMethod()).append("\n");
		buffer.append("Date: ").append(this.getDate()).append("\n");
		buffer.append("Duration: ").append(this.getDuration()).append("\n");
		buffer.append("ResponseCode: ").append(this.getResponseCode()).append("\n");
		buffer.append("=======================================");
		buffer.append("REQUEST\n");
		if (this.getRequest() != null) {
			buffer.append("Headers: ").append("\n").append(this.getRequest().getHeaders());
			buffer.append("Payload: ").append("\n").append(this.getRequest().getPayload()).append("\n");
		} else {
			buffer.append("null").append("\n");
		}
		buffer.append("=======================================");
		buffer.append("RESPONSE\n");
		if (this.getRequest() != null) {
			buffer.append("Headers: ").append("\n").append(this.getResponse().getHeaders());
			buffer.append("Payload: ").append("\n").append(this.getResponse().getPayload()).append("\n");
		} else {
			buffer.append("null").append("\n");
		}
		buffer.append("=======================================");
		return buffer.toString();
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "request_payload", length = 5000)
	private String requestPayload;
	public String getRequestPayload() {
		this.requestPayload = this.getRequest()!=null?this.getRequest().getPayload():this.requestPayload;
		return this.requestPayload;
	}
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	
	@Column(name = "response_payload", length = 5000)
	private String responsePayload;
	public String getResponsePayload() {
		this.responsePayload = this.getResponse()!=null?this.getResponse().getPayload():this.responsePayload; 
		return this.responsePayload;
	}
	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}

	@Column(name = "request_headers", length = 5000)
	private String requestHeaders;
	public String getRequestHeaders() {
		this.requestHeaders = this.getRequest()!=null?this.getRequest().getHeaders():this.requestHeaders;
		return this.requestHeaders;
	}
	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	
	@Column(name = "response_headers", length = 5000)
	private String responseHeaders;
	public String getResponseHeaders() {
		this.responseHeaders = this.getResponse()!=null?this.getResponse().getHeaders():this.responseHeaders;
		return this.responseHeaders;
	}
	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

}