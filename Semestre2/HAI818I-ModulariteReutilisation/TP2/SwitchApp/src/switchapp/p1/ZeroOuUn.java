/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switchapp.p1;

import javax.swing.JLabel;

/**
 *
 * @author louis.parent@etu.umontpellier.fr
 */
public class ZeroOuUn extends JLabel
{

    public ZeroOuUn()
    {
        this.setText("Zero");
    }
    
    public void changer()
    {
        if(this.getText().equals("Zero"))
        {
            this.setText("Un");
        }
        else
        {
            this.setText("Zero");
        }
    }
}
