/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LISA.ServiceCore;

/**
 *
 * @author Linus
 */
public interface LISAServiceLlifeCycle {
    
    public abstract void onStart();
    
    public abstract boolean action();

    public abstract void end();
}
