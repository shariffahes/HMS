import java.time.LocalDate;
import java.util.ArrayList;

public class Report implements Comparable<Report> {
public int reportNb;
public LocalDate issueDate;
static ArrayList<Integer> IDHistory;

public Report(LocalDate issueDate) {
	
	  if(IDHistory == null) IDHistory = new ArrayList<Integer>();
	  //generate unique invoice number;
	  		do {
	  			reportNb = new Generator().genrate();
	  			
	  		}while(IDHistory.contains(reportNb));
	  		IDHistory.add(reportNb);
	
	this.issueDate = issueDate;
	
}


//compare for sorting
public int compareTo(Report u) {
	int issueNb = ((Report) u).reportNb;
	return reportNb - issueNb;
}


}


