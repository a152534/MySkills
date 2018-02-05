package org.notice.gui.panels;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.buscontrol.BusinessControl;
import org.notice.client.Transaction;
import org.notice.enums.Skill_Levels;

import org.notice.tablemodel.ColleagueProfileSkillTableModel;




public class Colleague extends JPanel implements ActionListener, ListSelectionListener
{
	private JComboBox<String> comboBoxColleagueSearch = null, comboBox = null;
	private JScrollPane scrollPaneColleagueSkills = null;
	private JTable tableColleagueSkills = null;
	private JButton btnRequestEndorsement = null, btnSave = null, btnSearch = null, btnLookup = null;
	private Font fontButton = null, fontComboBox = null;
	private JTextField textSearch = null, textDisplaySelectedName = null;
	
	
	private CommonStuff commonStuff = null;
	private Transaction transaction = null;
	private ArrayList<User> users = null;
	private ArrayList<RatedSkills> ratedSkills= null;
	private ColleagueProfileSkillTableModel colleagueModel =  null;
	private static final Skill_Levels Notice = null, Advanced_Beginner = null, Competent = null, Proficient = null,
			Expert = null;
	private JComboBox<Skill_Levels> endorseBox = null; 
	private int loggedOnUserSkillId, selectedLevel;
	private boolean endorsementRequestResult = false, endorsementAdded = false,
			endorsementAddedResult = false;
	private EndorsementNomination endorseNom;
	private Boolean  endorsementRequested = false;

	
	
	
	public Colleague(CommonStuff inCommonStuff)
	{
		commonStuff = inCommonStuff;
		setLayout(null);
		
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("Arial", Font.PLAIN, 18));
		textSearch.setBounds(242, 25, 302, 22);
		add(textSearch);
		textSearch.setColumns(10);
		textSearch.addActionListener(this);
		
		
		
		
		btnLookup = new JButton("Lookup");
		btnLookup.setFont(fontButton);
		btnLookup.setBounds(552, 24, 108, 25);
		btnLookup.addActionListener(this);
		add(btnLookup);		
		
		setLayout(null);
		comboBoxColleagueSearch = new JComboBox<String>();
		comboBoxColleagueSearch.setFont(fontComboBox);
		comboBoxColleagueSearch.setBounds(242, 50, 302, 25);
		comboBoxColleagueSearch.setVisible(false);
		add(comboBoxColleagueSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(fontButton);
		btnSearch.setBounds(552, 50, 108, 25);
		btnSearch.setVisible(false);
		btnSearch.addActionListener(this);
		add(btnSearch);
		
		textDisplaySelectedName = new JTextField();
		textDisplaySelectedName.setColumns(10);
		textDisplaySelectedName.setBounds(242, 78, 302, 22);
		add(textDisplaySelectedName);
		textDisplaySelectedName.setEnabled(false);
		textDisplaySelectedName.setVisible(false);
		
		btnRequestEndorsement = new JButton("Request Endorsement");
		btnRequestEndorsement.setEnabled(false);
		btnRequestEndorsement.setFont(fontButton);
		btnRequestEndorsement.setBounds(190, 500, 243, 25);
		add(btnRequestEndorsement);
		btnRequestEndorsement.addActionListener(this);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.setFont(fontButton);
		btnSave.setBounds(620, 500, 90, 25);
		add(btnSave);
		btnSave.addActionListener(this);
		
		
		
		this.populateColleagueWhoRequestedEndorsement(commonStuff.getColleague());
		


	}
	
	class colleagueListiner implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			btnSave.setEnabled(true);
		}
	}

	
	
	
	public void setUpLevelColumn(JTable table, TableColumn levelColumn)
	{	
		endorseBox = new JComboBox<Skill_Levels>(Skill_Levels.values());
	
		levelColumn.setCellEditor(new DefaultCellEditor(endorseBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to set endorsement level");
		levelColumn.setCellRenderer(renderer);
	}
	
//	public void setUpLevelColumn(JTable table, TableColumn levelColumn)
//	{
//
//		JComboBox<String> comboBox = new JComboBox<String>();
//		comboBox.addItem("1");
//		comboBox.addItem("2");
//		comboBox.addItem("3");
//		comboBox.addItem("4");
//		comboBox.addItem("5");
//
//		levelColumn.setCellEditor(new DefaultCellEditor(comboBox));
//
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setToolTipText("Click for combo box");
//		levelColumn.setCellRenderer(renderer);
//	}
	
	
	public void populateColleague(String searchID)
	{
		transaction = new Transaction("getUserSkills", searchID);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		
		colleagueModel = new ColleagueProfileSkillTableModel(ratedSkills);
		tableColleagueSkills = new JTable(colleagueModel);
		
	
		colleagueListiner cListen = new colleagueListiner();
		tableColleagueSkills.getSelectionModel().addListSelectionListener(cListen);

		tableColleagueSkills.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged(TableModelEvent te)
			{
				int row = te.getFirstRow();
				
				System.out.println("Colleague table changed event at row  " + row);
				int endorseLevel = (int) colleagueModel.getValueAt(row, 2);
 

				
			}
		});
	

		
		tableColleagueSkills.setCellSelectionEnabled(true);
		tableColleagueSkills.setShowHorizontalLines(true);
		tableColleagueSkills.setShowVerticalLines(true);
		
		setUpLevelColumn(tableColleagueSkills, tableColleagueSkills.getColumnModel().getColumn(2));
		colleagueModel.fireTableDataChanged();
		
		scrollPaneColleagueSkills = new JScrollPane(tableColleagueSkills);
		scrollPaneColleagueSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneColleagueSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneColleagueSkills.setBounds(150, 150, 620, 320);
		add(scrollPaneColleagueSkills);
		btnRequestEndorsement.setVisible(true);
		btnRequestEndorsement.setEnabled(true);
	}
	
	public String setupColleagueSelection(ArrayList<User> users)
	{
		String displayName = null;
		for(int pos = 0; pos < users.size(); pos++)
		{
			displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": " + users.get(pos).getUserID());
			comboBoxColleagueSearch.addItem(displayName);
			comboBoxColleagueSearch.setVisible(true);
			btnSearch.setVisible(true);
			textSearch.setVisible(false);
			btnLookup.setVisible(false);
			if(tableColleagueSkills != null)
			{
				tableColleagueSkills.clearSelection(); //Tony - Get a null pointer here, not sure why?
			}
		}
		return displayName;
	}
	
	public void populateColleagueWhoRequestedEndorsement(User userIn)
	{
		ArrayList<User> user = new ArrayList<User>();
		this.setupColleagueSelection(user);
	}
	
	public int getLoggedOnUserSkillId()
	{
		transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		
		for(int pos = 0; pos < ratedSkills.size(); pos++)
		{
			if((commonStuff.getLoggedOnUser().getUserID()).equals(ratedSkills.get(pos).getUserId()))
			{
				loggedOnUserSkillId =  ratedSkills.get(pos).getUserSkillId();
			}
		}
		return loggedOnUserSkillId;
	}

	
	public boolean createEndorsementRequest()
	{
		EndorsementNomination endorseNom;
		endorseNom = new EndorsementNomination(commonStuff.getLoggedOnUser().getUserID(), commonStuff.getColleague().getUserID());
		
		transaction = new Transaction("createEndorseNomination", endorseNom);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		
		endorsementRequested = (Boolean)transaction.getObject();
		return endorsementRequested;
	}
	
	public ArrayList<Endorsement> saveEndorsement()
	{
		
		ArrayList<Endorsement> endorsement = new ArrayList<Endorsement>();
		

		
		String temp = (String) comboBox.getSelectedItem();
		System.out.println("Temp is: " + temp);
//		selectedLevel = Integer.parseInt((String)comboBox.getSelectedItem());
//		endorsement.add(new Endorsement(this.getLoggedOnUserSkillId(), commonStuff.getLoggedOnUser().getUserID(), selectedLevel));
		return endorsement;
	}
	
	public boolean addEndorsement()
	{
		transaction = new Transaction("createEndorsement", this.saveEndorsement());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		endorsementAdded = (boolean)transaction.getObject();
		return endorsementAdded;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		String searchName = null, searchID = null;
		
		if(source == btnLookup)
		{
			if((textSearch.getText()) == null)
			{
				JOptionPane.showMessageDialog(this, "Invalid Selection. Please type a search Name, Surname or User ID");
			}
			else
			{
//				String displayName = null;
				transaction = new Transaction("getUserList", textSearch.getText());
				transaction = commonStuff.getClient().sendTransaction(transaction);
				users = (ArrayList<User>) transaction.getObject();
				if(users != null)
				{
					setupColleagueSelection(users);
//					for(int pos = 0; pos < users.size(); pos++)
//					{
//						displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": " + users.get(pos).getUserID());
//						comboBoxColleagueSearch.addItem(displayName);
//						comboBoxColleagueSearch.setVisible(true);
//						btnSearch.setVisible(true);
//						textSearch.setVisible(false);
//						btnLookup.setVisible(false);
//						if(tableColleagueSkills != null)
//						{
//							tableColleagueSkills.clearSelection(); //Tony - Get a null pointer here, not sure why?
//						}
//					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No Data For Selection");
				}
			}
		}
		
		
		
		if(source == btnSearch)
		{
			searchName = (String)comboBoxColleagueSearch.getSelectedItem();
			if(searchName != null)
			{
				int delimeter = searchName.indexOf(':');
				searchID = searchName.substring(delimeter + 2);
				if(searchID.equals(commonStuff.getLoggedOnUser().getUserID()))
				{
					JOptionPane.showMessageDialog(this, "May not select yourself as a colleague.");
					System.out.println("ERROR - Cannot rate yourself as colleague.");
					textSearch.setVisible(true);
					textSearch.setText(null);
					btnLookup.setVisible(true);
					btnSearch.setVisible(false);
					comboBoxColleagueSearch.setVisible(false);
					comboBoxColleagueSearch.removeAllItems();
				}
				else
				{
					ArrayList<User> user = new ArrayList<User>();
					populateColleague(searchID);
					transaction = new Transaction("getUser", searchID);
					transaction = commonStuff.getClient().sendTransaction(transaction);
					user = (ArrayList<User>) transaction.getObject();
					for(int pos = 0; pos < user.size(); pos++)
					{
						commonStuff.setColleague(new User(searchID, user.get(pos).getFirstName(), user.get(pos).getSurName(), user.get(pos).getAliasName(),
								user.get(pos).getEmail(), user.get(pos).getPhoneNumber()));
					}
					textDisplaySelectedName.setText(searchName);
					textDisplaySelectedName.setVisible(true);
					textSearch.setVisible(true);
					textSearch.setText(null);
					btnLookup.setVisible(true);
					btnSearch.setVisible(false);
					comboBoxColleagueSearch.setVisible(false);
					comboBoxColleagueSearch.removeAllItems();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid Selection");	
			}
			


		}
		
		if(source == btnRequestEndorsement)
		{
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to request an endorsement");
			
			if(option == JOptionPane.OK_OPTION)
			{
				endorsementRequestResult = createEndorsementRequest();
				if (endorsementRequestResult == false)
				{
					JOptionPane.showMessageDialog(this, "An error has occurred - Your endorsement request has not been saved");
					System.out.println("Error - endorsement request not saved.");
				}
			}
			return;	
		}
		
		if(source == btnSave)
		{
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to save the endorsements you have made?");
			
			if(option == JOptionPane.OK_OPTION)
			{
				endorsementAddedResult = addEndorsement();
				if (endorsementAddedResult == false)
				{
					JOptionPane.showMessageDialog(this, "An error has occurred - Your endorsement has not been added");
					System.out.println("Error - endorsement not added.");
				}
			}
			return;	
		
		}
		
	}
	
	


	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		// TODO Auto-generated method stub
		
	}




}
