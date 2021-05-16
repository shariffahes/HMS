import java.time.LocalDateTime;
import java.util.ArrayList;

public  class  BSearch {

	public  int searchReports(int id, ArrayList<Report> r) {
		int start=0;
		int end = r.size()-1;
		
		while(start<=end) {
			
			int middle = start+(end-start)/2;
			int ID = r.get(middle).reportNb;
			
			if(ID < id) {
				start = middle+1;
				
			}else if(ID > id) {
				end =middle-1;
				
			}else {
				return middle;
			}
		}
		
		return -1;
		
	}
	
	
	public  int searchUsers(int id, ArrayList<User> u) {
		int start=0;
		int end = u.size()-1;
		
		while(start<=end) {
			
			int middle = start+(end-start)/2;
			int ID = u.get(middle).getId();
			
			if(ID < id) {
				start = middle+1;
				
			}else if(ID > id) {
				end =middle-1;
				
			}else {
				return middle;
			}
		}
		
		return -1;
		
	}
	
	public  int searchAppointments(LocalDateTime currDate, ArrayList<LocalDateTime> time) {
		int start=0;
		int end = time.size()-1;
		int temp=-1;
		while(start<=end) {
			
			int middle = start+(end-start)/2;
		   LocalDateTime middleTime = time.get(middle);
			
			if(currDate.isAfter(middleTime)) {
				
				start = middle+1;
				
				
			}else if(currDate.isBefore(middleTime)) {
				temp=middle;
				end =middle-1;
				
				
			}else {
				return middle;
			}
		}
		
		return temp;
		
	}
	
	public int searchDoctors(Doctor.MedicalField specialty,ArrayList<Doctor> doctors) {
		int start=0;
		int end = doctors.size()-1;
	
		while(start<=end) {
			
			int middle = start+(end-start)/2;
		   Doctor.MedicalField medicalF = doctors.get(middle).specialty;
			
			if(specialty.ordinal()>medicalF.ordinal()) {
				start = middle+1;
				
				
			}else if(specialty.ordinal() < medicalF.ordinal()) {
			
				end =middle-1;
				
				
			}else {
				int i = middle;
				while(i-1>=0&&doctors.get(i-1).specialty == specialty) {
					i--;
				}
				return i;
			}
		}
		return -1;
	}
	
	
	public  int searchPatients(int id, ArrayList<Patient> u) {
		int start=0;
		int end = u.size()-1;
		
		while(start<=end) {
			
			int middle = start+(end-start)/2;
			int ID = u.get(middle).getId();
			
			if(ID < id) {
				start = middle+1;
				
			}else if(ID > id) {
				end =middle-1;
				
			}else {
				return middle;
			}
		}
		
		return -1;
		
	}


}
