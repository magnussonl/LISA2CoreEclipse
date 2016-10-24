/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore;

import LISA.ServiceCore.Publisher.Publisher;
import LISA.ServiceCore.Subscriber.Subscriber;
import com.google.common.collect.ListMultimap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 *
 * @author Linus
 */
public abstract class LISAServiceCore extends Subscriber implements LISAServiceLlifeCycle {

    public enum ServiceState {
        SETUP, STARTUP, ACTION, END, WAITING
    }

    protected Publisher publisher = null;
    protected Connection connection = null;
    protected String topicStrSub, topicStrPub;
    protected ServiceState state;
    protected String serviceID;
    public ListMultimap<String, String> dataMapping;
    protected long executeFrequency = 0;
    protected long executionTimestamp = 0;

    /**
     * Use this constructor to run the subscriber and the publisher on the same topic
     *
     * @param connection Connection to the activemq buss
     * @param topicIn Name of the topic you want the subscriber and publisher to
     * have
     */
    public LISAServiceCore(Connection connection, String topicIn) {
        this.connection = connection;
        this.topicStrSub = topicIn;
        this.topicStrPub = topicIn;
        this.state = ServiceState.SETUP;

    }
    /**
     * Use this constructor to run the subscriber and the publisher on different topics
     * 
     * @param connection Connection to the activemq buss from LISAEndPointCore
     * @param topicInSub Name of the topic you want the subscriber to subscribe on
     * @param topicInPub Name of the topic you want the publisher to publish on
     */
    public LISAServiceCore(Connection connection, String topicInSub, String topicInPub) {
        this.connection = connection;
        this.topicStrSub = topicInSub;
        this.topicStrPub = topicInPub;
        this.state = ServiceState.SETUP;
    }

    public void serviceSetup() {
        createSession();
        createPublisher(topicStrPub);
        createSubscriber(topicStrSub);
    }

    private void createSession() {
        try {
            if (this.connection != null) {
                Session sessionNew = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                setSession(sessionNew);
            }
        } catch (JMSException ex) {
            Logger.getLogger(LISAServiceCore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createPublisher(String topicIn) {
        this.publisher = new Publisher(this.session, topicIn);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    private void setSession(Session session) {
        this.session = session;
    }

    public ServiceState getState() {
        return this.state;
    }

    public void setState(ServiceState state) {
        this.state = state;
    }

    public void setDataMap(ListMultimap map) {
        this.dataMapping = map;
    }

    public String getServiceID() {
        return this.serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }
	public long getExecuteFrequency() {
		return executeFrequency;
	}
	
	/**
     * Setting the execution frequency of each service, the minimum time between execution
     * 
     * @param executeFrequency Time in milliseconds. Default = 0
     */
	public void setExecuteFrequency(long executeFrequency) {
		this.executeFrequency = executeFrequency;
	}
	public long getExecutionTimestamp() {
		return executionTimestamp;
	}
	public void setExecutionTimestamp(long executionTimestamp) {
		this.executionTimestamp = executionTimestamp;
	}
    
    //</editor-fold>
}
