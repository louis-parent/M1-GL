package tp2.client;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;

import tp2.client.model.Address;
import tp2.client.model.Hotel;
import tp2.client.ui.LookAndFeel;
import tp2.client.ui.MainWindow;

public class Main
{		
	public static void main(String[] args) throws MalformedURLException
	{
		LookAndFeel.setBestLookAndFeel();
		
		Collection<Hotel> hotels = new HashSet<Hotel>();
		hotels.add(new Hotel("L'Anxova d'Aqui", new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), 5, "http://localhost:3000/HotelService?wsdl", "airone", "r1"));
		
		new MainWindow(hotels).open();
	}
}
