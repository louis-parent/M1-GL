package storage.model;

import storage.model.container.Directory;
import storage.operation.StorageVisitor;

public interface StorageElement
{
	public abstract String getName();

	public abstract Directory getParent();

	public abstract String absoluteAddress();

	public abstract void ls();
	
	public default void rm()
	{
		this.getParent().rm(this);
	}

	public abstract int getBaseSize();

	public abstract int size();
	
	public abstract void accept(StorageVisitor visitor);
}
