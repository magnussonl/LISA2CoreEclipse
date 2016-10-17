/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore.Publisher;

import LISA.Message.LISAMessage;
import LISA.Utils.LISAMarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Linus
 */
public class Publisher {
    
    private Session session = null;
    private Topic topic;
    private MessageProducer publisher;
    
    public Publisher(Session sessionIn, String topicStr) {
        try {
            this.session = sessionIn;
            topic = session.createTopic(topicStr);
            publisher = session.createProducer(topic);
        } catch (JMSException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMsg(LISAMessage msg) {
        try {
            //Creates the message and sending it
            TextMessage message = session.createTextMessage();
            String msgStr = LISAMarshaller.marshallObj(msg);
            message.setText(msgStr);
            publisher.send(message);
            
            //Output to the user
            //System.out.println("Sent message: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
