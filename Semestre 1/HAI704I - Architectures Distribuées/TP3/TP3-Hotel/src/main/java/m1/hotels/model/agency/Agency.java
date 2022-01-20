package m1.hotels.model.agency;

public class Agency
{
    private String name;

    private String login;
    private String password;
    
    private float rate;

    public Agency(String name, String login, String password, float rate)
    {
        this.name = name;
        this.login = login;
        this.password = password;
        this.rate = rate;
    }

    public String getName()
    {
    	return this.name;
    }
    
    public float getRate()
    {
    	return this.rate;
    }

    public boolean connect(String login, String password)
    {
        return this.login.equals(login) && this.password.equals(password);
    }
}
