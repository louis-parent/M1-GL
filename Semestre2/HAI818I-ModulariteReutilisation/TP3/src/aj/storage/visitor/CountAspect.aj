package aj.storage.visitor;

import java.util.function.Predicate;

import aj.storage.model.File;

public aspect CountAspect
{
	private static Predicate<File> filter = file -> { return true; };
	
	private static int count = 0;
	
	public static void changeFilter(Predicate<File> filter)
	{
		CountAspect.filter = filter;
	}
	
	public static int getCount()
	{
		return CountAspect.count;
	}
	
	public static void reset()
	{
		CountAspect.count = 0;
	}
	
	pointcut created(File f):
		target(f) &&
		execution(File.new(..));
	
	after(File f): created(f)
	{
		if(filter.test(f))
		{
			CountAspect.count++;
		}
	}
}
