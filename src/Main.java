import com.java.framework.arguments.ArgumentsManager;

public class Main {
	private static ArgumentsManager arguments = new ArgumentsManager("-", "/", ":", false);
	
	public static void main(String[] args) {
		arguments.newParameter("ip", "localhost");
		
		arguments.parseArguments(args);
		
		System.out.println(arguments.getParameter("ip"));
	}
}
