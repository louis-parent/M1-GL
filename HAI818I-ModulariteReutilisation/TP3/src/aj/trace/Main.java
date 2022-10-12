package aj.trace;

public class Main
{
	private void test1()
	{
		System.out.println("TEST 1");
	}
	
	private void test2()
	{
		System.out.println("TEST 2");
	}
	
	private void test3()
	{
		System.out.println("TEST 3");
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.test1();
		main.test2();
		main.test3();
	}

}
