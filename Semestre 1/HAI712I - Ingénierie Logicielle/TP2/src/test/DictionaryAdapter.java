package test;

import dictionary.IDictionary;

public class DictionaryAdapter implements IDictionary
{
	private withBug.AbstractDictionary inner;
	
	public DictionaryAdapter(withBug.AbstractDictionary inner)
	{
		this.inner = inner;
	}
	
	@Override
	public Object get(Object key)
	{
		try
		{
			return this.inner.get(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IDictionary put(Object key, Object value)
	{
		return new DictionaryAdapter((withBug.AbstractDictionary) this.inner.put(key, value));
	}

	@Override
	public boolean isEmpty()
	{
		return this.inner.isEmpty();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return this.inner.containsKey(key);
	}

	@Override
	public int size()
	{
		return this.inner.size();
	}
	
}
