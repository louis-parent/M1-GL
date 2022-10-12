package storage.model.container;

import java.util.Collection;
import java.util.HashSet;

import storage.model.StorageElement;
import storage.operation.StorageVisitor;

public abstract class Directory implements StorageElement
{
	private Collection<StorageElement> content;

	public Directory()
	{
		this.content = new HashSet<StorageElement>();
	}
	
	public void add(StorageElement element)
	{
		this.content.add(element);
	}
	
	public void rm(StorageElement element)
	{
		this.content.remove(element);
	}

	@Override
	public void ls()
	{
		for(StorageElement element : this.content)
		{
			element.ls();
		}
	}

	@Override
	public int getBaseSize()
	{
		return 4;
	}

	@Override
	public int size()
	{
		int size = this.getBaseSize();

		for(StorageElement element : this.content)
		{
			size += element.size();
		}

		return size;
	}
	
	@Override
	public void accept(StorageVisitor visitor)
	{
		for(StorageElement element : this.content)
		{
			element.accept(visitor);
		}
	}
}
