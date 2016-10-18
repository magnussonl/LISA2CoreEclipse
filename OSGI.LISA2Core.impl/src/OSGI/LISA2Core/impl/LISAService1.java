package OSGI.LISA2Core.impl;
import LISA.Message.KeyPairValue;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAServiceCore;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Message;

/**
 *
 * @author Linus
 */
public class LISAService1 extends LISAServiceCore {

    public LISAService1(Connection connection, String topicStr, String topicStr2) {
        super(connection, topicStr, topicStr2);

    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public boolean action() {

        LISAMessage msgToSend = new LISAMessage();

        LinkedList<KeyPairValue> dataList = new LinkedList<KeyPairValue>();

        dataList.add(new KeyPairValue("time", " " + (new Date()).getTime()));
        dataList.add(new KeyPairValue("a", "test1"));
        dataList.add(new KeyPairValue("b", "test2"));

        msgToSend.getMessageBody().setKeyPairValues(dataList);
        

        publisher.sendMsg(msgToSend);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LISAService1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public void end() {

    }

}