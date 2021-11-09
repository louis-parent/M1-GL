package tp2.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

import tp2.server.model.offer.Offer;

public class OfferCache
{
	private List<Offer> cached;

	public OfferCache()
	{
		this.cached = new ArrayList<Offer>();
	}

	public void cacheAll(Collection<Offer> offers)
	{
		for(Offer offer : offers)
		{
			this.cache(offer);
		}
	}

	public void cache(Offer offer)
	{
		offer.setId(this.getNextId());
		this.cached.add(offer);
	}

	public Offer uncache(int id)
	{
		for(Offer offer : this.cached)
		{
			if(offer.getId() == id)
			{
				this.cached.remove(offer);
				return offer;
			}
		}

		return null;
	}

	private int getNextId()
	{
		OptionalInt max = this.cached.stream().mapToInt(offer -> {
			return offer.getId();
		}).max();

		if(max.isPresent())
		{
			return max.getAsInt() + 1;
		}
		else
		{
			return 0;
		}
	}
}