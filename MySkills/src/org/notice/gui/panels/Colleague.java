package org.notice.gui.panels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.buscontrol.BusinessControl;
import org.notice.client.Transaction;



public class Colleague extends JPanel implements ActionListener
{
	private JComboBox comboBoxColleagueSearch;
	private JButton btnSearch;
	private JScrollPane scrollPaneColleagueSkills;
	private JTable tableColleagueSkills;
	private JButton btnRequestEndorsement, btnSave;
	private Font fontButton, fontComboBox;
	private JTextField textSearch;
	private BusinessControl businessControl = null;
	private int tableRows = 1, tableColumns = 3;
	private CommonStuff commonStuff = null;
	private Transaction transaction = null;
	private ArrayList<User> users = null;
	private ArrayList<UserSkills> userSkills= null;
	
	public Colleague(CommonStuff inCommonStuff)
	{
		commonStuff = inCommonStuff;
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		setLayout(null);
		comboBoxColleagueSearch = new JComboBox();
		comboBoxColleagueSearch.setFont(fontComboBox);
		comboBoxColleagueSearch.setBounds(242, 51, 302, 25);
		comboBoxColleagueSearch.setVisible(false);
		add(comboBoxColleagueSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(fontButton);
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		btnSearch.addActionListener(this);
		
		scrollPaneColleagueSkills = new JScrollPane();
		scrollPaneColleagueSkills.setBounds(140, 150, 620, 320);
		add(scrollPaneColleagueSkills);
		
		

		
//		tableColleagueSkills = new JTable(tableRows, tableColumns);
//		tableColleagueSkills.setShowHorizontalLines(true);
//		tableColleagueSkills.setShowVerticalLines(true);
		scrollPaneColleagueSkills.setColumnHeaderView(tableColleagueSkills);
		
		
		
		
		btnRequestEndorsement = new JButton("Request Endorsement");
		btnRequestEndorsement.setEnabled(false);
		btnRequestEndorsement.setFont(fontButton);
		btnRequestEndorsement.setBounds(190, 500, 243, 25);
		add(btnRequestEndorsement);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setFont(fontButton);
		btnSave.setBounds(620, 500, 90, 25);
		add(btnSave);
		
		textSearch = new JTextField();
		textSearch.setBounds(242, 25, 302, 22);
		add(textSearch);
		textSearch.setColumns(10);
		textSearch.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		String searchName = null, searchID = null;
		
		if(source == textSearch)
		{
			String displayName = null;
			transaction = new Transaction("getUserList", null);
			transaction = commonStuff.getClient().sendTransaction(transaction);
			users = (ArrayList<User>) transaction.getObject();
			if(users != null)
			{
				for(int pos = 0; pos < users.size(); pos++)
				{
					displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": " + users.get(pos).getUserID());
					comboBoxColleagueSearch.addItem(displayName);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Data For Selection");
			}
		}
		
		
		
		if(source == btnSearch)
		{
			searchName = (String)comboBoxColleagueSearch.getSelectedItem();
			if(searchName != null)
			{
				int delimeter = searchName.indexOf(':');
				searchID = searchName.substring(delimeter + 1);
				transaction = new Transaction("getUserSkills", searchID);
				userSkills = (ArrayList<UserSkills>) transaction.getObject();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Selection");	
			}
			
//			searchID = businessControl.fetchUserIDFromArrayList(searchSurname, searchFirstName);
//			businessControl.getUserSkills(searchID);
			
//			myModel new MyProfileRatedSkillTableModel(ratedSkills);
//			tableColleagueSkills = new JTable(myModel);
			
			
			
			tableColleagueSkills.setShowHorizontalLines(true);
			tableColleagueSkills.setShowVerticalLines(true);
			scrollPaneColleagueSkills.setColumnHeaderView(tableColleagueSkills);
		}
	}
}
