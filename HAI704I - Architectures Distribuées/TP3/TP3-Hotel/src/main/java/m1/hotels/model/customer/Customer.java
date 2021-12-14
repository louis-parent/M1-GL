package m1.hotels.model.customer;

import m1.hotels.model.customer.card.CreditCard;

public class Customer
{
    private String firstName;
    private String lastName;
    private CreditCard card;

    public Customer()
    {
    	this("", "", null);
    }

    public Customer(String firstName, String lastName, CreditCard cards)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.card = cards;
    }

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public CreditCard getCard()
	{
		return card;
	}

	public void setCard(CreditCard card)
	{
		this.card = card;
	}
    
    
}
