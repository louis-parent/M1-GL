package veterinaire.model.file;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FollowupFile extends UnicastRemoteObject implements File
{
	private static final long serialVersionUID = -5710770141758732920L;

	private List<String> entries;
	
	public FollowupFile() throws RemoteException
	{
		super();
		this.entries = new ArrayList<String>();
	}

	@Override
	public List<String> getEntries() throws RemoteException
	{
		return this.entries;
	}
}
