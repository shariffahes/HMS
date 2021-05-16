import java.time.LocalDateTime;

public class TestInvoice extends MedicalInvoice {

	
	private double TestCosts;
	private double HospitalFees;

	
	public TestInvoice(LocalDateTime issueDate, LocalDateTime dueDate,
			double testCosts, double hospitalFees) throws InputExceptions {
		super((testCosts+hospitalFees), issueDate, dueDate);
		TestCosts = testCosts;
		HospitalFees = hospitalFees;
	}
	
	@Override
	public String toString() {
		return "TestInvoice ["+super.getInvoiceNumber()+","+super.getIssueDate().toString()+"]"+"\n\tTestCosts: " + TestCosts + "\n\tHospitalFees: " + HospitalFees
				+"\n\ttotalCost: "+super.getTotalCost()+"\n\tTax: "+super.getTax()+"\n\tDue Date: "+super.getDueDate().toString()+"\n";
	}


	//getters and setters
	public void setTestCosts(double testCosts) {
		TestCosts = testCosts;
	}	
	
	public double getTestCosts() {
		return TestCosts;
	}
	
	public double getHospitalFees() {
		return HospitalFees;
	}
	public void setHospitalFees(double hospitalFees) {
		HospitalFees = hospitalFees;
	}
	
	
}
