package m1.agencies.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import m1.agencies.model.offer.AgencyOffer;

public class Agency
{
	private static final Agency instance = new Agency("Voyage Voyage", "desireless", "1986");
	
	private String name;
	private String login;
	private String password;
	private Collection<Hotel> hotels;

	private Agency(String name, String login, String password)
	{
		this(name, login, password, new Hotel[0]);
	}
	
	private Agency(String name, String login, String password, Hotel... hotels)
	{
		this.name = name;
		this.login = login;
		this.password = password;
		this.hotels = new HashSet<Hotel>(List.of(hotels));
	}

	public void addHotel(Hotel hotel)
	{
		this.hotels.add(hotel);
	}
	
	public boolean removeHote(Hotel hotel)
	{
		return this.hotels.remove(hotel);
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Collection<Hotel> getHotels()
	{
		return hotels;
	}

	public void setHotels(Collection<Hotel> hotels)
	{
		this.hotels = hotels;
	}

	public Collection<AgencyOffer> fetchOffersOf(Hotel hotel, LocalDate start, LocalDate end, Integer persons)
	{
		return hotel.getOffers(this.login, this.password, start, end, persons);
	}
	
	public static Agency getInstance()
	{
		return Agency.instance;
	}
}
