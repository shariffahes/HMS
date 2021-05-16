import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalRecord {

	private int MRID;
	private double weight;
	private double height;
	private LocalDate birthDate;
	private ArrayList<String> Diseases;
	private ArrayList<String> allergies;
	private ArrayList<String> Medication;
	private ArrayList<Tests> conductedTests;
	private ArrayList<Prescription> prescriptionsHistory;
	private EmergencyContact emrgencyContact;
	static ArrayList<Integer> IDHistory;
	
	
	public MedicalRecord(double weight, double height, LocalDate birthDate, ArrayList<String> diseases,
			ArrayList<String> allergies,ArrayList<String> medications, EmergencyContact emrgencyContact) {

		
		//First time initialize the id storage history.
	    if(IDHistory == null) IDHistory = new ArrayList<Integer>();
	    
	  //generate unique 6 digits id.
	   //keep looping until the id is unique.
	  		do {
	  			MRID = new Generator().genrate();
	  			
	  		}while(IDHistory.contains(MRID));
	  		IDHistory.add(MRID);
	  		
	  	
	  	
		
		
		
		this.weight = weight;
		this.height = height;
		this.birthDate = birthDate;
		this.Diseases = diseases;
		this.allergies = allergies;
		this.Medication = medications;
		this.emrgencyContact = emrgencyContact;
		
		if(this.allergies == null) this.allergies = new ArrayList<String>();
		if(this.Medication==null) this.Medication = new ArrayList<String>();
		if(this.conductedTests == null) this.conductedTests = new ArrayList<Tests>();
		if(this.Diseases==null) this.Diseases = new ArrayList<String>();
		if(this.prescriptionsHistory == null) this.prescriptionsHistory = new ArrayList<Prescription>();
	}


	
	
	
	public void AddPrescription(Prescription p) {
		prescriptionsHistory.add(p);
	}
	public void AddAllergies(String allergy) {
		allergies.add(allergy);
	}
	
	public void AddDisease(String disease) {
		Diseases.add(disease);
	}
	public void AddMedication(String medicine) {
		Medication.add(medicine);
	}
	public ArrayList<String> getMedication() {
		return Medication;
	}





	public void setMedication(ArrayList<String> medication) {
		Medication = medication;
	}





	@Override
	public String toString() {
		return "\n\tMedicalRecord [MRID: " + MRID + "]\n\t\tweight: " + weight + "\n\t\theight: " + height + "\n\t\tbirthDate: " + birthDate
				+ "\n\t\tDiseases: " + Diseases + "\n\t\tallergies: " + allergies + "\n\t\tMedication: " + Medication
				+ "\n\t\tconductedTests: " + conductedTests + "\n\t\tprescriptionsHistory: " + prescriptionsHistory
				+ "\n\t\temrgencyContact: " + emrgencyContact + "\n";
	}





	//getters and setters
	public int getMRID() {
		return MRID;
	}


	public void setMRID(int mRID) {
		MRID = mRID;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public ArrayList<String> getDiseases() {
		return Diseases;
	}


	public void setDiseases(ArrayList<String> diseases) {
		Diseases = diseases;
	}


	public ArrayList<String> getAllergies() {
		return allergies;
	}


	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}


	public ArrayList<Tests> getConductedTests() {
		return conductedTests;
	}


	public void setConductedTests(ArrayList<Tests> conductedTests) {
		this.conductedTests = conductedTests;
	}


	public ArrayList<Prescription> getPrescriptionsHistory() {
		return prescriptionsHistory;
	}


	public void setPrescriptionsHistory(ArrayList<Prescription> prescriptionsHistory) {
		this.prescriptionsHistory = prescriptionsHistory;
	}


	public EmergencyContact getEmrgencyContact() {
		return emrgencyContact;
	}


	public void setEmrgencyContact(EmergencyContact emrgencyContact) {
		this.emrgencyContact = emrgencyContact;
	}
	
	
	
	
	
}
