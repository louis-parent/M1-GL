package storage.operation.modifier;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;
import storage.operation.StorageVisitor;

public class JavaCleanVisitor implements StorageVisitor
{

	@Override
	public void visitFile(File file)
	{
		if(file.getName().endsWith(".class"))
		{
			file.rm();
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

}
