package veterinaire.model.practice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import veterinaire.model.animal.Animal;
import veterinaire.model.practice.alert.PracticeAlertListenable;

public interface Practice extends Remote, PracticeAlertListenable
{
	public abstract Collection<Animal> getPatients() throws RemoteException;
	
	public abstract Animal getPatient(String name) throws RemoteException;
	
	public abstract void addPatient(Animal patient) throws RemoteException;
	
	public abstract void removePatient(Animal patient) throws RemoteException;
}
