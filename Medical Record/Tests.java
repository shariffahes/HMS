import java.time.LocalDateTime;

public class Tests implements Comparable<Tests>  {
	
	public  String typeOfTest;
	public String result;
	public LocalDateTime time;
	
	
	
	public Tests(String typeOfTest, String result, LocalDateTime time) {
		
		this.typeOfTest = typeOfTest;
		this.result = result;
		this.time = time;
	}
	
	public int compareTo(Tests t) {
		if(time.isAfter(t.time)) return 1;
		else if(time.isBefore(t.time)) return -1;
		else return 0;
		
	}

	@Override
	public String toString() {
		return "Test: \n\ttypeOfTest: " + typeOfTest + "\n\tresult: " + result + "\n\ttime: " + time + "\n";
	}
	
	
	
}
