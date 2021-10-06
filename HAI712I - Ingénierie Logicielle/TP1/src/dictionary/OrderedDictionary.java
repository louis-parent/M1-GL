package dictionary;

public class OrderedDictionary extends AbstractDictionary
{
	public OrderedDictionary()
	{
		this.keys = new Object[0];
		this.values = new Object[0];
	}
	
	@Override
	public int size()
	{
		return this.keys.length;
	}
	
	@Override
	protected int indexOf(Object key)
	{
		for(int i = 0; i < this.size(); i++)
		{
			if(this.keys[i].equals(key))
			{
				return i;
			}
		}
		
		return -1;
	}

	@Override
	protected int newIndexOf(Object key)
	{
		int oldSize = this.size();
		Object[] oldKeys = this.keys;
		Object[] oldValues = this.values;
		
		this.keys = new Object[oldSize + 1];
		this.values = new Object[oldSize + 1];
		
		for(int i = 0; i < oldSize; i++)
		{
			this.keys[i] = oldKeys[i];
			this.values[i] = oldValues[i];
		}
		
		return oldSize;
	}
}
