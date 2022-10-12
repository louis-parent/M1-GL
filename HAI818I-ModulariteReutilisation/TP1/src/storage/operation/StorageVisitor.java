package storage.operation;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;

public interface StorageVisitor
{
	public abstract void visitFile(File file);
	public abstract void visitDirectory(NodeDirectory directory);
	public abstract void visitRoot(RootDirectory root);
}
