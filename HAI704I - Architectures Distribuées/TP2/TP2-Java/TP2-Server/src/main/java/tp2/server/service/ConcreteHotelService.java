package tp2.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import tp2.server.model.Hotel;
import tp2.server.model.address.Address;
import tp2.server.model.address.GPSCoordinate;
import tp2.server.model.agency.Agency;
import tp2.server.model.customer.Customer;
import tp2.server.model.offer.Offer;
import tp2.server.model.room.Room;
import tp2.server.model.room.bed.Bed;
import tp2.server.model.room.bed.DoubleBed;
import tp2.server.model.room.bed.SimpleBed;

@WebService
public class ConcreteHotelService implements HotelService
{
	private Hotel hotel;
	private OfferCache cache;

	public ConcreteHotelService()
	{
		this.hotel = new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373));

		Room simpleRoom = new Room(66, "src/main/resources/cheap.jpg", new Bed[] { new SimpleBed() });
		this.hotel.addRoom(simpleRoom);

		Room familyRoom = new Room(106, "src/main/resources/family.jpg", new Bed[] { new DoubleBed(), new SimpleBed(), new SimpleBed() });
		this.hotel.addRoom(familyRoom);

		Room luxuryVIPGoldenPremiumLounge = new Room(666, "src/main/resources/vip.jpg", new Bed[] { new DoubleBed() });
		this.hotel.addRoom(luxuryVIPGoldenPremiumLounge);

		Agency agency1 = new Agency("Voyage Voyage", "desireless", "1986");
		this.hotel.addPartnership(agency1, 0.98f);

		Agency agency2 = new Agency("Erwan Aviation", "airone", "r1");
		this.hotel.addPartnership(agency2, 1f);

		this.cache = new OfferCache();
	}

	@Override
	public String helloWorld()
	{
		return "Hello World";
	}

	@Override
	public Offer[] fetchAvailableOffers(String login, String password, Date start, Date end, int persons)
	{
		Agency agency = this.hotel.isPartner(login, password);

		if(agency != null)
		{
			List<Room> rooms = this.hotel.getAvailableRooms(start, end, persons);
			List<Offer> offers = new ArrayList<Offer>();

			for(Room room : rooms)
			{
				offers.add(new Offer(0, room.getPersonMaxAmount(), this.hotel.getFirstAvailability(room, start), room.getPrice() * this.hotel.getRateFor(agency), start, end, room));
			}

			this.cache.cacheAll(offers);
			return offers.toArray(new Offer[0]);
		}
		else
		{
			return new Offer[0];
		}
	}

	@Override
	public boolean makeReservation(String login, String password, int id, Customer customer)
	{
		Agency agency = this.hotel.isPartner(login, password);

		if(agency != null)
		{
			Offer offer = this.cache.uncache(id);
			this.hotel.makeReservation(offer, customer);
			return true;
		}
		else
		{
			return false;
		}
	}

}
