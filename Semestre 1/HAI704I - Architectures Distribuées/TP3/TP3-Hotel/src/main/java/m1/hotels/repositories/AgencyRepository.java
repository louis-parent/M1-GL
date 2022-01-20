package m1.hotels.repositories;

import java.util.Collection;
import java.util.HashSet;

import m1.hotels.model.agency.Agency;

public class AgencyRepository
{
	private static Collection<Agency> repository = new HashSet<Agency>() {
		private static final long serialVersionUID = -4421439887335053382L;
		{
			add(new Agency("Voyage Voyage", "desireless", "1986", 0.98f));
			add(new Agency("Erwan Aviation", "airone", "r1", 1));
		}
	};
	
	public static Collection<Agency> readAll()
	{
		return AgencyRepository.repository;
	}
}
