package etc.bda.utils.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	private Properties props;
	private String username;
	private String senha;
	
	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EmailUtil(String protocolo,String servidor, String porta, boolean autenticar) {
		props = new Properties();
		props.put("mail.transport.protocol", protocolo);
		props.put("mail.smtp.host", servidor);
		props.put("mail.smtp.auth", autenticar);
		props.put("mail.smtp.port", porta);
	}
	
	public void sendMail(String emailDestinatario, String nomeDestinatario, String emailRemetente, String nomeRemetente, String assunto, String body) {
		

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);

		try {
			InternetAddress iaFrom = new InternetAddress(emailRemetente, nomeRemetente);
			InternetAddress[] iaTo = new InternetAddress[1];
			InternetAddress[] iaReplyTo = new InternetAddress[1];

			iaReplyTo[0] = new InternetAddress(emailDestinatario, nomeDestinatario);
			iaTo[0] = new InternetAddress(emailDestinatario, nomeDestinatario);

			MimeMessage msg = new MimeMessage(session);

			if (iaReplyTo != null)
				msg.setReplyTo(iaReplyTo);
			if (iaFrom != null)
				msg.setFrom(iaFrom);
			if (iaTo.length > 0)
				msg.setRecipients(Message.RecipientType.TO, iaTo);
			msg.setSubject(assunto);
			msg.setSentDate(new Date());

			msg.setContent(body, "text/html");

			Transport tr = session.getTransport(props.getProperty("protocolo"));
			tr.connect(props.getProperty("servidor"), username, senha);

			msg.saveChanges();

			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
