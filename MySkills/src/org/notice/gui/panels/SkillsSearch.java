package org.notice.gui.panels;


import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import org.notice.beans.CommonStuff;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
 
import org.notice.buscontrol.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;

public class SkillsSearch extends JPanel implements ActionListener
{
	//private JComboBox comboBoxSkillsSearch;
	private JComboBox<String> comboBoxSkillsSearch = null;
	private JButton btnSearch;
	private JScrollPane scrollPaneSkillsSearch;
	private JScrollPane scrollPaneSkills;
	private JTable tableSkillsSearch;
	private Font fontButton;
	private Font fontComboBox;
	private Transaction transaction ; 
	private JTable tableSkills;
	private CommonStuff commonStuff;
	private BusinessControl businessControl;
	
	
	public SkillsSearch(CommonStuff inCommonStuff) 
	{
		System.out.println("In Skills Search");
		commonStuff = inCommonStuff;
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		comboBoxSkillsSearch = new JComboBox();
		
	//	populateSkillsList();
		
		comboBoxSkillsSearch.setFont(fontComboBox);
		comboBoxSkillsSearch.setBounds(240, 50, 302, 25);
		add(comboBoxSkillsSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(fontButton);
		btnSearch.setBounds(552, 50, 108, 25);
		add(btnSearch);
		btnSearch.addActionListener(this);
		
		scrollPaneSkillsSearch = new JScrollPane();
		scrollPaneSkillsSearch.setBounds(140, 150, 620, 320);
		add(scrollPaneSkillsSearch);
		
		tableSkillsSearch = new JTable();
		scrollPaneSkillsSearch.setViewportView(tableSkillsSearch);
	}
	
	private void populateSkillsList() {
		ArrayList<Skill> skillsList = new ArrayList<Skill> ();
		businessControl = new BusinessControl();
		
		System.out.println("In populate skills");
//		commonStuff = new CommonStuff();
		
	//	skillsList = commonStuff.getSkillsList(); 
		
		transaction = new Transaction("getSkillList", null);
 		transaction = commonStuff.getClient().sendTransaction(transaction);
 		
 	//	skillsList = (ArrayList<Skill>)businessControl.getSkillList();
		skillsList = (ArrayList<Skill>)transaction.getObject();
		
		System.out.println("After skill list");
		
	 
	//	System.out.println("Skill List to string" + skillsList.toString());
	//	System.out.printl("Skill List to object" + skillsList.toArray());
		System.out.println("userskilllist size" + skillsList.size());
	//	System.out.println("Skill List is empty" + skillsList.isEmpty());
		
		 
		for(int pos = 0; pos < skillsList.size() -1 ; pos++)
		{
			System.out.println("In for loop");
			//comboBoxSkillsSearch.addItem(skillsList.get(pos).getSkillName());
		}
	}
	
	private void populateSkillPerUser() {
		
		int skill = 0; 
	    skill = comboBoxSkillsSearch.getSelectedIndex();
	    String skillSearch = null;
	 // get the skill id
	    
	    
		ArrayList<RatedSkills> ratedSkills;
		//  getuserskills, null
//		transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
//		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>)transaction.getObject();
				
		MyProfileRatedSkillTableModel myModel = new MyProfileRatedSkillTableModel(ratedSkills);

		tableSkills = new JTable(myModel);
		//tableSkills.setColumnSelectionAllowed(true);
		tableSkills.setCellSelectionEnabled(true);
	
		// tableSkills.setBounds(0, 0, 1, 1);
		// scrollPaneSkills.add(tableSkills);
		setUpLevelColumn(tableSkills, tableSkills.getColumnModel().getColumn(1));
		myModel.fireTableDataChanged();

		scrollPaneSkills = new JScrollPane(tableSkills);
		scrollPaneSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setBounds(140, 120, 620, 250);
		add(scrollPaneSkills);
		
	}
	public void setUpLevelColumn(JTable table, TableColumn levelColumn) {
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		
		levelColumn.setCellEditor(new DefaultCellEditor(comboBox));

		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		levelColumn.setCellRenderer(renderer);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == btnSearch) 
		{
			populateSkillPerUser();
					}
	}
}
