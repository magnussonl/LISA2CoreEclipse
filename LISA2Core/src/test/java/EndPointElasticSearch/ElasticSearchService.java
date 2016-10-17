/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EndPointElasticSearch;

import LISA.ServiceCore.LISAServiceCore;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 *
 * @author Linus
 */
public class ElasticSearchService extends LISAServiceCore {

    Client client;
    Settings settings;

    public ElasticSearchService(Connection connection, String topicIn) {
        super(connection, topicIn);
    }


    public void onMessage(Message message) {
        System.out.println("Got something");

        if (message instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println(text);
//                IndexResponse response = client.prepareIndex("lisa", "buss").setSource(text).get();
                
                
                IndexResponse response = client.prepareIndex("lisa", "buss")
                        .setSource(text)
                        .get();
                System.out.println(response.getIndex() + " " + response.getType() + " " + response.getId());

            } catch (JMSException ex) {
                Logger.getLogger(ElasticSearchService.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

    }


    public void onStart() {
        try {
            //Creating the connection to the elastic search
            settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ElasticSearchService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean action() {
        return false;
    }


    public void end() {

    }

}
