package m1.hotels.repositories;

import m1.hotels.model.Hotel;
import m1.hotels.model.address.Address;
import m1.hotels.model.address.GPSCoordinate;
import m1.hotels.model.agency.Agency;
import m1.hotels.model.room.Room;
import m1.hotels.model.room.bed.Bed;
import m1.hotels.model.room.bed.DoubleBed;
import m1.hotels.model.room.bed.SimpleBed;

public class HotelRepository
{
	
	public static Hotel getHotel()
	{
		Hotel hotel = new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373));

		Room simpleRoom = new Room(66, "src/main/resources/cheap.jpg", new Bed[] { new SimpleBed() });
		hotel.addRoom(simpleRoom);

		Room familyRoom = new Room(106, "src/main/resources/family.jpg", new Bed[] { new DoubleBed(), new SimpleBed(), new SimpleBed() });
		hotel.addRoom(familyRoom);

		Room luxuryVIPGoldenPremiumLounge = new Room(666, "src/main/resources/vip.jpg", new Bed[] { new DoubleBed() });
		hotel.addRoom(luxuryVIPGoldenPremiumLounge);

		Agency agency1 = new Agency("Voyage Voyage", "desireless", "1986");
		hotel.addPartnership(agency1, 0.98f);

		Agency agency2 = new Agency("Erwan Aviation", "airone", "r1");
		hotel.addPartnership(agency2, 1f);
		
		return hotel;
	}
}
