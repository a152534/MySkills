package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

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
import java.awt.Button;

public class Admin extends JPanel implements ActionListener
{
	private JComboBox comboBox;
	private JTable tableSkills;
	private JComboBox<String> skillsList = null;
	private JScrollPane scrollPaneSkills;
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
	private Button btnSearch;
	
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
		
		btnSearch = new Button("Search");
		btnSearch.setBounds(605, 50, 70, 25);
		btnSearch.setFont(fontButton);
		add(btnSearch);
		
		populateSkillsInfo();
				
	}
	
	private void populateSkillsInfo() 
	{
		//comboBox.getToolTipText(commonStuff.getLoggedOnUser());
		
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
		
		scrollPaneSkills = new JScrollPane(tableSkills);
		scrollPaneSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setBounds(300, 75, 300, 250);
		add(scrollPaneSkills);

	}
	
	public void setUpLevelColumn(JTable table, TableColumn levelColumn) 
	{
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		comboBox.addItem("6");
		comboBox.addItem("7");
		comboBox.addItem("8");
		comboBox.addItem("9");
		comboBox.addItem("10");
		
		levelColumn.setCellEditor(new DefaultCellEditor(comboBox));

		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		levelColumn.setCellRenderer(renderer);
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
