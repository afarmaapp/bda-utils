package etc.bda.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_HEURE_JSON_SERIALIZE_2);
		String dateString = dateFormat.format(value);
		jgen.writeString(dateString);
	}

	public static final String FORMAT_HEURE_JSON = new String("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	public static final String FORMAT_HEURE_JSON_SERIALIZE = new String("EEE yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	public static final String FORMAT_HEURE_JSON_SERIALIZE_2 = new String("EEE, dd MMM yyyy HH:mm:ss zzz");
	public static final String FORMAT_HEURE_JSON_SERIALIZE_3 = new String("yyyy-MM-dd HH:mm:ss");
}
