package storage.model;

import storage.model.container.Directory;
import storage.operation.StorageVisitor;

public class File implements StorageElement
{
	private Directory parent;
	private String name;
	private String content;

	public File(Directory parent, String name)
	{
		this(parent, name, "");
	}

	public File(Directory parent, String name, String content)
	{
		this.parent = parent;
		this.parent.add(this);
		this.name = name;
		this.content = content;
	}

	public void cat()
	{
		System.out.println(this.content);
	}
	
	public void setContent(String content)
	{
		this.content = content;
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
		return this.parent.absoluteAddress();
	}

	@Override
	public void ls()
	{
		System.out.println(this.absoluteAddress() + "/" + this.name);
	}

	@Override
	public int getBaseSize()
	{
		return 0;
	}

	@Override
	public int size()
	{
		return this.getBaseSize() + this.content.length();
	}
	
	@Override
	public void accept(StorageVisitor visitor)
	{
		visitor.visitFile(this);
	}
}
