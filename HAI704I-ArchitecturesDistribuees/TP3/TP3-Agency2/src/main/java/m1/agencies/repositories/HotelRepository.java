package m1.agencies.repositories;

import java.util.Collection;
import java.util.HashSet;

import m1.agencies.model.Hotel;
import m1.agencies.model.address.Address;
import m1.agencies.model.address.GPSCoordinate;

public class HotelRepository
{
	private static Collection<Hotel> repository = new HashSet<Hotel>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373), "http://localhost:8080/api/v1"));
		}
	};

	public static Collection<Hotel> readAll()
	{
		return HotelRepository.repository;
	}
}
