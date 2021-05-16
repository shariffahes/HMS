import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class Doctor extends User
{
	
	
	
	public String name;
	private String address;
	private double salary;
	private LocalDate birthdate;
	private double workHours;
	public  MedicalField specialty;
	public ArrayList<LocalDateTime> schedule;
	ArrayList<Patient> patients;
	
	//all the doctors in the hospital
	public static ArrayList<Doctor> AllDoctors;
	
	//different medical fields
	 static enum MedicalField {
		Audiologist,Cardiologist, Dentist, Neurologist, Pediatrician, Psychiatrist, Surgeon, other
	}
	

	public Doctor(String email, String password, String phoneNb, String name,
			String address,LocalDate birthdate, double salary, double workHours, MedicalField speciality) {
		
		
		super( email, password, phoneNb, User.userType.Doctor);
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.workHours = workHours;
		this.specialty = speciality;
		this.birthdate = birthdate;
		
		if(AllDoctors == null) AllDoctors = new ArrayList<Doctor>();
		schedule = new ArrayList<LocalDateTime>();
		patients = new ArrayList<Patient>();
		AllDoctors.add(this);
		
		//sort doctor according to their specialty.
		Collections.sort(AllDoctors, new Comparator<Doctor>() {
			public int compare(final Doctor Dr1,Doctor Dr2) {
			return Dr1.specialty.ordinal() - Dr2.specialty.ordinal() ;
			}
		});

	}
	
	

	
	public void ViewMedicalRecord(int patientId) throws InputExceptions{
		//id must be of 6 digits.
		if(String.valueOf(patientId).length()<6) throw new InputExceptions("Invalid id. It must be of 6 digits");
		
		//sort the patients according to their id's.
		Collections.sort(patients);
		//perform binary search.
		int ind  = new BSearch().searchPatients(patientId, patients);
		//not found
		if(ind == -1) {
			System.out.println("No patient with such id( "+patientId+")");
			return;
		}
		//get the medical record for this patient.
		MedicalRecord mr = patients.get(ind).getMR();
		
		System.out.println("Viewing "+patients.get(ind).name+ "'s medical record: ");
		System.out.println(mr);
	}
	
	//doctor needs to prescribe for patient. Prescription stored in MR.
	public void createPrescription(int patientId, Prescription prescription) throws InputExceptions{
		//id must be of 6 digits.
		if(String.valueOf(patientId).length()<6) throw new InputExceptions("Invalid id. It must be of 6 digits");
		
		//sort patients by id.
		Collections.sort(patients);
		//perform binary search for id.
	int ind  = new BSearch().searchPatients(patientId, patients);
		//not found.
		if(ind == -1) {
			System.out.println("No patient with such id( "+patientId+")");
			return;
		
		}
		//for each medicine in the doctor's prescription.
		prescription.getMedicine().forEach((medicine)->{
		
			//for each element in the drug allergy.
			for(Entry<String, ArrayList<String>> entry : Prescription.drugAllergy.entrySet()) {
				
				//get the specified drug and what allergic with.
				String drug = entry.getKey();
				//when the prescribed medicine is found in the drugs that dangerous with some other meds or allergies.
				if(drug.equalsIgnoreCase(medicine)) {
					
				//get what this medicine is dangerous with.
				ArrayList<String> drugAllergies = entry.getValue();
				
				//go to the patient's MR and check if this patient has any allergies that matches the prescribed drug allergy.
				patients.get(ind).getMR().getAllergies().forEach((Allergy)->{
					
					//if you found any prescribe another.
					if(drugAllergies.contains(Allergy)){
					System.out.println("Prescribe another medication. Drug "+medicine+" interact with allergy "+Allergy);
					
				}
				});
				
				//go to the patient's MR and check if he/she is taking any medication that may react with the prescribed drug.
				patients.get(ind).getMR().getMedication().forEach((med)->{
					//if found any. alert doctor another
					if(drugAllergies.contains(med)) {
						System.out.println("Prescribe another medication. Drug "+medicine+" interact with drug "+med);
						
					}
				});
				
				}	
			} 
		});
		
		
	//Add prescription after checking it's safety for the patient.
	patients.get(ind).getMR().AddPrescription(prescription);
	
	Collections.sort(patients.get(ind).getMR().getPrescriptionsHistory());
	//prescription in the patient's medical record.
	System.out.println("Prescription for "+patients.get(ind).name+" added succefully.");
		
	}
	
	//Doctor viewing his own schedule.
	public void ViewSchedule() {
		
		//if schedule is still empty.
		if(schedule.isEmpty()||schedule==null) {
			System.out.println("No appointments to show.");
		}else {
			
			System.out.println("Upcoming appointments: ");
			//sort schedule according to date.
			Collections.sort(schedule);
			//get the index of the dates that are after the current date by binary search.
			LocalDateTime currDate = LocalDateTime.now();
			int currDateIndex = new BSearch().searchAppointments(currDate, schedule);
			
			//if all dates are older than the current date, then no appointment to show.
			if(currDateIndex  == -1) {
				System.out.println("No Upcoming Appointments.");
				return;
			}
			
			//start from the index of the dates that are after current date and present them.
			for(int i=currDateIndex;i<schedule.size();i++) {
				LocalDateTime date = schedule.get(i);
				
				System.out.println("Appointment "+(i-currDateIndex+1)+" at "+date);
			}
	
		}
	}
	



	@Override
	public String toString() {
		return super.toString()+"\tname: " + name + "\n\taddress: " + address + "\n\tsalary: " + salary + "\n\tbirthdate: " + birthdate
				+ "\n\tworkHours: " + workHours + "\n\tspecialty: " + specialty + "\n\tschedule: " + schedule + "\n\tpatients: "
				+ patients + "\n";
	}
	
	//setters and getters
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	
	
	
	

}
