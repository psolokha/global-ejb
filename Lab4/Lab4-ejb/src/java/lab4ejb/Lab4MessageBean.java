/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4ejb;

import java.util.Date;
import java.util.Properties;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Student1
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/HRTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/HRTopic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/HRTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class Lab4MessageBean implements MessageListener {
    
    public Lab4MessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        sendEmail(message);
    }
    
    private void sendEmail(Message message) {
      Properties props = new Properties();
              props.put("mail.smtp.host", "localhost");
              Session session = Session.getInstance(props);
              
              try {
                  System.out.println("onMessage() - " + message + " JMSMessageType: " + message.getJMSType());
                  String from = message.getStringProperty("from");
                  String to = message.getStringProperty("to");
                  String subject = message.getStringProperty("subject");
                  String content = message.getStringProperty("content");
                  
                  javax.mail.Message msg = new MimeMessage(session);
                  msg.setFrom(new InternetAddress(from));
                  InternetAddress[] address = {new InternetAddress(to)};
                  msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
                  msg.setSubject(subject);
                  msg.setSentDate(new Date());
                  msg.setContent(content, "text/html");
                  msg.setHeader("encoding","Windows-1251");
                 
                  System.out.println("SendMailMessageBean (MDB): Sending Mail Message...");
                  Transport.send(msg);
                  System.out.println("SendMailMessageBean (MDB): Mail Message Sent");
                 
              }
              catch (Throwable ex) {
                  ex.printStackTrace();
              }
    }
}
