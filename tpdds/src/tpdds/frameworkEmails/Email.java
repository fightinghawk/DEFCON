package tpdds.frameworkEmails;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Email {

	/**
	 * FRAMEWORK ENVIO DE MAILS
	 */
	public static void enviar(String emailEnvio, String password, String emailRecibo, String asunto, String mensaje) {
		// PROPIEDADES CONEXION EMAIL
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.yandex.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		// AUTENTIFICACION EMAIL
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailEnvio, password);
			}
		};

		// SESION Y ENVIO EMAIL
		Session session = Session.getDefaultInstance(props, auth);
		FEmail.enviar(session, emailRecibo, emailEnvio, asunto, mensaje);

	}

}