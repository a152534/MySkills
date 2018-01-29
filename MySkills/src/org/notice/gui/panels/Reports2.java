package org.notice.gui.panels;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.notice.beans.CommonStuff;
import org.notice.beans.EndorsementsPerSkill;
import org.notice.beans.Skill;
import org.notice.beans.UserSkillEndorsements;
import org.notice.client.Transaction;
import org.notice.tablemodel.ReportsSkillsReportTableModel;
import org.notice.tablemodel.SkillsRatedTableModel;

public class Reports2 extends JPanel implements ActionListener, ListSelectionListener
{
	private JButton btnSkills;
	private JButton btnUsersForSelected;
	private JScrollPane scrollPane;
	private  ArrayList<EndorsementsPerSkill> skillReport = null;
	private  ArrayList<UserSkillEndorsements> userSkillReport = null;
	private ArrayList<Skill> skillList = null;
	private Transaction transaction = null;
	private CommonStuff commonStuff;
	private JTable tableSkillsReport = null , tableUserSkillsReport = null;
	private int skillId = 0 ,  rowId = 0;   

    /**
     * Create the panel.
     */
    public Reports2(CommonStuff inCommonStuff)
    {
    	commonStuff = inCommonStuff;
    
    	
    	btnSkills = new JButton("Skills");
    	
    	btnUsersForSelected = new JButton("Users for selected skills");
    	
    	scrollPane = new JScrollPane();
    	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(148)
    						.addComponent(btnSkills)
    						.addGap(168)
    						.addComponent(btnUsersForSelected))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(129)
    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 497, GroupLayout.PREFERRED_SIZE)))
    				.addContainerGap(131, Short.MAX_VALUE))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(73)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(btnSkills)
    					.addComponent(btnUsersForSelected))
    				.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
    				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
    				.addGap(68))
    	);
    	btnSkills.addActionListener(this);
    	btnUsersForSelected.addActionListener(this);
    	
    	tableSkillsReport = new JTable();
    	scrollPane.setViewportView(tableSkillsReport);
    	setLayout(groupLayout);

    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
	 System.out.println("selected row listselection");
	 rowId =tableSkillsReport.getSelectedRow();
	 
    }

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
		tableSkillsReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSkillsReport.getSelectionModel().addListSelectionListener(this);
			
		myModel.fireTableDataChanged();

		scrollPane = new JScrollPane(tableSkillsReport);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(140, 120, 620, 450);
		add(scrollPane);
	        }
	    
	    if(source == btnUsersForSelected)

	        {
		//tableSkillsReport.clearSelection();
		//rowId =tableSkillsReport.getSelectedRow();
		System.out.println("rowId " + rowId);
		skillId = Integer.parseInt(tableSkillsReport.getValueAt(rowId, 1).toString());
		System.out.println("SKILL ID " + skillId);
		skillList = new ArrayList<Skill>(skillId);
		transaction = new Transaction("getUserEndorsementsPerSkill", skillList);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		userSkillReport = (ArrayList<UserSkillEndorsements>) transaction.getObject();

		SkillsRatedTableModel myModelUser = new SkillsRatedTableModel(userSkillReport);

		tableUserSkillsReport = new JTable(myModelUser);
				
		myModelUser.fireTableDataChanged();

		scrollPane = new JScrollPane(tableUserSkillsReport);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(140, 120, 620, 450);
		add(scrollPane);
	        }
		
	
    }
}
