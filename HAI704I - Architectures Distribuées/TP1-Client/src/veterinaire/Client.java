package veterinaire;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import veterinaire.model.animal.Animal;
import veterinaire.model.practice.Practice;
import veterinaire.model.practice.alert.PracticeAlertListener;

public class Client
{
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException
	{		
		Registry registry = LocateRegistry.getRegistry("localhost", 2021);
		Practice stub = (Practice) registry.lookup("Practice");
		
		stub.addPracticeAlertListener(new PracticeAlertPrinter());
		
		System.out.println("Patients : ");
		
		for (Animal patient : stub.getPatients())
		{
			System.out.println("- " + patient.getResume());
		}
		
		for(int i = 0; i < 1500; i++)
		{
			Animal patient = Animal.create("Dog", "CD-" + System.currentTimeMillis(), "Louis", "Labrador");
			stub.addPatient(patient);
		}
		
		for (Animal patient : stub.getPatients())
		{
			stub.removePatient(patient);
		}
	}
	
	private static class PracticeAlertPrinter extends UnicastRemoteObject implements PracticeAlertListener, Serializable
	{
		private static final long serialVersionUID = 8305391564538440146L;

		public PracticeAlertPrinter() throws RemoteException
		{
			super();
		}

		@Override
		public void overtake(int patientThresold) throws RemoteException
		{
			System.out.println("Thresold reached : " + patientThresold + " patients");
		}
		
		@Override
		public void recede(int patientThresold) throws RemoteException
		{
			System.out.println("Thresold recede : " + patientThresold + " patients");			
		}
	}
}
