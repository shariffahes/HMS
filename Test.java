import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {

	public static void main(String[] args) {
		
		try {
		LocalDate birthDate = LocalDate.of(2001, 01, 01);
		Admin admin1 = new Admin("test@gmail.com","123456","70249036","Alex Bernard","Beirut",birthDate,1000,10);
		admin1.setId(388532);
		
		birthDate =  LocalDate.of(1999, 05, 25);
		Admin admin2 = new Admin("admin2@gmail.com","123456","70908036","Al Ber","Beirut",birthDate,1300,13);
		admin2.setId(426174);
		
		birthDate =  LocalDate.of(1995, 11, 25);
		
	    Doctor.MedicalField speciality =Doctor.MedicalField.Pediatrician;
	   
		Doctor Dr1 = new Doctor("doctor1@gmail.com","doctor123.","71149936","Faraday","Beirut",birthDate,3200,14,speciality);
	    Dr1.setId(420244);
		admin1.addUser(Dr1);
		admin1.verifyLogin(388532,"123456");
		LocalDateTime time = LocalDateTime.of(LocalDate.of(2020, 5, 10), LocalTime.of(18, 00));
	    Dr1.schedule.add(time);
		//admin2.removeUser(420244);
	   
	   
		birthDate =  LocalDate.of(1995, 11, 25);
	    Doctor Dr2 = new Doctor("doctor2@gmail.com","dr0123.","72149936","Albert","Beirut",birthDate,1100,9,Doctor.MedicalField.Audiologist);
	    Dr2.setId(281233);
	    admin2.addUser(Dr2);
	    time = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 00));
	    Dr2.schedule.add(time);
	    time = LocalDateTime.of(birthDate, LocalTime.NOON);
	    Dr2.schedule.add(time);
	    time = LocalDateTime.of(LocalDate.of(2021, 05, 25), LocalTime.NOON);
	    Dr2.schedule.add(time);
		
		//admin1.updatePhone(281233,"81234122");
		//admin1.ViewDoctorsSchedule(420244);
	    
	    birthDate = LocalDate.of(2000, 01, 12);
	    EmergencyContact ec = new EmergencyContact("Ahmad","7122134","Zahle","ec1@hotmail.com","Husband");
	    MedicalRecord mr = new MedicalRecord(79.3,192,birthDate,null,null,null,ec);
	    mr.AddDisease("Diabetes");
	    mr.AddMedication("DiabetA");
	    mr.AddMedication("polx");
	    
	    Patient patient1 = new Patient("patient1@gmail.com","patient123.","71123321",User.userType.Patient,"Alice Smith","Zahle",mr);
		patient1.setId(689092);
	    admin1.addUser(patient1);
	    
	    
	    birthDate = LocalDate.of(1988, 10, 11); 
	    ec = new EmergencyContact("hasan","7212323","Beirut","ec2@hotmail.com","Brother");
	    mr = new MedicalRecord(72.3,185.5,birthDate,null,null,null,ec);
	    mr.AddAllergies("flower");
	    mr.AddDisease("disease1");
	    mr.AddDisease("disease2");
	    mr.AddMedication("ploxionx");
	    
	    Patient patient2 = new Patient("patient2@gmail.com","patient223.","03214752",User.userType.Patient,"Rima Smith","Beirut",mr);
		patient2.setId(638810);
	    admin1.addUser(patient2);
	    
	    
	   admin1.AssignPatientToDoc(638810, 281233);
	  admin1.AssignPatientToDoc(689092, 281233);
	   
	    Report rep = new Covid19Report(LocalDate.of(2021, 5, 9),10000,700,10000-700,400);
	    rep.reportNb = 201875;
	    admin1.getReports().add(rep);
	   
	    Prescription pres = new Prescription(new ArrayList<String>(Arrays.asList("med1")), "1 pill After lunch",LocalDate.of(2021, 1, 19), 10,"diabetes");
	  Dr2.createPrescription(638810, pres);
	   
	   
	   //pres = new Prescription(new ArrayList<String>(Arrays.asList("med2")), "2 pill before sleep",LocalDate.now(), 10,"diabetes");
	  // Dr2.createPrescription(638810, pres);
	    
	  // patient2.ViewMedicalRecord();
	    
	   // System.out.print(patient2);
	   
	   
	   Tests test1 = new Tests("Covid test","positive",LocalDateTime.of(LocalDate.of(2021, 4, 4),LocalTime.of(12, 30)));
	   Tests test2 = new Tests("Blood test","Kidney",LocalDateTime.of(LocalDate.of(2021, 1, 25),LocalTime.of(10, 30)));
	   Tests test3 = new Tests("Blood test","O+",LocalDateTime.of(LocalDate.of(2020, 5, 5),LocalTime.of(9, 30)));
	   patient2.getMR().getConductedTests().add(test1);
	   patient2.getMR().getConductedTests().add(test3);
	   patient2.getMR().getConductedTests().add(test2);
	    
	    //admin2.ViewReports(202021);
	   // ArrayList<Patient> p1 = Dr1.patients; 
	   // System.out.println(p1);
	    //System.out.print(admin2.getUsers());
	    
	   //System.out.print(Doctor.AllDoctors);
	
	  // patient2.ViewLatestReport();
	   
	   //Dr2.ViewSchedule();
	    patient2.ReserveAppointment(LocalDateTime.of(LocalDate.of(2021, 05, 12), LocalTime.of(15, 30)), 281233);
	    Dr1.ViewSchedule();
	   // admin1.removeUser(420244);
	    
	    
	    patient2.ViewAllAppointment();
	    //patient2.ViewAllAppointment();
	    
		}catch(Exception e) {
			
			System.err.print(e.toString());
		}
	}

}

