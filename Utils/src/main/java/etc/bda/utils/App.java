package etc.bda.utils;

import java.util.HashMap;

import etc.bda.utils.email.EmailUtil;
import etc.bda.utils.location.GeoLocationPoint;
import etc.bda.utils.location.GoogleGeoCodeResponse;
import etc.bda.utils.location.GoogleGeocode;
import etc.bda.utils.pushNotification.PushNotification;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        //sendNotification();
        //sendMail();
        //calculateDistance();
        getLocation();
    }
    
    public static void getLocation() {


    	GoogleGeocode gg = new GoogleGeocode();
    	GoogleGeoCodeResponse ggcr =  gg.geocode("24120350");
		

		System.out.println(ggcr.getFormattedAddress());

	}

    public static void calculateDistance() {

		System.out.println("GeoLocationPoint");

		GeoLocationPoint a = new GeoLocationPoint(-22.8712700, -43.2044200);
		GeoLocationPoint b = new GeoLocationPoint(-22.82809, -42.19711);

		System.out.println(a.distance(b));

	}
    
    public static void sendNotification() {
    	String jsonFirebaseFile = "afarma-popular-firebase-adminsdk-k5n6f-f5253eb9b9.json";
		String databaseURL = "https://afarma-popular.firebaseio.com";
    	String deviceToken = "eN6kA-b9404dri52pxuf5p:APA91bH0qGTdTtNK6pXpVsyqk5y-nGUVlHuWdeYx7Ry3eahCCfM6RbYfr_fjiT4ES93VzT6F9O2sg5FX536azjio_iw9UopONtXky41i5ZbnekllSBijIhug5OqP7OyzkjqjqRiSEYvG";
    	String title = "GeoLocationPoint";
    	String body = "Derick me diz se recebeu, manda um print";
    	HashMap<String, String> demaisConteudos = new HashMap<String, String>();
    	demaisConteudos.put("msg", "msg");
		
    	PushNotification pn = new PushNotification(jsonFirebaseFile,databaseURL);
    	pn.sendNotification(deviceToken, title, body, demaisConteudos);
    }
    
    public static void sendMail() {
    	boolean autenticar = true;
    	String protocolo = "smtp";
    	String servidor = "smtplw.com.br"; // do painel de controle do SMTP
    	String username = "suporte@singularidiomas.com"; // do painel de controle do SMTP
    	String senha = "senha"; // do painel de controle do SMTP
    	String porta = "Singular080290!"; // do painel de controle do SMTP
    	
    	String emailDestinatario = "mariotcosta@yahoo.com.br";
    	String nomeDestinatario = "Mario Costa";
    	String emailRemetente = "suporte@singularidiomas.com";
    	String nomeRemetente = "Singular Idiomas";
    	String assunto = "Cadastro de senha";
    	String body = "Corpo da mensagem";

    	
    	
    	EmailUtil email = new EmailUtil(protocolo, servidor, porta, autenticar);
    	email.setUsername(username);
    	email.setSenha(senha);
    	email.sendMail(emailDestinatario, nomeDestinatario, emailRemetente, nomeRemetente, assunto, body);
    }
}
