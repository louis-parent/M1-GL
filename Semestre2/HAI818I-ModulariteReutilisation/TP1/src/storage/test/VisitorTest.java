package storage.test;

import java.util.Set;

import storage.model.File;
import storage.model.container.NodeDirectory;
import storage.model.container.RootDirectory;
import storage.operation.general.CountAllGreaterThanTenVisitor;
import storage.operation.general.CountVisitor;
import storage.operation.general.FindVisitor;
import storage.operation.modifier.JavaCleanVisitor;
import storage.operation.modifier.ResetVisitor;

public class VisitorTest
{
	private RootDirectory root;
	
	private void initFileTree()
	{
		this.root = new RootDirectory();
		
		new File(this.root, "ReadMe", "Hello there");
		
		NodeDirectory srcDir = new NodeDirectory(this.root, "src");
		new File(srcDir, "Main.java", "System.out.println(\"Hello World\");");
		
		NodeDirectory binDir = new NodeDirectory(this.root, "bin");
		new File(binDir, "Main.class", "ùù*ù$;:ù$ù,;:BLABLA$ùù$*ù$ù*:!;COMPILE$ù*$ù$$");
	}
	
	public void testResetVisitor()
	{
		this.initFileTree();
		
		assert this.root.size() == 4 + 11 + 4 + 34 + 4 + 45;
		
		ResetVisitor visitor = new ResetVisitor();
		this.root.accept(visitor);
		
		assert this.root.size() == 4 + 4 + 4;
	}
	
	public void testCountAllGreaterThanTenVisitor()
	{
		this.initFileTree();
				
		CountAllGreaterThanTenVisitor visitor = new CountAllGreaterThanTenVisitor();
		this.root.accept(visitor);
		
		assert visitor.getCount() == 3;
	}
	
	public void testFindVisitor()
	{
		this.initFileTree();
		
		FindVisitor visitor = new FindVisitor("Main.java");
		this.root.accept(visitor);
		
		assert visitor.getFound().equals(Set.of("/src"));
	}
	
	public void testJavaCleanVisitor()
	{
		this.initFileTree();
		
		JavaCleanVisitor visitor1 = new JavaCleanVisitor();
		this.root.accept(visitor1);
		
		CountVisitor visitor2 = new CountVisitor(file -> { return true; });
		this.root.accept(visitor2);
		
		assert visitor2.getCount() == 2;
	}
	
	public void testGenericCountVisitor()
	{
		this.initFileTree();
				
		CountVisitor visitor = new CountVisitor(file -> {
			return file.getName().endsWith(".java");
		});
		this.root.accept(visitor);
		
		assert visitor.getCount() == 1;
	}
	
	public static void main(String[] args)
	{
		VisitorTest test = new VisitorTest();
		
		System.out.print("Test Reset Visitor : ");
		test.testResetVisitor();
		System.out.println("PASSED");
		
		System.out.print("Test Count All size > 10 Visitor : ");
		test.testCountAllGreaterThanTenVisitor();
		System.out.println("PASSED");
		
		System.out.print("Test Find Visitor : ");
		test.testFindVisitor();
		System.out.println("PASSED");

		System.out.print("Test Java Clean Visitor : ");
		test.testJavaCleanVisitor();
		System.out.println("PASSED");
		
		System.out.print("Test Generic Count Visitor : ");
		test.testGenericCountVisitor();
		System.out.println("PASSED");
	}
}
