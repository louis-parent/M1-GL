package m1.agencies.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m1.agencies.model.Agency;
import m1.agencies.model.Hotel;
import m1.agencies.model.offer.AgencyOffer;
import m1.agencies.repositories.HotelRepository;

@RestController
@RequestMapping("api/v1")
public class OfferController
{
	private static Agency agency = Agency.getInstance();
	
	static
	{
		for(Hotel hotel : HotelRepository.readAll())
		{
			agency.addHotel(hotel);
		}
	}
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "Welcome to agency " + agency.getName();
	}
	
	@CrossOrigin
	@GetMapping("/offers/{city}/{start}/{end}/{persons}/{stars}")
	public List<AgencyOffer> getOffers(@PathVariable String city, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @PathVariable Integer persons, @PathVariable Integer stars)
	{
		List<AgencyOffer> offers = new ArrayList<AgencyOffer>();
		
		for(Hotel hotel : agency.getHotels())
		{
			if(hotel.isLocatedIn(city) && hotel.getStars() >= stars)
			{
				offers.addAll(agency.fetchOffersOf(hotel, start, end, persons));
			}
		}
		
		return offers;
	}
}
