package tp2.client.ui.listener.search;

import java.util.Collection;
import java.util.Date;

public interface HotelSearchListenable
{
	public abstract Collection<HotelSearchListener> getHotelSearchListeners();
	
	public default void addHotelSearchListener(HotelSearchListener listener)
	{
		this.getHotelSearchListeners().add(listener);
	}
	
	public default void removeHotelSearchListener(HotelSearchListener listener)
	{
		this.getHotelSearchListeners().remove(listener);
	}
	
	public default void notifyHotelSearch(String city, Date arrival, Date departure, int persons)
	{
		for(HotelSearchListener listener : this.getHotelSearchListeners())
		{
			listener.search(city, arrival, departure, persons);
		}
	}
}
