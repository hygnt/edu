package com.shareku.util;

import javax.mail.PasswordAuthentication;
public class EmailAuthenticator extends javax.mail.Authenticator{
    private String strUser;
    private String strPwd;
    public EmailAuthenticator(String user, String password) {
      this.strUser = user;
      this.strPwd = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(strUser, strPwd);
    }
}
