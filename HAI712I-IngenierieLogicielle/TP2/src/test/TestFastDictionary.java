package test;
import org.junit.jupiter.api.BeforeEach;

import dictionary.FastDictionary;

public class TestFastDictionary extends TestDictionary
{
	@Override
	@BeforeEach
	public void setUp()
	{
		this.setDictionaryToTest(new FastDictionary());
	}
}
