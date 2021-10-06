package dictionary;

public class UtilisationDictionnaire
{
	public static void test(String name, boolean test)
	{
		if(test)
		{			
			System.out.println(name + " : PASSED");
		}
		else
		{
			System.err.println(name + " : FAILED");
		}
	}
	
	public static void main(String[] args)
	{
		IDictionary dic = new SortedDictionary((Object obj1, Object obj2) -> {
			return obj1.toString().compareTo(obj2.toString());
		});
		
		test("EMPTY", dic.isEmpty());
		test("GET EMPTY", dic.get("Test") == null);
		test("PUT", dic.put("Test", "Test") == dic);
		test("CONTAINS", dic.containsKey("Test"));
		test("SIZE", dic.size() == 1);
		test("GET", dic.get("Test").equals("Test"));
		test("NOT EMPTY", !dic.isEmpty());
		
		for(int i = 25; i >= 0; i--)
		{
			dic.put(i, i);
		}
		
		test("END SIZE", dic.size() == 27);
		System.out.println("DIC : " + dic.toString());
	}

}
