package storage.operation.general;

import java.util.Collection;
import java.util.HashSet;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;
import storage.operation.StorageVisitor;

public class FindVisitor implements StorageVisitor
{
	private String name;
	private Collection<String> found;
	
	public FindVisitor(String name)
	{
		this.name = name;
		this.found = new HashSet<String>();
	}

	@Override
	public void visitFile(File file)
	{
		if(file.getName().equals(name))
		{
			this.found.add(file.absoluteAddress());
		}
	}

	@Override
	public void visitDirectory(NodeDirectory directory)
	{
		
	}

	@Override
	public void visitRoot(RootDirectory root)
	{
		
	}
	
	public Collection<String> getFound()
	{
		return this.found;
	}
}
