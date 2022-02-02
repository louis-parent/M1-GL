package m1.agencies.model.offer;

import java.time.LocalDate;

public class HotelOffer
{
	private String id;
	private int maxPersonAmount;
	private LocalDate availabiltyDate;
	private float price;
	private byte[] image;

	public HotelOffer()
	{
		this("", 0, LocalDate.now(), 0, new byte[0]);
	}
	
	public HotelOffer(String id, int maxPersonAmount, LocalDate availabiltyDate, float price, byte[] image)
	{
		this.id = id;
		this.maxPersonAmount = maxPersonAmount;
		this.availabiltyDate = availabiltyDate;
		this.price = price;
		this.image = image;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getMaxPersonAmount()
	{
		return maxPersonAmount;
	}

	public void setMaxPersonAmount(int maxPersonAmount)
	{
		this.maxPersonAmount = maxPersonAmount;
	}

	public LocalDate getAvailabiltyDate()
	{
		return availabiltyDate;
	}

	public void setAvailabiltyDate(LocalDate availabiltyDate)
	{
		this.availabiltyDate = availabiltyDate;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

}
