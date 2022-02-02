package veterinaire;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import veterinaire.model.animal.Animal;
import veterinaire.model.practice.Practice;
import veterinaire.share.Cat;
import veterinaire.share.PracticeAlertPrinter;

public class Client
{
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException
	{
		Registry registry = LocateRegistry.getRegistry("localhost", 2021);
		Practice stub = (Practice) registry.lookup("Practice");

		stub.addPracticeAlertListener(new PracticeAlertPrinter());

		Client.printAllPatients(stub);

		stub.addPatient(new Cat("Garfield", "Jon", "Roux"));

		Client.printAllPatients(stub);
		
		System.exit(0);
	}

	private static void printAllPatients(Practice stub) throws RemoteException
	{
		System.out.println("Patients : ");

		for(Animal patient : stub.getPatients())
		{
			System.out.println("- " + patient.getResume());
		}
	}
}
