package veterinaire.model.animal.species;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Species extends Remote
{
	public abstract String getName() throws RemoteException;
	public abstract int getLifeExpectancy() throws RemoteException;
	
	public default String getResume() throws RemoteException
	{
		return this.getName() + " (" + this.getLifeExpectancy() + " years)";
	}
	
	public abstract Species duplicate() throws RemoteException;
}
