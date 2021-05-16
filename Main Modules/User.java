import java.util.ArrayList;

public class User implements Comparable<User> {

	
	private int id;
	private String password;
	private String email;
	private String phone;
	
	//initially false and turns into true when the login credentials are verified.
	public boolean loginStatus=false;
	
	//this is to prevent the random generator from generating the same id.
    static ArrayList<Integer> IDHistory;
	
    //the type of users available in out system.
	static enum userType {
		Admin, Doctor, Nurse, Laboratorian, Accountant, Patient
	}
    private userType type;




	public User(String email,String password,String phoneNb,userType type) {
		
		//First time initialize the id storage history.
	    if(IDHistory == null) IDHistory = new ArrayList<Integer>();
	    
	  //generate unique 6 digits id.
	   //keep looping until the id is unique.
	  		do {
	  			id = new Generator().genrate();
	  			
	  		}while(IDHistory.contains(id));
	  		IDHistory.add(id);

		this.email = email;
		this.phone = phoneNb;
		this.password = password;
	    this.type = type;
		
	}
	
	public boolean verifyLogin(int id,String pass) throws InputExceptions {
		
		//when the is is not of 6 digits. Alert user.
		if(String.valueOf(id).length() != 6) throw new InputExceptions("The id must be of 6 digits.");
		if(this.id == id && pass.equals(password)) {
			System.out.println("successful");
			loginStatus = true;
		}else {
			System.out.println("Invalid login\nloginStatus: "+loginStatus);
			//when id doesn't match the current id or the password. Invalid Login
			loginStatus = false;
			throw new InputExceptions("The password or id is incorrect");
		}
		
		return loginStatus;
	}
	
	public void logOut() {
		System.out.println("Logging out...");
		System.out.println("logged out");
		loginStatus = false;
	}
	
	
	public void updatePassword(String pass) {
		
		//updating password must happen when the user is logged in. After credentials verification.
		if(loginStatus) {
		String temp = password;
		password = pass;
		System.out.println("Password updated successfully");
		System.out.println("Old password: "+temp+"\nnew password: "+password);
		
		}
		
	}
	

	
	public String toString() {
		return "User: \n\tid: " + id + "\n\temail: " + email + "\n\tpassword: "+password+"\n\tphone: " + phone + "\n\tloginStatus: "
				+ loginStatus + "\n\ttype: " + type + "\n";
	}

	//compare for sorting
	public int compareTo(User u) {
		int ID = ((User) u).getId();

		return id-ID;
	}
	
	
	
    //getter and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public userType getType() {
		return type;
	}
	
}
