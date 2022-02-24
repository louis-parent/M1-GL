package aj.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

aspect BoundPoint
{
	private PropertyChangeSupport Point.support = new PropertyChangeSupport(this);

	public void Point.addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}

	public void Point.addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{

		support.addPropertyChangeListener(propertyName, listener);
	}

	public void Point.removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(propertyName, listener);
	}

	public void Point.removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}

	public void Point.hasListeners(String propertyName)
	{
		support.hasListeners(propertyName);
	}

	declare parents: Point implements Serializable;

	pointcut setter(Point p): call(* Point.set*(*)) && target(p);

	void around(Point p): setter(p)
	{
		String propertyName = thisJoinPointStaticPart.getSignature().getName().substring("set".length());
		int oldX = p.getX();
		int oldY = p.getY();
		proceed(p);
		if(propertyName.equals("X"))
		{
			firePropertyChange(p, propertyName, oldX, p.getX());
		}
		else
		{
			firePropertyChange(p, propertyName, oldY, p.getY());
		}
	}

	void firePropertyChange(Point p, String property, double oldval, double newval)
	{
		p.support.firePropertyChange(property, new Double(oldval), new Double(newval));
	}
}