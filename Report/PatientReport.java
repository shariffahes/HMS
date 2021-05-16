import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientReport extends Report {
	
	
	private int nbOfPatient;
	private int nbOfPatientDischarged;
	private int deathsNb;
	private ArrayList<String> Treatment;
	public PatientReport(LocalDate issueDate, int nbOfPatient, int nbOfPatientDischarged, int deathsNb,
			ArrayList<String> treatment) {
		super(issueDate);
		this.nbOfPatient = nbOfPatient;
		this.nbOfPatientDischarged = nbOfPatientDischarged;
		this.deathsNb = deathsNb;
		Treatment = treatment;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "PatientReport: ["+super.reportNb+","+super.issueDate.toString()+"]"  + "\n\tnbOfPatient: " + nbOfPatient + "\n\tnbOfPatientDischarged: " + nbOfPatientDischarged
				+ "\n\tdeathsNb: " + deathsNb + "\n\tTreatment:" + Treatment.toString()+ "\n";
	}





	//getters and setters
	public int getNbOfPatient() {
		return nbOfPatient;
	}
	public void setNbOfPatient(int nbOfPatient) {
		this.nbOfPatient = nbOfPatient;
	}
	public int getNbOfPatientDischarged() {
		return nbOfPatientDischarged;
	}
	public void setNbOfPatientDischarged(int nbOfPatientDischarged) {
		this.nbOfPatientDischarged = nbOfPatientDischarged;
	}
	public int getDeathsNb() {
		return deathsNb;
	}
	public void setDeathsNb(int deathsNb) {
		this.deathsNb = deathsNb;
	}
	public ArrayList<String> getTreatment() {
		return Treatment;
	}
	public void setTreatment(ArrayList<String> treatment) {
		Treatment = treatment;
	}

	
	
	

}
