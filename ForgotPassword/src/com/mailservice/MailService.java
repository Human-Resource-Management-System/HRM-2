package com.mailservice;

public interface MailService {

	boolean sendOtpMail(String to, String otp);
}
