package OSGI.LISA2Core.impl;
import LISA.EndPointCore.LISAEndPointCore;
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


    public LISAService2(LISAEndPointCore epIn, Connection connection, String topicStr, String topicStr2) {
        super(epIn, connection, topicStr, topicStr2);

    }

    @Override
    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                LISAMessage msg = (LISAMessage)LISAMarshaller.unMarshall(LISAMessage.class, text);
                System.out.println(msg);
                LinkedList<KeyPairValue> list = msg.getMessageBody().getKeyPairValues();
                
//                int i = 0;
//                for (KeyPairValue keyPairValue : list) {
//                    System.out.println(keyPairValue);
//                    System.out.println(i);
//                    i++;
//                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(LISAService1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void onStart() {
        
    }

    @Override
    public boolean action() {

        return false;
    }

    @Override
    public void end() {
        System.out.println("End of " + this.getClass());
    }

    

}