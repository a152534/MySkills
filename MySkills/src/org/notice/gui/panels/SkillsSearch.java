package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SkillsSearch extends JPanel
{
	private JComboBox comboBoxSkillsSearch;
	private JButton btnSearch;
	private JScrollPane scrollPaneSkillsSearch;
	private JTable tableSkillsSearch;
	private Font fontButton;
	private Font fontComboBox;
	
	public SkillsSearch() 
	{
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		comboBoxSkillsSearch = new JComboBox();
		comboBoxSkillsSearch.setFont(fontComboBox);
		comboBoxSkillsSearch.setBounds(240, 50, 302, 25);
		add(comboBoxSkillsSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(fontButton);
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		
		scrollPaneSkillsSearch = new JScrollPane();
		scrollPaneSkillsSearch.setBounds(140, 150, 620, 320);
		add(scrollPaneSkillsSearch);
		
		tableSkillsSearch = new JTable();
		scrollPaneSkillsSearch.setViewportView(tableSkillsSearch);
	}
}
