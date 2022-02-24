package aj.storage.visitor;

import aj.storage.model.File;

public aspect ResetAspect
{
	pointcut created(File f):
		target(f) &&
		execution(File.new(..));
	
	after(File f): created(f)
	{
		f.setContent("");
	}
}
