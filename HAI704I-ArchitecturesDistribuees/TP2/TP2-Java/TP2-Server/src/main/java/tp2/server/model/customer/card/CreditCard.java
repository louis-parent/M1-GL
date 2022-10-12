package tp2.server.model.customer.card;

public class CreditCard
{
	private String number;
    private String expirationDate;
    private String CVV;
    
    public CreditCard()
	{
    	this("", "", "");
	}
    
	public CreditCard(String number, String expirationDate, String cVV)
	{
		this.number = number;
		this.expirationDate = expirationDate;
		this.CVV = cVV;
	}
	
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public String getExpirationDate()
	{
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate)
	{
		this.expirationDate = expirationDate;
	}
	public String getCVV()
	{
		return CVV;
	}
	public void setCVV(String cVV)
	{
		CVV = cVV;
	}
    
    
}

