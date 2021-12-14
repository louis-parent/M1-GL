package m1.hotels.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m1.hotels.model.Hotel;
import m1.hotels.model.agency.Agency;
import m1.hotels.model.customer.Customer;
import m1.hotels.model.offer.Offer;
import m1.hotels.model.room.Room;
import m1.hotels.repositories.AgencyRepository;
import m1.hotels.repositories.RoomRepository;

@RestController
@RequestMapping("api/v1")
public class OfferController
{
	private static Hotel hotel = Hotel.getInstance();
	
	static
	{
		for(Room room : RoomRepository.readAll())
		{
			hotel.addRoom(room);
		}
		
		for(Agency agency : AgencyRepository.readAll())
		{
			hotel.addPartnership(agency);
		}
	}
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "Welcome to hotel " + hotel.getName();
	}
	
	@GetMapping("/offers/{login}/{password}/{start}/{end}/{persons}")
	public List<Offer> getOffers(@PathVariable String login, @PathVariable String password, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @PathVariable Integer persons)
	{
		Agency agency = OfferController.hotel.isPartner(login, password);
		
		Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(agency != null)
		{
			List<Room> rooms = OfferController.hotel.getAvailableRooms(startDate, endDate, persons);
			List<Offer> offers = new ArrayList<Offer>();
			
			for(Room room : rooms)
			{
				offers.add(new Offer(room.getPersonMaxAmount(), OfferController.hotel.getFirstAvailability(room, startDate), agency.getRate() * room.getPrice(), startDate, endDate, room));
			}
			
			return offers;
		}
		else
		{
			return List.of();
		}
	}
	
	@PostMapping("/reservation/{login}/{password}/{offer}")
	public int reserve(@PathVariable String login, @PathVariable String password, @PathVariable String offer, @RequestBody Customer customer)
	{
		Agency agency = OfferController.hotel.isPartner(login, password);
		
		if(agency != null)
		{
			System.out.println("---------------------------------------------------\n" + offer + "\n-----------------------------------------------------------------");
			return OfferController.hotel.makeReservation(offer, customer).getId();
		}
		else
		{
			return -1;
		}
	}
	
}
