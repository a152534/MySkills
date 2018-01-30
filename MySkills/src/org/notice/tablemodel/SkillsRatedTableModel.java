package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.RatedSkills;
import org.notice.beans.UserSkillEndorsements;

 
@SuppressWarnings("serial")
public class SkillsRatedTableModel extends AbstractTableModel {
	private boolean DEBUG = false;
	
	ArrayList<UserSkillEndorsements> userSkillEndorsements;
	private String[] columnNames = { "Name", "Skill", "Endorsement Average" , "Number of endorsements"};

	public SkillsRatedTableModel(ArrayList<UserSkillEndorsements> userSkillEndorsements) {
		super();
		this.userSkillEndorsements = userSkillEndorsements;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// return ratedSkills.size();
		return userSkillEndorsements.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
//		if (col == 1) {
//			return true ;
//		} else {
			return false;
//		}
	}
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        userSkillEndorsements.get(row).setLevel(Integer.parseInt((String)value));
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
         
        }
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		UserSkillEndorsements ratedSkills = userSkillEndorsements.get(rowIndex);
		
		if (columnIndex == 0) {
			return (ratedSkills.getSurname() + ", " + ratedSkills.getFirstName());
			}

			if (columnIndex == 1) {
			return ratedSkills.getSkillName();
		}
		if (columnIndex == 2) {
			return ratedSkills.getAvgEndorsement();
		}
		if (columnIndex == 3) {
			return ratedSkills.getNumOfEndorsements();
		}
		
		return "1";
	}

}
