package veterinaire.model.animal.kind;

import java.rmi.RemoteException;

import veterinaire.model.animal.AbstractAnimal;
import veterinaire.model.animal.species.AnimalSpecies;
import veterinaire.model.animal.species.Species;

public class Dog extends AbstractAnimal
{
	private static final long serialVersionUID = -8681319671877655782L;
	public static Species species;
	
	static
	{
		try
		{
			species = new AnimalSpecies("Dog", 12);
		}
		catch(Exception e)
		{
			System.err.println("Error initializing dog : " + e.getLocalizedMessage());
		}
	}
	
	public Dog(String name, String owner, String race) throws RemoteException
	{
		super(name, owner, Dog.species, race);
	}
}
