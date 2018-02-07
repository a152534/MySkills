package org.notice.gui.panels;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.enums.Skill_Levels;
//import org.notice.gui.panels.Colleague.colleagueListiner;
import org.notice.tablemodel.ColleagueProfileSkillTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Colleague2 extends JPanel implements ActionListener, ListSelectionListener {
	private JTextField lookupField = null, displaySelected = null;
	private JComboBox<String> colleagueSearchBox;
	private JComboBox<Skill_Levels> endorseBox = null;
	private JButton lookupButton = null, selectButton = null, requestButton = null;// saveButton = null ;
	private JScrollPane scrollPaneColleagueSkills = null;
	private JTable tableColleagueSkills = null;

	private CommonStuff commonStuff = null;
	private Transaction transaction = null;
	private ColleagueProfileSkillTableModel colleagueModel = null;

	private ArrayList<User> users = null;
	private ArrayList<ColleagueRatings> ratedSkills = null;

	private static final Skill_Levels Notice = null, Advanced_Beginner = null, Competent = null, Proficient = null,
			Expert = null;

	private boolean endorsementRequested, endorsementAdded;
	private int selectedLevel, colleagueSkillId, skillId;
	private Font fontLabel, fontButton, fontTextArea, fontTextBox, fontComboBox;
	private SkillTableListener skillTableListener;
	private UserSelector userSelector;
	private JButton btnSearchColl;
	
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAlias;
	private JTextField txtPhone;
	private JLabel lblEmailAddress;
	private JTextField txtEmail;
	
	
	

	/**
	 * Create the panel.
	 */
	public Colleague2(CommonStuff inCommonStuff) {
		commonStuff = inCommonStuff;

		fontLabel = commonStuff.getFontLabel();
		fontButton = commonStuff.getFontButton();
		fontTextArea = commonStuff.getFontTextArea();
		fontTextBox = commonStuff.getFontTextBox();
		fontComboBox = commonStuff.getFontComboBox();

		setLayout(null);

		
		btnSearchColl = new JButton("Search Colleague");
		btnSearchColl.setBounds(20, 20, 180, 20);
		btnSearchColl.addActionListener(this);
		add(btnSearchColl);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(fontLabel);
		lblName.setBounds(20, 50, 130, 20);
		add(lblName);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(fontLabel);
		lblSurname.setBounds(20, 80, 130, 20);
		add(lblSurname);

		JLabel lblAlias = new JLabel("Alias:");
		lblAlias.setFont(fontLabel);
		lblAlias.setBounds(20, 110, 130, 20);
		add(lblAlias);

		txtName = new JTextField();
		txtName.setFont(fontTextBox);
		txtName.setBounds(120, 50, 290, 20);
		add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setFont(fontTextBox);
		txtSurname.setBounds(120, 80, 290, 19);
		add(txtSurname);
		txtSurname.setColumns(10);

		txtAlias = new JTextField();
		txtAlias.setFont(fontTextBox);
		txtAlias.setBounds(120, 110, 290, 19);
		add(txtAlias);
		txtAlias.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(fontLabel);
		lblPhoneNumber.setBounds(440, 51, 136, 15);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setFont(fontTextBox);
		txtPhone.setColumns(10);
		txtPhone.setBounds(580, 51, 290, 19);
		add(txtPhone);

		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(fontLabel);
		lblEmailAddress.setBounds(440, 81, 131, 15);
		add(lblEmailAddress);

		txtEmail = new JTextField();
		txtEmail.setFont(fontTextBox);
		txtEmail.setColumns(10);
		txtEmail.setBounds(580, 81, 290, 19);
		add(txtEmail);
		

		requestButton = new JButton("Request Endorsement");
		requestButton.setFont(fontButton);
		requestButton.setBounds(560, 500, 240, 25);
		requestButton.setVisible(false);
		requestButton.setEnabled(false);
		requestButton.addActionListener(this);
		add(requestButton);

		if (commonStuff.getColleague() != null && commonStuff.getColleague().getAliasName() != null) {
			selectUser();
		}
		ratedSkills = new ArrayList<ColleagueRatings>();
		colleagueModel = new ColleagueProfileSkillTableModel(ratedSkills);
		tableColleagueSkills = new JTable(colleagueModel);
		tableColleagueSkills.getSelectionModel().addListSelectionListener(this);
		skillTableListener = new SkillTableListener();
		tableColleagueSkills.getModel().addTableModelListener(skillTableListener);
	

		scrollPaneColleagueSkills = new JScrollPane(tableColleagueSkills);
		scrollPaneColleagueSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneColleagueSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scrollPaneColleagueSkills.setBounds(150, 150, 620, 320);
		scrollPaneColleagueSkills.setBounds(140, 150, 620, 250);
		
		add(scrollPaneColleagueSkills);

	}

	public String setupColleagueSelection(ArrayList<User> users) {
		String displayName = null;
		for (int pos = 0; pos < users.size(); pos++) {
			displayName = (users.get(pos).getSurName() + ", " + users.get(pos).getFirstName() + ": "
					+ users.get(pos).getUserID());
			colleagueSearchBox.addItem(displayName);
			colleagueSearchBox.setVisible(true);
			colleagueSearchBox.setEnabled(true);
			selectButton.setVisible(true);
			selectButton.setEnabled(true);
			lookupField.setVisible(false);
			lookupButton.setVisible(false);
			displaySelected.setText("");

		}
		return displayName;
	}

	public void setUpLevelColumn(JTable table, TableColumn levelColumn) {
		endorseBox = new JComboBox<Skill_Levels>(Skill_Levels.values());

		levelColumn.setCellEditor(new DefaultCellEditor(endorseBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click to set endorsement level");
		levelColumn.setCellRenderer(renderer);
	}

	private void refreshSkills() {
		users = new ArrayList<User>();
		
		users.add(commonStuff.getLoggedOnUser()); // endorsor
		users.add(commonStuff.getColleague()); // endorse

		// transaction = new Transaction("getColleagueProfile", users);
		// transaction = commonStuff.getClient().sendTransaction(transaction);
		//

		// lleagueSkills = (ArrayList<ColleagueRatings>) transaction.getObject();

		tableColleagueSkills.getModel().removeTableModelListener(skillTableListener);
		//System.out.println("Refresh " + commonStuff.getColleague().getUserID());
		transaction = new Transaction("getColleagueProfile", users);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<ColleagueRatings> refreshedSkills = (ArrayList<ColleagueRatings>) transaction.getObject();
		ratedSkills.clear();
		for (ColleagueRatings skill : refreshedSkills) {
			ratedSkills.add(skill);
		//	System.out.println("in refresh loop");

		}
		setUpLevelColumn(tableColleagueSkills, tableColleagueSkills.getColumnModel().getColumn(5));
		colleagueModel.fireTableDataChanged();
		requestButton.setVisible(true);
		requestButton.setEnabled(true);

		colleagueModel.fireTableDataChanged();
		tableColleagueSkills.getModel().addTableModelListener(skillTableListener);

	}

	private void populateUserInfo() {
		txtAlias.setText(commonStuff.getColleague().getAliasName());
		txtName.setText(commonStuff.getColleague().getFirstName());
		txtSurname.setText(commonStuff.getColleague().getSurName());
		txtPhone.setText(commonStuff.getColleague().getPhoneNumber());
		txtEmail.setText(commonStuff.getColleague().getEmail());

	}
	public boolean createEndorsementRequest() {
		EndorsementNomination endorseNom;
		endorseNom = new EndorsementNomination(commonStuff.getLoggedOnUser().getUserID(),
				commonStuff.getColleague().getUserID());
		transaction = new Transaction("createEndorsementNomination", endorseNom);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		endorsementRequested = (boolean) transaction.getObject();
		return endorsementRequested;
	}

	public int getColleagueSkillId(int selectedSkill) {
		transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<ColleagueRatings>) transaction.getObject();

		for (int pos = 0; pos < ratedSkills.size(); pos++) {
			if ((ratedSkills.get(pos).getSkillId()) == selectedSkill) {
				colleagueSkillId = ratedSkills.get(pos).getUserSkillId();
			}
			// System.out.println("Skill ID: " + ratedSkills.get(pos).getSkillId() + "
			// UserSkillID: "
			// + ratedSkills.get(pos).getUserSkillId() + " userID: " +
			// ratedSkills.get(pos).getUserId());
		}
		return colleagueSkillId;

	}

	public boolean addEndorsement(int userSkillId, int level) {
		Endorsement endorsement = new Endorsement(userSkillId, commonStuff.getLoggedOnUser().getUserID(), level);
		transaction = new Transaction("createEndorsement", endorsement);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		endorsementAdded = (boolean) transaction.getObject();
		return endorsementAdded;

	}

//	public void populateColleagueWhoRequestedEndorsement() {
//		ArrayList<User> user = new ArrayList<User>();
//		user.add(new User(commonStuff.getColleague().getUserID(), commonStuff.getColleague().getFirstName(),
//				commonStuff.getColleague().getSurName(), commonStuff.getColleague().getAliasName(),
//				commonStuff.getColleague().getEmail(), commonStuff.getColleague().getPhoneNumber()));
//		this.setupColleagueSelection(user);
//	}

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

	public void reload() {
		if (commonStuff.getColleague() != null && commonStuff.getColleague().getUserID() != "-1") {
			commonStuff.setColleague(fetchuser(commonStuff.getColleague()));
		//	System.out.println("reload  after if  - after fetch ");
		//	System.out.println("Colleague is: " + commonStuff.getColleague());
		//	this.populateColleagueWhoRequestedEndorsement();
			refreshSkills();
			populateUserInfo();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		String searchName = null, searchID = null;
		
		if( source == btnSearchColl) {
			selectUser();
		}

		if (source == requestButton) {
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to request an endorsement");

			if (option == JOptionPane.OK_OPTION) {

				if (createEndorsementRequest() == false) {
					JOptionPane.showMessageDialog(this,
							"An error has occurred - Your request for endorsement has not been submitted.");
					System.out.println("Error - endorsement request not saved.");
				} else {
					JOptionPane.showMessageDialog(this, "Your request for endorsement has been submitted.");
				}
			}
			requestButton.setVisible(false);
			requestButton.setEnabled(false);
			return;
		}

	}

	
	
	private void selectUser() {

		transaction = new Transaction("getUserList", "A");
		transaction = commonStuff.getClient().sendTransaction(transaction);
		users = (ArrayList<User>) transaction.getObject();
		userSelector = new UserSelector(users);

		int result = JOptionPane.showConfirmDialog(null, userSelector, "Select a user ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {

			return;
		}
		if (result == JOptionPane.OK_OPTION) {

		}

		User selectedUser = userSelector.getSelectedUser();
		if (selectedUser.getUserID() == "-1") {
			JOptionPane.showMessageDialog(this, "No User has been selected");
			return;

		} else {
			selectedUser = fetchuser(selectedUser);
		}
		commonStuff.setColleague(selectedUser);
		//System.out.println("colleague     "  + commonStuff.getColleague().getAliasName().toString()  + commonStuff.getColleague().getUserID()) ; 
		refreshSkills();
		populateUserInfo();
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	class SkillTableListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = tableColleagueSkills.getSelectedRow();
			//System.out.println("selected row : " + tableColleagueSkills.getSelectedRow());
			skillId = (int) tableColleagueSkills.getValueAt(row, 1);
			Skill_Levels level;// Added by Tony
			level = (Skill_Levels) endorseBox.getSelectedItem();// Added by tony
			int selectedLevelInt = level.ordinal() + 1;// Added by tony

			int userSkillId = ratedSkills.get(row).getUserSkillId();

			if (addEndorsement(userSkillId, selectedLevelInt)) {
				
			}else {
				System.out.println("The insert in the database did not work ");
			}
			;
			//System.out.println("Got this far--->>> skill id = " + skillId + " level " + level + "" + selectedLevelInt);

			//System.out.println("table changed event at row  " + row);
			refreshSkills();

		}
	}
}
