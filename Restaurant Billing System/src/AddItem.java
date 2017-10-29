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
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("unused")
public class AddItem extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Container cont;
	JPanel p1,p2;
	JLabel lItemName,lItemType,lPrice;
	JTextField tfItemName, tfItemType, tfPrice;
	JButton bAdd,bCancel;
	File f;
	FileInputStream fis;
	FileOutputStream fos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	FoodItem fItem;
	ArrayList<FoodItem> al;
	public AddItem(){
		super("Add food item");
		cont = getContentPane();
		p1 = new JPanel();
		p2 = new JPanel();
		bAdd = new JButton("Add");
		bCancel = new JButton("Cancel");
		lItemName = new JLabel("Item Name:");
		lItemType = new JLabel("Item Type:");
		lPrice = new JLabel("Price:");
		tfItemName = new JTextField(20);
		tfItemType = new JTextField(20);
		tfPrice = new JTextField(5);
		al = new ArrayList<FoodItem>();
		f= new File("FoodItem.db");
		
		cont.setLayout(new GridLayout(2,1));
		p1.setLayout(new GridLayout(3,2));
		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		cont.add(p1);
		cont.add(p2);
		p1.add(lItemName);
		p1.add(tfItemName);
		p1.add(lItemType);
		p1.add(tfItemType);
		p1.add(lPrice);
		p1.add(tfPrice);
		p2.add(bCancel);
		p2.add(bAdd);
		
		bAdd.setActionCommand("bAdd");
		bCancel.setActionCommand("bCancel");
		
		bAdd.addActionListener(this);
		bCancel.addActionListener(this);
	}

	/*public static void main(String[] args) {
		AddItem obj = new AddItem();
		obj.launchFrame();
	}*/

	public void launchFrame() {
		setVisible(true);
		setSize(400,250);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch(ac){
		case "bAdd":
			if(tfItemName.getText().trim().equals("") || tfItemType.getText().trim().equals("") || tfPrice.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "Please Enter details!!!");
				return;
			}
				File f = new File("FoodItem.db");
				try{
				if(f.exists()){
						fis = new FileInputStream(f);
						ois = new ObjectInputStream(fis);
						al = (ArrayList<FoodItem>)ois.readObject();
					for(FoodItem fi:al){
						if(tfItemName.getText().trim().equals(fi.getvItemName())){
							JOptionPane.showMessageDialog(this,"Item already exists");
							return;
						}
					}
				}
				else
					al = new ArrayList<FoodItem>();
					al.add(new FoodItem(tfItemName.getText().trim(),tfItemType.getText().trim(),Integer.parseInt(tfPrice.getText().trim())));
						fos = new FileOutputStream(f);
						oos = new ObjectOutputStream(fos);
						oos.writeObject(al);
					JOptionPane.showMessageDialog(this, "Item added successfully");
				}catch (IOException | ClassNotFoundException | NumberFormatException e1) 
				{
					e1.printStackTrace();
				}
			this.dispose();
			break;
		case "bCancel":
			this.dispose();
			break;
		}
		
	}

}
