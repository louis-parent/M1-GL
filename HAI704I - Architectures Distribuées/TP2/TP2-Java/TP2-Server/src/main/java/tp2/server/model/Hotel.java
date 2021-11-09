package tp2.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import tp2.server.model.address.Address;
import tp2.server.model.address.GPSCoordinate;
import tp2.server.model.agency.Agency;
import tp2.server.model.customer.Customer;
import tp2.server.model.offer.Offer;
import tp2.server.model.reservation.Reservation;
import tp2.server.model.room.Room;

public class Hotel
{
	private static final int MILLIS_PER_DAY = 1000*24*60*60;
	
	private String name;
	private int stars;
	private Address address;
	private GPSCoordinate coordinate;
	private Collection<Room> rooms;
	private Collection<Reservation> reservations;

	private Map<Agency, Float> partnerships;

	public Hotel(String name, int stars, Address address, GPSCoordinate coordinate)
	{
		this.name = name;
		this.stars = stars;
		this.address = address;
		this.coordinate = coordinate;

		this.rooms = new HashSet<Room>();
		this.reservations = new HashSet<Reservation>();
		this.partnerships = new HashMap<Agency, Float>();
	}

	public void addRoom(Room room)
	{
		this.rooms.add(room);
	}

	public void addReservation(Reservation reservation)
	{
		this.reservations.add(reservation);
	}

	public void addPartnership(Agency agency, float rate)
	{
		this.partnerships.put(agency, rate);
	}

	public Agency isPartner(String login, String password)
	{
		for(Agency agency : this.partnerships.keySet())
		{
			if(agency.connect(login, password))
			{
				return agency;
			}
		}

		return null;
	}

	public List<Room> getAvailableRooms(Date start, Date end, int persons)
	{
		List<Room> rooms = new ArrayList<Room>();

		for(Room room : this.rooms)
		{
			if(room.canHost(persons) && this.isFree(room, start, end))
			{
				rooms.add(room);
			}
		}

		return rooms;
	}

	public boolean isFree(Room room, Date start, Date end)
	{
		for(Reservation reservation : this.reservations)
		{
			if(reservation.concern(room) && reservation.intersect(start, end))
			{
				return false;
			}
		}

		return true;
	}

	public float getRateFor(Agency agency)
	{
		return this.partnerships.get(agency);
	}

	public Date getFirstAvailability(Room room, Date start)
	{
		Date first = new Date();

		for(Reservation reservation : this.reservations)
		{
			if(reservation.concern(room) && reservation.isLaterThan(first) && reservation.isEarlierThan(start))
			{
				first = new Date(reservation.getEnd().getTime() + MILLIS_PER_DAY);
			}
		}

		return first;
	}

	public void makeReservation(Offer offer, Customer customer)
	{
		this.reservations.add(new Reservation(offer.getStart(), offer.getEnd(), customer, offer.getOfferedRoom()));
	}
}
