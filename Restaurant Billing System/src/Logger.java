import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Logger extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	java.net.URL imgURL = getClass().getResource("./img");
	JPanel p1,p2,p3,p4,p5,p6;
	ImageIcon imgtitle, imglogin, imgclear, imgclose, imgregister,imgbackground;
	JLabel lLogin, lUsername, lPassword, lNewRegistration, lCopyright, imgtitlelabel;
	ButtonGroup bg;
	JRadioButton rbAdmin, rbWaiter;
	JTextField tfUsername;
	JPasswordField  pfPassword;
	JButton  blogin, bclear, bclose, bregister;
	Container cont;
	Employee e;
	ArrayList<Employee> al;
	File f;
	FileInputStream fis;
	ObjectInputStream ois;
	public Logger()
	{ 
		super("LOGIN  WINDOW");
		
		cont = getContentPane();
		
		p1= new JPanel();
		p2= new JPanel();
		p3= new JPanel();
		p4= new JPanel();
		p5= new JPanel();
		p6= new JPanel();
		
		imgbackground = new ImageIcon("./img/background.jpg");
		imgtitle= new ImageIcon("./img/Fu.gif");
		imgtitlelabel= new JLabel(imgtitle);
		
		lLogin= new JLabel(" EMPLOYEE  LOGIN   :");
		
		bg= new ButtonGroup();
		rbAdmin= new JRadioButton("Admin");
		rbWaiter = new JRadioButton("Waiter");
		lUsername= new JLabel("USERNAME   :");
		lPassword = new JLabel("PASSWORD  :");
		tfUsername= new JTextField(20);
		pfPassword = new JPasswordField(15);
		al = new ArrayList<Employee>();
		imglogin = new ImageIcon("./img/login.png");
		blogin = new JButton("Login", imglogin);
		imgclear = new ImageIcon("./img/clear.png");
		bclear= new JButton("CLEAR ", imgclear);
		imgclose= new ImageIcon("./img/close.png");
		bclose= new JButton("Close", imgclose);
		
		lNewRegistration = new JLabel("New Waiter Registration    :    ");
		imgregister= new ImageIcon("./img/register.png");
		bregister= new JButton("  Register  ", imgregister);
		
		lCopyright = new JLabel("Copyright@Santa'sRestaurant");
		
		cont.setLayout(new GridLayout(6,1));
		p1.setLayout(new GridLayout(1,1));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.setLayout(new GridLayout(3,2,5,0));
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		p6.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cont.add(p1);
		cont.add(p2);
		cont.add(p3);
		cont.add(p4);
		cont.add(p5);
		cont.add(p6);
		
		p1.add(imgtitlelabel);
		p2.add(lLogin);
		
		p3.add(rbAdmin);
		p3.add(rbWaiter);
		p3.add(lUsername);
		p3.add(tfUsername);
		p3.add(lPassword);
		p3.add(pfPassword);
		
		p4.add(blogin);
		p4.add(bclear);
		p4.add(bclose);
		
		p5.add(lNewRegistration);
		p5.add(bregister);
		
		p6.add(lCopyright);
		
		bg.add(rbAdmin);
		bg.add(rbWaiter);
		blogin.setActionCommand("login");
		bclear.setActionCommand("clear");
		bclose.setActionCommand("close");
		bregister.setActionCommand("register");
		blogin.addActionListener(this);
		bclear.addActionListener(this);
		bclose.addActionListener(this);
		bregister.addActionListener(this);
		
	}
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e)
	{
		String ac = e.getActionCommand();
		switch(ac)
		{
		case "login" :
			if(tfUsername.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this," Username Cannot Be Left Blank");
				tfUsername.setText("");
				tfUsername.requestFocus();
				return;
			}
			if(String.valueOf(pfPassword.getPassword()).trim().equals(""))
			{
				JOptionPane.showMessageDialog(this," Password Cannot Be Left Blank");
				pfPassword.setText("");
				pfPassword.requestFocus();
				return;
			}
			if(!rbAdmin.isSelected() && !rbWaiter.isSelected())
			{
				JOptionPane.showMessageDialog(this," Select mode Admin/ Waiter??");
				return;	
			}
			if(rbAdmin.isSelected())
			{
				
				if(tfUsername.getText().trim().equals("admin") && String.valueOf(pfPassword.getPassword()).trim().equals("admin"))
				{
					Admin objAdmin = new Admin();
					objAdmin.launchFrame();
					this.setVisible(false);
					//JOptionPane.showMessageDialog(this," Admin Login Succeded");
				}
						else
						{
							JOptionPane.showMessageDialog(this, "Invalid Username/Password");
							clearAll();
							return;
						}
			}
			if(rbWaiter.isSelected())
			{
				f = new File("Employee.db");
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
				for(Employee emp : al){
					if(emp.getvUserName().equals(tfUsername.getText()) && emp.getvPassword().equals(String.valueOf(pfPassword.getPassword()).trim())){
						FoodItemList objFIL = new FoodItemList();
						objFIL.launchFrame();
						this.setVisible(false);
						i=0;
						break;
					}
					else if(!((emp.getvUserName().equals(tfUsername.getText()) && emp.getvPassword().equals(String.valueOf(pfPassword.getPassword()).trim()))))
						i=1;	
				}
				if(i==1){
					JOptionPane.showMessageDialog(this,"Invalid Username/Password!!!");
				}
			}
			break;
		case "clear" :
			clearAll();
			break;
		case "close" :
			int x = JOptionPane.showConfirmDialog(this, "Do you want to quit?","Close Login",JOptionPane.OK_CANCEL_OPTION);
			if(x== JOptionPane.OK_OPTION)
				this.dispose();
			break;
		
		case "register" :
			Register rg= new Register();
			rg.setVisible(true);
			rg.setSize(700,500);
			this.setVisible(false);
			break;
		}
	}

	private void clearAll() {
		tfUsername.setText("");
		pfPassword.setText("");
		bg.clearSelection();
		tfUsername.requestFocus();
		
	}
	public static void main(String[] args)
	{
		Logger lg = new Logger();
		lg.launchFrame();

	}
	public void launchFrame()
	{
		setSize(700,500);
		setVisible(true);
		rbWaiter.setSelected(true);
	}

}
