﻿package m1.hotels.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import m1.hotels.model.address.Address;
import m1.hotels.model.address.GPSCoordinate;
import m1.hotels.model.agency.Agency;
import m1.hotels.model.customer.Customer;
import m1.hotels.model.offer.Offer;
import m1.hotels.model.reservation.Reservation;
import m1.hotels.model.room.Room;

public class Hotel
{
	private static final int MILLIS_PER_DAY = 1000*24*60*60;

	private static final Hotel instance = new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373));
	
	private String name;
	private int stars;
	private Address address;
	private GPSCoordinate coordinate;
	private Collection<Room> rooms;
	private Collection<Reservation> reservations;

	private Collection<Agency> partnerships;

	private Hotel(String name, int stars, Address address, GPSCoordinate coordinate)
	{
		this.name = name;
		this.stars = stars;
		this.address = address;
		this.coordinate = coordinate;

		this.rooms = new HashSet<Room>();
		this.reservations = new HashSet<Reservation>();
		this.partnerships = new HashSet<Agency>();
	}
	
	public String getName()
	{
		return name;
	}

	public void addRoom(Room room)
	{
		this.rooms.add(room);
	}

	public void addReservation(Reservation reservation)
	{
		this.reservations.add(reservation);
	}

	public void addPartnership(Agency agency)
	{
		this.partnerships.add(agency);
	}

	public Agency isPartner(String login, String password)
	{
		for(Agency agency : this.partnerships)
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

	public LocalDate getFirstAvailability(Room room, Date start)
	{
		Date first = new Date();

		for(Reservation reservation : this.reservations)
		{
			if(reservation.concern(room) && reservation.isLaterThan(first) && reservation.isEarlierThan(start))
			{
				first = new Date(reservation.getEnd().getTime() + MILLIS_PER_DAY);
			}
		}

		return Instant.ofEpochMilli(first.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public Reservation makeReservation(String offer, Customer customer)
	{
		Reservation reservation = new Reservation(Offer.getStartForId(offer), Offer.getEndForId(offer), customer, this.getRoomForId(Offer.getRoomForId(offer)));
		this.reservations.add(reservation);
		System.out.println(this.reservations);
		return reservation;
	}

	private Room getRoomForId(int id)
	{
		for(Room room : this.rooms)
		{
			if(room.getId() == id)
			{
				return room;
			}
		}
		
		return null;
	}
	
	public static Hotel getInstance()
	{
		return Hotel.instance;
	}
}
