package org.notice.gui.panels;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import org.notice.beans.CommonStuff;
import org.notice.buscontrol.BusinessControl;
import java.awt.Color;
import java.awt.Dimension;

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
		
		
//		TableModel colleagueSkillTable = new AbstractTableModel()
//				{
//
//					@Override
//					public int getRowCount()
//					{
//						return 5;//Must find a way to insert int of how man y actual rows.
//					}
//
//					@Override
//					public int getColumnCount()
//					{
//						return 3;
//					}
//
//					@Override
//					public Object getValueAt(int rowIndex, int columnIndex)
//					{
//						return null;
//					}
//			
//				};
		
		tableColleagueSkills = new JTable(tableRows, tableColumns);
		tableColleagueSkills.setShowHorizontalLines(true);
		tableColleagueSkills.setShowVerticalLines(true);
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
		businessControl = new BusinessControl();
		String searchName = null, searchID = null;
		ArrayList searchList = new ArrayList();
		
		if(source == textSearch)
		{
			searchList = businessControl.getUserList(textSearch.getText());
			
			for(int pos = 0; pos < searchList.size(); pos++)
			{
				comboBoxColleagueSearch.addItem(((BusinessControl) searchList.get(pos)).fetchUserNamesFromArrayList(searchList));
			}
		}
		
		if(source == btnSearch)
		{
			searchName = (String)comboBoxColleagueSearch.getSelectedItem();
			int index = searchName.indexOf(',');
			String searchSurname = searchName.substring(0, index);
			String searchFirstName = searchName.substring(index + 1);				
			searchID = businessControl.fetchUserIDFromArrayList(searchSurname, searchFirstName);
			businessControl.getUserSkills(searchID);
			
		}
	}
}
