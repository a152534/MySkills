package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import org.notice.beans.Skill;
import org.notice.beans.User;
import org.notice.tablemodel.SkillsUtilTableModel;
import org.notice.tablemodel.UserSelectorModel;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTable;
import java.awt.FlowLayout;

public class UserSelector extends JPanel implements ListSelectionListener {
	private JTextField txtSearch;
	private JTable tableUser;
	private User selectedUser;
	private TableRowSorter<UserSelectorModel> sorter;
	private UserSelectorModel userModel;
	private ArrayList<User> users;

	/**
	 * Create the panel.
	 */
	public UserSelector(ArrayList<User> users) {

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtSearch = new JTextField();
		txtSearch.setText("Search");
		add(txtSearch);

		txtSearch.setColumns(10);

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}

		});

		userModel = new UserSelectorModel(users);
		tableUser = new JTable(userModel);
		tableUser.getSelectionModel().addListSelectionListener(this);

		sorter = new TableRowSorter<UserSelectorModel>(userModel);

		tableUser.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(tableUser);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		selectedUser = new User("-1", null, null, null, null, null);

	}

	private void newFilter() {

		RowFilter<? super UserSelectorModel, ? super Integer> rf = null;

		rf = RowFilter.regexFilter("(?i)" + txtSearch.getText());

		sorter.setRowFilter(rf);
		//selectedUser = new User("-1", null, null, null, null, null);
		

	}

	public User getSelectedUser() {
		return selectedUser;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("Selected User " + tableUser.getSelectedRow() );
		if (tableUser.getSelectedRow() > -1) {
			//int row = tableUser.convertRowIndexToModel(tableUser.getSelectedRow());
			int row = tableUser.getSelectedRow();
			String userId = (String) tableUser.getValueAt(row, 3);
			selectedUser = new User(userId, null, null, null, null, null);
		} else {
			selectedUser = new User("-1", null, null, null, null, null);
		}

	}

	public JTable getUserTable() {
		return tableUser;
	}

	public void setUserTable(JTable tableUser) {
		this.tableUser = tableUser;
	}

	public UserSelectorModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserSelectorModel userModel) {
		this.userModel = userModel;
	}

}
