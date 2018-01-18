package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyProfile extends JPanel {
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAlias;

	/**
	 * Create the panel.
	 */
	public MyProfile() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(15, 15, 60, 15);
		add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(15, 45, 60, 15);
		add(lblSurname);
		
		JLabel lblAlias = new JLabel("Alias:");
		lblAlias.setBounds(15, 75, 60, 15);
		add(lblAlias);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(120, 10, 160, 19);
		add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setText("Surname");
		txtSurname.setBounds(120, 37, 114, 19);
		add(txtSurname);
		txtSurname.setColumns(10);
		
		txtAlias = new JTextField();
		txtAlias.setText("Alias");
		txtAlias.setBounds(120, 64, 114, 19);
		add(txtAlias);
		txtAlias.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setBounds(382, 15, 130, 15);
		add(lblPhoneNumber);

	}
}
