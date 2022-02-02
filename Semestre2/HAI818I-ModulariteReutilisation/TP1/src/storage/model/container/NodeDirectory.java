package storage.model.container;

import storage.operation.StorageVisitor;

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
	
	@Override
	public void accept(StorageVisitor visitor)
	{
		super.accept(visitor);
		visitor.visitDirectory(this);
	}
}
