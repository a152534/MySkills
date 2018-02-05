package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.enums.Skill_Levels;
import org.notice.gui.panels.Colleague.colleagueListiner;
import org.notice.tablemodel.ColleagueProfileSkillTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Colleague2 extends JPanel implements ActionListener, ListSelectionListener
{
	private JTextField lookupField = null, displaySelected = null;
	private JComboBox<String> colleagueSearchBox;
	private JComboBox<Skill_Levels> endorseBox = null;
	private JButton lookupButton = null, selectButton = null, requestButton = null;//saveButton = null ;
	private JScrollPane scrollPaneColleagueSkills = null;
	private JTable tableColleagueSkills = null;
	
	private CommonStuff commonStuff = null;
	private Transaction transaction = null;
	private ColleagueProfileSkillTableModel colleagueModel =  null;
	
	private ArrayList<User> users = null;
	private ArrayList<RatedSkills> ratedSkills= null;
	
	
	private static final Skill_Levels Notice = null, Advanced_Beginner = null, Competent = null, Proficient = null,
			Expert = null;
	
	private boolean endorsementRequested, endorsementAdded;
	private int selectedLevel, colleagueSkillId;
	private Font fontLabel, fontButton, fontTextArea, fontTextBox, fontComboBox;

	 

	/**
	 * Create the panel.
	 */
	public Colleague2(CommonStuff inCommonStuff)
	{
		commonStuff = inCommonStuff;
		
		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();
		
		setLayout(null);

		
		lookupField = new JTextField();
		lookupField.setFont(fontTextBox);
		lookupField.setBounds(242, 25, 302, 22);
//		System.out.println(commonStuff.getColleague());
//		System.out.println(commonStuff.getColleague().getAliasName());
		lookupField.setVisible(true);
		lookupField.setEnabled(true);
		add(lookupField);
		
		
		colleagueSearchBox = new JComboBox();
		colleagueSearchBox.setFont(fontTextBox);
		colleagueSearchBox.setBounds(242, 50, 302, 22);
		colleagueSearchBox.setVisible(false);
		colleagueSearchBox.setEnabled(false);
		add(colleagueSearchBox);
		
		displaySelected = new JTextField();
		displaySelected.setFont(fontTextBox);
		displaySelected.setEditable(false);
		displaySelected.setBounds(242, 75, 302, 22);
		displaySelected.setVisible(false);
		displaySelected.setEnabled(false);
		add(displaySelected);
		
		
		lookupButton = new JButton("Lookup");
		lookupButton.setFont(fontButton);
		lookupButton.setBounds(560, 25, 120, 25);
		lookupButton.setVisible(true);
		lookupButton.setEnabled(true);
		lookupButton.addActionListener(this);
		add(lookupButton);
		
		selectButton = new JButton("Select");
		selectButton.setFont(fontButton);
		selectButton.setBounds(560, 50, 120, 25);
		selectButton.setVisible(false);
		selectButton.setEnabled(false);
		selectButton.addActionListener(this);
		add(selectButton);
		
		requestButton = new JButton("Request Endorsement");
		requestButton.setFont(fontButton);
		requestButton.setBounds(560, 500, 240, 25);
		requestButton.setVisible(false);
		requestButton.setEnabled(false);
		requestButton.addActionListener(this);
		add(requestButton);
		
//		saveButton = new JButton("Save");
//		saveButton.setFont(fontButton);
//		saveButton.setBounds(556, 500, 120, 25);
//		saveButton.setVisible(false);
//		saveButton.setEnabled(false);
//		saveButton.addActionListener(this);
//		add(saveButton);
		
		if(commonStuff.getColleague() != null &&commonStuff.getColleague().getAliasName() != null)
		{
			this.populateColleagueWhoRequestedEndorsement();
		}
		ratedSkills = new ArrayList<RatedSkills>();
		colleagueModel = new ColleagueProfileSkillTableModel(ratedSkills);
		tableColleagueSkills = new JTable(colleagueModel);
		tableColleagueSkills.getSelectionModel().addListSelectionListener(this);
		scrollPaneColleagueSkills = new JScrollPane(tableColleagueSkills);
		scrollPaneColleagueSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneColleagueSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneColleagueSkills.setBounds(150, 150, 620, 320);
		add(scrollPaneColleagueSkills);

	}
	
		
	public String setupColleagueSelection(ArrayList<User> users)
	{
		String displayName = null;
		for(int pos = 0; pos < users.size(); pos++)
		{
			displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": " + users.get(pos).getUserID());
			colleagueSearchBox.addItem(displayName);
			colleagueSearchBox.setVisible(true);
			colleagueSearchBox.setEnabled(true);
			selectButton.setVisible(true);
			selectButton.setEnabled(true);
			lookupField.setVisible(false);
			lookupButton.setVisible(false);

		}
		return displayName;
	}
	
	public void setUpLevelColumn(JTable table, TableColumn levelColumn)
	{	
		endorseBox = new JComboBox<Skill_Levels>(Skill_Levels.values());
	
		levelColumn.setCellEditor(new DefaultCellEditor(endorseBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to set endorsement level");
		levelColumn.setCellRenderer(renderer);
	}
	
//	public void populateColleagueSkillsTable(String searchID)
//	{
//		transaction = new Transaction("getUserSkills", searchID);
//		transaction = commonStuff.getClient().sendTransaction(transaction);
//		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
//		
//		colleagueModel = new ColleagueProfileSkillTableModel(ratedSkills);
//		tableColleagueSkills.setModel(colleagueModel);
//		colleagueModel.fireTableDataChanged();
//		
//		
//		tableColleagueSkills.getSelectionModel().addListSelectionListener(this);
//
//		tableColleagueSkills.getModel().addTableModelListener(new TableModelListener()
//		{
//			@Override
//			public void tableChanged(TableModelEvent te)
//			{
//				int row = te.getFirstRow();
//				saveButton.setVisible(true);
//				saveButton.setEnabled(true);
//				System.out.println("Colleague table changed event at row  " + row);
//
//			}
//		});
//		
//		tableColleagueSkills.setCellSelectionEnabled(true);
//		tableColleagueSkills.setShowHorizontalLines(true);
//		tableColleagueSkills.setShowVerticalLines(true);
//		
//		setUpLevelColumn(tableColleagueSkills, tableColleagueSkills.getColumnModel().getColumn(2));
//		colleagueModel.fireTableDataChanged();
//		
//		
//		add(scrollPaneColleagueSkills);
//		requestButton.setVisible(true);
//		requestButton.setEnabled(true);
//	}
	
	private void refreshSkills()
	{

		transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<RatedSkills> refreshedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		ratedSkills.clear();
		for (RatedSkills skill : refreshedSkills)
		{
			ratedSkills.add(skill);
			System.out.println("in refresh loop");

		}
		setUpLevelColumn(tableColleagueSkills, tableColleagueSkills.getColumnModel().getColumn(3));
		colleagueModel.fireTableDataChanged();
		requestButton.setVisible(true);
		requestButton.setEnabled(true);
		
		tableColleagueSkills.getSelectionModel().addListSelectionListener(this);

		tableColleagueSkills.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged(TableModelEvent te)
			{
				int row = te.getFirstRow();
				
				System.out.println("Colleague table changed event at row  " + row);
				addEndorsement();
				
			}
		});
		tableColleagueSkills.setCellSelectionEnabled(true);
		
	}

	
	public boolean createEndorsementRequest()
	{
		EndorsementNomination endorseNom;
		endorseNom = new EndorsementNomination(commonStuff.getLoggedOnUser().getUserID(), commonStuff.getColleague().getUserID());
		transaction = new Transaction("createEndorsementNomination", endorseNom);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		endorsementRequested = (boolean) transaction.getObject();
		return endorsementRequested;
	}
	
	public int getColleagueSkillId()
	{
		transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		
		for(int pos = 0; pos < ratedSkills.size(); pos++)
		{
			if((commonStuff.getColleague().getUserID()).equals(ratedSkills.get(pos).getUserId()))
			{
				colleagueSkillId =  ratedSkills.get(pos).getUserSkillId();
			}
		}
		return colleagueSkillId;
	}
	
	public Endorsement saveEndorsement()
	{	
		Skill_Levels level;
		Endorsement endorsement;				
		level = (Skill_Levels)endorseBox.getSelectedItem();
		selectedLevel = level.ordinal() + 1;
		//Add + 1 to ensure correct value pulls through from combo box, to factor out position 0
		System.out.println("Selected level is---->>>> " + selectedLevel);
		endorsement = new Endorsement(this.getColleagueSkillId(), commonStuff.getLoggedOnUser().getUserID(), selectedLevel);
		System.out.println(endorsement.toString());
		return endorsement;
	}
	
	public boolean addEndorsement()
	{
		transaction = new Transaction("createEndorsement", this.saveEndorsement());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		endorsementAdded = (boolean)transaction.getObject();
		return endorsementAdded;
	}
	
	public void populateColleagueWhoRequestedEndorsement()
	{
		ArrayList<User> user = new ArrayList<User>();
		user.add(new User(commonStuff.getColleague().getUserID(), commonStuff.getColleague().getFirstName(), commonStuff.getColleague().getSurName(),
				commonStuff.getColleague().getAliasName(), commonStuff.getColleague().getEmail(), commonStuff.getColleague().getPhoneNumber()));
		this.setupColleagueSelection(user);
	}
	
	private User fetchuser(User selectedUser) {
		Transaction transaction = new Transaction("getUser", selectedUser.getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);

		if (transaction.getObject() == null) {
			JOptionPane.showConfirmDialog(this, "Failed to find user in database ");
		} else {
			ArrayList<User> users = (ArrayList<User>) transaction.getObject();
			selectedUser = users.get(0);
			commonStuff.setColleague(selectedUser);
		}
		return selectedUser;
	}
	
	public void reload()
	{
			if(commonStuff.getColleague() != null && commonStuff.getColleague().getUserID() != "-1")
		{
			commonStuff.setColleague(fetchuser(commonStuff.getColleague()));
			System.out.println("reload  after if  - after fetch ");
			System.out.println("Colleague is: " + commonStuff.getColleague());
			this.populateColleagueWhoRequestedEndorsement();
//			refreshSkills();
		}
	}
			
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		String searchName = null, searchID = null;
		
		if(source == lookupButton)
		{
		    requestButton.setVisible(false);
			requestButton.setEnabled(false);
			if((lookupField.getText()).equals(""))
			{
				JOptionPane.showMessageDialog(this, "Invalid Selection. Please type a search Name, Surname or User ID");
			}
			else
			{
				transaction = new Transaction("getUserList", lookupField.getText());
				transaction = commonStuff.getClient().sendTransaction(transaction);
				users = (ArrayList<User>) transaction.getObject();
				if(!users.isEmpty())
				{
					setupColleagueSelection(users);
					displaySelected.setVisible(false);																
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No Data For Selection");
				}
			}
		}
		
		
		if(source == selectButton)
		{
			searchName = (String)colleagueSearchBox.getSelectedItem();
			
		    searchID = users.get(colleagueSearchBox.getSelectedIndex()).getUserID();
		    System.out.println("select Button  searchID = " + searchID);
			if(searchID != null)
			{

				if(searchID.equals(commonStuff.getLoggedOnUser().getUserID()))
				{
					JOptionPane.showMessageDialog(this, "May not select yourself as a colleague.");
					System.out.println("ERROR - Cannot rate yourself as colleague.");
					lookupField.setVisible(true);
					lookupField.setEnabled(true);
					lookupField.setText(null);
					lookupButton.setVisible(true);
					lookupButton.setEnabled(true);
					selectButton.setVisible(false);
					selectButton.setEnabled(false);
					colleagueSearchBox.setVisible(false);
					colleagueSearchBox.setEnabled(false);
					colleagueSearchBox.removeAllItems();
				}
				else
				{
					ArrayList<User> user = new ArrayList<User>();
					
					transaction = new Transaction("getUser", searchID);
					transaction = commonStuff.getClient().sendTransaction(transaction);
					user = (ArrayList<User>) transaction.getObject();
					commonStuff.setColleague(user.get(0));
					//this.populateColleagueSkillsTable(searchID);
					refreshSkills();
										
					for(int pos = 0; pos < user.size(); pos++)
					{
						commonStuff.setColleague(new User(searchID, user.get(pos).getFirstName(), user.get(pos).getSurName(), user.get(pos).getAliasName(),
								user.get(pos).getEmail(), user.get(pos).getPhoneNumber()));
					}
					
					displaySelected.setText(searchName);
					displaySelected.setVisible(true);
					displaySelected.setEnabled(true);
					lookupField.setVisible(true);
					lookupField.setEnabled(true);
					lookupField.setText(null);
					lookupButton.setVisible(true);
					lookupButton.setEnabled(true);
					selectButton.setVisible(false);
					selectButton.setEnabled(false);
					colleagueSearchBox.setVisible(false);
					colleagueSearchBox.setEnabled(false);
					colleagueSearchBox.removeAllItems();
					}
				}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid Selection");	
			}
		}
		
		if(source == requestButton)
		{
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to request an endorsement");
			
			if(option == JOptionPane.OK_OPTION)
			{
				
				if(createEndorsementRequest() == false)
				{
					JOptionPane.showMessageDialog(this, "An error has occurred - Your request for endorsement has not been submitted.");
					System.out.println("Error - endorsement request not saved.");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Your request for endorsement has been submitted.");
				}
			}
			requestButton.setVisible(false);
			requestButton.setEnabled(false);
			return;
		}
		
//		if(source == saveButton)
//		{
//			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to save the endorsements you have made?");
//			if(option == JOptionPane.OK_OPTION)
//			{
//				
//				if(addEndorsement() == false)
//				{
//					JOptionPane.showMessageDialog(this, "An error has occurred - Your endorsement has not been added to your colleague's profile");
//					System.out.println("Error - endorsement not added.");
//				}
//				else
//				{
//					JOptionPane.showMessageDialog(this, "Your colleague's profile has been updated with your endorsement.");
//					saveButton.setVisible(false);
//					saveButton.setEnabled(false);
//				}
//			}
//			return;
//		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
//	    saveButton.setVisible(true);
//	    saveButton.setEnabled(true);
		
	}	
		
}
	







