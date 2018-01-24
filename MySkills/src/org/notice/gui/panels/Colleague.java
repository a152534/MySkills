package org.notice.gui.panels;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.buscontrol.BusinessControl;
import org.notice.client.Transaction;
import org.notice.tablemodel.ColleagueProfileSkillTableModel;




public class Colleague extends JPanel implements ActionListener
{
	private JComboBox comboBoxColleagueSearch;
	private JScrollPane scrollPaneColleagueSkills;
	private JTable tableColleagueSkills;
	private JButton btnRequestEndorsement, btnSave, btnSearch;
	private Font fontButton, fontComboBox;
	private JTextField textSearch;
	private BusinessControl businessControl = null;
	private int tableRows = 1, tableColumns = 3;
	private CommonStuff commonStuff = null;
	private Transaction transaction = null;
	private ArrayList<User> users = null;
	private ArrayList<RatedSkills> ratedSkills= null;
	private ColleagueProfileSkillTableModel colleagueModel =  null;
	private JButton TestButton;
	
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
		btnRequestEndorsement.setVisible(true);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setFont(fontButton);
		btnSave.setBounds(620, 500, 90, 25);
		add(btnSave);
		btnSave.setVisible(true);
		
		textSearch = new JTextField();
		textSearch.setBounds(242, 25, 302, 22);
		add(textSearch);
		textSearch.setColumns(10);
		textSearch.addActionListener(this);
		
		TestButton = new JButton("Test");
		TestButton.setBounds(552, 24, 97, 25);
		TestButton.addActionListener(this);
		add(TestButton);
	}


	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		String searchName = null, searchID = null;
		
		if(source == TestButton)
		{
			String displayName = null;
			transaction = new Transaction("getUserList", textSearch.getText());
			transaction = commonStuff.getClient().sendTransaction(transaction);
			users = (ArrayList<User>) transaction.getObject();
			if(users != null)
			{
				for(int pos = 0; pos < users.size(); pos++)
				{
					displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": " + users.get(pos).getUserID());
					comboBoxColleagueSearch.addItem(displayName);
					comboBoxColleagueSearch.setVisible(true);
					btnRequestEndorsement.setVisible(true);					
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
				searchID = searchName.substring(delimeter + 2);
				System.out.println(searchID);
//				transaction = new Transaction("getUserSkills", searchID);
//				ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Selection");	
			}
			

			colleagueModel = new ColleagueProfileSkillTableModel(ratedSkills);
			tableColleagueSkills = new JTable(colleagueModel);
			tableColleagueSkills.setCellSelectionEnabled(true);
			tableColleagueSkills.setShowHorizontalLines(true);
			tableColleagueSkills.setShowVerticalLines(true);
			
			scrollPaneColleagueSkills.setColumnHeaderView(tableColleagueSkills);
			scrollPaneColleagueSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneColleagueSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPaneColleagueSkills.setBounds(140, 120, 620, 250);
			add(scrollPaneColleagueSkills);
			btnSave.setVisible(true);
		}
		
		if(source == btnRequestEndorsement)
		{
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to request an endorsement");
			
			if(option == JOptionPane.OK_OPTION)
			{
				
			}
			return;	
		}
	}
}
