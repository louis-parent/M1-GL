package m1.agencies.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import m1.agencies.model.address.Address;
import m1.agencies.model.address.GPSCoordinate;
import m1.agencies.model.offer.AgencyOffer;
import m1.agencies.model.offer.HotelOffer;

public class Hotel
{	
	private String name;
	private int stars;
	private Address address;
	private GPSCoordinate coordinate;
	private String service;
	
	public Hotel()
	{
		this("", 0, new Address(), new GPSCoordinate(), "");
	}

	public Hotel(String name, int stars, Address address, GPSCoordinate coordinate, String service)
	{
		this.name = name;
		this.stars = stars;
		this.address = address;
		this.coordinate = coordinate;
		this.service = service;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getStars()
	{
		return stars;
	}

	public void setStars(int stars)
	{
		this.stars = stars;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public GPSCoordinate getCoordinate()
	{
		return coordinate;
	}

	public void setCoordinate(GPSCoordinate coordinate)
	{
		this.coordinate = coordinate;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public boolean isLocatedIn(String city)
	{
		return this.address.getCity().equals(city);
	}
	
	public Collection<AgencyOffer> getOffers(String login, String password, LocalDate start, LocalDate end, Integer persons)
	{		
		RestTemplate proxy = new RestTemplate();
		
		URI uri = this.buildGetOfferURI(login, password, start, end, persons);
		HotelOffer[] offers = proxy.getForObject(uri, HotelOffer[].class);
		
		Collection<AgencyOffer> agencyOffers = new ArrayList<AgencyOffer>();
		
		for(HotelOffer offer : offers)
		{
			agencyOffers.add(new AgencyOffer(this, offer));
		}
		
		return agencyOffers;
	}
	
	private URI buildGetOfferURI(String login, String password, LocalDate start, LocalDate end, Integer persons)
	{
		String url = this.service + "/offers/";
		url += login + "/";
		url += password + "/";
		url += this.dateToUri(start) + "/";
		url += this.dateToUri(end) + "/";
		url += persons;
		
		try
		{
			return new URI(url);
		}
		catch(URISyntaxException e)
		{
			return null;
		}
	}
	
	private String dateToUri(LocalDate date)
	{
		String month = String.valueOf(date.getMonthValue());
		if(month.length() == 1)
		{
			month = "0" + month;
		}
		
		String day = String.valueOf(date.getDayOfMonth());
		if(day.length() == 1)
		{
			day = "0" + day;
		}
		
		return date.getYear() + "-" + month + "-" + day;
	}
}
