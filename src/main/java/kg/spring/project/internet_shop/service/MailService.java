package kg.spring.project.internet_shop.service;

public interface MailService {

  void sendEmail(String to, String subject, String text);

}
