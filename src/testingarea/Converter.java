package testingarea;

public class Converter {

	public static void main(String[] args) {
		
		String Numbers = "[uno, duo, tres]";
		Convert(Numbers);
		
	}
//	String Numbers = "[uno, duo, tres]";
//	Convert(Numbers);
	public static void Convert(String x){
		String c =x.substring(1, x.length()-1);
		String[] cA = c.split(", ");
		for(int i=0; i < cA.length; i++){
			System.out.println(cA[i]); // uno duo tres
		}
	}

}
