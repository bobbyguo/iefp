package com.demandforce.iefp.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demandforce.iefp.model.Customer;

public class EmailSenderTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("email-context.xml");
		EmailSender es = context.getBean("emailSender", EmailSender.class);
		Customer customer = new Customer();
		customer.setFirstName("Bobby");
		customer.setLastName("Guo");
		customer.setEmailAddress("cxondemand@sina.com");
		customer.setBusinessName("Dental");
		customer.setRequestId("111");
		es.sendPermissionRequest(customer);
	}
}
