import java.time.LocalDate;

public class Covid19Report extends Report {

	public int nbOfTests;
	public int positiveCasesNb;
	public int NegativeCasesNb;
	public int HospitalizedNb;
	
	
	public Covid19Report(LocalDate issueDate, int nbOfTests, int positiveCasesNb, int negativeCasesNb,
			int hospitalizedNb) {
		super(issueDate);
		this.nbOfTests = nbOfTests;
		this.positiveCasesNb = positiveCasesNb;
		NegativeCasesNb = negativeCasesNb;
		HospitalizedNb = hospitalizedNb;
	}


	@Override
	public String toString() {
		return "\tCovid19Report: ["+super.reportNb+","+super.issueDate.toString()+"]"+"\n\t-nbOfTests: " + nbOfTests + "\n\t-positiveCasesNb: " + positiveCasesNb + "\n\t-NegativeCasesNb:"
				+ NegativeCasesNb + "\n\t-HospitalizedNb: " + HospitalizedNb + "\n";
	}
	
	
	


}
