package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.notice.beans.CommonStuff;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class MyProfile extends JPanel 
{
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAlias;
	private JTextField txtPhone;
	private JLabel lblEmailAddress;
	private JTextField txtEmail;
	private JScrollPane scrollPaneSkills;
	private JTable tableSkills;
	private JButton btnSave;
	private JButton btnAddSkill;
	private JButton btnDeleteSkill;
	private JScrollPane scrollPaneEndorsementRequests;
	private JTable tableEndorsementRequests;
	private Font fontLabel;
	private Font fontTextBox;
	private Font fontButton;
	private Font fontComboBox;
	private CommonStuff commonStuff; 

	/**
	 * Create the panel.
	 */
	public MyProfile(CommonStuff inCommonStuff) 
	{
		commonStuff = inCommonStuff ; 
		setLayout(null);
		
		fontLabel = (new Font("Arial", Font.BOLD, 14));
		fontTextBox = (new Font("Arial", Font.PLAIN, 12));
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(fontLabel);
		lblName.setBounds(20, 20, 60, 20);
		add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(fontLabel);
		lblSurname.setBounds(20, 50, 75, 20);
		add(lblSurname);
		
		JLabel lblAlias = new JLabel("Alias:");
		lblAlias.setFont(fontLabel);
		lblAlias.setBounds(20, 80, 60, 20);
		add(lblAlias);
		
		txtName = new JTextField();
		txtName.setFont(fontTextBox);
		txtName.setBounds(97, 20, 300, 20);
		add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setFont(fontTextBox);
		txtSurname.setBounds(97, 50, 300, 19);
		add(txtSurname);
		txtSurname.setColumns(10);
		
		txtAlias = new JTextField();
		txtAlias.setFont(fontTextBox);
		txtAlias.setBounds(97, 80, 300, 19);
		add(txtAlias);
		txtAlias.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(fontLabel);
		lblPhoneNumber.setBounds(450, 21, 136, 15);
		add(lblPhoneNumber);
		
		txtPhone = new JTextField();
		txtPhone.setFont(fontTextBox);
		txtPhone.setColumns(10);
		txtPhone.setBounds(570, 21, 300, 19);
		add(txtPhone);
		
		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(fontLabel);
		lblEmailAddress.setBounds(450, 51, 131, 15);
		add(lblEmailAddress);
		
		txtEmail = new JTextField();
		txtEmail.setFont(fontTextBox);
		txtEmail.setColumns(10);
		txtEmail.setBounds(570, 51, 300, 19);
		add(txtEmail);
		
		scrollPaneSkills = new JScrollPane();
		scrollPaneSkills.setBounds(140, 120, 620, 250);
				
		tableSkills = new JTable();
		tableSkills.setBounds(0, 0, 1, 1);
		scrollPaneSkills.add(tableSkills);
		add(scrollPaneSkills);
		
		btnSave = new JButton("Save");
		btnSave.setFont(fontButton);
		btnSave.setBounds(780, 80, 90, 25);
		add(btnSave);
		
		btnAddSkill = new JButton("Add Skill");
		btnAddSkill.setFont(fontButton);
		btnAddSkill.setBounds(220, 392, 145, 25);
		add(btnAddSkill);
		
		btnDeleteSkill = new JButton("Delete Skill");
		btnDeleteSkill.setEnabled(false);
		btnDeleteSkill.setFont(fontButton);
		btnDeleteSkill.setBounds(535, 392, 145, 25);
		add(btnDeleteSkill);
		
		scrollPaneEndorsementRequests = new JScrollPane();
		scrollPaneEndorsementRequests.setBounds(140, 440, 620, 132);
		add(scrollPaneEndorsementRequests);
		
		tableEndorsementRequests = new JTable();
		scrollPaneEndorsementRequests.setColumnHeaderView(tableEndorsementRequests);

		populateUserInfo();
	}

	private void populateUserInfo() {
		txtAlias.setText(commonStuff.getLoggedOnUser().getAliasName());
		txtName.setText(commonStuff.getLoggedOnUser().getFirstName());
		txtSurname.setText(commonStuff.getLoggedOnUser().getSurName());
		txtPhone.setText(commonStuff.getLoggedOnUser().getPhoneNumber());
		txtEmail.setText(commonStuff.getLoggedOnUser().getEmail());
		
		
	}
	
}
