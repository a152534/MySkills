package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.EndorsementNomination;
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;

public class EndorseNominationModel extends AbstractTableModel {
	ArrayList<EndorsementNomination> nominations;
	private String[] columnNames = { "User", "User ID" };

	public EndorseNominationModel(ArrayList<EndorsementNomination> inNominations) {
		this.nominations = inNominations;
		if (this.nominations == null) {
			System.out.println("No nominations");
		} else {
			System.out.println("Skills list size " + this.nominations.size());
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return nominations.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		EndorsementNomination nomination = nominations.get(rowIndex);
		if (columnIndex == 0) {
			return nomination.getUserName();
		}
		if (columnIndex == 1) {
			return nomination.getUserID();
		}

		return null;
	}

}
