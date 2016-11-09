package osgi.lisa.eventmonitoring;

import java.util.LinkedList;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.KeyPairValue;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAServiceCore;
import LISA.Utils.LISAMarshaller;

public class EventMonitoringService extends LISAServiceCore {
	
	public static LinkedList<String> mainEvents;

	public EventMonitoringService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub,
			LinkedList<String> mainEventsIn) {
		super(epIn, connection, topicInSub, topicInPub);
		EventMonitoringService.mainEvents = mainEventsIn;
		setExecuteFrequency(1000);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean action() {
		//System.out.println(mainEvents);
		return false;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				String text;
				text = textMessage.getText();
				LISAMessage msg = (LISAMessage) LISAMarshaller.unMarshall(LISAMessage.class, text);

				String eventName = msg.getMessageBody().getType();

				addEvent(eventName);

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void addEvent(String eventName) {
		if(!mainEvents.contains(eventName)) {
			mainEvents.add(eventName);
		}

	}

}
