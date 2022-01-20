package storage.operation.modifier;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;
import storage.operation.StorageVisitor;

public class ResetVisitor implements StorageVisitor
{

	@Override
	public void visitFile(File file)
	{
		file.setContent("");
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
