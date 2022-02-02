package storage.model.container;

import storage.operation.StorageVisitor;

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
	
	@Override
	public void accept(StorageVisitor visitor)
	{
		super.accept(visitor);
		visitor.visitRoot(this);
	}
}
