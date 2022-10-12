package veterinaire.share;

import java.rmi.RemoteException;

import veterinaire.model.animal.AbstractAnimal;
import veterinaire.model.animal.species.AnimalSpecies;
import veterinaire.model.animal.species.Species;

public class Cat extends AbstractAnimal
{
	private static final long serialVersionUID = -8681319671877655782L;
	public static Species species;
	
	static
	{
		try
		{
			species = new AnimalSpecies("Cat", 8);
		}
		catch(Exception e)
		{
			System.err.println("Error initializing dog : " + e.getLocalizedMessage());
		}
	}
	
	public Cat(String name, String owner, String race) throws RemoteException
	{
		super(name, owner, Cat.species, race);
	}
}
