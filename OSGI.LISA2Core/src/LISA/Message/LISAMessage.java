/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.Message;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Linus
 */
@XmlRootElement(name = "lisaMessage")
public class LISAMessage {

    private LISAMessageHeader messageHeader = new LISAMessageHeader();
    private LISAMessageBody messageBody = new LISAMessageBody();

    public LISAMessage() {
        try {

            messageHeader.setSenderID(InetAddress.getLocalHost().getHostAddress());
            Date date = new Date();
            messageHeader.setTimeStamp(date.getTime());
            messageHeader.setID(date.getTime() + "+" + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(LISAMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public LISAMessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(LISAMessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public LISAMessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(LISAMessageBody messageBody) {
        this.messageBody = messageBody;
    }


    //</editor-fold>
    
    @Override
    public String toString() {
        return this.getMessageHeader().toString() + "\n" + this.getMessageBody().toString();
    }
    
}
