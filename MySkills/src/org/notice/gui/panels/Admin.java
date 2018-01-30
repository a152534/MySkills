package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
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
	private JButton btnAdd;
	private JButton btnDelete;
	private CommonStuff commonStuff;
	private Transaction transaction;
	private static JButton btnYes;
	private JButton btnNo;
	private BusinessControl businessControl;
	private String deleteSkill;
	private int skillID;
	private String dulicateSkill;
	private JComboBox<String> comboBoxSearch = null;
	private String skillName;
	private SkillSelector skillSelector;
	private ArrayList<Skill> skills;
	private MyProfileRatedSkillTableModel myModel;
	private Font fontLabel;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontTextBox;
	private Font fontComboBox;

	public Admin(CommonStuff inCommonStuff)
	{
		// skillName = ("PetraTest");
		// skillID = 51517;

		setLayout(null);
		
		commonStuff = inCommonStuff;
		
		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();

//		fontButton = (new Font("Arial", Font.BOLD, 18));
//		fontTextArea = (new Font("Arial", Font.PLAIN, 14));
//		fontComboBoxSearch = (new Font("Arial", Font.PLAIN, 14));

		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		skillSelector.setBounds(100, 32, 620, 500);
		add(skillSelector);

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

		// populateSkillsList();
	}

	private void populateSkillsList()
	{
		// ArrayList<Skill> skillsList = new ArrayList<Skill> ();
		//
		transaction = new Transaction("getSkillList", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);

		commonStuff.setSkillsList((ArrayList<Skill>) transaction.getObject());
		//
		// for(int pos = 0; pos < skillsList.size() -1 ; pos++)
		// {
		// comboBoxSearch.addItem(skillsList.get(pos).getSkillName());
		// }
	}
	

	private void refreshSkills()
	{
		transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<Skill> skillsList = (ArrayList<Skill>) transaction.getObject();

		skills.clear();
		for (Skill skill : skillsList)
		{
			skills.add(skill);
		}
		myModel.fireTableDataChanged();
		
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
				System.out.println("Delete SkillID: " + removeSkill.getSkillID());
				transaction = commonStuff.getClient().sendTransaction(transaction); // sent
																					// transaction
				successful = (boolean) transaction.getObject();
				
				if(successful)
				{
					JOptionPane.showMessageDialog(null, "Skill has been successfully deleted");
					populateSkillsList();
					skillSelector.getSkillModel().fireTableDataChanged();
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
					
					populateSkillsList();
					skillSelector.getSkillModel().fireTableDataChanged();
					refreshSkills();
				}

			}
		}
	}
}
