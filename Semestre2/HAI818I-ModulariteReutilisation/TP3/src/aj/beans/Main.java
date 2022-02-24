package aj.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main implements PropertyChangeListener
{

	static final String fileName = "test.tmp";

	public void propertyChange(PropertyChangeEvent e)
	{
		System.out.println("Property " + e.getPropertyName() + " changed from " + e.getOldValue() + " to " + e.getNewValue());
	}

	public static void main(String[] args)
	{
		Point p1 = new Point();
		p1.addPropertyChangeListener(new Main());
		System.out.println("p1 =" + p1);
		p1.setRectangular(5, 2);
		System.out.println("p1 =" + p1);
		p1.setX(6);
		p1.setY(3);
		System.out.println("p1 =" + p1);
		p1.offset(6, 4);
		System.out.println("p1 =" + p1);
	}
}