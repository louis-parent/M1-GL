package veterinaire;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import veterinaire.model.animal.kind.Dog;
import veterinaire.model.practice.Practice;
import veterinaire.model.practice.VeterinaryPractice;

public class Server
{
	public static void main(String[] args) throws RemoteException
	{
		System.setProperty("java.security.policy", "all.policy");
		System.setProperty("java.rmi.server.codebase", "file:/home/louis/Documents/HAI704I - Architectures Distribuées/TP1-Codebase/bin/");
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		new ClientCustomClass().print();
		
		Registry registry = LocateRegistry.createRegistry(2021);
		
		if(registry == null)
		{
			System.err.println("No registry found");
		}
		else
		{
			registry.rebind("Practice", Server.createTestPratice());
			System.out.println("Server started");
		}
	}
	
	private static Practice createTestPratice() throws RemoteException
	{
		Practice practice = new VeterinaryPractice();
		practice.addPatient(new Dog("Rex", "Louis", "Labrador"));
		
		return practice;
	}
}
