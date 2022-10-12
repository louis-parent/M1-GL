/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switchapp.p2;

import javax.swing.JLabel;

/**
 *
 * @author louis.parent@etu.umontpellier.fr
 */
public class AfficheurBean extends JLabel
{
    public AfficheurBean()
    {
        this.setText(Integer.toString(0));
        this.setAlignmentX(CENTER_ALIGNMENT);
    }
    
    public void display(int i)
    {
        this.setText(Integer.toString(i));
    }
}
