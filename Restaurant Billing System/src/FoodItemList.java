
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;



public class FoodItemList extends JFrame implements ActionListener {

	//declaration
	private static final long serialVersionUID = 1L;
	ArrayList<FoodItem> alfi;
	ArrayList<Account> ala;
	int total;
	int cost[];
	JLabel lFoodItem,lQuantity,lIndian,lContinental,lChinese,lVat,lSTax,lSBTax;
	FileInputStream fis;ObjectInputStream ois;
	FileOutputStream fos;
	ObjectOutputStream oos;
	JTextField tfFoodItem,tfVat,tfSTax,tfSBTax;
	JTextArea taBill;
	JSpinner sQuantity;
	JButton bOk,bCancel,bPay,bGenerateBill,bLogout;
	JPanel p1,p2;
	File f1;
	int i;
	float tax;
	float gTotal;
	Container cont;
	DateFormat dateFormat;
	Date date;
	@SuppressWarnings("unchecked")
	public FoodItemList()
	{
		//instantiation
		super("Food Items");
		total = 0;
		i=0;
		tax=0;
		cost = new int[15];
		lFoodItem = new JLabel("Food Item");
		lQuantity = new JLabel("Quantity");
		lIndian = new JLabel("Indian");
		lContinental = new JLabel("Continental");
		lVat = new JLabel("VAT:");
		lSTax = new JLabel("Service Tax:");
		lSBTax = new JLabel("Swachh Bharat Tax:");
		lChinese = new JLabel("Chinese");
		cont = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		sQuantity = new JSpinner(new SpinnerNumberModel(1,1,10,1));
		bOk = new JButton("Ok");
		bCancel = new JButton("Cancel");
		bLogout = new JButton("Logout");
		bGenerateBill = new JButton("Generate Bill");
		bPay = new JButton("Pay");
		tfFoodItem = new JTextField(10);
		taBill = new JTextArea(15,20);
		tfVat = new JTextField(3);
		tfSTax = new JTextField(3);
		tfSBTax = new JTextField(3);
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		
		tfVat.setSize(new Dimension(2,2));
		tfSTax.setSize(new Dimension(2,2));
		tfSBTax.setSize(new Dimension(2,2));
		tfFoodItem.setSize(new Dimension(4,2));
		taBill.setText(date.toString()+"\n");		
		f1 = new File("FoodItem.db");
		try
		{
		fis = new FileInputStream(f1);
		ois = new ObjectInputStream(fis);
		alfi = (ArrayList<FoodItem>)ois.readObject();
		}
		catch (IOException | ClassNotFoundException | NumberFormatException e1) 
		{
			e1.printStackTrace();
		}
		cont.setLayout(new GridLayout(2,1));
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.setLayout(new GridLayout(alfi.size()+1,3));
		
		cont.add(p1);
		cont.add(p2);
		p1.add(lFoodItem);
		p1.add(tfFoodItem);
		p1.add(lQuantity);
		p1.add(sQuantity);
		p1.add(bOk);
		p1.add(bCancel);
		p1.add(bGenerateBill);
		p1.add(bPay);
		p1.add(bLogout);
		p1.add(lVat);
		p1.add(tfVat);
		p1.add(lSTax);
		p1.add(tfSTax);
		p1.add(lSBTax);
		p1.add(tfSBTax);
		p1.add(taBill);
		
		tfVat.setText("14.5");
		tfSTax.setText("14");
		tfSBTax.setText("0.5");
		for(FoodItem e:alfi)
		{
			p2.add(new JTextField(e.getvItemName())).setBackground(Color.lightGray);
			p2.add(new JTextField(e.getvItemType())).setBackground(Color.lightGray);
			p2.add(new JTextField(String.valueOf(e.getvPrice()))).setBackground(Color.lightGray);
		}
		bOk.setActionCommand("bOk");
		bCancel.setActionCommand("bCancel");
		bPay.setActionCommand("bPay");
		bGenerateBill.setActionCommand("bGenerateBill");
		bLogout.setActionCommand("bLogout");
		
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		bPay.addActionListener(this);
		bGenerateBill.addActionListener(this);
		bLogout.addActionListener(this);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String ac = e.getActionCommand();
		switch(ac){
		case "bOk":
			if(tfFoodItem.getText().trim().equals("") || (sQuantity.getValue().toString().equals("0"))){
				JOptionPane.showMessageDialog(this,"Please enter the fields");
				return;
			}
			else{
				for(FoodItem fim: alfi){
					if(tfFoodItem.getText().trim().equals(fim.getvItemName())){
						taBill.append(" "+tfFoodItem.getText().trim()+"-"+sQuantity.getValue().toString()+"  ="+
						((Integer.parseInt(sQuantity.getValue().toString())*fim.getvPrice())+"\n "));
						 cost[i]= Integer.parseInt(sQuantity.getValue().toString())*fim.getvPrice();
						 i++;
					}
					
				}
				tfFoodItem.setText("");
				sQuantity.setValue(1);
			}
			break;
		case "bCancel":
			taBill.setText(date.toString()+"\n");
			tfFoodItem.setText("");
			break;
		case "bPay":
			if(gTotal==0){
				JOptionPane.showMessageDialog(this, "Generate bill / Buy some food items");
				return;
			}
			taBill.setText(date.toString()+"\n");
			ala = new ArrayList<Account>();
			File fa = new File("Account.db");
			try {
				fis = new FileInputStream(fa);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ala = (ArrayList<Account>)ois.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ala.add(new Account(date.toString(), gTotal));
			try {
				fos = new FileOutputStream(fa);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				oos = new ObjectOutputStream(fos);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				oos.writeObject(ala);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Billing objBilling = new Billing();
			objBilling.launchFrame();
			break;
		case "bGenerateBill":
			taBill.append("------------------------------------------------\n");
			for(int x:cost){
				total = total +x;
			}
			taBill.append("Total = "+total+"\n");
			tax = ((Float.parseFloat(tfVat.getText().trim()))+(Float.parseFloat(tfSTax.getText().trim()))
					+Float.parseFloat(tfSBTax.getText().trim()))*total/100;
			taBill.append("Tax    = "+tax+"\n");
			taBill.append("------------------------------------------------\n");
			gTotal = total + tax;
			taBill.append("Grand Total    = "+gTotal+"\n");
			break;
		case "bLogout":
			Admin objadmin = new Admin();
			Logger obj = new Logger();
			//objadmin.setVisible(false);
			obj.launchFrame();
			this.dispose();
			objadmin.dispose();
			break;
		}
	
	}
	/*public static void main(String[] args) 
	{
		FoodItemList obj = new FoodItemList();
		obj.launchFrame();
	}*/
	public void launchFrame() 
	{
		setVisible(true);
		setSize(700,500);
	}

}
