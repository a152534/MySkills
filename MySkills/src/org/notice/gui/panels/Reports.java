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

import org.notice.beans.CommonStuff;
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
	private Transaction transaction = null;
	private CommonStuff commonStuff;
	private JTable tableSkillsReport;
	
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
		
	
		scrollPaneSkillsUsers.setViewportView(tableSkillsReport);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    Object source = e.getSource();
	    String skillName = null;
	    int skillId = 0;
	    
	    if(source == btnSkills)

	        {
		
		transaction = new Transaction("getSkillsReport", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		skillReport = (ArrayList<EndorsementsPerSkill>) transaction.getObject();

		ReportsSkillsReportTableModel myModel = new ReportsSkillsReportTableModel(skillReport);

		tableSkillsReport = new JTable(myModel);
		tableSkillsReport.getSelectionModel().addListSelectionListener(this);
		tableSkillsReport.setCellSelectionEnabled(true);
	
		
		myModel.fireTableDataChanged();

		scrollPaneSkillsUsers = new JScrollPane(tableSkillsReport);
		scrollPaneSkillsUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setBounds(140, 120, 620, 450);
		add(scrollPaneSkillsUsers);
	        }
	    
	    if(source == btnUsers)

	        {
		skillId = 4321;
		int colId =tableSkillsReport.getSelectedColumn();
		skillName = tableSkillsReport.getValueAt(1, colId).toString();
		
		transaction = new Transaction("getUserEndorsementsPerSkill", skillId);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		userSkillReport = (ArrayList<UserSkillEndorsements>) transaction.getObject();

		SkillsRatedTableModel myModelUser = new SkillsRatedTableModel(userSkillReport);

		tableSkillsReport = new JTable(myModelUser);
		tableSkillsReport.setColumnSelectionAllowed(true);
		tableSkillsReport.setCellSelectionEnabled(true);
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
	    tableSkillsReport.getCellSelectionEnabled();
	    
	    
	}

}
