package dictionary;

public class FastDictionary extends AbstractDictionary
{
	private static final int DEFAULT_SIZE = 10;
	
	public FastDictionary()
	{
		this.keys = new Object[DEFAULT_SIZE];
		this.values = new Object[DEFAULT_SIZE];
	}
	
	@Override
	public int size()
	{
		int size = 0;
		
		for(int i = 0; i < this.keys.length; i++)
		{
			if(this.keys[i] != null)
			{
				size++;
			}
		}
		
		return size;
	}

	@Override
	protected int indexOf(Object key)
	{
		int index = key.hashCode() % this.keys.length;
		
		while(index < this.keys.length && this.keys[index] != null)
		{
			if(this.keys[index].equals(key))
			{
				return index;
			}
			else
			{
				index++;
			}
		}
		
		return -1;
	}

	@Override
	protected int newIndexOf(Object key)
	{
		if(this.mustGrow())
		{
			this.grow();
		}
		
		int index = key.hashCode() % this.keys.length;
		
		while(this.keys[index] != null)
		{
			index++;
		}
		
		return index;
	}

	private boolean mustGrow()
	{
		return (this.size() / this.keys.length) > 0.75;
	}
	
	private void grow()
	{
		int oldSize = this.keys.length;
		Object[] oldKeys = this.keys;
		Object[] oldValues = this.values;
		
		this.keys = new Object[oldSize * 2];
		this.values = new Object[oldSize * 2];
		
		for(int i = 0; i < oldSize; i++)
		{
			if(oldKeys[i] != null)
			{
				int index = this.newIndexOf(oldKeys[i]);
				this.keys[index] = oldKeys[i];
				this.values[index] = oldValues[i];
			}
		}
	}
}
