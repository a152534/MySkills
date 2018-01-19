package org.notice.gui.panels;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Colleague extends JPanel
{
	private JComboBox comboBoxColleagueSearch;
	private JButton btnSearch;
	private JScrollPane scrollPaneColleagueSkills;
	private JTable tableColleagueSkills;
	private JButton btnRequestEndorsement;
	private JButton btnSave;
	
	public Colleague() 
	{
		setLayout(null);
		comboBoxColleagueSearch = new JComboBox();
		comboBoxColleagueSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBoxColleagueSearch.setBounds(240, 50, 302, 25);
		add(comboBoxColleagueSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Ariel", Font.BOLD, 18));
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		
		scrollPaneColleagueSkills = new JScrollPane();
		scrollPaneColleagueSkills.setBounds(140, 150, 620, 320);
		add(scrollPaneColleagueSkills);
		
		tableColleagueSkills = new JTable();
		scrollPaneColleagueSkills.setColumnHeaderView(tableColleagueSkills);
		
		btnRequestEndorsement = new JButton("Request Endorsement");
		btnRequestEndorsement.setEnabled(false);
		btnRequestEndorsement.setFont(new Font("Arial", Font.BOLD, 18));
		btnRequestEndorsement.setBounds(190, 500, 243, 25);
		add(btnRequestEndorsement);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Arial", Font.BOLD, 18));
		btnSave.setBounds(620, 500, 90, 25);
		add(btnSave);
	}

}
