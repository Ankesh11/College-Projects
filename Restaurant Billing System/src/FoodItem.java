import java.io.Serializable;

public class FoodItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String vItemName,vItemType;
	private int vPrice;
	public FoodItem(String vItemName, String vItemType, int vPrice){
		this.setvItemName(vItemName);
		this.setvItemType(vItemType);
		this.setvPrice(vPrice);
	}
	public void setvPrice(int vPrice) {
		this.vPrice=vPrice;
	}
	public int getvPrice(){
		return vPrice;
	}
	public void setvItemType(String vItemType) {
		this.vItemType=vItemType;
		
	}
	public String getvItemType(){
		return vItemType;
	}
	public void setvItemName(String vItemName) {
		this.vItemName= vItemName;
	}
	public String getvItemName(){
		return vItemName;
	}
}