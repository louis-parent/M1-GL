package aj.storage.model.container;

public class NodeDirectory extends Directory
{
	private Directory parent;
	private String name;

	public NodeDirectory(Directory parent, String name)
	{
		super();
		this.parent = parent;
		this.parent.add(this);
		this.name = name;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public Directory getParent()
	{
		return this.parent;
	}

	@Override
	public String absoluteAddress()
	{
		return this.getParent().absoluteAddress() + "/" + this.getName();
	}
}
