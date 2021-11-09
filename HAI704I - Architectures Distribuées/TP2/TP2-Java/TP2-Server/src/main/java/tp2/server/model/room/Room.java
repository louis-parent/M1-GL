package tp2.server.model.room;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import tp2.server.model.room.bed.Bed;

public class Room
{
	private float price;
	private Image photo;
	private Set<Bed> beds;

	public Room(float price, Bed[] beds)
	{
		this(price, new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
		this.addBeds(beds);
	}

	public Room(float price, Image photo)
	{
		this.price = price;
		this.photo = photo;
		this.beds = new HashSet<Bed>();
	}

	public float getPrice()
	{
		return this.price;
	}
	
	public int getPersonMaxAmount()
	{
		return this.beds.stream().mapToInt(bed -> {
			return bed.getPersonAmount();
		}).sum();
	}

	public void addBeds(Bed[] beds)
	{
		for(Bed bed : beds)
		{
			this.addBed(bed);
		}
	}

	public void addBed(Bed bed)
	{
		this.beds.add(bed);
	}

	public boolean canHost(int persons)
	{
		return this.getPersonMaxAmount() >= persons;
	}
	
	public Image getPhoto()
	{
		return this.photo;
	}
}
