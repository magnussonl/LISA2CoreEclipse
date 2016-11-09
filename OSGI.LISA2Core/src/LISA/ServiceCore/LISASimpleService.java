/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore;

import javax.jms.Connection;

import LISA.EndPointCore.LISAEndPointCore;

/**
 *
 * @author jpdsf
 */
public abstract class LISASimpleService extends LISAServiceCore{
    
    public LISASimpleService(LISAEndPointCore epIn, Connection connection, String topicIn) {
        super(epIn, connection, topicIn);
    }
    

    public boolean action() {
        return true;
    }


    public void end() {
        
    }      
}
