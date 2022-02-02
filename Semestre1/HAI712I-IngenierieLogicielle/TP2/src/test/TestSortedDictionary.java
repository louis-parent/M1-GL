package test;
import org.junit.jupiter.api.BeforeEach;

import dictionary.SortedDictionary;

public class TestSortedDictionary extends TestDictionary
{
	@Override
	@BeforeEach
	public void setUp()
	{
		this.setDictionaryToTest(new SortedDictionary((obj1, obj2) -> {
			return obj1.toString().compareTo(obj2.toString()); 
		}));
	}
}
