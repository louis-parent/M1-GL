/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switchapp.p2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author louis.parent@etu.umontpellier.fr
 */
public class CompteurBean implements Serializable
{
    private final PropertyChangeSupport propertySupport;
    private int count;
    private boolean running;

    public CompteurBean()
    {
        this.propertySupport = new PropertyChangeSupport(this);
        this.setCount(0);
        this.running = false;
    }
    
    public void incr()
    {
        if(this.running)
        {
            this.setCount(this.getCount()+1);
        }
    }
    
    public void decr()
    {
        if(this.running)
        {
            this.setCount(this.getCount()-1);
        }
    }
    
    public void raz()
    {
        if(this.running)
        {
            this.setCount(0);
        }
    }
        
    public int getCount()
    {
        return this.count;
    }
    
    public void setCount(int i)
    {
        this.propertySupport.firePropertyChange("count", this.getCount(), i);
        this.count = i;
    }
    
    public void start()
    {
        this.running = true;
    }
    
    public void stop()
    {
        this.running = false;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertySupport.removePropertyChangeListener(listener);
    }
}
