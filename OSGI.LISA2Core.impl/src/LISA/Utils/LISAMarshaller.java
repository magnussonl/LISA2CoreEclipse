/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.Utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

/**
 *
 * @author Linus
 */
public class LISAMarshaller {
    
    /**
     * Method to marshall java object to string
     * @param obj XmlRootElement object input
     * @return string with the content of the input Object
     */
    
    public static String marshallObj(Object obj) {
        
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        
        try {
            Class c = obj.getClass();

            JAXBContext jc = JAXBContext.newInstance(c);

            Marshaller marshaller = jc.createMarshaller();

            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            marshaller.marshal(obj, sw);
            
            return sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(LISAMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Method to unmarshall string into given object
     * @param c Type of class the object is
     * @param str The string to be converted into the given object
     * @return Returns a object of the given class
     */
    
    public static Object unMarshall(Class c, String str) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        try {
            JAXBContext jc = JAXBContext.newInstance(c);
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
            
            StreamSource json = new StreamSource(new StringReader(str));
            
            Object obj = unmarshaller.unmarshal(json, c).getValue();
            return obj;
            
        } catch (JAXBException ex) {
            Logger.getLogger(LISAMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
