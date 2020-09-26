package com.member.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		String id = "itwillbs_yakbbal";
		String pass = "xlavmfhwprxm!123";
		return new PasswordAuthentication(id, pass);
	}
}
