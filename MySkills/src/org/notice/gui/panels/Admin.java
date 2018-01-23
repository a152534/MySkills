package org.notice.gui.panels;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.notice.beans.*;
import org.notice.buscontrol.*;
import org.notice.client.Transaction;
import org.notice.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;

public class Admin extends JPanel implements ActionListener
{
	private JComboBox comboBox;
	private JTable tableSkills;
	private JComboBox<String> skillsList = null;
	private JButton btnAdd;
	private JButton btnDelete;
	private Font fontComboBox;
	private Font fontButton;
	private CommonStuff commonStuff; 
	private Transaction transaction;
	private static JButton btnYes;
	private JButton btnNo;
	private BusinessControl businessControl;
	private String deleteSkill;
	private int skillID;
	private String dulicateSkill;
	
	public Admin(CommonStuff inCommonStuff)  
	{
		commonStuff = inCommonStuff ; 
		
		setLayout(null);
		
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		fontButton = (new Font("Arial", Font.BOLD, 18));		
		
		comboBox = new JComboBox();
		comboBox.setBounds(300, 50, 300, 25);
		comboBox.setFont(fontComboBox);
		add(comboBox);
		
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
		comboBox.setToolTipText(commonStuff.getLoggedOnUser().getSurName());
		
		ArrayList<Skill> skillsList = new ArrayList<Skill>();
		businessControl = new BusinessControl();
		
		transaction = new Transaction("getSkillList", null);
 		transaction = commonStuff.getClient().sendTransaction(transaction);
		
 		skillsList = (ArrayList<Skill>)transaction.getObject();
 		
		for(int pos = 0; pos < skillsList.size() -1 ; pos++)
		{
			System.out.println("In for loop");
		}
 		
		populateSkillsInfo();

	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == btnDelete) 
		{
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete skill?");
			
			if(dialogResult == JOptionPane.YES_NO_OPTION)
			{
				transaction = new Transaction(deleteSkill, skillID);
			}
			
			int dialogResult2 = JOptionPane.showConfirmDialog(null, "Skill is already in use, so cannot be deleted?");
			
			//if(dialogResult2 == dulicateSkill)
			{
				
			}
		}
		
		if(source ==btnAdd)
		{
			populateSkillsInfo();
		}
	}
}
