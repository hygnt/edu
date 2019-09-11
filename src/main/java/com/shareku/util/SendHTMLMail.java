package com.shareku.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 发送HTML邮件
 * 关键是使用MineMultipart类
 */
public class SendHTMLMail {
	
	public static void send(String subject,String content,String _recvAddress){
		Configure conf = new Configure("config.properties");
		Properties props = conf.getProps();
		Address sendAddress = null; 
		Address recvAddress = null;
		
		Session session = null;
		Transport transport = null;
		try{
			sendAddress = new InternetAddress(conf.getValue("mail.sendAddress"));
			recvAddress = new InternetAddress(_recvAddress);
		    //基本的邮件会话
//			session = Session.getInstance(props);
			EmailAuthenticator auth = new EmailAuthenticator(
					conf.getValue("mail.sendAddress"), conf.getValue("mail.sendMailPwd"));
		    session = Session.getDefaultInstance(props,auth);
		    
		    //构造信息体
		    MimeMessage message = new MimeMessage(session); 
		    //设置发送地址
		    message.setFrom(sendAddress);
		    //设置收件地址，对于群发，这里可多次调用设置多个地址
		    message.addRecipient(MimeMessage.RecipientType.TO, recvAddress);
		    //主题
		    message.setSubject(subject);
		    
		    /**
		     * 接下来这一段是发送HTML邮件的关键
		     */
		    Multipart mul = new MimeMultipart(); //新建一个MineMultipart对象来存放多个BodyPart对象
		    BodyPart mdp = new MimeBodyPart(); //新建一个存放邮件内容的BodyPart对象
		    mdp.setContent(content, "text/html;charset=utf-8"); //设置正文
		    mul.addBodyPart(mdp); //将含有邮件内容的BodyPart对象加入到Multipart对象中
            		    
		    //设置正文
		    message.setContent(mul); //将Multipart对象设置为message的正文
		    
		    message.saveChanges(); //保证报头域与会话内容保持一致
		    
     	    session.setDebug(true); //debug
		    
		    transport = session.getTransport("smtp");
		    
		    Transport.send(message, message.getAllRecipients());
		    //连接至STMP服务器
//		    transport.connect(conf.getValue("mail.smtp.host"), conf.getValue("mail.sendAddress"), conf.getValue("mail.sendMailPwd"));
		    //发送，如果有多封邮件要发送，这里可多次调用
//		    transport.sendMessage(message, message.getAllRecipients());
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		SendHTMLMail.send("Hello", "<h1 style=\"color:red\">这是一封闹着玩儿的邮件</h1>", "sharekuceshi1@163.com");
	}

}
