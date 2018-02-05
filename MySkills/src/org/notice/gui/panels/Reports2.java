package org.notice.gui.panels;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.notice.beans.User;
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
	private User user = null;
	private Transaction transaction = null;
	private CommonStuff commonStuff;
	private JTable tableSkillsReport = null , tableUserSkillsReport = null;
	private int skillId = 0 ,  rowId = 0;   
	private String skillName , userName , userId;
	private ReportsSkillsReportTableModel myModel = null;
	private Skill skillobj = null;
	private JButton btnExportSkillsToExcel;
	private File file;
	private JButton btnExportUserToExcel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnUserDetail;
	private JFileChooser fileChooser = null;
	private Font fontLabel;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontTextBox;
	private Font fontComboBox;

    /**
     * Create the panel.
     */
	
    public Reports2(CommonStuff inCommonStuff)
    {
    	commonStuff = inCommonStuff;
    
		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();
    	
    	btnSkills = new JButton("Skills");
    	btnSkills.setFont(fontButton);
    	btnSkills.setBounds(128, 11, 250, 25);
    	
    	btnUsersForSelected = new JButton("Users for selected skills");
    	btnUsersForSelected.setBounds(529, 11, 250, 25);
    	btnUsersForSelected.setFont(fontButton);
    	btnUsersForSelected.setEnabled(false);
    	    	
    	scrollPane = new JScrollPane();
    	scrollPane.setBounds(129, 69, 650, 550);
    	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	btnExportSkillsToExcel = new JButton("Export to Excel");
    	btnExportSkillsToExcel.setBounds(128, 40, 250, 25);
    	btnExportSkillsToExcel.setFont(fontButton);
    	btnExportSkillsToExcel.addActionListener(this);
    	btnExportSkillsToExcel.setEnabled(false);
    	
    	
    	btnExportUserToExcel = new JButton("Export to Excel");
    	btnExportUserToExcel.setBounds(529, 40, 250, 25);
    	btnExportUserToExcel.setFont(fontButton);
    	btnExportUserToExcel.addActionListener(this);
    	btnExportUserToExcel.setEnabled(false);
    	btnSkills.addActionListener(this);
    	btnUsersForSelected.addActionListener(this);
    	
    	btnUserDetail = new JButton("User Details");
    	btnUserDetail.setBounds(350, 650, 250, 25);
    	btnUserDetail.setFont(fontButton);
    	btnUserDetail.addActionListener(this);
    	btnUserDetail.setEnabled(false);
    	btnUserDetail.addActionListener(this);
    	
    
    	transaction = new Transaction("getSkillsReport", null);
	transaction = commonStuff.getClient().sendTransaction(transaction);
	skillReport = (ArrayList<EndorsementsPerSkill>) transaction.getObject();
	myModel = new ReportsSkillsReportTableModel(skillReport);

	tableSkillsReport = new JTable();
	tableSkillsReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tableSkillsReport.getSelectionModel().addListSelectionListener(this);
    	setLayout(null);
    	scrollPane.setViewportView(tableSkillsReport);
    	add(scrollPane);
    	add(btnSkills);
    	add(btnExportSkillsToExcel);
    	add(btnUsersForSelected);
    	add(btnExportUserToExcel);
    	add(btnUserDetail);

    }
    
    public void setColumnWidths() {
	tableSkillsReport.removeColumn(tableSkillsReport.getColumnModel().getColumn(1));
	
    }

    private void exportSkillToExcel() {
   	 
    	fileChooser = new JFileChooser("C:\\Users");
		fileChooser.showSaveDialog(this);
	
 
  

	//	file = new File("C:\\Users\\a044339\\java\\javaprojects\\javaio\\Skills.csv");
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try
		{
			int returnVal =	0;
			 
			if (returnVal != fileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();  
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
			}
				
			} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		finally
		{
		 
		//	pw.close();
			 
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
			int returnVal =	0;
			 
			if (returnVal != fileChooser.APPROVE_OPTION) {
				fw = new FileWriter(file);
				pw = new PrintWriter(fw);
				
				String msg = null;
				msg = "Name, Skill, Endorsement Average, Number of Endorsements";
				pw.println(msg);
				
				for (int count = 0; count < userSkillReport.size(); count++)
				{
					 
					msg =   userSkillReport.get(count).getSurname() + " " +
							userSkillReport.get(count).getFirstName() + ","
							+ userSkillReport.get(count).getSkillName() + "," 
							+ userSkillReport.get(count).getAvgEndorsement() + "," 
							+ userSkillReport.get(count).getNumOfEndorsements(); 
							
					pw.println(msg);
				}
				
				pw.close();
				fw.close();
				btnExportSkillsToExcel.setEnabled(false);
				JOptionPane.showMessageDialog(null, "File has been successfully exported");
			}		
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			//pw.close();
			 
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
		btnUserDetail.setEnabled(false);
		btnUserDetail.setVisible(false);
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
		btnUserDetail.setVisible(true);
		btnUserDetail.setEnabled(true);

		
	        }
		if(source == btnExportSkillsToExcel)
        	{
        		exportSkillToExcel();
        	}
        	if(source == btnExportUserToExcel) 
        	{
        		exportUserToExcel();  
        	}
        	if(source == btnUserDetail) 
        	{
        	    userId = userSkillReport.get(rowId).getUserId();
        	    userName = userSkillReport.get(rowId).getFirstName();
        	    System.out.println("skillreprot length " +userSkillReport.size() );
        	    System.out.println("userdetail " + userId + "rowid " + rowId + "name " + userName);
        	//    user.setFirstName(userName);
        	//    user.setUserID(userId);
        	    user  = new User(userId, null	,null,null,null,null);
        	    commonStuff.setColleague(user);
        	    commonStuff.getTabbedPane().setSelectedIndex(2);
        	     	    
        	}
		
	
    }
}
