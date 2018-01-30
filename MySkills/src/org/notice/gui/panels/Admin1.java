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


public class Admin1 extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableSkills;
	private JButton btnAdd;
	private JButton btnDelete;
	private Font fontButton;
	private CommonStuff commonStuff;
	private SkillsUtilTableModel myModel ;
	private Transaction transaction;
	private SkillSelector skillSelector;
	private ArrayList<Skill> skills;
	
	public Admin1(CommonStuff inCommonStuff)
	{
		// skillName = ("PetraTest");
		// skillID = 51517;

		commonStuff = inCommonStuff;

		setLayout(null);

		fontButton = (new Font("Arial", Font.BOLD, 18));
	
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(true);
		//btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		btnDelete.setFont(fontButton);
		btnDelete.setBounds(574, 532, 100, 25);
		add(btnDelete);

		btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		// btnAdd.setEnabled(false);
		btnAdd.addActionListener(this);

		btnAdd.setFont(fontButton);
		btnAdd.setBounds(259, 532, 100, 25);
		add(btnAdd);
		
		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		skillSelector.setBounds(100, 32, 620, 400);
		
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
	 		
	    System.out.println("SKILLS  "  + skills.size());
	    skillSelector.getSkillModel().setSkills(skills);
		//skillSelector = new SkillSelector(commonStuff.getSkillsList());
		 
		skillSelector.setBounds(100, 32, 620, 500);
		add(skillSelector);
		System.out.println("After add skillSelector " );
	}
	

	private void refreshSkills()
	{
		transaction = new Transaction("getSkillList", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<Skill> newSkillList = (ArrayList<Skill>)transaction.getObject();
	    commonStuff.setSkillsList(newSkillList);
	    skillSelector.getSkillModel().setSkills(newSkillList);
	
	//	skillSelector = new SkillSelector(commonStuff.getSkillsList());
		
		
		System.out.println("Before SKILLS CLEAR");	
	 	
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
