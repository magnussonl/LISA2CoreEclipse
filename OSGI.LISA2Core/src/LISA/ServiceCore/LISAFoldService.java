package LISA.ServiceCore;

import java.util.LinkedList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import LISA.EndPointCore.LISAEndPointCore;
import LISA.Message.LISAMessage;
import LISA.Utils.LISAMarshaller;

public abstract class LISAFoldService extends LISAServiceCore {

	private int maxSize = 10;
	private List<LISAMessage> dataList = new LinkedList<LISAMessage>();

	public LISAFoldService(LISAEndPointCore epIn, Connection connection, String topicIn) {
		super(epIn, connection, topicIn);
		// TODO Auto-generated constructor stub
	}

	public LISAFoldService(LISAEndPointCore epIn, Connection connection, String topicInSub, String topicInPub) {
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
				
				addMsgToList(lisaMsg);
				

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public abstract void fold();
	
	public abstract void addMsgToList(LISAMessage msgIn);

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public List<LISAMessage> getDataList() {
		return dataList;
	}

	public void setDataList(List<LISAMessage> dataList) {
		this.dataList = dataList;
	}

}
