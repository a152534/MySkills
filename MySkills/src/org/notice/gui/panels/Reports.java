package org.notice.gui.panels;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Reports extends JPanel
{
	private JButton btnSkills;
	private JButton btnUsers;
	private JScrollPane scrollPaneSkillsUsers;
	private JTable tableSkillsUsers;
	private Font fontButton;
	
	public Reports() 
	{
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		
		btnSkills = new JButton("Skills");
		btnSkills.setFont(fontButton);
		btnSkills.setBounds(220, 50, 100, 25);
		add(btnSkills);
		
		btnUsers = new JButton("Users");
		btnUsers.setFont(fontButton);
		btnUsers.setBounds(535, 50, 100, 25);
		add(btnUsers);
		
		scrollPaneSkillsUsers = new JScrollPane();
		scrollPaneSkillsUsers.setBounds(140, 150, 620, 320);
		add(scrollPaneSkillsUsers);
		
		tableSkillsUsers = new JTable();
		scrollPaneSkillsUsers.setViewportView(tableSkillsUsers);
		
		
		
		
	}

}
