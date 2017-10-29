
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreditCard extends JFrame implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	java.net.URL imgURL = getClass().getResource("./img");
	//declaration
	JPanel p1,p2,p3,p4;
	JLabel lCcd,lCcn,lName,lCvv,lExpiry;
	JTextField tfCcn,tfName,tfExpiry;
	JPasswordField pfCvv;
	JButton bPay,bClear,bCancel, bshow;
	ImageIcon imgbPay, imgbClear,imgbCancel, imgbshow;
	
	Container cont;
	
	public CreditCard()
	{
		super("Credit card details");
		//Instantiations
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		lCcd = new JLabel("Credit Card Details");
		lName= new JLabel("Name on the card");
		lCcn = new JLabel("Enter card no.");
		lCvv = new JLabel("Enter CVV no.");
		lExpiry = new JLabel("Expiry Date");
		
		tfCcn = new JTextField(16);
		tfName = new JTextField(20);
		tfExpiry = new JTextField(10);
		
		pfCvv = new JPasswordField(3);
		
		ImageIcon imgbPay= new ImageIcon("./img/cash.png");
		bPay = new JButton("PAY",imgbPay);
		ImageIcon imgbClear= new ImageIcon("./img/clear.png");
		bClear = new JButton("CLEAR", imgbClear);
		ImageIcon imgbCancel= new ImageIcon("./img/cancel.png");
		bCancel = new JButton("CANCEL", imgbCancel);
		ImageIcon imgbShow= new ImageIcon("./img/calender.png");
		bshow = new JButton("SHOW", imgbShow);

		
		
		cont = getContentPane();
		
		//setting layout
		
		cont.setLayout(new GridLayout(4,1));
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new GridLayout(4,2));
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//adding the components
		
		cont.add(p1);
		cont.add(p2);
		cont.add(p3);
		cont.add(p4);
		p1.add(lCcd);
		
		p2.add(lCcn);
		p2.add(tfCcn);
		p2.add(lName);
		p2.add(tfName);
		p2.add(lCvv);
		p2.add(pfCvv);
		p2.add(lExpiry);
		p2.add(tfExpiry);
		
		p3.add(bshow);
		
		p4.add(bPay);
		p4.add(bClear);
		p4.add(bCancel);
		
		//setting the action command;
		bPay.setActionCommand("bPay");
		bClear.setActionCommand("bClear");
		bCancel.setActionCommand("bCancel");
		bshow.setActionCommand("bshow");
		
		//adding the action listener
		bPay.addActionListener(this);
		bClear.addActionListener(this);
		bCancel.addActionListener(this);
		bshow.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac= e.getActionCommand();
		 String value= tfCcn.getText().trim();
		 String value1= (String.valueOf(pfCvv.getPassword()));
		 int k= value1.length();
	      int l= value.length();
	      
		switch(ac)
		{
		
		case "bClear":
			clearALL();
			break;
		case "bPay" :
		{
			 if((tfCcn.getText().trim().equals("")))
			 {
				 JOptionPane.showMessageDialog(this,"Enter Card no.");
				 tfCcn.setText("");
				 tfCcn.requestFocus();
				 
				 return;
			 }
			 if(l!=16)
			 {
				 JOptionPane.showMessageDialog(this,"ENTER 16 DIGIT CARD NUMBER");
				 tfCcn.setText("");
				 tfCcn.requestFocus();
				 return;
			 }
			 
			 if(k!=3)
			 {
				 JOptionPane.showMessageDialog(this,"ENTER 3 DIGIT CVV NUMBER");
				 
				 pfCvv.requestFocus();
				 return;
			 }
			 
			 if((tfName.getText().trim().equals("")))
			 {
				 JOptionPane.showMessageDialog(this,"NAME CANNOT BE EMPTY");
				 tfName.setText("");
				 tfName.requestFocus();
				 return;
			 }
			 if((String.valueOf(pfCvv.getPassword()).trim().equals("")))
			 {
				 JOptionPane.showMessageDialog(this,"CVV CANNOT BE LEFT EMPTY");
				 pfCvv.setText("");
				 pfCvv.requestFocus();
				 return;
			 }
			 if((tfExpiry.getText().trim().equals("")))

             {
				 JOptionPane.showMessageDialog(this,"Date CANNOT BE LEFT EMPTY");
				 tfExpiry.setText("");
				 tfExpiry.requestFocus();
				 return;
             }
			 
		      if(k==16 && l==3 && !tfCcn.getText().trim().equals("") && !tfName.getText().trim().equals("") && 
		    		  !tfExpiry.getText().trim().equals("") && !(String.valueOf(pfCvv.getPassword()).trim().equals("")))
		      {
		    	  JOptionPane.showMessageDialog(this, "THANKS");
		    	  return;
		      }
		      JOptionPane.showMessageDialog(this,"Payment Successful!!");
		      this.dispose();
			break;
		}
			
		case "bCancel":
		{
			int x = JOptionPane.showConfirmDialog(this,"Doy you want to Quit??","Close Login",
					JOptionPane.OK_CANCEL_OPTION);
			if(x == JOptionPane.OK_OPTION)
			{
				Billing bg= new Billing();
				bg.setVisible(true);
				bg.setSize(500,300);
			}
			this.dispose();
			break;
		}
		case "bshow" :
			tfExpiry.setText(new DatePicker(this).setPickedDate());
			return;
		}

	}

	private void clearALL() {
		tfCcn.setText("");
		pfCvv.setText("");
		tfExpiry.setText("");
		tfName.setText("");
		tfCcn.requestFocus();
	}

	/*(public static void main(String[] args) 
	{
		CreditCard cc = new CreditCard();
		cc.launchFrame();
		
	}*/

	public void launchFrame() 
	{
		setSize(450,400);
		setVisible(true);
		
		
	}

}
