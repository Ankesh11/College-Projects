
 import javax.swing.*;

import java.util.*;
 import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;

public class Register extends JFrame implements ActionListener{
	//declaration
	java.net.URL imgURL = getClass().getResource("./img");
	Container cont;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8;
	JLabel lUsername,lPassword,lConfirmPassword,lDateOfJoining,lAge,lQualification,lGender,lHobbies,lAddress,lNewUser,lMale,lFemale;
	JTextField tfUsername,tfDateOfJoining;
	JPasswordField pfPassword,pfConfirmPassword;
	ImageIcon  imgshow, imgclear, imgclose, imgregister;
	JButton bShow,bRegister,bClear,bClose;
	JSpinner sAge;
	JRadioButton rbMale,rbFemale;
	ButtonGroup bg;
	JComboBox<String> cbQualification;
	JCheckBox ckbGames,ckbSports,ckbReading,ckbTrekking;
	JTextArea taAddress,taTerms;
	FileInputStream fis;
	FileOutputStream fos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Employee e;
	ArrayList<Employee> al;
	private static final long serialVersionUID = 1L;
	public Register(){
		super("REGISTRATION   FORM");
		cont = getContentPane();
		p1 = new JPanel();	p2 = new JPanel();p3 = new JPanel();p4 = new JPanel();p5 = new JPanel();p6 = new JPanel();
		p7 = new JPanel();p8 = new JPanel();
		lNewUser = new JLabel("NEW USER REGISTRATION");lUsername = new JLabel("Username:");
		lPassword = new JLabel("Password:");
		lConfirmPassword = new JLabel("ConfirmPassword:");lDateOfJoining = new JLabel("Date of joining:");
		lAge = new JLabel("Age:");
		lGender = new JLabel("Gender:");lQualification = new JLabel("Qualification:");lHobbies = new JLabel("Hobbies:");
		lAddress = new JLabel("Address:");
		lMale = new JLabel(new ImageIcon("./img/Male.png"));
		lFemale = new JLabel(new ImageIcon("./img/Female.png"));
		
		tfUsername = new JTextField(20);
		tfDateOfJoining = new JTextField(20);
		pfPassword = new JPasswordField(20);
		pfConfirmPassword = new JPasswordField(20);
		
		imgshow = new ImageIcon("./img/calender.png");
		bShow = new JButton("Show", imgshow);
		imgregister = new ImageIcon("./img/register.png");
		bRegister = new JButton("Register", imgregister);
		imgclear = new ImageIcon("./img/clear1.png");
		bClear = new JButton("Clear", imgclear);
		imgclose = new ImageIcon("./img/close.png");
		bClose = new JButton("Close", imgclose);
		
		sAge = new JSpinner(new SpinnerNumberModel(22,22,58,1));
		rbMale = new JRadioButton();
		rbFemale = new JRadioButton();
		bg = new ButtonGroup();
		
		String[] message= { "10+", "12+", "Graduate"};
		cbQualification  = new JComboBox<String>(message);
		ckbGames = new JCheckBox("Games");
		ckbSports = new JCheckBox("Sports");
		ckbReading = new JCheckBox("Reading");
		ckbTrekking = new JCheckBox("Trekking");
		
		taAddress = new JTextArea(10,20);
		taTerms = new JTextArea(10,20);
		al = new ArrayList<Employee>();
		//setting layout
		cont.setLayout(new GridLayout(8,1));
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new GridLayout(4,2));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p5.setLayout(new FlowLayout(FlowLayout.CENTER));
		p6.setLayout(new FlowLayout(FlowLayout.CENTER));
		p7.setLayout(new GridLayout(1,2));
		p8.setLayout(new FlowLayout(FlowLayout.CENTER));
		taTerms.setBackground(Color.lightGray);
		taTerms.setLineWrap(true);
        taTerms.setEditable(false);
		taTerms.setText("Terms & Condition\n");
		taTerms.append("1:minimun age:22\n");
		taTerms.append("2:minimum qualificatoion:10+\n");
		taTerms.append("3:Language known:Hindi & English\n");
		//adding components
		cont.add(p1);
		cont.add(p2);
		cont.add(p3);
		cont.add(p4);
		cont.add(p5);
		cont.add(p6);
		cont.add(p7);
		cont.add(p8);
		p1.add(lNewUser);
		p2.add(lUsername);p2.add(tfUsername);p2.add(lPassword);p2.add(pfPassword);p2.add(lConfirmPassword);p2.add(pfConfirmPassword);
		p2.add(lQualification);p2.add(cbQualification);
		p3.add(lDateOfJoining);p3.add(tfDateOfJoining);p3.add(bShow);
		p4.add(lAge);p4.add(sAge);
		p5.add(lGender);p5.add(rbMale);p5.add(lMale);p5.add(rbFemale);p5.add(lFemale);
		p6.add(lHobbies);p6.add(ckbGames);p6.add(ckbSports);p6.add(ckbReading);p6.add(ckbTrekking);
		p7.add(lAddress);p7.add(taAddress);p7.add(taTerms);
		p8.add(bRegister);p8.add(bClear);p8.add(bClose);
		bg.add(rbMale);bg.add(rbFemale);
		//setting action Command
		bShow.setActionCommand("bShow");
		bRegister.setActionCommand("bRegister");
		bClear.setActionCommand("bClear");
		bClose.setActionCommand("bClose");
		//adding action listener
		bShow.addActionListener(this);
		bRegister.addActionListener(this);
		bClear.addActionListener(this);
		bClose.addActionListener(this);
	}
	/*public static void main(String args[]){
		Register rg = new Register();
		rg.launchFrame();

	}*/
	public void launchFrame(){
		setSize(700,500);
		setVisible(true);
	}
	private void clearAll(){
		tfUsername.setText("");
		pfPassword.setText("");
		pfConfirmPassword.setText("");
		bg.clearSelection();
		tfUsername.requestFocus();
		tfDateOfJoining.setText("");
		taAddress.setText("");
		ckbGames.setSelected(false);
		ckbSports.setSelected(false);
		ckbReading.setSelected(false);
		ckbTrekking.setSelected(false);
		cbQualification.setSelectedItem("10+");
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch(ac){
		case "bRegister":
			
		/*
			static boolean	equals(char[] a, char[] a2)
			Returns true if the two specified arrays of chars are equal to one another.
			here the get Password functions have data type of char 
			and since static function are called by
			classname.methodname();
			here Arrays is the class which has various methods for searching ,sorting etc:
			*/
			if(tfUsername.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Username cannot be blank!!!");
				tfUsername.setText("");
				tfUsername.requestFocus();
				return;
			}
			if(String.valueOf(pfPassword.getPassword()).trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Password cannot be blank!!!");
				pfPassword.setText("");
				pfPassword.requestFocus();
				return;
			}
			if(String.valueOf(pfConfirmPassword.getPassword()).trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Confirm Password cannot be blank!!!");
				pfConfirmPassword.setText("");
				pfConfirmPassword.requestFocus();
				return;
			}
			if(!(String.valueOf(pfConfirmPassword.getPassword()).trim().equals(String.valueOf(pfPassword.getPassword()).trim()))){
				JOptionPane.showMessageDialog(this,"Password donot match!!!");
				return;
			}
			if((!rbMale.isSelected()) && (!rbFemale.isSelected()))
			{
				JOptionPane.showMessageDialog(this,"PLEASE SELECT GENDER");
				return;
			}
			File f1 = new File("Employee.db");
			try 
			{
			if(f1.exists())
			{
					fis = new FileInputStream(f1);
					ois = new ObjectInputStream(fis);
					al = (ArrayList<Employee>)ois.readObject();
					for(Employee emp:al){
						if(tfUsername.getText().trim().equals(emp.getvUserName())){
							JOptionPane.showMessageDialog(this,"Username already exists\nPlease enter another username");
							return;
						}
					}
			}
			else
				al = new ArrayList<Employee>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			al.add(new Employee(tfUsername.getText().trim(),String.valueOf(pfPassword.getPassword()),
					cbQualification.getSelectedItem().toString(),taAddress.getText(),
					(rbMale.isSelected())?true:false,Integer.parseInt(sAge.getValue().toString()),
							sdf.parse(tfDateOfJoining.getText())));
			fos = new FileOutputStream(f1);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(al);
			JOptionPane.showMessageDialog(this,"User Registered successfully");
			}
			catch (IOException | ClassNotFoundException | NumberFormatException | ParseException e1) 
			{
				e1.printStackTrace();
			}
			Logger obj = new Logger();
			obj.setVisible(true);
			obj.setSize(700,500);
			this.dispose();
			
			break;
		case "bShow":
			tfDateOfJoining.setText(new DatePicker(this).setPickedDate());
			break;
		case "bClear":
			clearAll();
			break;
		case "bClose":
				Logger objLogger = new Logger();
				objLogger.setVisible(true);
				objLogger.setSize(700,500);
				this.dispose();
			break;
		}
		
	}
}