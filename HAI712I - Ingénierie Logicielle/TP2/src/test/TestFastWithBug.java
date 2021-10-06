package test;

import org.junit.jupiter.api.BeforeEach;

public class TestFastWithBug extends TestDictionary
{
	@Override
	@BeforeEach
	public void setUp()
	{
		this.setDictionaryToTest(new DictionaryAdapter(new withBug.FastDictionary()));
	}
}
