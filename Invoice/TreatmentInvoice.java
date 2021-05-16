import java.time.LocalDateTime;

public class TreatmentInvoice extends MedicalInvoice {

	
	
	private int stayDuration;
	private double typeOfRoomCost;
	private double medicationCost;
	private double surgicalCosts;
	
	
	public TreatmentInvoice(LocalDateTime issueDate,
			LocalDateTime dueDate, int stayDuration, double typeOfRoom, double medication, double surgicalCosts) throws InputExceptions {
		super((stayDuration*typeOfRoom)+medication+surgicalCosts, issueDate, dueDate);
		this.stayDuration = stayDuration;
		this.typeOfRoomCost = typeOfRoom;
		this.medicationCost = medication;
		this.surgicalCosts = surgicalCosts;
	}


	
	@Override
	public String toString() {
		return "TreatmentInvoice ["+super.getInvoiceNumber()+ ","+super.getIssueDate()+"]"+"\t\nstayDuration: " + stayDuration + "\n\ttypeOfRoomCosts: " + typeOfRoomCost + "\n\tmedicationCosts: "
				+ medicationCost + "\n\tsurgicalCosts: " + surgicalCosts + "\n\ttotalCost: "+super.getTotalCost()+
				"\n\tTax: "+super.getTax()+"\n\tDue Date: "+super.getDueDate().toString()+"\n";
	}



	//getters and setters
	public int getStayDuration() {
		return stayDuration;
	}


	public void setStayDuration(int stayDuration) {
		this.stayDuration = stayDuration;
	}


	public double getTypeOfRoomCost() {
		return typeOfRoomCost;
	}


	public void setTypeOfRoom(double typeOfRoomCost) {
		this.typeOfRoomCost = typeOfRoomCost;
	}


	public double getMedication() {
		return medicationCost;
	}


	public void setMedication(double medicationCost) {
		this.medicationCost = medicationCost;
	}


	public double getSurgicalCosts() {
		return surgicalCosts;
	}


	public void setSurgicalCosts(double surgicalCosts) {
		this.surgicalCosts = surgicalCosts;
	}
	
	

	
}
