package veterinaire.model.practice.alert;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PracticeAlertListenable extends Remote
{
	public abstract Collection<PracticeAlertListener> getListeners() throws RemoteException;
	
	public default void addPracticeAlertListener(PracticeAlertListener listener) throws RemoteException
	{
		this.getListeners().add(listener);
	}
	
	public default void removePracticeAlertListener(PracticeAlertListener listener) throws RemoteException
	{
		this.getListeners().remove(listener);
	}
	
	public default void notifyOvertake(int threshold) throws RemoteException
	{
		for (PracticeAlertListener listener : this.getListeners())
		{
			listener.overtake(threshold);
		}
	}
	
	public default void notifyRecede(int threshold) throws RemoteException
	{
		for (PracticeAlertListener listener : this.getListeners())
		{
			listener.recede(threshold);
		}
	}
}
