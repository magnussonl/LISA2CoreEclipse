package LISA.ServiceCore;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.LISAMessage;
import LISA.Utils.LISAMarshaller;

public abstract class LISAMapService extends LISAServiceCore {

	

	public LISAMapService(LISAEndPointCore epIn, Connection connection, String topicIn) {
		super(epIn, connection, topicIn);
		// TODO Auto-generated constructor stub
	}
	public LISAMapService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub) {
		super(epIn, connection, topicInSub, topicInPub);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean action() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			String text;
			try {
				text = textMessage.getText();
				LISAMessage lisaMsg = (LISAMessage) LISAMarshaller.unMarshall(LISAMessage.class, text);
				LISAMessage msgToSend = map(lisaMsg);
				publisher.sendMsg(msgToSend);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public abstract LISAMessage map(LISAMessage lisaMsg);

}
