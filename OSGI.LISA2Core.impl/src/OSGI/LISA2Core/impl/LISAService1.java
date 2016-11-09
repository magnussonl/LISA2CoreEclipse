package OSGI.LISA2Core.impl;
import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.KeyPairValue;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAServiceCore;
import java.util.Date;
import java.util.LinkedList;
import javax.jms.Connection;
import javax.jms.Message;

/**
 *
 * @author Linus
 */
public class LISAService1 extends LISAServiceCore {

    public LISAService1(LISAEndPointCore epIn, Connection connection, String topicStr, String topicStr2) {
        super(epIn, connection, topicStr, topicStr2);

    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void onStart() {
    	setExecuteFrequency(500);
    }

    @Override
    public boolean action() {

        LISAMessage msgToSend = new LISAMessage();

        msgToSend.getMessageBody().setType("testMess");
        
        LinkedList<KeyPairValue> dataList = new LinkedList<KeyPairValue>();

        dataList.add(new KeyPairValue("time", " " + (new Date()).getTime()));
        dataList.add(new KeyPairValue("a", "test1"));
        dataList.add(new KeyPairValue("b", "test2"));

        msgToSend.getMessageBody().setKeyPairValues(dataList);
        

        publisher.sendMsg(msgToSend);


        return false;
    }

    @Override
    public void end() {

    }

}