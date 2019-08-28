package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	@Autowired
	private JavaMailSender javaMailSender;



	void sendEmail(){
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setFrom("fabrodev@gmail.com");
		msg.setTo("fmiredin7@gmail.com", "fabhandy15@gmail.com");
		msg.setSubject("Test Spring email sender");
		msg.setText("Hello 2 @Fabrice, you just send an Emnail to say you persevere in your road");

		javaMailSender.send(msg);


	}

	void sendEmailWithAttach()  throws MessagingException, IOException{
		MimeMessage msg = javaMailSender.createMimeMessage();


		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("1@gmail.com");
		helper.setSubject("ceci est un test de mail");
		helper.setText("<h1> Check PJ for Photo please. </h1>",true);



		helper.addAttachment("dddexplained.png", new File("/Users/work/Dev/Workspaces/JavaStuff/demo/src/main/resources/dddexplained.png"));

		javaMailSender.send(msg);


	}






	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}


	public void run(String... args) throws IOException, MessagingException {
		System.out.println("Sending Email...");


		try {

//			sendEmail();
			sendEmailWithAttach();

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

}




