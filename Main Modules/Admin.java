import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Admin extends User {
	
	public String adminName;
	private String address;
	private LocalDate birthDate;
	private double salary;
	private double workHours;
	
	//all admins will share the same reports and users. So any admin can take a look at the reports and users.
	private static ArrayList<Report> reports;
	private static ArrayList<User> users;
	
	
	
	public Admin(String email, String password, String phoneNb, String adminName, String address,
			LocalDate birthDate, double salary, double workHours) {
		super(email, password, phoneNb, userType.Admin);
		this.adminName = adminName;
		this.address = address;
		this.birthDate = birthDate;
		this.salary = salary;
		this.workHours = workHours;
		
		//First time. Initialize the array lists.
		if(users==null) users = new ArrayList<User>();
		if(reports==null) reports = new ArrayList<Report>();
		
		//add the current admin employee to the list of users.
		users.add(this);
		
		//keep sorting users by id's each time a new user is added.
		Collections.sort(users);
		
	}

	
	//view specific report using report Id.
	public void ViewReports(int reportId) throws InputExceptions {
	
		if(String.valueOf(reportId).length() != 6) throw new InputExceptions("report id should be of 6 digits");
		
		if(reports.isEmpty()) {
			System.out.println("No availabel reports");
			return;
		}
		//perform a binary search on reports to find the index of the report. 
		int ind = new BSearch().searchReports(reportId, reports);
		
		//no same id has been found in the array list.
		if(ind == -1 )
			System.out.println("no report with such id");
		else {
			
			//view the report
			System.out.println("Viewing Report: ");
			System.out.print(reports.get(ind));
		}
	}
	
	//admin is responsible to register all users.
	public void addUser(User u) throws InputExceptions {
	
		//Any exception will be detected in user class.
		users.add(u);
		
		//sort by id.
		Collections.sort(users);
		
		System.out.println("Added Successfully");
	}
	
	
	public void removeUser(int id) throws InputExceptions{
		
		if(String.valueOf(id).length()<6) {
			throw new InputExceptions("Invalid id. It must be of 6 digits");
		}
        //perform binary search to extract the index of the user.
		int ind = new BSearch().searchUsers(id, users);
		
		//id not found then no such id in users.
		if(ind == -1 )
			System.out.println("User of id "+id+" can't be found");
		else {
			
			User us = users.get(ind);
			//if it is doctor. It should be removed from the list of doctors.
			if(us.getType() == User.userType.Doctor) {
				Doctor.AllDoctors.remove(us);
				
			}else if(us.getType() == User.userType.Patient) {
				
			//if the removed user is patient. Then this patient should be removed
			//from any doctor's list of patients.
			ArrayList<Doctor> doctors = Doctor.AllDoctors;
			for(int i=0;i<doctors.size();i++) {
					doctors.get(i).getPatients().remove((Patient)us);
				}
			}
			users.remove(ind);
			System.out.println("User of id "+id + " is removed successfully");
			
			//sort after removing.
			Collections.sort(users);
		}
		
	}
	
	//update phone number of user with userID.
	public void updatePhone(int userId, String phoneNb) throws InputExceptions{
		
		//make sure the id is of 6 digits
		if(String.valueOf(userId).length()<6) throw new InputExceptions("Invalid id. it must be of 6 digits.");
		
		//not found in the system. 
		int ind = new BSearch().searchUsers(userId, users);
		if(ind == -1 )
			System.out.println("User of id "+userId+" can't be found");
		else {	
			String oldNumber = users.get(ind).getPhone();
			users.get(ind).setPhone(phoneNb);
			System.out.println("Updated succefully");
			System.out.println("Old number: "+oldNumber+"\nNew number: "+phoneNb);
		}
		
	}
	
	//if admin need to view the doctor schedule for any reason. For instance: need to assign a patient with doctors that are free on specific time.
	public void ViewDoctorsSchedule(int doctorID) {
		
		//search in the users for the doctor id.
		int ind = new BSearch().searchUsers(doctorID, users);
		//not found
		if(ind == -1) {
			System.out.println("No such doctor with id "+doctorID);
			return;
		}else if(users.get(ind).getType() != User.userType.Doctor) { 
			//found but the user is not a doctor.
			System.out.println("This user is not a doctor. ");
			return;
		}
		
	Doctor dr = (Doctor) users.get(ind);
	System.out.println("-Doctor "+ dr.name+" schedule");
	//call the view schedule method in the doctor class.
	dr.ViewSchedule();
	
	}
	
	//admins responsible to assign patient to the doctor based on specific criteria and nurse suggestions as well as patient choice.
	public void AssignPatientToDoc (int patientId, int doctorId) {
		
		//perform binary search for doctor id in users. Make sure the id is for a doctor.
		int ind1 = new BSearch().searchUsers(doctorId, users);
		if(ind1 == -1) {
			System.out.println("No such doctor with id "+doctorId);
			return;
		}else if(users.get(ind1).getType() != User.userType.Doctor) {
			System.out.println("This user is not a doctor. ");
			return;
		}
		
		//perform binary search for patients id in users. Make sure the id is for a patient.
		int ind2 = new BSearch().searchUsers(patientId, users);
		if(ind2 == -1) {
			System.out.println("No such patient with id "+patientId);
			return;
		}else if(users.get(ind2).getType() != User.userType.Patient) {
			System.out.println("This user is not a patient. ");
			return;
		}
		
	Doctor dr = (Doctor) users.get(ind1);
	Patient p =(Patient) users.get(ind2);	
	
	//patient already in doctors array.
	if(dr.patients.contains(p)) {
		System.out.println("patient " +p.name+" already assigned to doctor "+dr.name);
		return;
	}
	
	//add the patient to the list of patients of the specified doctor
	dr.patients.add(p);
	System.out.println("Patient "+p.name+" assigned to doctor "+dr.name+" successfully.");
	}

	

	
	@Override
	public String toString() {
		return super.toString()+"\tadminName: " + adminName + "\n\taddress: " + address + "\n\tbirthDate: " + birthDate + "\n\tsalary: "
				+ salary + "\n\tworkHours: " + workHours + "\n";
	}


	//getters and setters
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}

	public double getSalary() {
		return salary;
	}
	
	public double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}
	public ArrayList<Report> getReports() {
		return reports;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	

	

}
