import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private float amount;
	public Account(String date, float amount){
		this.setDate(date);
		this.setAmount(amount);
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setAmount(float amount){
		this.amount = amount;
	}
	public String getDate(){
		return date;
	}
	public float getAmount(){
		return amount;
	}
}
