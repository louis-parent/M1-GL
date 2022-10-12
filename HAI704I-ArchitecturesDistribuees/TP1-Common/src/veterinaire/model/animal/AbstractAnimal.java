package veterinaire.model.animal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veterinaire.model.animal.species.Species;
import veterinaire.model.file.File;
import veterinaire.model.file.FollowupFile;

public class AbstractAnimal extends UnicastRemoteObject implements Animal
{
	private static final long serialVersionUID = 924803408937801490L;

	private String name;
	private String owner;
	private Species species;
	private String race;
	private File followupFile;
	
	public AbstractAnimal(String name, String owner, Species species, String race) throws RemoteException
	{
		this.name = name;
		this.owner = owner;
		this.species = species;
		this.race = race;
		this.followupFile = new FollowupFile();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public Species getSpecies() throws RemoteException
	{
		return species.duplicate();
	}
	
	public String getRace()
	{
		return race;
	}
	
	public void setRace(String race)
	{
		this.race = race;
	}
	
	@Override
	public File getFollowUpFile() throws RemoteException
	{
		return this.followupFile;
	}
}
