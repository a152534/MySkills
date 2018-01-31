package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.notice.beans.CommonStuff;
import org.notice.beans.EndorsementNomination;
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
import org.notice.beans.User;
import org.notice.beans.UserSkills;
import org.notice.client.Transaction;
import org.notice.tablemodel.EndorseNominationModel;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ColleagueProfile extends JPanel implements ActionListener, ListSelectionListener {
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAlias;
	private JTextField txtPhone;
	private JLabel lblEmailAddress;
	private JTextField txtEmail;
	private JScrollPane scrollPaneSkills;
	private JTable tableSkills;
	private JButton btnSearchColl;
	private JButton btnRequestEndorsement;

	private Font fontLabel;
	private Font fontTextBox;
	private Font fontButton;
	private Font fontComboBox;

	private CommonStuff commonStuff;
	private Transaction transaction;
	private ArrayList<RatedSkills> ratedSkills;
	private MyProfileRatedSkillTableModel ratedSkillModel;

	private ArrayList<User> users;
	private UserSelector userSelector;

	/**
	 * Create the panel.
	 */
	public ColleagueProfile(CommonStuff inCommonStuff) {
		commonStuff = inCommonStuff;
		setLayout(null);

		fontLabel = (new Font("Arial", Font.BOLD, 14));
		fontTextBox = (new Font("Arial", Font.PLAIN, 12));
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));

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
		
		ratedSkills = new ArrayList<RatedSkills>(); 
		ratedSkillModel = new MyProfileRatedSkillTableModel(ratedSkills);
		
		if(commonStuff.getColleague() != null &&commonStuff.getColleague().getAliasName() != null) {
			populateUserInfo();
			populateSkills();
		}

		// setColumnWidths();

	}

	private void populateUserInfo() {
		txtAlias.setText(commonStuff.getColleague().getAliasName());
		txtName.setText(commonStuff.getColleague().getFirstName());
		txtSurname.setText(commonStuff.getColleague().getSurName());
		txtPhone.setText(commonStuff.getColleague().getPhoneNumber());
		txtEmail.setText(commonStuff.getColleague().getEmail());

	}

	private void populateSkills() {

		transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();

		ratedSkillModel = new MyProfileRatedSkillTableModel(ratedSkills);

		tableSkills = new JTable(ratedSkillModel);
		mySkillLestenr ml = new mySkillLestenr();
		tableSkills.getSelectionModel().addListSelectionListener(ml);

		tableSkills.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();

				System.out.println("table changed event at row  " + row);
				int SkillId = (int) ratedSkillModel.getValueAt(row, 4);
				int selectedLevelInt = (int) ratedSkillModel.getValueAt(row, 1);
				UserSkills newSkill = new UserSkills(commonStuff.getLoggedOnUser().getUserID(), SkillId,
						selectedLevelInt);
				Transaction transaction = new Transaction("SaveUserSkill", newSkill);
				transaction = commonStuff.getClient().sendTransaction(transaction);

			}
		});

		tableSkills.setCellSelectionEnabled(true);

		setUpLevelColumn(tableSkills, tableSkills.getColumnModel().getColumn(1));

		ratedSkillModel.fireTableDataChanged();

		scrollPaneSkills = new JScrollPane(tableSkills);
		scrollPaneSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setBounds(140, 150, 620, 250);
		add(scrollPaneSkills);

	}

	private void refreshSkills() {

		transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<RatedSkills> newratedSkills = (ArrayList<RatedSkills>) transaction.getObject();

		ratedSkills.clear();
		for (RatedSkills skill : newratedSkills) {
			ratedSkills.add(skill);

		}
		ratedSkillModel.fireTableDataChanged();

	}

	

	public void setUpLevelColumn(JTable table, TableColumn levelColumn) {

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");

		levelColumn.setCellEditor(new DefaultCellEditor(comboBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		levelColumn.setCellRenderer(renderer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnSearchColl) {
			selectUser();
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
		populateSkills();
		populateUserInfo();
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

	// @Override
	// public void valueChanged(ListSelectionEvent e) {
	//
	// if (e.getSource() == tableSkills) {
	// btnDeleteSkill.setEnabled(true);
	// }
	//
	// }
	//
	class mySkillLestenr implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			System.out.println("");
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
	public void reload() {
		//System.out.println("Attemting reload " + commonStuff.getColleague().getUserID() ) ;
		if(commonStuff.getColleague() != null && commonStuff.getColleague().getUserID() != "-1") {
			commonStuff.setColleague(fetchuser(commonStuff.getColleague()));
			System.out.println("reload  after if  - after fetch ");
			populateUserInfo();
			refreshSkills();
		}
	}

}
