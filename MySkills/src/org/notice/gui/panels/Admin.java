package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;

public class Admin extends JPanel
{
	private JComboBox comboBox;
	private JButton btnAdd;
	private JButton btnDelete;
	private Font fontComboBox;
	private Font fontButton;
	
	public Admin() 
	{
		setLayout(null);
		
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		fontButton = (new Font("Arial", Font.BOLD, 18));		
		
		comboBox = new JComboBox();
		comboBox.setBounds(300, 50, 300, 25);
		comboBox.setFont(fontComboBox);
		add(comboBox);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(fontButton);
		btnAdd.setBounds(220, 451, 100, 25);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(fontButton);
		btnDelete.setBounds(535, 451, 100, 25);
		add(btnDelete);
	}

}
