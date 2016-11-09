package LISA.Message;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Linus
 */
public class LISAMessageBody {

    private LinkedList<KeyPairValue> keyPairValues = new LinkedList<KeyPairValue>();
    private String type;

    protected LISAMessageBody() {
		
	}

	//<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public LinkedList<KeyPairValue> getKeyPairValues() {
        return keyPairValues;
    }

    public void setKeyPairValues(LinkedList<KeyPairValue> keyPairValues) {
        this.keyPairValues = keyPairValues;
    }

    public void addKeyPairValues(LinkedList<KeyPairValue> keyPairValues) {
        this.keyPairValues.addAll(keyPairValues);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //</editor-fold>
    
    @Override
    public String toString() {
        
        String keysStr = new String();
        
        for (KeyPairValue keyPairValue : keyPairValues) {
            keysStr = keysStr + "Type: " + keyPairValue.getType() + " Data: " + keyPairValue.getData() + "\n";
        }
        
        return "----------Body----------\n" + "BodyType: " + this.getType() + "\n" + keysStr;
    }
}
