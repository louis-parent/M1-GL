package veterinaire.model.file;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface File extends Remote
{
	public List<String> getEntries() throws RemoteException;
	
	public default String getEntry(int n) throws RemoteException
	{
		return this.getEntries().get(n);
	}
	
	public default void addEntry(String entry) throws RemoteException
	{
		this.getEntries().add(entry);
	}
	
	public default void removeEntry(int n) throws RemoteException
	{
		this.getEntries().remove(n);
	}
}
