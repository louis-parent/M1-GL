package tp2.server.model.room;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import tp2.server.model.room.bed.Bed;

public class Room
{
	private float price;
	private BufferedImage photo;
	private Set<Bed> beds;

	public Room(float price, Bed[] beds)
	{
		this(price, "src/main/resources/missing.jpg", beds);
	}
	
	public Room(float price, String photoPath, Bed[] beds)
	{
		this(price, (BufferedImage) null);
		
		try
		{
			this.photo = ImageIO.read(new File(photoPath));
		}
		catch(IOException e)
		{
			this.photo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		}
		
		this.addBeds(beds);
	}

	public Room(float price, BufferedImage photo)
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
	
	public BufferedImage getPhoto()
	{
		return this.photo;
	}
}
