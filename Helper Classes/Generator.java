import java.util.Random;

public class Generator{
	
	public int genrate() {
		
		Random rand = new Random();
		int number = rand.nextInt(999999);
		
		String n =  String.valueOf(number);
	
			int i = n.length();
			while(i<6) {
				n+="0";
				i++;
		
		}
		return Integer.parseInt(n);
	}

}
