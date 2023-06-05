package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mailservice.MailService;
import com.models.MailOtpModel;

@Controller
public class MailController {

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String ForgotPage(Model model) {
		return "forgot";
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<String> handleEmailAjaxRequest(Model model, MailOtpModel mail) {

		System.out.println(mail.getEmail().trim());

		String email = mail.getEmail().trim();

		/*
		 * check whether the email is registered or not
		 * 
		 * status -- pending
		 * 
		 */

		int otp = (int) (Math.random() * 9000) + 1000;

		System.out.println(otp);
		boolean flag = mailService.sendOtpMail(email, String.valueOf(otp));

		if (flag)
			return ResponseEntity.ok("Email Successfully sent!");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");

	}

	@RequestMapping(value = "/otpvalidate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<String> handleOTPAjaxRequest(Model mod, MailOtpModel mail) {
		System.out.println("In here");
		return ResponseEntity.ok("otp verification ");
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<String> changePasswordAjaxRequest(Model mod) {
		System.out.println("In here");
		return ResponseEntity.ok("Password change");
	}

}
