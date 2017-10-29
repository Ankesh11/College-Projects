

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Billing extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	java.net.URL imgURL = getClass().getResource("./img");
	 JLabel billPayment;
	 JPanel p1,p2,p3;
	 ButtonGroup bg;
	 JRadioButton rbCash, rbCard;
	 JButton Proceed;
	 ImageIcon imgProceed;
	 
	 Container cont;
	 public Billing()
	 {
		 super("Payment Mode");
		 cont = getContentPane();
		 p1= new JPanel();
		 p2= new JPanel();
		 p3= new JPanel();
		 
		 billPayment= new JLabel("Welcome To Online Payment");
		 rbCash= new JRadioButton("CASH");
		 rbCard= new JRadioButton("CREDIT CARD");
		
		 ImageIcon imgProceed = new ImageIcon ("./img/proceed.png");
		 Proceed= new JButton("PROCEED", imgProceed);
		
		 ButtonGroup bg= new ButtonGroup();
		 cont.setLayout(new GridLayout(3,1));
		 p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		 p2.setLayout(new GridLayout(1,2));
		 p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		 cont.add(p1);
		 cont.add(p2);
		 cont.add(p3);
		 
		 p1.add(billPayment);
		 p2.add(rbCash);
		 p2.add(rbCard);
		 p3.add(Proceed);
		 bg.add(rbCash);
		 bg.add(rbCard);
		 
		 Proceed.setActionCommand("Proceed");
		 Proceed.addActionListener(this);

	 }
	
		 public void actionPerformed(ActionEvent e) 
		 
		 {
			 String ac = e.getActionCommand();
			 switch(ac)
			 {
			 case "Proceed":
				 if((!rbCash.isSelected())&&(!rbCard.isSelected()))
				 {
					 JOptionPane.showMessageDialog(this,"SELECT MODE -->CASH or CREDIT CARD");
					 return;
				 }
				 if(rbCash.isSelected())
				 {
					 JOptionPane.showMessageDialog(this,"PAYMENT SUCCESSFUL");
					 this.dispose();
				 }
				 if(rbCard.isSelected())
				 {
					 CreditCard bg= new CreditCard();
					 bg.launchFrame();
					 this.setVisible(false);
				 }
				 break;
			 }
		 }
	
	/*public static void main(String[] args)
	{
		Billing bg= new Billing();
		bg.launchFrame();
	}*/
	public void launchFrame() {
		setVisible(true);
		setSize(400,300);
		
	}

}
