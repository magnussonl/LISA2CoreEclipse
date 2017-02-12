package LISA.ServiceCore;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.LISAMessage;
import LISA.Utils.LISAMarshaller;

public abstract class LISAFillService extends LISAServiceCore {

	public LISAFillService(LISAEndPointCore epIn, Connection connection, String topicIn) {
		super(epIn, connection, topicIn);
		// TODO Auto-generated constructor stub
	}

	public LISAFillService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub) {
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
				fill(lisaMsg);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println("catch");
				e.printStackTrace();
			}

		}

	}

	public abstract void fill(LISAMessage lisaMsg);

}
