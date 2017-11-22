package com.tfs.irp.userRandom.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.SysConfigUtil;

public class EmailUtils {
	
	private static  String FROM = SysConfigUtil.getSysConfigValue("SYS_EMAIL");
	private static  String password = SysConfigUtil.getSysConfigValue("SYS_PASSWORD");
	private static  String HOST = SysConfigUtil.getSysConfigValue("SYS_HOST");
	private static  String PORT = SysConfigUtil.getSysConfigValue("SYS_PORT");
	private static 	String NICK = SysConfigUtil.getSysConfigValue("EMAIL_NICK");
	/**
	 * 发送重设密码链接的邮件
	 */
	@SuppressWarnings("finally")
	public static boolean sendResetPasswordEmail(IrpUser user,String randoms) {		
		boolean flog=true;
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		 String nick="";
		 try {
			nick=javax.mail.internet.MimeUtility.encodeText(NICK);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			message.setSubject("找回您的帐户与密码");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(nick+" <"+FROM+">"));
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setContent("要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user,randoms) +"'>点击重新设置密码</a>或者直接复制下面地址到地址栏"+GenerateLinkUtils.generateResetPwdLink(user,randoms)+"","text/html;charset=utf-8");
			// 发送邮件
			Transport.send(message);
			flog=true;
		} catch (Exception e) {
			e.printStackTrace();
			flog=false;
		}
		finally{
		return flog;
		}
	}
	
	public static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, password);
			}
			
		});
		return session;
	}
	
	/**
	 * 发送重设密码链接的邮件
	 */
	@SuppressWarnings("finally")
	public static boolean sendMeetingEmail(IrpUser user,String content,String header) {		
		boolean flog=true;
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		 String nick="";
		 try {
			nick=javax.mail.internet.MimeUtility.encodeText(NICK);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			message.setSubject(header);
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(nick+" <"+FROM+">"));
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setContent(content,"text/html;charset=utf-8");
			// 发送邮件
			Transport.send(message);
			flog=true;
		} catch (Exception e) {
			e.printStackTrace();
			flog=false;
		}
		finally{
		return flog;
		}
	}
}

