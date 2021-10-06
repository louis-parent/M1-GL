package dictionary;

public abstract class AbstractDictionary implements IDictionary
{
	protected Object[] keys;
	protected Object[] values;
	
	@Override
	public Object get(Object key)
	{
		int index = this.indexOf(key);
		
		if(index != -1)
		{
			return this.values[index];			
		}
		else
		{
			return null;
		}
	}

	@Override
	public IDictionary put(Object key, Object value)
	{
		if(!this.containsKey(key))
		{
			int i = this.newIndexOf(key);
			this.keys[i] = value;
			this.values[i] = value;
		}
		
		return this;
	}

	@Override
	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	@Override
	public boolean containsKey(Object key)
	{
		return this.indexOf(key) != -1;
	}
	
	@Override
	public String toString()
	{
		String str = "{";
		
		for(int i = 0; i < this.keys.length; i++)
		{
			if(this.keys[i] != null)
			{
				str += this.keys[i] + ": " + this.values[i] + ", ";
			}
		}
		
		return str + "}";
	}

	protected abstract int indexOf(Object key);
	protected abstract int newIndexOf(Object key);
}
