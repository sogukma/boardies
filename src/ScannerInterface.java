import java.util.Scanner;

public class ScannerInterface {

	public ScannerInterface()
	{
	
	}
	
	public static int scan(String text)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println(text);
		int answer = scan.nextInt();
		return answer;
		
	}
	
	public static String scanString(String text)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println(text);
		String answer = scan.next();
		return answer;
		
	}
}
