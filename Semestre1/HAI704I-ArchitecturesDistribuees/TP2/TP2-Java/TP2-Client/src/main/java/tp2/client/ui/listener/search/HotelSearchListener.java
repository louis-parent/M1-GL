package tp2.client.ui.listener.search;

import java.util.Date;

public interface HotelSearchListener
{
	public abstract void search(String city, Date arrival, Date departure, int persons);
}
