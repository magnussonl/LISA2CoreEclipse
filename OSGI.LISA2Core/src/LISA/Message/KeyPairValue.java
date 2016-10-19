/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.Message;

/**
 *
 * @author Linus
 */
public class KeyPairValue {

    private String type;
    private String data;

    public KeyPairValue() {
    }

    public KeyPairValue(String type, String data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return "KeyPairValue{" + "type=" + type + ", data=" + data + '}';
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    //</editor-fold>        
}
