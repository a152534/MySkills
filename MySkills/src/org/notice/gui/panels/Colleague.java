package org.notice.gui.panels;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Colleague extends JPanel implements ActionListener
{
	private JComboBox comboBoxColleagueSearch;
	private JButton btnSearch;
	private JScrollPane scrollPaneColleagueSkills;
	private JTable tableColleagueSkills;
	private JButton btnRequestEndorsement, btnSave;
	private Font fontButton, fontComboBox;
	
	public Colleague()
	{
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		setLayout(null);
		comboBoxColleagueSearch = new JComboBox();
		comboBoxColleagueSearch.setFont(fontComboBox);
		comboBoxColleagueSearch.setBounds(240, 50, 302, 25);
		comboBoxColleagueSearch.isEditable();
		add(comboBoxColleagueSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(fontButton);
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		
		scrollPaneColleagueSkills = new JScrollPane();
		scrollPaneColleagueSkills.setBounds(140, 150, 620, 320);
		add(scrollPaneColleagueSkills);
		
		tableColleagueSkills = new JTable();
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
	}


	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if(source == btnSearch)
		{
			String colleagueSearch = (String)comboBoxColleagueSearch.getSelectedItem();
		}
		
	}

}
