package storage.operation.general;

public class CountAllGreaterThanTenVisitor extends CountVisitor
{
	public CountAllGreaterThanTenVisitor()
	{
		super(file -> { 
			return file.size() > 10;
		});
	}
}
