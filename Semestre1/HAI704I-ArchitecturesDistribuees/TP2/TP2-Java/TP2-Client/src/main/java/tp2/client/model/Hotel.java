package tp2.client.model;

import java.net.MalformedURLException;
import java.net.URL;

import tp2.client.ConcreteHotelService;
import tp2.client.ConcreteHotelServiceService;

public class Hotel
{
	private String name;
	private Address address;
	private int stars;
	private String login;
	private String password;
	private ConcreteHotelService service;
	
	public Hotel(String name, Address address, int stars, String service, String login, String password) throws MalformedURLException
	{
		this.name = name;
		this.address = address;
		this.stars = stars;
		this.service = new ConcreteHotelServiceService(new URL(service)).getConcreteHotelServicePort();
		this.login = login;
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public Address getAddress()
	{
		return address;
	}

	public int getStars()
	{
		return stars;
	}

	public String getLogin()
	{
		return login;
	}

	public String getPassword()
	{
		return password;
	}

	public ConcreteHotelService getService()
	{
		return service;
	}
	
	public boolean isLocatedIn(String city)
	{
		return this.getAddress().getCity().equals(city);
	}
}
