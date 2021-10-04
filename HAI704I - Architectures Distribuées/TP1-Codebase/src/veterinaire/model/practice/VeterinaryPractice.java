package veterinaire.model.practice;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import veterinaire.model.animal.Animal;
import veterinaire.model.practice.alert.PracticeAlertListener;

public class VeterinaryPractice extends UnicastRemoteObject implements Practice, Serializable
{
	private static final long serialVersionUID = -782996703989531615L;
	
	private Set<PracticeAlertListener> listeners;
	private Map<String, Animal> patients;
	
	public VeterinaryPractice() throws RemoteException
	{
		super();
		this.listeners = new HashSet<PracticeAlertListener>();
		this.patients = new HashMap<String, Animal>();
	}

	@Override
	public Collection<Animal> getPatients() throws RemoteException
	{
		return this.patients.values().stream().toList();
	}
	
	@Override
	public Animal getPatient(String name) throws RemoteException
	{
		return this.patients.get(name);
	}
	
	@Override
	public void addPatient(Animal patient) throws RemoteException
	{
		this.patients.put(patient.getName(), patient);
		
		if(this.patients.size() == 1000)
		{
			this.notifyOvertake(1000);
		}
		else if(this.patients.size() == 500)
		{
			this.notifyOvertake(500);
		}
		else if(this.patients.size() == 100)
		{
			this.notifyOvertake(100);
		}
	}
	
	@Override
	public void removePatient(Animal patient) throws RemoteException
	{
		this.patients.remove(patient.getName(), patient);
		
		if(this.patients.size() == 1000)
		{
			this.notifyRecede(1000);
		}
		else if(this.patients.size() == 500)
		{
			this.notifyRecede(500);
		}
		else if(this.patients.size() == 100)
		{
			this.notifyRecede(100);
		}
	}
	
	@Override
	public Collection<PracticeAlertListener> getListeners()
	{
		return this.listeners;
	}
}
