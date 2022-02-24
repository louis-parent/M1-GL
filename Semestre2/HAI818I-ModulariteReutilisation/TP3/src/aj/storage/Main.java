package aj.storage;

import aj.storage.model.File;
import aj.storage.model.container.NodeDirectory;
import aj.storage.model.container.RootDirectory;
import aj.storage.visitor.CountAspect;

public class Main
{
	public static void main(String[] args)
	{
		CountAspect.changeFilter(file -> {
			return !file.getName().contains(".class");
		});
		
		RootDirectory root = new RootDirectory();
		
		new File(root, "ReadMe", "Hello there");
		
		NodeDirectory srcDir = new NodeDirectory(root, "src");
		new File(srcDir, "Main.java", "System.out.println(\"Hello World\");");
		
		NodeDirectory binDir = new NodeDirectory(root, "bin");
		new File(binDir, "Main.class", "ùù*ù$;:ù$ù,;:BLABLA$ùù$*ù$ù*:!;COMPILE$ù*$ù$$");
		
		root.ls();
		System.out.println(CountAspect.getCount() + " File(s)");
		System.out.println("Root size : " + root.size());
	}
}
