/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package increment;

import javax.ejb.Remote;

/**
 *
 * @author louis.parent@etu.umontpellier.fr
 */
@Remote
public interface CounterRemote
{
    void start();
    void stop();
    void increment();
    void decrement();
    int getCount();
    void reset();
}
