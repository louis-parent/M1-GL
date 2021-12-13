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
import m1.hotels.repositories.HotelRepository;

@RestController
@RequestMapping("api/v1")
public class OfferController
{
	private Hotel hotel = HotelRepository.getHotel();
	
	@GetMapping("/hello")
	public String sayHelloWorld()
	{
		return "Hello World!";
	}
	
	@GetMapping("/offers/{login}/{password}/{start}/{end}/{persons}")
	public List<Offer> getOffers(@PathVariable String login, @PathVariable String password, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @PathVariable Integer persons)
	{
		Agency agency = this.hotel.isPartner(login, password);
		
		Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(agency != null)
		{
			List<Room> rooms = this.hotel.getAvailableRooms(startDate, endDate, persons);
			float rate = this.hotel.getRateFor(agency);

			List<Offer> offers = new ArrayList<Offer>();
			
			for(Room room : rooms)
			{
				offers.add(new Offer(room.getPersonMaxAmount(), this.hotel.getFirstAvailability(room, startDate), rate * room.getPrice(), startDate, endDate, room));
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
		Agency agency = this.hotel.isPartner(login, password);
		
		if(agency != null)
		{
			System.out.println("---------------------------------------------------\n" + offer + "\n-----------------------------------------------------------------");
			return this.hotel.makeReservation(offer, customer).getId();
		}
		else
		{
			return -1;
		}
	}
	
}
