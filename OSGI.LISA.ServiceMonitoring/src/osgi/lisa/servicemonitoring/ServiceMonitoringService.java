package osgi.lisa.servicemonitoring;

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

public class ServiceMonitoringService extends LISAServiceCore {

	public static Map<String, LinkedList<KeyPairValue>> mainServices;

	public ServiceMonitoringService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub,
			Map<String, LinkedList<KeyPairValue>> mainServicesIn) {
		super(epIn, connection, topicInSub, topicInPub);
		ServiceMonitoringService.mainServices = mainServicesIn;
		setExecuteFrequency(1000);
		setServiceDescription("Monitoring the all services on the buss!");
	}

	@Override
	public void onStart() {
		

	}

	@Override
	public boolean action() {
		//System.out.println(mainServices);
		return false;
	}

	@Override
	public void end() {

	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				String text;
				text = textMessage.getText();
				LISAMessage msg = (LISAMessage) LISAMarshaller.unMarshall(LISAMessage.class, text);
				if (msg.getMessageBody().getType().equals("ServiceMonitoringRegister")) {

					LinkedList<KeyPairValue> keyPairs = msg.getMessageBody().getKeyPairValues();
					String epName = null;
					String serviceName = null;
					String serviceDescr = null;
					for (int i = 0; i < keyPairs.size(); i++) {
						if (keyPairs.get(i).getType().equals("EndPoint")) {
							epName = keyPairs.get(i).getData();
						} else if (keyPairs.get(i).getType().equals("Service")) {
							serviceName = keyPairs.get(i).getData();
						} else if (keyPairs.get(i).getType().equals("ServiceDescription")) {
							serviceDescr = keyPairs.get(i).getData();
						}
					}

					addService(epName, serviceName, serviceDescr);

				} else if (msg.getMessageBody().getType().equals("ServiceMonitoringUnRegister")) {
					System.out.println("UnRegister message: " + msg.getMessageHeader().getID());
				}

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void addService(String epName, String serviceName, String serviceDescr) {
		if (!epName.equals(null) && !serviceName.equals(null) && !serviceDescr.equals(null)) {
			if (mainServices.containsKey(epName)) {
				mainServices.get(epName).add(new KeyPairValue(serviceName, serviceDescr));
			} else {
				LinkedList<KeyPairValue> list = new LinkedList<KeyPairValue>();
				list.add(new KeyPairValue(serviceName, serviceDescr));
				mainServices.put(epName, list);
			}
		} else {
			throw new NullPointerException();
		}

	}

}
