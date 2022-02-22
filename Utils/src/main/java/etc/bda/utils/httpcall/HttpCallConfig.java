package etc.bda.utils.httpcall;

import java.nio.charset.Charset;

//import org.demoiselle.jee.configuration.ConfigurationType;
//import org.demoiselle.jee.configuration.annotation.Configuration;
//import org.demoiselle.jee.configuration.annotation.ConfigurationName;

//@Configuration(type = ConfigurationType.PROPERTIES, prefix = "orus.config.")
public class HttpCallConfig {

//	@ConfigurationName("url")
//    private String url = "http://server.bda.dev.br/wle-rest/api";
	private String url = "http://168.121.76.10/integrator.server.php";
//	@ConfigurationName("username")
    private String username = "tpm.administrator";
//	@ConfigurationName("password")
    private String password = "RBZZ8zszyLDd";
//	@ConfigurationName("organizationId")
//    private String organizationId = "72e8e246-dbde-404f-8e9b-f204172f1d28";
//	@ConfigurationName("organizationPersonId")
//    private String organizationPersonId = "72e8e246-dbde-404f-8e9b-f204172f1d28";
//	@ConfigurationName("currency")
//    private String currency = "BRL";
//	@ConfigurationName("defaultPlanId")
//    private String defaultPlanId = "1b6d32d5-d42a-4214-aadd-ded9ae66e0ef";
//	@ConfigurationName("defaultProductId")
//    private String defaultProductId = "59a3a701-80d8-4358-ba46-b832af524dce";
//	@ConfigurationName("connectTimeout")
    private int connectTimeout = 15 * 1000;
//	@ConfigurationName("readTimeout")
    private int readTimeout = 30 * 1000;
//	@ConfigurationName("parameterRequestIdName")
    private String parameterRequestIdName = "external-code";
//	@ConfigurationName("charset")
    private String charset = "ISO-8859-1";


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public String getOrganizationId() {
//        return organizationId;
//    }
//
//    public void setOrganizationId(String organizationId) {
//        this.organizationId = organizationId;
//    }
//
//    public String getOrganizationPersonId() {
//        return organizationPersonId;
//    }
//
//    public void setOrganizationPersonId(String organizationPersonId) {
//        this.organizationPersonId = organizationPersonId;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public String getDefaultPlanId() {
//        return defaultPlanId;
//    }
//
//    public void setDefaultPlanId(String defaultPlanId) {
//        this.defaultPlanId = defaultPlanId;
//    }
//
//    public String getDefaultProductId() {
//        return defaultProductId;
//    }
//
//    public void setDefaultProductId(String defaultProductId) {
//        this.defaultProductId = defaultProductId;
//    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

	public String getParameterRequestIdName() {
		return parameterRequestIdName;
	}

	public void setParameterRequestIdName(String parameterRequestIdName) {
		this.parameterRequestIdName = parameterRequestIdName;
	}

    public String getCharsetAsString() {
        return charset;
    }

    public Charset getCharset() {
        String localCharset = this.charset;
        if (localCharset == null) {
            localCharset = charset;
        }
        try {
            return Charset.forName(localCharset);
        } catch (Exception error) {
            return Charset.forName(charset);
        }
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}