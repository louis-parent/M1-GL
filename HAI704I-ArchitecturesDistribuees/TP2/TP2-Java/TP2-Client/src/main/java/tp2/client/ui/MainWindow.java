package tp2.client.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import tp2.client.Offer;
import tp2.client.model.Hotel;
import tp2.client.ui.listener.filter.OfferFilterListener;
import tp2.client.ui.listener.reservation.ReservationListener;
import tp2.client.ui.listener.search.HotelSearchListener;
import tp2.client.ui.widget.FilterPanel;
import tp2.client.ui.widget.OfferListPanel;
import tp2.client.ui.widget.SearchPanel;

public class MainWindow extends JFrame implements HotelSearchListener, OfferFilterListener, ReservationListener
{
	private static final long serialVersionUID = 3440110167547462475L;

	private Collection<Hotel> hotels;

	private Map<Hotel, Collection<Offer>> offers;
	private int stars;
	private float maxPrice;
	
	private SearchPanel searchPanel;
	private OfferListPanel offerListPanel;
	private FilterPanel filterPanel;


	public MainWindow(Collection<Hotel> hotels)
	{
		super("Hotel Service Client");

		this.hotels = hotels;
		this.offers = Map.of();
		this.stars = 0;
		this.maxPrice = 0;

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.build();
		this.pack();
	}

	private void build()
	{
		this.setLayout(new BorderLayout());

		searchPanel = new SearchPanel(this.getCities());
		searchPanel.addHotelSearchListener(this);
		this.add(searchPanel, BorderLayout.NORTH);

		filterPanel = new FilterPanel();
		filterPanel.addOfferFilterListener(this);
		this.add(filterPanel, BorderLayout.WEST);
		
		offerListPanel = new OfferListPanel();
		offerListPanel.addReservationListener(this);
		this.add(offerListPanel, BorderLayout.CENTER);
	}

	public void open()
	{
		this.setVisible(true);
	}

	public Collection<String> getCities()
	{
		Collection<String> cities = new ArrayList<String>();

		for(Hotel hotel : this.hotels)
		{
			cities.add(hotel.getAddress().getCity());
		}

		return cities;
	}

	@Override
	public void search(String city, Date arrival, Date departure, int persons)
	{
		float min = -1;
		float max = -1;
		this.offers = new HashMap<Hotel, Collection<Offer>>();
		
		for(Hotel hotel : this.hotels)
		{
			if(hotel.isLocatedIn(city))
			{
				List<Offer> offers = hotel.getService().fetchAvailableOffers(hotel.getLogin(), hotel.getPassword(), this.dateToXML(arrival), this.dateToXML(departure), persons);
				
				for(Offer offer : offers)
				{
					if(max == -1 && min == -1)
					{
						max = offer.getPrice();
						min = offer.getPrice();
					}
					else
					{
						if(offer.getPrice() < min)
						{
							min = offer.getPrice();
						}
						
						if(offer.getPrice() > max)
						{
							max = offer.getPrice();
						}
					}
				}
				
				this.offers.put(hotel, offers);
				
			}
		}
		
		this.filterPanel.setMinPrice(min);
		this.filterPanel.setMaxPrice(max);
		
		this.changeOffersWithFilters();
	}	

	@Override
	public void filter(int stars, float maxPrice)
	{
		this.stars = stars;
		this.maxPrice = maxPrice;
		
		this.changeOffersWithFilters();
	}

	@Override
	public void reservationDone()
	{
		this.searchPanel.fetchSearch();
	}

	private XMLGregorianCalendar dateToXML(Date date)
	{
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try
		{
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		}
		catch(DatatypeConfigurationException e)
		{
			return null;
		}
	}
	

	private void changeOffersWithFilters()
	{
		Map<Hotel, Collection<Offer>> filtered = new HashMap<Hotel, Collection<Offer>>();
		
		for(Entry<Hotel, Collection<Offer>> entry : this.offers.entrySet())
		{
			if(entry.getKey().getStars() >= this.stars)
			{
				Collection<Offer> entryOffers = new ArrayList<Offer>();
				
				for(Offer offer : entry.getValue())
				{
					if(offer.getPrice() <= this.maxPrice)
					{
						entryOffers.add(offer);
					}
				}
				
				filtered.put(entry.getKey(), entryOffers);
			}
		}
		
		this.offerListPanel.changeOffers(filtered);
	}
}
