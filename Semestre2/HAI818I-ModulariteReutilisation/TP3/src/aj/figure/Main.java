package aj.figure;

public class Main
{
	public static void main(String[] args)
	{
		Line line1 = new Line();
		line1.setP1(new Point());
		
		if(MoveTracking.testAndClear())
		{
			System.out.println("Tracked");
		}
		else
		{
			System.out.println("Nothing");
		}
	}
}
