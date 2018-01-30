package org.notice.gui.panels;


import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import org.notice.beans.CommonStuff;
import org.notice.beans.RatedSkills;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.notice.beans.UserSkillEndorsements;
 
import org.notice.beans.Skill;
 
import org.notice.buscontrol.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;
import org.notice.tablemodel.SkillsRatedTableModel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;

public class SkillsSearch extends JPanel implements ActionListener
{

	private JComboBox<String> comboBoxSkillsSearch = null;
	private JScrollPane scrollPaneSkillsSearch;
	private JTable tableSkillsSearch;
	private Transaction transaction ; 
	private CommonStuff commonStuff;
	private SkillsRatedTableModel myModel ;
	private SkillSelector skillSelector;
	private JButton btnSearch;
	private JButton btnQuery;
	private TextArea textArea;
	private JButton btnClear;
	private ArrayList<Skill> skillList;
	private  ArrayList<UserSkillEndorsements> userSkillEndorsements;
	private Skill skillSelected;
	
	private Font fontLabel;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontTextBox;
	private Font fontComboBox;
	
	public SkillsSearch(CommonStuff inCommonStuff) 
	{
		
		commonStuff = inCommonStuff;
		
		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();
		
		
		
		setLayout(null);
		skillList = new ArrayList<Skill>();

//		fontButton = (new Font("Arial", Font.BOLD, 18));
//		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		skillSelector.setBounds(100, 32, 620, 400);
		
		btnSearch = new JButton("Search on Skills");
		btnSearch.setBounds(370, 115, 168, 24);
		btnSearch.setFont(fontButton);
		add(btnSearch);
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(this);
		
		btnQuery = new JButton("Select Skills to Search");
		btnQuery.setBounds(10, 115, 168, 24);
		btnQuery.setFont(fontButton);
		add(btnQuery);
		btnQuery.addActionListener(this);
		
		textArea = new TextArea();
		textArea.setBounds(10, 10, 587, 102);
		textArea.setFont(fontTextArea);
		textArea.setEditable(false);; 
		add(textArea);
		
		btnClear = new JButton("Clear Search Skills");
		btnClear.setBounds(190, 115, 168, 24);
		btnClear.setFont(fontButton);
		add(btnClear);
		btnClear.setEnabled(false);
		btnClear.addActionListener(this);
		
		populateSkillPerUser();
			
	}
	private void populateSkillPerUser() {
		
		int skill = 0;
		skillSelected = skillSelector.getSelectedSkill();
	 
	    transaction = new Transaction("getUserEndorsementsPerSkill", skillList);
 		
	    
	    transaction = commonStuff.getClient().sendTransaction(transaction);
 		
 		userSkillEndorsements = (ArrayList<UserSkillEndorsements>)transaction.getObject();
	    
		 
 		myModel = new SkillsRatedTableModel(userSkillEndorsements);

 		tableSkillsSearch = new JTable(myModel);
		tableSkillsSearch.setCellSelectionEnabled(true);
		tableSkillsSearch.setAutoCreateRowSorter(true);
		
	
		myModel.fireTableDataChanged();

		scrollPaneSkillsSearch = new JScrollPane(tableSkillsSearch);
		scrollPaneSkillsSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsSearch.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsSearch.setBounds(80, 180, 800, 450);
			 
		add(scrollPaneSkillsSearch);
		
	}
	
	private void refreshSkillPerUser() {
		
		skillSelected = skillSelector.getSelectedSkill();
	    transaction = new Transaction("getUserEndorsementsPerSkill", skillList);
 		
	    transaction = commonStuff.getClient().sendTransaction(transaction);
 		
 	    ArrayList<UserSkillEndorsements> newUserSkillEndorsements = (ArrayList<UserSkillEndorsements>)transaction.getObject();
 		
 		userSkillEndorsements.clear();
		for (UserSkillEndorsements skill : newUserSkillEndorsements) {
			userSkillEndorsements.add(skill);

		}		
	
		myModel.fireTableDataChanged();
		
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
		if (source == btnQuery)
		{
			btnSearch.setEnabled(true);
			btnClear.setEnabled(true);
			add(skillSelector);
			int result =JOptionPane.showConfirmDialog(null, skillSelector, "Select a skill ", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE);
		
			if (result == JOptionPane.CANCEL_OPTION) {
				return ;
			} 
			if (result == JOptionPane.OK_OPTION) {
			 
				Skill skillSelected = skillSelector.getSelectedSkill();
				
				String text =  null;
				text = skillSelected.getSkillName();
			
				textArea.setText(textArea.getText() + "\n" + text);
			 
				skillList.add(new Skill(skillSelected.getSkillID(), skillSelected.getSkillName())); 
			}
			
		}
		if (source == btnSearch)
		{
			System.out.println("IN BUTTON SEARCH");
			refreshSkillPerUser();
		}

		if (source == btnClear)
		{
			textArea.setText(null);
			skillList.clear();
		}
	}
}

