package m1.agencies.model.offer;

import m1.agencies.model.Hotel;
import m1.agencies.model.address.Address;
import m1.agencies.model.address.GPSCoordinate;

public class AgencyOffer
{
	private String hotelName;
	private Address hotelAddress;
	private GPSCoordinate hotelCoordinate;
	private int hotelStars;
	private int roomMaxPersons;
	private float roomPrice;
	private byte[] image;

	public AgencyOffer()
	{
		this("", new Address(), new GPSCoordinate(), 0, 0, 0, new byte[0]);
	}
	
	public AgencyOffer(Hotel hotel, HotelOffer offer)
	{
		this(hotel.getName(), hotel.getAddress(), hotel.getCoordinate(), hotel.getStars(), offer.getMaxPersonAmount(), offer.getPrice(), offer.getImage());
	}
	
	public AgencyOffer(String hotelName, Address hotelAddress, GPSCoordinate hotelCoordinate, int hotelStars, int roomMaxPersons, float roomPrice, byte[] image)
	{
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelCoordinate = hotelCoordinate;
		this.hotelStars = hotelStars;
		this.roomMaxPersons = roomMaxPersons;
		this.roomPrice = roomPrice;
		this.image = image;
	}

	public String getHotelName()
	{
		return hotelName;
	}

	public void setHotelName(String hotelName)
	{
		this.hotelName = hotelName;
	}

	public Address getHotelAddress()
	{
		return hotelAddress;
	}

	public void setHotelAddress(Address hotelAddress)
	{
		this.hotelAddress = hotelAddress;
	}

	public GPSCoordinate getHotelCoordinate()
	{
		return hotelCoordinate;
	}

	public void setHotelCoordinate(GPSCoordinate hotelCoordinate)
	{
		this.hotelCoordinate = hotelCoordinate;
	}

	public int getHotelStars()
	{
		return hotelStars;
	}

	public void setHotelStars(int hotelStars)
	{
		this.hotelStars = hotelStars;
	}

	public int getRoomMaxPersons()
	{
		return roomMaxPersons;
	}

	public void setRoomMaxPersons(int roomMaxPersons)
	{
		this.roomMaxPersons = roomMaxPersons;
	}

	public float getRoomPrice()
	{
		return roomPrice;
	}

	public void setRoomPrice(float roomPrice)
	{
		this.roomPrice = roomPrice;
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
