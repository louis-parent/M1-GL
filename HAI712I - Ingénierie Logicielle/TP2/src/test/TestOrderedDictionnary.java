package test;
import org.junit.jupiter.api.BeforeEach;

import dictionary.OrderedDictionary;

public class TestOrderedDictionnary extends TestDictionary
{
	@Override
	@BeforeEach
	public void setUp()
	{
		this.setDictionaryToTest(new OrderedDictionary());
	}
}
