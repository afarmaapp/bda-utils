package etc.bda.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSonUtil {
	/**
	 * Converter json para um bean JAVA
	 * 
	 * @param jsonInString
	 * @return bean
	 */
	public static <T> T convertJsonToJava(String jsonInString, Class<T> clazz) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// JSON to Java object
		T bean = gson.fromJson(jsonInString, clazz);

		return bean;
	}

	/**
	 * Converter um javabean para JSON
	 * 
	 * @param bean
	 * @return json
	 */
	public static String convertJavaToJson(Object bean) {
		Gson gson = new Gson();

		// Java object to JSON
		String jsonInString = gson.toJson(bean);

		return jsonInString;
	}

}
