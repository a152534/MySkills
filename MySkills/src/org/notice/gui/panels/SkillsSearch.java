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
	public SkillsSearch() {
		setLayout(null);
		
		comboBoxSkillsSearch = new JComboBox();
		comboBoxSkillsSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBoxSkillsSearch.setBounds(240, 50, 302, 25);
		add(comboBoxSkillsSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		
		scrollPaneSkillsSearch = new JScrollPane();
		scrollPaneSkillsSearch.setBounds(140, 150, 620, 320);
		add(scrollPaneSkillsSearch);
		
		tableSkillsSearch = new JTable();
		scrollPaneSkillsSearch.setViewportView(tableSkillsSearch);
	}
}
