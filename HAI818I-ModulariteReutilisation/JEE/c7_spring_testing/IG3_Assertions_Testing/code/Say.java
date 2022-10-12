public class Say {
    public static void main(String[] args) {
	assert args.length >= 1 : "Bad usage of the class. Please provide at least one argument";
	for(String s : args) {
	    System.out.println(s);
	}
    }
}
