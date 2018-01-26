package org.notice.gui.panels;


import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import org.notice.beans.CommonStuff;
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
	private Font fontButton;
	private Font fontComboBox;
	private Transaction transaction ; 
	private CommonStuff commonStuff;
	private SkillsRatedTableModel myModel ;
	private SkillSelector skillSelector;
	private JButton btnSearch;
	private JButton btnQuery;
	private TextArea textArea;
	private JButton btnClear;
	private ArrayList<Skill> skillList;
	 
	 
	
	public SkillsSearch(CommonStuff inCommonStuff) 
	{
		System.out.println("In Skills Search");
		commonStuff = inCommonStuff;
		setLayout(null);
		skillList = new ArrayList<Skill>();

	//	mediaCatalogue = new ArrayList<Media>();
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		skillSelector.setBounds(100, 32, 620, 400);
	//	add(skillSelector);
		
		btnSearch = new JButton("Search on Skills");
		btnSearch.setBounds(370, 115, 168, 24);
		add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnQuery = new JButton("Select Skills to Search");
		btnQuery.setBounds(10, 115, 168, 24);
	 
		add(btnQuery);
		btnQuery.addActionListener(this);
		
		textArea = new TextArea();
		textArea.setBounds(10, 10, 587, 102);
		 
		add(textArea);
		
		btnClear = new JButton("Clear Search Skills");
		btnClear.setBounds(190, 115, 168, 24);
		add(btnClear);
		btnClear.addActionListener(this);
		
	}
	private void populateSkillPerUser() {

		int skill = 0;
		Skill skillSelected = skillSelector.getSelectedSkill();
	 
		 
	    ArrayList<UserSkillEndorsements> userSkillEndorsements;
	    
	 //   transaction = new Transaction("getUserEndorsementsPerSkill", skillSelected.getSkillID());
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
	public void setUpLevelColumn(JTable table, TableColumn levelColumn) {
		
		System.out.println("In setuplevelcolumn");
		
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
			add(skillSelector);
			int result =JOptionPane.showConfirmDialog(null, skillSelector, "Select a skill ", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE);
		
			if (result == JOptionPane.CANCEL_OPTION) {
				return ;
			} 
			if (result == JOptionPane.OK_OPTION) {
			 
				Skill skillSelected = skillSelector.getSelectedSkill();
				System.out.println("Skill " + skillSelected);
				
				String text =  null;
				text = skillSelected.getSkillName();
			
				textArea.setText(textArea.getText() + "\n" + text);
			 
				skillList.add(new Skill(skillSelected.getSkillID(), skillSelected.getSkillName())); 
			}
		}
		if (source == btnSearch)
		{
			populateSkillPerUser();
			for(int pos = 0; pos < skillList.size(); pos++)
			{
				System.out.println("Array List LOOP" + skillList.get(pos).getSkillID());
				System.out.println("Array List LOOP" + skillList.get(pos).getSkillName());
			 
			}
			
		}
		if (source == btnClear)
		{
			textArea.setText(null);
			skillList.clear();
		}
	}
}

