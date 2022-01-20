package tp2.server.service;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import tp2.server.model.customer.Customer;
import tp2.server.model.offer.Offer;

@WebService
public interface HotelService
{
	@WebMethod
	public abstract String helloWorld();

	@WebMethod
	public abstract Offer[] fetchAvailableOffers(String login, String password, Date start, Date end, int persons);

	@WebMethod
	public abstract boolean makeReservation(String login, String password, int id, Customer customer);
}
