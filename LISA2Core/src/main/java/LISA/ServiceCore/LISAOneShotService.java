/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore;

import javax.jms.Connection;

/**
 *
 * @author jpdsf
 */
public abstract class LISAOneShotService extends LISAServiceCore{

    public LISAOneShotService(Connection connection, String topicIn) {
        super(connection, topicIn);
    }

    public abstract void oneShotAction();
    
    public boolean action() {
        oneShotAction();
        return true;
    }


    public void end() {
        
    }  
    
}
