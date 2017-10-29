
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private String vUserName,vPassword,vQualification,vAddress;
	private boolean vGender;
	private int vAge;
	private Date vDOJ;
	public Employee(String vUserName,String vPassword,
			String vQualification,String vAddress,boolean vGender,
			int vAge,Date vDOJ)
	{
		this.setvUserName(vUserName);
		this.setvPassword(vPassword);
		this.setvQualification(vQualification);
		this.setvAddress(vAddress);
		this.setvGender(vGender);
		this.setvAge(vAge);
		this.setvDOJ(vDOJ);
	}
	public String getvUserName() {
		return vUserName;
	}
	public void setvUserName(String vUserName) {
		this.vUserName = vUserName;
	}
	public String getvPassword() {
		return vPassword;
	}
	public void setvPassword(String vPassword) {
		this.vPassword = vPassword;
	}
	public String getvQualification() {
		return vQualification;
	}
	public void setvQualification(String vQualification) {
		this.vQualification = vQualification;
	}
	public String getvAddress() {
		return vAddress;
	}
	public void setvAddress(String vAddress) {
		this.vAddress = vAddress;
	}
	public boolean isvGender() {
		return vGender;
	}
	public void setvGender(boolean vGender) {
		this.vGender = vGender;
	}
	public int getvAge() {
		return vAge;
	}
	public void setvAge(int vAge) {
		this.vAge = vAge;
	}
	public Date getvDOJ() {
		return vDOJ;
	}
	public void setvDOJ(Date vDOJ) {
		this.vDOJ = vDOJ;
	}
}
