package org.notice.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.notice.beans.User;

@SuppressWarnings("serial")
public class UserSelectorModel extends AbstractTableModel {

	private ArrayList<User> users;
	private String[] columnNames = { "First Name", "Surname", "Alias", "userID" };

	public UserSelectorModel(ArrayList<User> inUsers) {
		this.users = inUsers;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = users.get(rowIndex);
		if (columnIndex == 0) {
			return user.getFirstName();
		}
		if (columnIndex == 1) {
			return user.getSurName();
		}
		if (columnIndex == 2) {
			return user.getAliasName();
		}
		if (columnIndex == 3) {
			return user.getUserID();
		}
		return null;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
