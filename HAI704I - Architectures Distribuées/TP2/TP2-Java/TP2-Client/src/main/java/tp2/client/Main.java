package tp2.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Main
{
	private static final String SERVICE_URL = "http://localhost:3000/HotelService?wsdl";
	private static final String LOGIN = "airone";
	private static final String PWD = "r1";
	
	public static void main(String[] args) throws MalformedURLException, DatatypeConfigurationException
	{
		URL url = new URL(SERVICE_URL);
		ConcreteHotelServiceService service = new ConcreteHotelServiceService(url);
		ConcreteHotelService proxy = service.getConcreteHotelServicePort();
		
		List<Offer> offers = proxy.fetchAvailableOffers(Main.LOGIN, Main.PWD, Main.dateToXML(LocalDate.of(2021, 11, 7)), Main.dateToXML(LocalDate.of(2021, 11, 14)), 2);

        for(Offer offer : offers)
        {
            System.out.println("Offer proposed : " + offer.id + " " + offer.maxPersonAmount + " " + offer.availabiltyDate + " " + offer.price + " " + offer.image);
        }

        System.out.println();

        Customer customer = new Customer();
        customer.firstName = "Lluis";
        customer.lastName = "Lluis";
        customer.card = new CreditCard();
        customer.card.number = "6600 0066 0660 6006";
        customer.card.expirationDate = "06/60";
        customer.card.cvv = "666";

        Offer selectedOffer = offers.get(0);
        boolean done = proxy.makeReservation(Main.LOGIN, Main.PWD, selectedOffer.id, customer);

        if(done)
        {
            System.out.println("Reservation for room for " + selectedOffer.maxPersonAmount + " persons done");
        }
        else
        {
            System.out.println("Reservation refused");
        }

        System.out.println();

		offers = proxy.fetchAvailableOffers(Main.LOGIN, Main.PWD, Main.dateToXML(LocalDate.of(2021, 11, 7)), Main.dateToXML(LocalDate.of(2021, 11, 14)), 2);

        for(Offer offer : offers)
        {
            System.out.println("Offer proposed : " + offer.id + " " + offer.maxPersonAmount + " " + offer.availabiltyDate + " " + offer.price);
        }
	}
	
	private static XMLGregorianCalendar dateToXML(LocalDate local) throws DatatypeConfigurationException
	{
		Date date = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	}
}
