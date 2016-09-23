package tpdds.frameworkEmails;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FEmail {

	/**
	 * FRAMEWORK E-MAILS
	 */
	public static void enviar(Session session, String toEmail, String ccEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(ccEmail, "TERMINAL POIS"));

			msg.setReplyTo(InternetAddress.parse(ccEmail, false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
			Transport.send(msg);
			
			//CAMBIAR POR UN AVISO DE VENTANA
			System.out.println("EMAIL ENVIADO EXITOSAMENTE!!");
		} catch (Exception e) {
			//CAMBIAR POR UN AVISO DE VENTANA
			System.out.println("ERROR AL TRATAR DE ENVIAR MAIL");
		}
	}
}