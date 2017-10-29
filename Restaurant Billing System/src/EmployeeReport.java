
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class EmployeeReport extends JFrame implements ActionListener {

	//declaration
	private static final long serialVersionUID = 1L;
	ArrayList<Employee> al;
	JLabel lUserName,lPassword,lDateOfJoining,lAge,lAddress,lGender,
	lQualification;
	FileInputStream fis;ObjectInputStream ois;
	File f1;
	@SuppressWarnings("unchecked")
	public EmployeeReport()
	{
		//instantiation
		lUserName = new JLabel("USERNAME");
		lPassword = new JLabel("PASSWORD");
		lDateOfJoining = new JLabel("DATE OF JOINING");
		lAge = new JLabel("AGE");
		lGender = new JLabel("GENDER");
		lQualification = new JLabel("QUALIFICATION");
		lAddress = new JLabel("ADDRESS");
		
		f1 = new File("Employee.db");
		try
		{
		fis = new FileInputStream(f1);
		ois = new ObjectInputStream(fis);
		al = (ArrayList<Employee>)ois.readObject();
		}
		catch (IOException | ClassNotFoundException | NumberFormatException e1) 
		{
			e1.printStackTrace();
		}
		
		//setting the layout
		setLayout(new GridLayout(al.size()+1,7));
		
		//adding components
		add(lUserName);add(lPassword);add(lDateOfJoining);add(lAge);
		add(lAddress);add(lGender);add(lQualification);
		
		//retrieving the records
		for(Employee e:al)
		{
			add(new JTextField(e.getvUserName()));
			add(new JTextField(e.getvPassword()));
			add(new JTextField(e.getvDOJ().toString()));
			add(new JTextField(String.valueOf(e.getvAge())));
			add(new JTextField(e.getvAddress()));
			add(new JTextField(e.isvGender()?"Male":"Female"));
			add(new JTextField(e.getvQualification()));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
	}
	/*public static void main(String[] args) 
	{
		EmployeeReport obj = new EmployeeReport();
		obj.launchFrame();
	}*/
	public void launchFrame() 
	{
		setVisible(true);
		setSize(700,500);
	}

}
