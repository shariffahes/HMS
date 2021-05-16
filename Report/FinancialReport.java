import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class FinancialReport extends Report {

	private double TotalExpenses;
	private double Revenues;
	private ArrayList<String> transactions;
	
	public FinancialReport(LocalDate issueDate, double TotalExpenses, double Revenues, ArrayList<String> transactions) {
		super(issueDate);
		this.TotalExpenses = TotalExpenses;
		this.Revenues = Revenues;
		this.transactions = transactions;
	}

	
	@Override
	public String toString() {
		return "FinancialReport: ["+super.reportNb+","+super.issueDate.toString()+"]"+ "\t\n TotalExpenses: " + TotalExpenses + "\t\nRevenues: " + Revenues + "\t\n transactions: "
				+ transactions.toString() + "\n";
	}


	//getters and setters
	public double getTotalExpenses() {
		return TotalExpenses;
	}

	public void setTotalExpenses(double totalExpenses) {
		TotalExpenses = totalExpenses;
	}

	public double getRevenues() {
		return Revenues;
	}

	public void setRevenues(double revenues) {
		Revenues = revenues;
	}


	public ArrayList<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<String> transactions) {
		this.transactions = transactions;
	}

	
	
}
