package veterinaire.model.animal.species;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veterinaire.model.animal.species.Species;

public class AnimalSpecies extends UnicastRemoteObject implements Species, Cloneable
{
	private static final long serialVersionUID = 3425264571176879145L;
	
	private String name;
	private int lifeExpectancy;

	public AnimalSpecies(String name, int lifeExpectancy) throws RemoteException
	{
		super();
		this.name = name;
		this.lifeExpectancy = lifeExpectancy;
	}

	@Override
	public String getName() throws RemoteException
	{
		return this.name;
	}

	@Override
	public int getLifeExpectancy() throws RemoteException
	{
		return this.lifeExpectancy;
	}

	@Override
	public Species duplicate() throws RemoteException
	{
		try {
			return (Species) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
