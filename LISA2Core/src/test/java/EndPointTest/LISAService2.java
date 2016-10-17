package EndPointTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import LISA.Message.KeyPairValue;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAServiceCore;
import LISA.Utils.LISAMarshaller;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 *
 * @author Linus
 */
public class LISAService2 extends LISAServiceCore {


    public LISAService2(Connection connection, String topicStr, String topicStr2) {
        super(connection, topicStr, topicStr2);

    }


    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                LISAMessage msg = (LISAMessage)LISAMarshaller.unMarshall(LISAMessage.class, text);
                
                LinkedList<KeyPairValue> list = msg.getMessageBody().getKeyPairValues();
                int i = 0;
                for (KeyPairValue keyPairValue : list) {
                    System.out.println(keyPairValue);
                    System.out.println(i);
                    i++;
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(LISAService1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void onStart() {
        
    }


    public boolean action() {

        return false;
    }


    public void end() {
        System.out.println("End of " + this.getClass());
    }

    

}
