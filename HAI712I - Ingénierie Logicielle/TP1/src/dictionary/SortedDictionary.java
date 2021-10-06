package dictionary;

import java.util.Comparator;

public class SortedDictionary extends OrderedDictionary
{
	private Comparator<Object> comparator;
	
	public SortedDictionary(Comparator<Object> comparator)
	{
		super();
		this.comparator = comparator;
	}

	@Override
	protected int indexOf(Object key)
	{
		int beginIndex = 0;
		int middleIndex = 0;
		int endIndex = this.size();
		
		if(middleIndex < this.size())
		{
			while(beginIndex < endIndex && !this.keys[middleIndex].equals(key))
			{
				middleIndex = (beginIndex + endIndex) / 2;
				
				int compare = this.comparator.compare(this.keys[middleIndex], key);
				if(compare < 0)
				{
					beginIndex = middleIndex + 1;
				}
				else if(compare > 0)
				{
					endIndex = middleIndex - 1;
				}
			}
			
			return this.keys[middleIndex].equals(key) ? middleIndex : -1;
		}
		else
		{
			return -1;
		}
	}

	@Override
	protected int newIndexOf(Object key)
	{
		int oldSize = this.size();
		Object[] oldKeys = this.keys;
		Object[] oldValues = this.values;
		
		this.keys = new Object[oldSize + 1];
		this.values = new Object[oldSize + 1];
	
		int oldIndex = 0;
		int newIndex = 0;
	
		if(oldIndex < oldSize)
		{			
			int emptyIndex = -1;

			for(newIndex = 0; newIndex < this.keys.length; newIndex++)
			{
				int compare = 1;
				
				if(oldIndex < oldSize)
				{
					compare = this.comparator.compare(oldKeys[oldIndex], key);
				}
				
				if(emptyIndex == -1 && compare > 0)
				{
					emptyIndex = oldIndex;
				}
				else
				{
					this.keys[newIndex] = oldKeys[oldIndex];
					this.values[newIndex] = oldValues[oldIndex];
					oldIndex++;
				}
			}
			return emptyIndex;
		}
		else
		{
			return 0;
		}
	}
}
