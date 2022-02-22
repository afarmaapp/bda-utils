package etc.bda.utils.pushNotification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;

public class PushNotification {
	
	private String jsonFirebaseFile;
	private String databaseURL;
	
	public PushNotification(String jsonFirebaseFile, String databaseURL) {
		this.jsonFirebaseFile = jsonFirebaseFile;
		this.databaseURL = databaseURL;
	}
	
	public boolean sendNotification(String deviceToken, String title, String body, Map<String, String> map) {
		System.out.println("Iniciando envio de PushNotification: Device [" + deviceToken + "]");
		if (FirebaseApp.getApps().isEmpty()) {
			try {
				ClassLoader loader = PushNotification.class.getClassLoader();
				InputStream serviceAccount = loader.getResourceAsStream(jsonFirebaseFile);
				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.setDatabaseUrl(databaseURL).build();
				FirebaseApp.initializeApp(options);

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		try {
			Builder builder = Message.builder()
					.setNotification(new Notification(title, body))
					.setToken(deviceToken);
			if (map != null)
				builder.putAllData(map);
			Message message = builder.build();
			// Send a message to the device corresponding to the provided registration token.
			System.out.print("Enviando...");
			FirebaseMessaging.getInstance().send(message);
			System.out.println("OK");
			
		} catch (FirebaseMessagingException e) {

			System.out.println("NOT OK");
			e.printStackTrace();
			return true;
		}

		return true;
	}
	
	public boolean sendGroupNotification(String deviceToken, String title, String body, Map<String, String> map, NotificationGroup group) {
		System.out.println("Iniciando envio de PushNotification: Device [" + deviceToken + "]");
		if (FirebaseApp.getApps().isEmpty()) {
			try {
				ClassLoader loader = PushNotification.class.getClassLoader();
				InputStream serviceAccount = loader.getResourceAsStream(jsonFirebaseFile);
				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.setDatabaseUrl(databaseURL).build();
				FirebaseApp.initializeApp(options);

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		try {
			Builder builder = Message.builder()
					.setNotification(new Notification(title, body));
			switch (group) {
			case ALL:
//				builder.setAndroidConfig(new AndroidConfig());
				builder.setApnsConfig(null);
				builder.setWebpushConfig(null);
				break;
			case ANDROID:
				builder.setAndroidConfig(null);
				break;

			case IOS:
				builder.setApnsConfig(null);
				break;

			case WEB:
				builder.setWebpushConfig(null);
				break;

			}
					
			if (map != null)
				builder.putAllData(map);
			Message message = builder.build();
			// Send a message to the device corresponding to the provided registration token.
			System.out.print("Enviando...");
			FirebaseMessaging.getInstance().send(message);
			System.out.println("OK");
			
		} catch (FirebaseMessagingException e) {

			System.out.println("NOT OK");
			e.printStackTrace();
			return true;
		}

		return true;
	}	
}
