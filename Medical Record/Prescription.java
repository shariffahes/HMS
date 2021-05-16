import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Prescription implements Comparable<Prescription>{
	
	private int PrescriptionID;
	private ArrayList<String> Medicine;
	//instruction of taking the medicine.
	private String Instructions;
	//date of prescribing.
	private LocalDate date;
	//how many days/months/years keep taking the medicine.
	private int Duration;
	//diagnosis of doctor.
	private String diagnosis;
	//to keep id's unique.
	static ArrayList<Integer> idHistory;
	//some medicines can't be taken with other medicines or with specific allergies.
	static HashMap<String,ArrayList<String>> drugAllergy;
	
	public Prescription(ArrayList<String> medicine, String instructions, LocalDate date, int duration,
			String diagnosis) {
		
		//generate 6 digit unique id.
		if(idHistory == null ) idHistory = new ArrayList<Integer>();
		
		do {
			PrescriptionID = new Generator().genrate();
		}while(idHistory.contains(PrescriptionID));
		
		Medicine = medicine;
		Instructions = instructions;
		this.date = date;
		Duration = duration;
		this.diagnosis = diagnosis;
		
		
		if(drugAllergy==null) {
			drugAllergy = new HashMap<String,ArrayList<String>>();
			
			//medicine1 should not be taken with patients taken these medicines or with that allergy.
			drugAllergy.put("Medicine1", new ArrayList<String>(Arrays.asList("diabetes","disease1","polyxionx")));
			drugAllergy.put("polyxionx", new ArrayList<String>(Arrays.asList("Medicine1","flower","disease2")));
		}
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Prescription: \n\tPrescriptionID: " + PrescriptionID + "\n\tMedicine: " + Medicine + "\n\tInstructions: "
				+ Instructions + "\n\tdate: " + date + "\n\tDuration: " + Duration + "\n\tdiagnosis: " + diagnosis + "\n";
	}

	
	public int compareTo(Prescription p) {
		
		if(this.date.isAfter(p.getDate())) return 1;
	    else if(date.isBefore(p.getDate())) return -1;
		else return 0;
			
	}

	
	
	//getters and setters
	public int getPrescriptionID() {
		return PrescriptionID;
	}
	public void setPrescriptionID(int prescriptionID) {
		PrescriptionID = prescriptionID;
	}
	public ArrayList<String> getMedicine() {
		return Medicine;
	}
	public void setMedicine(ArrayList<String> medicine) {
		Medicine = medicine;
	}
	public String getInstructions() {
		return Instructions;
	}
	public void setInstructions(String instructions) {
		Instructions = instructions;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	

}
