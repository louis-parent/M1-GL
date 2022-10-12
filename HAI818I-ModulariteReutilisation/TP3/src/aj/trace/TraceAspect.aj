package aj.trace;

public aspect TraceAspect
{
	pointcut method():
		call(* *.*(..)) && !within(TraceAspect);
	
	Object around(): method()
	{
		System.out.println("Before execution of " + thisJoinPointStaticPart.getSignature().getName());
		Object o = proceed();
		System.out.println("After execution of " + thisJoinPointStaticPart.getSignature().getName());
		return o;
	}
}
