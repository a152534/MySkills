package org.notice.gui.panels;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;


import org.notice.beans.Skill;

import org.notice.buscontrol.*;
import org.notice.beans.CommonStuff;
import org.notice.client.Transaction;
import org.notice.gui.panels.SkillSelector;
import org.notice.tablemodel.SkillsRatedTableModel;
import org.notice.tablemodel.SkillsUtilTableModel;


public class Admin extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableSkills;
	private JButton btnAdd;
	private JButton btnDelete;
	private CommonStuff commonStuff;
	private SkillsUtilTableModel myModel ;
	private Transaction transaction;
	private SkillSelector skillSelector;
	private ArrayList<Skill> skills;
	private Font fontLabel;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontTextBox;
	private Font fontComboBox;

	
	public Admin(CommonStuff inCommonStuff)
	{
		// skillName = ("PetraTest");
		// skillID = 51517;

		commonStuff = inCommonStuff;
		
		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();

		setLayout(null);
	
		btnDelete = new JButton("Delete Skill");
		btnDelete.setEnabled(true);
		//btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		btnDelete.setFont(fontButton);
		btnDelete.setBounds(510, 532, 150, 25);
		add(btnDelete);

		btnAdd = new JButton("Add Skill");
		btnAdd.setEnabled(true);
		// btnAdd.setEnabled(false);
		btnAdd.addActionListener(this);

		btnAdd.setFont(fontButton);
		btnAdd.setBounds(270, 532, 150, 25);
		add(btnAdd);
		
		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		skillSelector.setBounds(100, 32, 620, 537);
		
		skills = new ArrayList<Skill>();
	

	
		skillSelector.getSkillModel().fireTableDataChanged();
		 
 		 
		
		//myModel.fireTableDataChanged();
		 

		 populateSkillsList();
	}

	private void populateSkillsList()
	{
		transaction = new Transaction("getSkillList", null);
 		
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<Skill> skills = (ArrayList<Skill>)transaction.getObject();
	 		
	    //System.out.println("SKILLS  "  + skills.size());
	    skillSelector.getSkillModel().setSkills(skills);
		//skillSelector = new SkillSelector(commonStuff.getSkillsList());
		 
		skillSelector.setBounds(100, 32, 620, 500);
		add(skillSelector);
		//System.out.println("After add skillSelector " );
	}
	

	private void refreshSkills()
	{
		transaction = new Transaction("getSkillList", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<Skill> newSkillList = (ArrayList<Skill>)transaction.getObject();
	    commonStuff.setSkillsList(newSkillList);
	    skillSelector.getSkillModel().setSkills(newSkillList);
	
	//	skillSelector = new SkillSelector(commonStuff.getSkillsList());
		
	//	System.out.println("Before SKILLS CLEAR");	
	 	
	// 	skills.clear();  
	//	for (Skill skill : newSkillList)
	//	{
	//		skills.add(skill);
	//	}
//		skillSelector.setSkillModel(myModel);
//		 
//		myModel.fireTableDataChanged();
		skillSelector.getSkillModel().fireTableDataChanged();
		//validate();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		boolean successful = false;
		
		if(e.getSource()== tableSkills) 
		{
			btnDelete.setEnabled(true);
		}

		if (source == btnDelete)
		{
			Skill removeSkill = skillSelector.getSelectedSkill();
			
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete skill? " + removeSkill.getSkillName());

			if (dialogResult == JOptionPane.YES_OPTION)
			{
				transaction = new Transaction("DeleteSkill", removeSkill.getSkillID()); // setup
																		// transaction
			//	System.out.println("Delete SkillID: " + removeSkill.getSkillID());
				transaction = commonStuff.getClient().sendTransaction(transaction); // sent
																					// transaction
				successful = (boolean) transaction.getObject();
				
				if(successful)
				{
					JOptionPane.showMessageDialog(null, "Skill has been successfully deleted");
		
					refreshSkills();
				}
			}

			if (!successful)
			{
				JOptionPane.showMessageDialog(null, "Skill is already in use, so cannot be deleted?");
				 
			}
		}

		if (source == btnAdd)
		{
			Skill newSkill = skillSelector.getSelectedSkill();
			if (newSkill.getSkillID() == -1)
			{

				int dialogResult = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to add skill?" + newSkill.getSkillName());

				if (dialogResult == JOptionPane.YES_NO_OPTION)
				{
					transaction = new Transaction("AddSkill", newSkill.getSkillName()); // setup
					// transaction
					transaction = commonStuff.getClient().sendTransaction(transaction); // sent
																						// transaction
					successful = (boolean) transaction.getObject();
					 
				}

				if (!successful)
				{
					JOptionPane.showMessageDialog(null, "Error - Please contact Help Desk");
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Skill successfully added");
					refreshSkills();
				 
				}

			}
		}
	}
}
