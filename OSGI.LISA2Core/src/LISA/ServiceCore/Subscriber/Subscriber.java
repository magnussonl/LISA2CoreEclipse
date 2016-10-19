/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore.Subscriber;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Linus
 */
public abstract class Subscriber implements MessageListener{
    private MessageConsumer subscriber;
    protected Session session;
    private Topic topic;
    
    public void createSubscriber(String topicIn) {
        try {
            topic = session.createTopic(topicIn);
            subscriber = session.createConsumer(topic);
            subscriber.setMessageListener(this);
            
        } catch (JMSException ex) {
            Logger.getLogger(Subscriber.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
