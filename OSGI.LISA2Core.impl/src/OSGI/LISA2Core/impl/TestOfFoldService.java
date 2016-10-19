package OSGI.LISA2Core.impl;

import java.util.List;

import javax.jms.Connection;

import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAFoldService;

public class TestOfFoldService extends LISAFoldService {

	public TestOfFoldService(Connection connection, String topicInSub, String topicInPub) {
		super(connection, topicInSub, topicInPub);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onStart() {
		setMaxSize(200);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LISAMessage fold(List<LISAMessage> dataList) {
		// TODO Auto-generated method stub
		return null;
	}




}