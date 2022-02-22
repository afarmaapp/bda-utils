package etc.bda.utils.httpcall;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ObjectParser;

public class HttpUtils {
	
	public static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	public static final ObjectParser DEFAULT_RESPONSE_PARSER = new JsonObjectParser(JSON_FACTORY);

}
