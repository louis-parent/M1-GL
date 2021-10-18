package veterinaire.share;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veterinaire.model.practice.alert.PracticeAlertListener;

public class PracticeAlertPrinter extends UnicastRemoteObject implements PracticeAlertListener, Serializable
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
