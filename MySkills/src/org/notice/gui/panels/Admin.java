package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import org.notice.beans.*;
import org.notice.buscontrol.*;
import org.notice.client.Transaction;
import org.notice.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;
import java.awt.Button;

public class Admin extends JPanel implements ActionListener
{
	private JComboBox<String> comboBox = null;
	private JTable tableSkills;
	private JScrollPane scrollPaneSkills;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnDelete;
	private Font fontButton;
	private Font fontTextArea;
	private CommonStuff commonStuff; 
	private Transaction transaction;
	private static JButton btnYes;
	private JButton btnNo;
	private BusinessControl businessControl;
	private String deleteSkill;
	private int skillID;
	private String dulicateSkill;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextArea textAreaSearch;
	
	
	public Admin(CommonStuff inCommonStuff)  
	{
		commonStuff = inCommonStuff ; 
		
		setLayout(null);
	
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontTextArea = (new Font("Arial", Font.PLAIN, 14));
		
		textAreaSearch = new JTextArea();
		textAreaSearch.setBounds(184, 75, 371, 25);
		textAreaSearch.setFont(fontButton);
		add(textAreaSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(565, 75, 110, 25);
		btnSearch.setFont(fontButton);
		add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 107, 491, 302);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
		
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnAdd.setFont(fontButton);
		btnAdd.setBounds(220, 451, 100, 25);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setFont(fontButton);
		btnDelete.setBounds(535, 451, 100, 25);
		add(btnDelete);
			
		populateSkillsInfo();	
	}
	
	private void populateSkillsInfo() 
	{
		Vector<String> allskills = new Vector<String>() ; 
		for (Skill skill : commonStuff.getSkillsList()) 
		{
			allskills.add(skill.getSkillName() + "    ID_" + skill.getSkillID());
		}
		Collections.sort(allskills);
		JComboBox<String> comboSkill = new JComboBox<String>(allskills);
		
		JOptionPane.showMessageDialog( this, comboSkill, "select a skill", JOptionPane.QUESTION_MESSAGE);
		String selectedSkill = (String)comboSkill.getSelectedItem();
		JComboBox<String> comboLevel = new JComboBox<String>();
		comboLevel.addItem("1");
		comboLevel.addItem("2");
		comboLevel.addItem("3");
		comboLevel.addItem("4");
		comboLevel.addItem("5");
		comboLevel.addItem("6");
		comboLevel.addItem("7");
		comboLevel.addItem("8");
		
		
		
//			comboBox.getToolTipText(commonStuff.getLoggedOnUser());
//		
////		ArrayList<Skill> skillsList = new ArrayList<Skill>();
////		businessControl = new BusinessControl();
////		
////		transaction = new Transaction("getSkillList", null);
//// 		transaction = commonStuff.getClient().sendTransaction(transaction);
////		
//// 		skillsList = (ArrayList<Skill>)transaction.getObject();
//// 		
////		for(int pos = 0; pos < skillsList.size() -1 ; pos++)
////		{
////			comboBox.addItem(skillsList.get(pos).getSkillName());
////			System.out.println("In for loop");
////		}
// 		
//		//populateSkillsInfo();
//		
//		scrollPaneSkills = new JScrollPane(tableSkills);
//		scrollPaneSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPaneSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPaneSkills.setBounds(300, 75, 300, 250);
//		add(scrollPaneSkills);

	}


	
//	public void setUpLevelColumn(JTable table, TableColumn levelColumn) 
//	{
//		
//		JComboBox comboBox = new JComboBox();
//		comboBox.addItem("1");
//		comboBox.addItem("2");
//		comboBox.addItem("3");
//		comboBox.addItem("4");
//		comboBox.addItem("5");
//		comboBox.addItem("6");
//		comboBox.addItem("7");
//		comboBox.addItem("8");
//		comboBox.addItem("9");
//		comboBox.addItem("10");
//		
//		levelColumn.setCellEditor(new DefaultCellEditor(comboBox));
//
//		
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
//		levelColumn.setCellRenderer(renderer);
//	}
//	
	@Override
	public void actionPerformed(ActionEvent e)
	{
//		Object source = e.getSource();
//		if(source == btnDelete) 
//		{
//			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete skill?");
//			
//			if(dialogResult == JOptionPane.YES_NO_OPTION)
//			{
//				transaction = new Transaction(deleteSkill, skillID);
//			}
//			
//			int dialogResult2 = JOptionPane.showConfirmDialog(null, "Skill is already in use, so cannot be deleted?");
//			
//			//if(dialogResult2 == dulicateSkill)
//			{
//				
//			}
//		}
//		
//		if(source ==btnAdd)
//		{
//			populateSkillsInfo();
//		}
	}
}
