import java.time.LocalDateTime;

public class CheckUpInvoice extends MedicalInvoice {

	
	private double DoctorFees;
	private double HospitalFees;
	
	public CheckUpInvoice(LocalDateTime issueDate,
			LocalDateTime dueDate, double doctorFees, double hospitalFees) throws InputExceptions {
		super((doctorFees+hospitalFees), issueDate, dueDate);
		DoctorFees = doctorFees;
		HospitalFees = hospitalFees;
	}
	
	
	
	
	
	@Override
	public String toString() {
		
		
		return "CheckUpInvoice: [ "+super.getInvoiceNumber()+","+super.getIssueDate().toString()+"]"+"\n\tDoctorFees: " + DoctorFees + "\n\tHospitalFees:" + HospitalFees + 
				"\n\ttotalCost: "+super.getTotalCost()+"\n\tTax: "+super.getTax()+"\n\tDue Date: "+super.getDueDate().toString()+"\n";
	}





	//getters and setters
	public double getDoctorFees() {
		return DoctorFees;
	}
	public void setDoctorFees(double doctorFees) {
		DoctorFees = doctorFees;
	}
	public double getHospitalFees() {
		return HospitalFees;
	}
	public void setHospitalFees(double hospitalFees) {
		HospitalFees = hospitalFees;
	}
	
	
}
