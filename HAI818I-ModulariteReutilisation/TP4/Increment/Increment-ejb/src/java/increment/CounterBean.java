/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package increment;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author louis.parent@etu.umontpellier.fr
 */
@Stateful
@LocalBean
public class CounterBean implements CounterRemote
{    
    private int count;
    private boolean isCounting;

    public CounterBean()
    {
        this.count = 0;
        this.isCounting = false;
    }
    
    @Override
    public void start()
    {
        this.isCounting = true;
    }

    @Override
    public void stop()
    {
        this.isCounting = false;
    }

    @Override
    public void increment()
    {
        if(this.isCounting)
        {
            this.count++;
        }
    }

    @Override
    public void decrement()
    {
        if(this.isCounting)
        {
            this.count--;
        }
    }
    
    @Override
    public int getCount()
    {
        return this.count;
    }
    
    @Override
    public void reset()
    {
        if(this.isCounting)
        {
            this.count = 0;
        }
    }
}
