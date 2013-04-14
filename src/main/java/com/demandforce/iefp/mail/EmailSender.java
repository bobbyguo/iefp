package com.demandforce.iefp.mail;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.demandforce.iefp.model.Customer;

@Component
public class EmailSender{

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
	@Resource
	private JavaMailSender mailSender;

	@Resource
	private VelocityEngine velocityEngine;
	
	@Resource
	private String from;
	
	@Resource
	private String subject;

	public void sendPermissionRequest(final Customer customer) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Start sending permission email to customer : {}.", customer.getEmailAddress());
		}
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(customer.getEmailAddress());
				message.setFrom(from); 
				message.setSubject(subject);
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("customer", customer);
				model.put("serverRoot", "http://localhost:8001");
				String text = VelocityEngineUtils
						.mergeTemplateIntoString(velocityEngine,
								"permission-request.vm", "UTF-8", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Sending permission email to customer : {} is done.", customer.getEmailAddress());
		}
	}

}
