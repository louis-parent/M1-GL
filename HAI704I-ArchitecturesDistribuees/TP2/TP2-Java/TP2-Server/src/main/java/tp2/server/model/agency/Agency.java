package tp2.server.model.agency;

public class Agency
{
    private String name;

    private String login;
    private String password;

    public Agency(String name, String login, String password)
    {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName()
    {
    	return this.name;
    }

    public boolean connect(String login, String password)
    {
        return this.login.equals(login) && this.password.equals(password);
    }
}
