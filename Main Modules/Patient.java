import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Patient extends User {

	public String name;
	private String address;
	//all medical invoice
	private ArrayList<MedicalInvoice> medicalInvoice;
	//room -1 means no room assigned to the patient.
	private int room = -1;
	//the appointment and the associated doctor
	private HashMap<LocalDateTime,Doctor> appointments;
	private MedicalRecord MR;
	
	public Patient(String email, String password, String phoneNb, User.userType type, String name, String address,
		 MedicalRecord mR) {
		super(email, password, phoneNb, type);
		this.name = name;
		this.address = address;
		MR = mR;
		medicalInvoice = new ArrayList<MedicalInvoice>();
		appointments = new HashMap<LocalDateTime,Doctor>();
	}

	
	
	//last prescription
public void viewLatestPrescription() {
	ArrayList<Prescription> p = MR.getPrescriptionsHistory();
	
	//prescriptions is empty.
	if(p==null||p.isEmpty()) {
		System.out.println("No prescription found");
	}else {
	System.out.println("Viewing latest prescription: ");
	System.out.println(p.get(p.size()-1));
	}
	
}

//to view all prescriptions.
public void ViewPrescription() {
	//get prescription array from medical record.
	ArrayList<Prescription> p = MR.getPrescriptionsHistory();
	System.out.println("Viewing all prescription");
	if(p.isEmpty()||p==null) {
		System.out.println("No previous prescriptions");
		return;
	}
	System.out.println(p);
}

//view medical record information.
public void ViewMedicalRecord() {

	System.out.println("Viewing medical record: ");
	System.out.println(MR);
}
//view all doctors according to specialty
public void ViewDoctorsList(Doctor.MedicalField specialty) {
	//get all doctors.
	ArrayList<Doctor> doctorsList = Doctor.AllDoctors;
	
	//all doctors are sorted according to their medical field.
	//find first occurrence of specified medical field.
	int ind = new BSearch().searchDoctors(specialty, doctorsList);
	if(ind == -1) {
		System.out.println("Currently there is no doctors with this medical field in our hospital");
		return;
	}
	
	System.out.println("All doctors with specialty "+specialty+": ");
	for(int i=ind;i<doctorsList.size();i++) {
		
		//keep iterating until medical field changes.
		if(doctorsList.get(i).specialty != specialty) break;
		Doctor dr = doctorsList.get(i);
		System.out.println("Doctor "+(i-ind+1)+":\nName: Dr. "+ dr.name+"\nEmail: "+dr.getEmail()+"\nPhone: "+dr.getPhone()
		+"\nSpecialty: "+dr.specialty+"\nSchedule: "+dr.schedule+"\n");
	}
	
	
}

//view the last test result.
public void ViewLatestReport() {
	
	ArrayList<Tests> test = MR.getConductedTests();
	
	//if null
	if(test==null || test.isEmpty()) System.out.println("No tests found");
	else {
		Collections.sort(test);
		System.out.println("Viewing the latest test: ");
		System.out.print(test.get(test.size()-1));
		
	}
}

//view all appointments.
public void ViewAllAppointment() {
	//if empty
	if(appointments.isEmpty()||appointments==null) {
		System.out.println("No appointments to show.");
	}else {
		//list the upcoming appointments.
		System.out.println("Upcoming appointments: ");
		
		//sort the keys according to the date
		ArrayList<LocalDateTime> sortedAppointmentTime = new ArrayList(appointments.keySet());
		Collections.sort(sortedAppointmentTime);
		
		//check the current date
		LocalDateTime currDate = LocalDateTime.now();
		//perform special binary search to check the closest date.
		int currDateIndex = new BSearch().searchAppointments(currDate, sortedAppointmentTime);
		
		if(currDateIndex==-1) {
			System.out.println("No upcoming appointments.");
			currDateIndex = appointments.size();
		}else {
		//start from that date and after it
		for(int i=currDateIndex;i<appointments.size();i++) {
			LocalDateTime key = sortedAppointmentTime.get(i);
			Doctor dr = appointments.get(key);
			System.out.println("Appointment 1: Doctor: "+dr.name+", Time: "+key);
		}
		}
	
	   //list previous(done) appointments.
		//it is sorted then 0 is the oldest.
		System.out.println("Previous Appointments: ");
		int i=0;
		
		if(i==currDateIndex) {
			System.out.println("No old Appointments.");
			return;
		}
		for( i=0;i<currDateIndex;i++) {
			LocalDateTime key = sortedAppointmentTime.get(i);
			Doctor dr = appointments.get(key);
			System.out.println("Appointment 1: Doctor: "+dr.name+", Time: "+key);
		}
	}
}

public void ReserveAppointment(LocalDateTime time,int DoctorId) throws InputExceptions {
	
	
	if(String.valueOf(DoctorId).length()!=6) throw new InputExceptions("Invalid id. it must be of 6 digits");
	
	
	int ind = -1;
	ArrayList<Doctor> dr = Doctor.AllDoctors;
	//search for the doctor. We didn't perform binary search due to the fact that doctors are sorted according to field and not id.
	for(int i=0;i<dr.size();i++) {
	
		if(dr.get(i).getId() == DoctorId) ind = i;
		
	}
	//not found.
	if(ind == -1) {
		System.out.println("No such doctor with id "+DoctorId);
		return;
	}
	
	if(time.isBefore(LocalDateTime.now())) {
		System.out.println("You can't reserve appointment in the past date!!");
		return;
	}

	//if there is any same time appointment for the patient. Alert.
	
	if(appointments.containsKey(time)) {
		System.out.print("Reservation with Dr. "+dr.get(ind).name+" failed.\nReason: Time Contradiction.\nYou already have an appointment with Dr. "+ appointments.get(time).name+" on "+time);
		return;
	}
	
	
	ArrayList<LocalDateTime> doctorSchedule = dr.get(ind).schedule;
	Collections.sort(doctorSchedule);
	
	//perform binary search to get the schedule after desired time.
	int timeIndex = new BSearch().searchAppointments(time, doctorSchedule);
	
	if(timeIndex !=-1) {
		
	LocalDateTime appointmentAfterMe = doctorSchedule.get(timeIndex);
	LocalDateTime appointmentBeforeMe = doctorSchedule.get(timeIndex-1);
	
	
	//In order to the appointment to be reserved the time of appointment should be available in the doctor's schedule.
	//Between each appointment there is 5 min break for the doctor.
	//So we check from the schedule the appointment after me and before me in which there should be a 10 min difference.
	//if there is no 10 min difference. then appointment need to be rescheduled.
	if(!appointmentAfterMe.isAfter(time.plusMinutes(5)) || !appointmentBeforeMe.isBefore(time.minusMinutes(5))){
		
		System.out.println("This time has been already reserved by someone. Try different timing.");
		return;
	  }
	}
	
	//add appointment to patient.
	appointments.put(time,dr.get(ind));
	
	//add appointment to doctor's schedule.
	dr.get(ind).schedule.add(time);
	
    System.out.println("Your appointment with Dr. " + dr.get(ind).name + " on "+time+" is reserved. ");
    

}



	
	@Override
public String toString() {
	return super.toString()+ "\tname: " + name + "\n\taddress: " + address + "\n\tmedicalInvoice: " + medicalInvoice + "\n\troom: " + room
			+ "\n\tappointments: " + appointments + "\n\tMR: " + MR + "\n";
}




	//getters and setters:
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<MedicalInvoice> getMedicalInvoice() {
		return medicalInvoice;
	}

	public void setMedicalInvoice(ArrayList<MedicalInvoice> medicalInvoice) {
		this.medicalInvoice = medicalInvoice;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public HashMap<LocalDateTime, Doctor> getAppointments() {
		return appointments;
	}

	public void setAppointments(HashMap<LocalDateTime, Doctor> appointments) {
		this.appointments = appointments;
	}

	public MedicalRecord getMR() {
		return MR;
	}

	public void setMR(MedicalRecord mR) {
		MR = mR;
	}



	
	
	
	
	
	
	
}
