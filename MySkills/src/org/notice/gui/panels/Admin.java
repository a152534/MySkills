package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private JTable tableSkills;
	private JScrollPane scrollPaneSkills;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnDelete;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontComboBoxSearch;
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
	private JComboBox<String> comboBoxSearch = null;
	private String skillName;
	
	
	public Admin(CommonStuff inCommonStuff)
	{
		skillName = ("PetraTest");
		skillID = 51517; 
		
		commonStuff = inCommonStuff ; 
		
		setLayout(null);
	
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontTextArea = (new Font("Arial", Font.PLAIN, 14));
		fontComboBoxSearch = (new Font("Arial", Font.PLAIN, 14));
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(565, 75, 110, 25);
		btnSearch.setFont(fontButton);
		add(btnSearch);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setBounds(184, 79, 371, 20);
		add(comboBoxSearch);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(true);
		//btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		btnDelete.setFont(fontButton);
		btnDelete.setBounds(535, 451, 100, 25);
		add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		//btnAdd.setEnabled(false);
		btnAdd.addActionListener(this);

		btnAdd.setFont(fontButton);
		btnAdd.setBounds(220, 451, 100, 25);
		add(btnAdd);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 107, 491, 302);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
			
		populateSkillsList();	
	}

		private void populateSkillsList() 
		{
			ArrayList<Skill> skillsList = new ArrayList<Skill> ();

			transaction = new Transaction("getSkillList", null);
	 		transaction = commonStuff.getClient().sendTransaction(transaction);

	 		skillsList = (ArrayList<Skill>)transaction.getObject();
			 
			for(int pos = 0; pos < skillsList.size() -1 ; pos++)
			{
				comboBoxSearch.addItem(skillsList.get(pos).getSkillName());
			}
			
		}
		
//		
	@Override
		public void actionPerformed(ActionEvent e)
	{
		
		Object source = e.getSource();
		boolean success = false;
		if(source == btnSearch) 
		{
			System.out.println("in action performed");
			populateSkillsList();
		}
		
		if(source == btnDelete) 
		{
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete skill? " + skillID );
			
			if(dialogResult == JOptionPane.YES_NO_OPTION)
			{
				transaction = new Transaction("DeleteSkill", skillID); //setup transaction
				transaction = commonStuff.getClient().sendTransaction(transaction); //sent transaction
				success = (boolean) transaction.getObject();
			} 
			
			if(!success)
			{
				int dialogResult2 = JOptionPane.showConfirmDialog(null,"Skill is already in use, so cannot be deleted?");
				dialogResult2 = JOptionPane.OK_OPTION; //duplicateSkill
				}
		}
		
		if(source ==btnAdd)
		{
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add skill?" + skillName);
			
			if(dialogResult == JOptionPane.YES_NO_OPTION)
			{
				transaction = new Transaction("AddSkill", skillName); //setup transaction
				transaction = commonStuff.getClient().sendTransaction(transaction); //sent transaction
				success = (boolean) transaction.getObject();
			} 
			
			if(!success)
			{
				int dialogResult2 = JOptionPane.showConfirmDialog(null, "Error - Please contact Help Desk");
				dialogResult2 = JOptionPane.OK_OPTION; //duplicateSkill
				
			}
			else
			{
				int dialogResult2 = JOptionPane.showConfirmDialog(null, "Skill successfully added");
			}
		}
	}
}
