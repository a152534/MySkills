package org.notice.gui.panels;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.*;


public class Reports extends JPanel implements ActionListener, ListSelectionListener
{
	private JButton btnSkills;
	private JButton btnUsers;
	private JScrollPane scrollPaneSkillsUsers;
	private Font fontButton;
	private  ArrayList<EndorsementsPerSkill> skillReport = null;
	private  ArrayList<UserSkillEndorsements> userSkillReport = null;
	private ArrayList<Skill> skillList = null;
	private Transaction transaction = null;
	private CommonStuff commonStuff;
	private JTable tableSkillsReport;
	private int skillId = 0 , rowId =0;
	
	public Reports(CommonStuff inCommonStuff)
	{
	    	commonStuff = inCommonStuff;
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		
		btnSkills = new JButton("Skills");
		btnSkills.setFont(fontButton);
		btnSkills.setBounds(220, 50, 100, 25);
		add(btnSkills);
		btnSkills.addActionListener(this);
		
		btnUsers = new JButton("Users");
		btnUsers.setFont(fontButton);
		btnUsers.setBounds(535, 50, 100, 25);
		add(btnUsers);
		btnUsers.addActionListener(this);
		
		scrollPaneSkillsUsers = new JScrollPane();
		scrollPaneSkillsUsers.setBounds(140, 150, 620, 320);
		add(scrollPaneSkillsUsers);
		
		tableSkillsReport = new JTable();
		scrollPaneSkillsUsers.setViewportView(tableSkillsReport);
		
		
		
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e)
	{
	    Object source = e.getSource();
	   
	    
	    
	    if(source == btnSkills)

	        {
		
		transaction = new Transaction("getSkillsReport", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		skillReport = (ArrayList<EndorsementsPerSkill>) transaction.getObject();

		ReportsSkillsReportTableModel myModel = new ReportsSkillsReportTableModel(skillReport);

		tableSkillsReport = new JTable(myModel);
		tableSkillsReport.setRowSelectionAllowed(true);
		tableSkillsReport.getSelectionModel().addListSelectionListener(this);
		
		
		
		myModel.fireTableDataChanged();

		scrollPaneSkillsUsers = new JScrollPane(tableSkillsReport);
		scrollPaneSkillsUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setBounds(140, 120, 620, 450);
		add(scrollPaneSkillsUsers);
	        }
	    
	    if(source == btnUsers)

	        {
		tableSkillsReport.setRowSelectionAllowed(true);
		tableSkillsReport.getSelectionModel().addListSelectionListener(this);
		rowId =tableSkillsReport.getSelectedRow();
		System.out.println("rowId " + rowId);
		skillId = Integer.parseInt(tableSkillsReport.getValueAt(rowId, 1).toString());
		transaction = new Transaction("getUserEndorsementsPerSkill", skillId);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		userSkillReport = (ArrayList<UserSkillEndorsements>) transaction.getObject();

		SkillsRatedTableModel myModelUser = new SkillsRatedTableModel(userSkillReport);

		tableSkillsReport = new JTable(myModelUser);
		tableSkillsReport.setColumnSelectionAllowed(true);
		tableSkillsReport.setCellSelectionEnabled(true);
		tableSkillsReport.setRowSelectionAllowed(true);
		tableSkillsReport.getSelectionModel().addListSelectionListener(this);
		
	
		
		myModelUser.fireTableDataChanged();

		scrollPaneSkillsUsers = new JScrollPane(tableSkillsReport);
		scrollPaneSkillsUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setBounds(140, 120, 620, 450);
		add(scrollPaneSkillsUsers);
	        }
		
	    
	}


	@Override
	public void valueChanged(ListSelectionEvent e)
	{
	    System.out.println("selected row");
	    
	    
	}

}
