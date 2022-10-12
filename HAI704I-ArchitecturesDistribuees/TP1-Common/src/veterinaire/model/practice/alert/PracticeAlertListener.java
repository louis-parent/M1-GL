package veterinaire.model.practice.alert;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PracticeAlertListener extends Remote
{
	public abstract void overtake(int patientThresold) throws RemoteException;
	public abstract void recede(int patientThresold) throws RemoteException;
}
