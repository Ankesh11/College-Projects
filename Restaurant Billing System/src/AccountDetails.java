import java.awt.Container;
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccountDetails extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	Container cont;
	JTextArea taDetails;
	JPanel p1,p2;
	JLabel lTotal,lReset;
	JTextField tfTotal;
	JButton bReset,bCancel;
	File f,fa;
	FileInputStream fis;
	ObjectInputStream ois;
	FileOutputStream fos;
	ObjectOutputStream oos;
	float total;
	ArrayList<Account> al;
	ArrayList<Account> ala;
	@SuppressWarnings("unchecked")
	public AccountDetails(){
		super("Account Details");
		total=0;
		cont = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		lTotal = new JLabel("Total Collection: ");
		lReset = new JLabel("Reset Account Details");
		bReset = new JButton("Reset");
		bCancel = new JButton("Cancel");
		tfTotal = new JTextField(5);
		taDetails = new JTextArea(30,50);
		ala = new ArrayList<Account>();
		taDetails.setColumns (30);
		taDetails.setLineWrap (true);
		taDetails.setWrapStyleWord (false);
		cont.setLayout(new GridLayout(1,2));
		p1.setLayout(new FlowLayout());
		p2.setLayout(new GridLayout(5,1));
		cont.add(p1);
		cont.add(p2);
		p1.add(taDetails);
		p2.add(lTotal);
		p2.add(tfTotal);
		p2.add(lReset);
		p2.add(bReset);
		p2.add(bCancel);
		
		f = new File("Account.db");
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			al = (ArrayList<Account>)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Account ac : al){
			total = total + ac.getAmount();
			taDetails.append(ac.getDate()+"->  "+ac.getAmount()+"\n");
		}
		tfTotal.setText(String.valueOf(total));
		
		bReset.setActionCommand("bReset");
		bCancel.setActionCommand("bCancel");
		
		bReset.addActionListener(this);
		bCancel.addActionListener(this);
	}
	public void launchFrame(){
		setVisible(true);
		setSize(700,500);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch(ac){
		case "bReset":
			File fa = new File("Account.db");
			try {
				fis = new FileInputStream(fa);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				ala = (ArrayList<Account>)ois.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			ala.clear();
			try {
				fos = new FileOutputStream(fa);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				oos = new ObjectOutputStream(fos);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				oos.writeObject(ala);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			taDetails.setText("");
			tfTotal.setText("");
			JOptionPane.showMessageDialog(this, "Reset successfull!!!");
			break;
		}
		
	}
	
}
