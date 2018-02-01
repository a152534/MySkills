package org.notice.gui.panels;

import javax.swing.JPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
	private String skillName;
	private ReportsSkillsReportTableModel myModel = null;
	private Skill skillobj = null;
	private JButton btnExportSkillsToExcel;
	private File file;
	private JButton btnExportUserToExcel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JFileChooser fileChooser = null;


    /**
     * Create the panel.
     */
    public Reports2(CommonStuff inCommonStuff)
    {
    	commonStuff = inCommonStuff;
    
    	
    	btnSkills = new JButton("Skills");
    	
    	btnUsersForSelected = new JButton("Users for selected skills");
    	
    	btnUsersForSelected.setEnabled(false);
    	
    	

    	
    	
    	scrollPane = new JScrollPane();
    	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	btnExportSkillsToExcel = new JButton("Export to Excel");
    	btnExportSkillsToExcel.addActionListener(this);
    	btnExportSkillsToExcel.setEnabled(false);
    	
    	btnExportUserToExcel = new JButton("Export to Excel");
    	btnExportUserToExcel.addActionListener(this);
    	btnExportUserToExcel.setEnabled(false);
 
    	
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(129)
    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(146)
    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(btnSkills)
    							.addComponent(btnExportSkillsToExcel))
    						.addPreferredGap(ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(btnUsersForSelected)
    							.addComponent(btnExportUserToExcel))
    						.addGap(166)))
    				.addContainerGap())
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addContainerGap()
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(btnSkills)
    					.addComponent(btnUsersForSelected))
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(btnExportSkillsToExcel)
    					.addComponent(btnExportUserToExcel))
    				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
    				.addGap(68))
    	);
    	btnSkills.addActionListener(this);
    	btnUsersForSelected.addActionListener(this);
    	
    
    	transaction = new Transaction("getSkillsReport", null);
	transaction = commonStuff.getClient().sendTransaction(transaction);
	skillReport = (ArrayList<EndorsementsPerSkill>) transaction.getObject();
	myModel = new ReportsSkillsReportTableModel(skillReport);

	tableSkillsReport = new JTable();
	tableSkillsReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tableSkillsReport.getSelectionModel().addListSelectionListener(this);
    	scrollPane.setViewportView(tableSkillsReport);
    	setLayout(groupLayout);

    }
    
    public void setColumnWidths() {
	tableSkillsReport.removeColumn(tableSkillsReport.getColumnModel().getColumn(1));
	
    }

    private void exportSkillToExcel() {
   	 
    	fileChooser = new JFileChooser("C:\\Users");
		fileChooser.showSaveDialog(this);
		File file = fileChooser.getSelectedFile();
	//	file = new File("C:\\Users\\a044339\\java\\javaprojects\\javaio\\Skills.csv");
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try
		{
			fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			String msg = null;
			msg = "Skills, Number Of Endorsements, Avgerage Endorsement, Number Of Resources";
			pw.println(msg);
			
			for (int count = 0; count < skillReport.size(); count++)
			{
				msg =   skillReport.get(count).getSkillName() + "," 
						+ skillReport.get(count).getNumEndorsements() + "," 
						+ skillReport.get(count).getAvgEndorsement() + ","
						+ skillReport.get(count).getNumResources(); 
						
				pw.println(msg);
			}
			
			
			pw.close();
			fw.close();
			btnExportUserToExcel.setEnabled(false);
			JOptionPane.showMessageDialog(null, "File has been successfully exported");
			
			 
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			pw.close();
			 
		}
		
	}
    
    private void exportUserToExcel() {
    	fileChooser = new JFileChooser("C:\\Users");
		fileChooser.showSaveDialog(this);
		File file = fileChooser.getSelectedFile();

//		file = new File("C:\\Users\\a044339\\java\\javaprojects\\javaio\\Users.csv");
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try
		{
			fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			
			String msg = null;
			msg = "Name, Skill, Endorsement Average, Number of Endorsements";
			pw.println(msg);
			
			for (int count = 0; count < userSkillReport.size(); count++)
			{
				 
				msg =   userSkillReport.get(count).getSurname() + "," 
						+ userSkillReport.get(count).getSkillName() + "," 
						+ userSkillReport.get(count).getAvgEndorsement() + "," 
						+ userSkillReport.get(count).getNumOfEndorsements(); 
						
				pw.println(msg);
			}
			
			pw.close();
			fw.close();
			btnExportSkillsToExcel.setEnabled(false);
			JOptionPane.showMessageDialog(null, "File has been successfully exported");
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			pw.close();
			 
		}
		
	}


    @Override
    public void valueChanged(ListSelectionEvent e)
    {
	btnUsersForSelected.setEnabled(true);
	 rowId =tableSkillsReport.getSelectedRow();
	 
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	Object source = e.getSource();
	
	    
	    
	    if(source == btnSkills)

	        {
		
		btnUsersForSelected.setEnabled(false);
		tableSkillsReport.setModel(myModel);
		setColumnWidths();
		myModel.fireTableDataChanged();
		btnExportSkillsToExcel.setEnabled(true);
		btnExportUserToExcel.setEnabled(false);



	        }
	    
	    if(source == btnUsersForSelected)

	        {
		
		
		skillId = Integer.parseInt(myModel.getValueAt(rowId, 1).toString());
		skillName = tableSkillsReport.getValueAt(rowId, 0).toString();
		
		skillList = new ArrayList<Skill>();
		skillList.add(new Skill(skillId,skillName));
		
		transaction = new Transaction("getUserEndorsementsPerSkill", skillList);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		userSkillReport = (ArrayList<UserSkillEndorsements>) transaction.getObject();
		
		SkillsRatedTableModel myModelUser = new SkillsRatedTableModel(userSkillReport);

		
		
		tableSkillsReport.setModel(myModelUser);		
		myModelUser.fireTableDataChanged();
		btnExportUserToExcel.setEnabled(true);
		btnExportSkillsToExcel.setEnabled(false);


		
	        }
		if(source == btnExportSkillsToExcel)
    	{
    		exportSkillToExcel();
    	}
    	if(source == btnExportUserToExcel) 
    	{
    		exportUserToExcel();  
    	}

		
	
    }
}
