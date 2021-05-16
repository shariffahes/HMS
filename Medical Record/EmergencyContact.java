
public class EmergencyContact {

	private String Name;
	private String phoneNb;
	private String Address;
	private String Email;
	private String Relation;
	public EmergencyContact(String name, String phoneNb, String address, String email, String relation) {
		super();
		Name = name;
		this.phoneNb = phoneNb;
		Address = address;
		Email = email;
		Relation = relation;
	}
	
	
	@Override
	public String toString() {
		return "\n\t\t\tName: " + Name + "\n\t\t\tphoneNb: " + phoneNb + "\n\t\t\tAddress: " + Address + "\n\t\t\tEmail: " + Email
				+ "\n\t\t\tRelation: " + Relation + "\n";
	}


	public void UpdatePhoneNb(String number) {
		phoneNb = number;
	}
	
	//getters and setters
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNb() {
		return phoneNb;
	}
	public void setPhoneNb(String phoneNb) {
		this.phoneNb = phoneNb;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	
	
	
}
