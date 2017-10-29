import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class Admin extends JFrame implements ActionListener{
	//declaration
	private static final long serialVersionUID = 1L;
	java.net.URL imgURL = getClass().getResource("./img");
	Container cont;
	JPanel p1,p2,p3;
	JLabel l,lTextField;
	JButton bShowUser,bRemoveUser,bEmployeeReport,bShowTotal,bLogout,bAddItem,bRemoveItem,bShowItem,bProceed;
	JTextField tfTextField;
	JTextArea taField;
	ImageIcon imgAdmin;
	ImageIcon imgShow,imgRemove,imgReport,imgTotal,imgLogout,imgAddItem,imgRemoveItem,imgProceed,imgShowItem;
	Employee ep;
	ArrayList<Employee> al;
	ArrayList<FoodItem> alfi;
	File f;
	File fa;
	FileInputStream fis;
	FileOutputStream fos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	public Admin(){
		//initialization
		super("Admin");
		cont = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		taField = new JTextArea(8,40);
		tfTextField = new JTextField(20);
		lTextField = new JLabel("Enter Text:");
		imgAdmin = new ImageIcon("./img/admin.png");
		imgShow = new ImageIcon("./img/show.png");
		imgRemove = new ImageIcon("./img/remove.png");
		imgReport = new ImageIcon("./img/report.png");
		imgTotal = new ImageIcon("./img/total.png");
		imgLogout = new ImageIcon("./img/logout.png");
		imgRemoveItem = new ImageIcon("./img/remove.png");
		imgAddItem = new ImageIcon("./img/add.png");
		imgShowItem = new ImageIcon("./img/showItem.png");
		imgProceed = new ImageIcon("./img/proceed.png");
		bShowUser = new JButton("Show User",imgShow);
		bShowItem = new JButton("Show items",imgShowItem);
		bProceed = new JButton("Proceed",imgProceed);
		
		
		bRemoveUser = new JButton("Remove User",imgRemove);
		bEmployeeReport = new JButton("Employee Report",imgReport);
		bShowTotal = new JButton("Account Details",imgTotal);
		bLogout = new JButton("Logout",imgLogout);
		bAddItem = new JButton("Add food Item",imgAddItem);
		bRemoveItem = new JButton("Remove food item",imgRemoveItem);
		l = new JLabel(imgAdmin);
		al = new ArrayList<Employee>();
		alfi = new ArrayList<FoodItem>();
		//setting layout
		taField.setBackground(Color.lightGray);
		cont.setLayout(new GridLayout(3,1));
		p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p2.setLayout(new GridLayout(2,3));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		//adding components
		cont.add(p1);
		cont.add(p2);
		cont.add(p3);
		
		p1.add(lTextField);
		p1.add(tfTextField);
		p1.add(bLogout);
		p2.add(bShowUser);
		p2.add(bRemoveUser);
		p2.add(bAddItem);
		p2.add(bRemoveItem);
		p2.add(bEmployeeReport);
		p2.add(bShowTotal);
		p2.add(bShowItem);
		p2.add(bProceed);
		p3.add(taField);
		//setting action command
		bShowUser.setActionCommand("bShowUser");
		bRemoveUser.setActionCommand("bRemoveUser");
		bEmployeeReport.setActionCommand("bEmployeeReport");
		bShowTotal.setActionCommand("bShowTotal");
		bLogout.setActionCommand("bLogout");
		bAddItem.setActionCommand("bAddItem");
		bRemoveItem.setActionCommand("bRemoveItem");
		bShowItem.setActionCommand("bShowItem");
		bProceed.setActionCommand("bProceed");
		//adding action Listener
		bShowUser.addActionListener(this);
		bRemoveUser.addActionListener(this);
		bEmployeeReport.addActionListener(this);
		bShowTotal.addActionListener(this);
		bLogout.addActionListener(this);
		bAddItem.addActionListener(this);
		bRemoveItem.addActionListener(this);
		bShowItem.addActionListener(this);
		bProceed.addActionListener(this);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch(ac){
		case "bShowUser":
			if(tfTextField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this,"Provide Username!!!");
				return;
			}
			f = new File("Employee.db");
			if(f.exists())
			{
					try {
						fis = new FileInputStream(f);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						ois = new ObjectInputStream(fis);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						al = (ArrayList<Employee>)ois.readObject();
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					int i=0;
					for(Employee emp:al){
						if(tfTextField.getText().trim().equals(emp.getvUserName())){
							taField.setText(" Username: "+emp.getvUserName()+"\n Qualification: "+emp.getvQualification()+
									"\n Date Of Joining: "+emp.getvDOJ()+"\n Age: "+emp.getvAge()+"\n Gender: "+((emp.isvGender())?"Male":"Female")+
									"\n Address: "+emp.getvAddress());
							i=0;
							return;
						}
						else
							i=1;
					}
					if(i==1){
						JOptionPane.showMessageDialog(this, "No such user exists!!!");
					}
					
			}
			break;
		case "bRemoveUser":
			if(tfTextField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this,"Provide Username!!!");
				return;
			}
			f = new File("Employee.db");
			if(f.exists())
			{
					try {
						fis = new FileInputStream(f);
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
						al = (ArrayList<Employee>)ois.readObject();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int i=0;
					for(Employee emp:al){
						if(tfTextField.getText().trim().equals(emp.getvUserName())){
							al.remove(emp);
							JOptionPane.showMessageDialog(this, "User removed successfully!!!");
							try {
								fos = new FileOutputStream(f);
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
								oos.writeObject(al);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							i=0;
							return;
						}
						else{
							i=1;
						}
					}
					if(i==1){
						JOptionPane.showMessageDialog(this, "No such user exists!!!");
						return;
					}
					
			}
			break;
		case "bEmployeeReport":
			EmployeeReport eReport = new EmployeeReport();
			eReport.launchFrame();
			break;
		case "bShowTotal":
			AccountDetails objAcc = new AccountDetails();
			objAcc.launchFrame();
			break;
		case "bLogout":
			Logger objLogger = new Logger();
			objLogger.setVisible(true);
			objLogger.setSize(700,500);
			this.dispose();
			break;
		case "bAddItem":
			AddItem obj = new AddItem();
			obj.launchFrame();
			break;
		case "bRemoveItem":
			if(tfTextField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this,"Provide item name in the textbox");
				return;
			}
			f = new File("FoodItem.db");
			if(f.exists())
			{
					try {
						fis = new FileInputStream(f);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						ois = new ObjectInputStream(fis);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						alfi = (ArrayList<FoodItem>)ois.readObject();
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					int i=0;
					for(FoodItem emp:alfi){
						if(tfTextField.getText().trim().equals(emp.getvItemName())){
							alfi.remove(emp);
							JOptionPane.showMessageDialog(this, "Item removed successfully!!!");
							try {
								fos = new FileOutputStream(f);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							try {
								oos = new ObjectOutputStream(fos);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								oos.writeObject(alfi);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							i=0;
							return;
						}
						else{
							i=1;
						}
					}
					if(i==1){
						JOptionPane.showMessageDialog(this, "No such item exists!!!");
						return;
					}
			}
			break;
		case "bShowItem":
			FoodItemList objFoodList = new FoodItemList();
			objFoodList.launchFrame();
			break;
		case "bProceed":
			FoodItemList objfil = new FoodItemList();
			objfil.launchFrame();
			break;
		}
	}
	/*public static void main(String args[]){
		Admin obj = new Admin();
		obj.launchFrame();
	}*/
	public void launchFrame() {
		setSize(700,500);
		setVisible(true);
	}
}
