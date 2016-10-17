/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EndPointElasticSearch;

import LISA.EndPointCore.LISAEndPointCore;
import javax.jms.Connection;

/**
 *
 * @author Linus
 */
public class ElasticSearchEndPoint extends LISAEndPointCore {
    public static void main(String[] args) {
        
        Connection connection = createConnection();
        
        ElasticSearchService s1 = new ElasticSearchService(connection, "test");
        
        services.put("ElasticSearchService", s1);
        
        endpointThread.start();
        
    }
}
