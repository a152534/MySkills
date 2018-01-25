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
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
import org.notice.beans.UserSkills;
import org.notice.client.Transaction;
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

public class MyProfile1 extends JPanel implements ActionListener, ListSelectionListener {
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAlias;
	private JTextField txtPhone;
	private JLabel lblEmailAddress;
	private JTextField txtEmail;
	private JScrollPane scrollPaneSkills;
	private JTable tableSkills;
	private JButton btnSave;
	private JButton btnAddSkill;
	private JButton btnDeleteSkill;
	private JScrollPane scrollPaneEndorsementRequests;
	private JTable tableEndorsementRequests;
	private Font fontLabel;
	private Font fontTextBox;
	private Font fontButton;
	private Font fontComboBox;
	private CommonStuff commonStuff;
	private Transaction transaction;
	private ArrayList<RatedSkills> ratedSkills;
	private MyProfileRatedSkillTableModel myModel;
	private SkillSelector skillSelector;

	/**
	 * Create the panel.
	 */
	public MyProfile1(CommonStuff inCommonStuff) {
		commonStuff = inCommonStuff;
		setLayout(null);

		fontLabel = (new Font("Arial", Font.BOLD, 14));
		fontTextBox = (new Font("Arial", Font.PLAIN, 12));
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(fontLabel);
		lblName.setBounds(20, 20, 130, 20);
		add(lblName);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(fontLabel);
		lblSurname.setBounds(20, 50, 130, 20);
		add(lblSurname);

		JLabel lblAlias = new JLabel("Alias:");
		lblAlias.setFont(fontLabel);
		lblAlias.setBounds(20, 80, 130, 20);
		add(lblAlias);

		txtName = new JTextField();
		txtName.setFont(fontTextBox);
		txtName.setBounds(120, 20, 290, 20);
		add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setFont(fontTextBox);
		txtSurname.setBounds(120, 50, 290, 19);
		add(txtSurname);
		txtSurname.setColumns(10);

		txtAlias = new JTextField();
		txtAlias.setFont(fontTextBox);
		txtAlias.setBounds(120, 80, 290, 19);
		add(txtAlias);
		txtAlias.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(fontLabel);
		lblPhoneNumber.setBounds(440, 21, 136, 15);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setFont(fontTextBox);
		txtPhone.setColumns(10);
		txtPhone.setBounds(580, 21, 290, 19);
		add(txtPhone);

		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(fontLabel);
		lblEmailAddress.setBounds(440, 51, 131, 15);
		add(lblEmailAddress);

		txtEmail = new JTextField();
		txtEmail.setFont(fontTextBox);
		txtEmail.setColumns(10);
		txtEmail.setBounds(580, 51, 290, 19);
		add(txtEmail);

		// tableSkills = new JTable();
		// tableSkills.setBounds(0, 0, 1, 1);
		// scrollPaneSkills.add(tableSkills);

		btnSave = new JButton("Save");
		btnSave.setFont(fontButton);
		btnSave.setBounds(780, 80, 90, 25);
		add(btnSave);

		btnAddSkill = new JButton("Add Skill");
		btnAddSkill.addActionListener(this);
		btnAddSkill.setFont(fontButton);
		btnAddSkill.setBounds(220, 392, 145, 25);
		add(btnAddSkill);

		btnDeleteSkill = new JButton("Delete Skill");
		btnDeleteSkill.addActionListener(this);
		btnDeleteSkill.setEnabled(false);
		btnDeleteSkill.setFont(fontButton);
		btnDeleteSkill.setBounds(535, 392, 145, 25);
		add(btnDeleteSkill);

		scrollPaneEndorsementRequests = new JScrollPane();
		scrollPaneEndorsementRequests.setBounds(140, 440, 620, 132);
		add(scrollPaneEndorsementRequests);

		tableEndorsementRequests = new JTable();
		scrollPaneEndorsementRequests.setColumnHeaderView(tableEndorsementRequests);

		populateUserInfo();
		populateSkills();
	}

	private void populateUserInfo() {
		txtAlias.setText(commonStuff.getLoggedOnUser().getAliasName());
		txtName.setText(commonStuff.getLoggedOnUser().getFirstName());
		txtSurname.setText(commonStuff.getLoggedOnUser().getSurName());
		txtPhone.setText(commonStuff.getLoggedOnUser().getPhoneNumber());
		txtEmail.setText(commonStuff.getLoggedOnUser().getEmail());

	}

	private void populateSkills() {

		transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();

		myModel = new MyProfileRatedSkillTableModel(ratedSkills);

		tableSkills = new JTable(myModel);
		tableSkills.getSelectionModel().addListSelectionListener(this);

		// tableSkills.setColumnSelectionAllowed(true);
		tableSkills.setCellSelectionEnabled(true);

		// tableSkills.setBounds(0, 0, 1, 1);
		// scrollPaneSkills.add(tableSkills);
		setUpLevelColumn(tableSkills, tableSkills.getColumnModel().getColumn(1));

		// hide skillid and userSkillID column
		TableColumnModel tcm = tableSkills.getColumnModel();
		// tcm.removeColumn(tcm.getColumn(5));

		// tcm.removeColumn(tcm.getColumn(4));

		myModel.fireTableDataChanged();

		scrollPaneSkills = new JScrollPane(tableSkills);
		scrollPaneSkills.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneSkills.setBounds(140, 120, 620, 250);
		add(scrollPaneSkills);

	}

	private void refreshSkills() {

		transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<RatedSkills> newratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		// for(int i = 0 ; i < ratedSkills.size(); i++) {
		// ratedSkills.remove(i);
		//
		// }
		ratedSkills.clear();
		for (RatedSkills skill : newratedSkills) {
			ratedSkills.add(skill);

		}
		myModel.fireTableDataChanged();
		System.out.println("skills refreshed");

	}

	private void addUserSkill() {

		Vector<String> allskills = new Vector<String>();
		for (Skill skill : commonStuff.getSkillsList()) {
			allskills.add(skill.getSkillName() + "    ID_" + skill.getSkillID());
		}
		Collections.sort(allskills);
		JComboBox<String> comboSkill = new JComboBox<String>(allskills);

		skillSelector = new SkillSelector(commonStuff.getSkillsList());

		int result =JOptionPane.showConfirmDialog(null, skillSelector, "Select a skill ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("MyProfile Selected Cancel ");
			return ;
		} 
		if (result == JOptionPane.OK_OPTION) {
			System.out.println("MyProfile Selected Ok ");
		} 
		
		Skill SelectedSkill = skillSelector.getSelectedSkill();



		JComboBox<String> comboLevel = new JComboBox<String>();
		comboLevel.addItem("1");
		comboLevel.addItem("2");
		comboLevel.addItem("3");
		comboLevel.addItem("4");
		comboLevel.addItem("5");

		result = JOptionPane.showConfirmDialog(this, comboLevel, "Select Level for Skill: " + SelectedSkill.getSkillName(),
				JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("MyProfile Selected Cancel ");
			return ;
		} 
		if (result == JOptionPane.OK_OPTION) {
			System.out.println("MyProfile Selected Ok ");
		} 
		
		
		System.out.println("result = " + result + "\n" );
		String selectedLevel = (String) comboLevel.getSelectedItem();
		int selectedLevelInt = Integer.parseInt(selectedLevel);
	
		
		UserSkills newSkill = new UserSkills(commonStuff.getLoggedOnUser().getUserID(), SelectedSkill.getSkillID(), selectedLevelInt);
		Transaction transaction = new Transaction("SaveUserSkill", newSkill);
		transaction = commonStuff.getClient().sendTransaction(transaction);
		if ((Boolean) transaction.getObject()) {

		}

		refreshSkills();

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
		if (source == btnAddSkill) {
			addUserSkill();
		}
		if (source == btnDeleteSkill) {
			deleteUserSkill();
		}

	}

	private void deleteUserSkill() {
		int rowId = tableSkills.getSelectedRow();
		if (rowId < 0) {
			rowId = 0;
		}
		int userSkillId = (int) tableSkills.getValueAt(rowId, 5);
		String selectedSkill = (String) tableSkills.getValueAt(rowId, 0);

		if (JOptionPane.showConfirmDialog(this, "Delete " + selectedSkill + "? ", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			transaction = new Transaction("deleteUserSkill", userSkillId);
			transaction = commonStuff.getClient().sendTransaction(transaction);

			refreshSkills();

		} else {
			return;
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		btnDeleteSkill.setEnabled(true);

	}
}
