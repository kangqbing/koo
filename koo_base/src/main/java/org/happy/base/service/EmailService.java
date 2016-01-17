package org.happy.base.service;

public interface EmailService {

	boolean send(String to, String subject, String html);

}
