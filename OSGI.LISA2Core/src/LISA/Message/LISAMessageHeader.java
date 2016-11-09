package LISA.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Linus
 */
public class LISAMessageHeader {
    private String ID;
    private long timeStamp;
    private String history;
    private String senderID;
    private boolean handshakeConfirmation;
    
    protected LISAMessageHeader() {
    	
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public boolean isHandshakeConfirmation() {
        return handshakeConfirmation;
    }

    public void setHandshakeConfirmation(boolean handshakeConfirmation) {
        this.handshakeConfirmation = handshakeConfirmation;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        
        return "----------Header----------\n" + "ID: " + this.getID() + "\nTimestamp: " + this.getTimeStamp() + "\nHistory: " + this.getHistory() + "\nSenderID: " + this.getSenderID() + "\nHandshake: " + this.isHandshakeConfirmation();
    }
}
