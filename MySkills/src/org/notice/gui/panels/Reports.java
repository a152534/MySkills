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

import org.notice.beans.CommonStuff;
import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.tablemodel.*;


public class Reports extends JPanel implements ActionListener
{
	private JButton btnSkills;
	private JButton btnUsers;
	private JScrollPane scrollPaneSkillsUsers;
	private JTable tableSkillsUsers;
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
		
		tableSkillsUsers = new JTable();
		scrollPaneSkillsUsers.setViewportView(tableSkillsUsers);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    Object source = e.getSource();
	    String skilName = null;
	    int skillId = 0;
	    
	    if(source == btnSkills)

	        {
		
		transaction = new Transaction("getSkillsReport", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		skillReport = (ArrayList<EndorsementsPerSkill>) transaction.getObject();

		ReportsSkillsReportTableModel myModel = new ReportsSkillsReportTableModel(skillReport);

		tableSkillsReport = new JTable(myModel);
		
		tableSkillsReport.setCellSelectionEnabled(true);
	
		
		myModel.fireTableDataChanged();

		scrollPaneSkillsUsers = new JScrollPane(tableSkillsReport);
		scrollPaneSkillsUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setBounds(140, 120, 620, 450);
		add(scrollPaneSkillsUsers);
	        
	    
	    if(source == btnUsers)

	        {
		//skillid = skillReport.skillName
		transaction = new Transaction("getUserEndorsementsPerSkill", skillId);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		userSkillReport = (ArrayList<UserSkillEndorsements>) transaction.getObject();

		ReportsUserReportTableModel myModelUser = new ReportsUserReportTableModel(userSkillReport);

		tableSkillsReport = new JTable(myModelUser);
		//tableSkills.setColumnSelectionAllowed(true);
		tableSkillsReport.setCellSelectionEnabled(true);
	
		
		myModelUser.fireTableDataChanged();

		scrollPaneSkillsUsers = new JScrollPane(tableSkillsReport);
		scrollPaneSkillsUsers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkillsUsers.setBounds(140, 120, 620, 450);
		add(scrollPaneSkillsUsers);
	        }
		}
	    
	}

}
