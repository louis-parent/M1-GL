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
		Server.setupSecurity();
		Server.setupCodebase();

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

	private static void setupSecurity()
	{
		System.setProperty("java.security.policy", "all.policy");

		if(System.getSecurityManager() == null)
		{
			System.setSecurityManager(new SecurityManager());
		}
	}

	private static void setupCodebase()
	{
		System.setProperty("java.rmi.server.codebase", "file:../TP1-Client/bin/veterinaire/share/");
	}

	private static Practice createTestPratice() throws RemoteException
	{
		Practice practice = new VeterinaryPractice();
		practice.addPatient(new Dog("Rex", "Louis", "Labrador"));

		return practice;
	}
}
