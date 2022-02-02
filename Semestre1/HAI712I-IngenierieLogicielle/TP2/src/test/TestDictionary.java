package test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.jupiter.api.Test;

import dictionary.IDictionary;

public abstract class TestDictionary
{
	private IDictionary dic;
	
	public abstract void setUp();
	
	protected void setDictionaryToTest(IDictionary dictionary)
	{
		this.dic = dictionary;
	}
	
	@Test
	public void testEmptyDico()
	{
		assertThat(this.dic.size(), is(0));
		assertThat(this.dic.isEmpty(), is(true));
	}
	
	@Test
	public void testAddOneElementToEmptyDico()
	{
		this.dic.put("key", "value");
		assertThat(this.dic.size(), is(1));
		assertThat(this.dic.containsKey("key"), is(true));
		assertThat(this.dic.get("key"), equalTo("value"));
	}
	
	@Test
	public void testAddTwoElementsToEmptyDico()
	{
		this.dic.put("key1", "value1");
		this.dic.put("key2", "value2");
		
		assertThat(this.dic.size(), is(2));
		assertThat(this.dic.containsKey("key1"), is(true));
		assertThat(this.dic.containsKey("key2"), is(true));
		
		assertThat(this.dic.get("key1"), equalTo("value1"));
		assertThat(this.dic.get("key2"), equalTo("value2"));
	}
	
	@Test
	public void testPutOverExistingElementOnEmptyDico()
	{
		this.dic.put("key", "value1");
		this.dic.put("key", "value2");
		
		assertThat(this.dic.size(), is(1));
		assertThat(this.dic.containsKey("key"), is(true));
		assertThat(this.dic.get("key"), equalTo("value2"));
	}
	
	@Test
	public void testMassivePutOnEmptyDico()
	{
		for(int i = 0; i < 10000; i++)
		{
			this.dic.put("index: " + i, "element: " + i);
			assertThat(this.dic.size(), is(i+1));
		}
		
	}
	
	@Test
	public void testGetOnEmptyDico()
	{
		assertThat(this.dic.get("key"), nullValue());
	}
}
