import java.time.LocalDateTime;
import java.util.ArrayList;

public class MedicalInvoice {
	private int InvoiceNumber;
	private double TotalCost;
	private LocalDateTime issueDate;
	private double tax = 11;
	private LocalDateTime DueDate;
	static ArrayList<Integer> InvoiceHistory;
	
	
	
	public MedicalInvoice(double totalCost,LocalDateTime issueDate,
			LocalDateTime dueDate) throws InputExceptions{
	
		if(InvoiceHistory == null) InvoiceHistory = new ArrayList<Integer>(); 
		
		//generate unique invoice number;
		do {
			InvoiceNumber = new Generator().genrate();
			
		}while(InvoiceHistory.contains(InvoiceNumber));
		InvoiceHistory.add(InvoiceNumber);
		 
	
		this.issueDate = issueDate;
		
		//due date should be after the issue date
		if(dueDate.compareTo(issueDate)<0) throw new InputExceptions("FAILED. Due date is "+dueDate.toString()+ " is before issue Date"+issueDate);
		DueDate = dueDate;
		TotalCost = totalCost;
		
	
	 
	}
	
	
	
	//getters and setters
	public int getInvoiceNumber() {
		return InvoiceNumber;
	}
	public void setInvoiceNumber(int invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}
	public double getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}
	public LocalDateTime getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public LocalDateTime getDueDate() {
		return DueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		DueDate = dueDate;
	}
	
	
	

}
