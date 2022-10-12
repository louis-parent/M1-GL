package veterinaire.model.animal;

import java.rmi.Remote;
import java.rmi.RemoteException;

import veterinaire.model.animal.kind.Dog;
import veterinaire.model.animal.species.Species;
import veterinaire.model.file.File;

public interface Animal extends Remote
{
	public File getFollowUpFile() throws RemoteException;
	
	public String getName() throws RemoteException;
	public void setName(String name) throws RemoteException;
	
	public String getOwner() throws RemoteException;
	public void setOwner(String owner) throws RemoteException;
	
	public default String getFullName() throws RemoteException
	{
		return this.getName() + " - " + this.getOwner();
	}
	
	public Species getSpecies() throws RemoteException;
	
	public String getRace() throws RemoteException;
	public void setRace(String race) throws RemoteException;
	
	public default String getResume() throws RemoteException
	{
		return this.getSpecies().getResume() + "(" + this.getRace() + ") " + this.getName() + " - " + this.getOwner();
	}
	
	public static Animal create(String species, String name, String owner, String race) throws RemoteException
	{
		switch(species)
		{
			case "Dog":
				return new Dog(name, owner, race);
			
			default:
				return null;
		}
	}
}
