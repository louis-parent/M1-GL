package tp2.client.ui.listener.filter;

import java.util.Collection;

public interface OfferFilterListenable
{
	public abstract Collection<OfferFilterListener> getOfferFilterListeners();
	
	public default void addOfferFilterListener(OfferFilterListener listener)
	{
		this.getOfferFilterListeners().add(listener);
	}
	
	public default void removeOfferFilterListener(OfferFilterListener listener)
	{
		this.getOfferFilterListeners().remove(listener);
	}
	
	public default void notifyOfferFilter(int starts, float maxPrice)
	{
		for(OfferFilterListener listener : this.getOfferFilterListeners())
		{
			listener.filter(starts, maxPrice);
		}
	}
}
