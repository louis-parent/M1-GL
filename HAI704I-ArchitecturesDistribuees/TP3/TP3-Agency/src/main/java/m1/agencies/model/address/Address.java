package m1.agencies.model.address;

public class Address
{
	private String number;
	private String street;
	private String city;
	private String zipCode;
	private String country;

	public Address()
	{
		this("", "", "", "", "");
	}
	
	public Address(String number, String street, String city, String zipCode, String country)
	{
		this.number = number;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}
	
	
}
