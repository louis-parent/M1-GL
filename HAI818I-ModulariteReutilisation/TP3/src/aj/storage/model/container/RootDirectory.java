package aj.storage.model.container;

public class RootDirectory extends Directory
{
	@Override
	public String getName()
	{
		return "/";
	}

	@Override
	public Directory getParent()
	{
		return null;
	}
	
	@Override
	public String absoluteAddress()
	{
		return "";
	}
}
