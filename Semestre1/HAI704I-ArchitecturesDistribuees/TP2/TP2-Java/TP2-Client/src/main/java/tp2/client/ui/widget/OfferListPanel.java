package tp2.client.ui.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tp2.client.Offer;
import tp2.client.model.Hotel;
import tp2.client.ui.listener.reservation.ReservationListenable;
import tp2.client.ui.listener.reservation.ReservationListener;

public class OfferListPanel extends JScrollPane implements ReservationListenable, ReservationListener
{
	private static final long serialVersionUID = 9104683083732262925L;

	private Collection<ReservationListener> listeners;
	
	private JPanel content;
	
	public OfferListPanel()
	{
		this(Map.of());
	}

	public OfferListPanel(Map<Hotel, Collection<Offer>> offers)
	{
		super(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.listeners = new ArrayList<ReservationListener>();
		
		this.content = (JPanel) this.getViewport().getView();
		this.content.setLayout(new BoxLayout(this.content, BoxLayout.PAGE_AXIS));
		
		this.build(offers);
	}
	
	public void changeOffers(Map<Hotel, Collection<Offer>> offers)
	{
		this.content.removeAll();
		this.build(offers);
		this.content.revalidate();
		this.content.repaint();
	}
	
	@Override
	public Collection<ReservationListener> getReservationListeners()
	{
		return this.listeners;
	}
	
	@Override
	public void reservationDone()
	{
		this.notifyReservation();
	}

	private void build(Map<Hotel, Collection<Offer>> offers)
	{
		for(Entry<Hotel, Collection<Offer>> offersOfHotel : offers.entrySet())
		{
			Hotel hotel = offersOfHotel.getKey();
			
			for(Offer offer : offersOfHotel.getValue())
			{
				OfferPanel offerPanel = new OfferPanel(hotel, offer);
				offerPanel.addReservationListener(this);
				this.content.add(offerPanel);
			}
		}
	}
}
