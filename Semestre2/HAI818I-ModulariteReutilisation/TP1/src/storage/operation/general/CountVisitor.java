package storage.operation.general;

import java.util.function.Predicate;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;
import storage.operation.StorageVisitor;

public class CountVisitor implements StorageVisitor
{
	private int count;
	private Predicate<File> predicate;
	
	public CountVisitor(Predicate<File> predicate)
	{
		this.count = 0;
		this.predicate = predicate;
	}
	
	@Override
	public void visitFile(File file)
	{
		if(this.predicate.test(file))
		{			
			this.count++;
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

	public int getCount()
	{
		return this.count;
	}
}
