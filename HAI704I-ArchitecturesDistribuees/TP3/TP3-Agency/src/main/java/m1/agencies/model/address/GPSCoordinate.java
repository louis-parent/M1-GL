package m1.agencies.model.address;

public class GPSCoordinate
{
	private double latitude;
	private double longitude;

	public GPSCoordinate()
	{
		this(0, 0);
	}
	
	public GPSCoordinate(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	
}
