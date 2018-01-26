package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.RatedSkills;
import org.notice.beans.UserSkillEndorsements;

 
@SuppressWarnings("serial")
public class SkillsRatedTableModel extends AbstractTableModel {
	private boolean DEBUG = false;
	
	ArrayList<UserSkillEndorsements> userSkillEndorsements;
	private String[] columnNames = { "Name", "Skill", "Number of endorsements", "Endorsement Average" };

	public SkillsRatedTableModel(ArrayList<UserSkillEndorsements> userSkillEndorsements) {
		super();
		this.userSkillEndorsements = userSkillEndorsements;
		System.out.println("SkillsRatedTableModel :  rows in rated skills " + userSkillEndorsements.size());
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
		System.out.println("In setvalueat");
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
		// System.out.println("MyProfileRatedSkillTableModel getValueAt ");
		
			UserSkillEndorsements ratedSkills = userSkillEndorsements.get(rowIndex);
			System.out.println("GET VALUE AT " + ratedSkills );
		if (columnIndex == 0) {
			return (ratedSkills.getSurname() + ", " + ratedSkills.getFirstName());
			}

			if (columnIndex == 1) {
			System.out.print("SKILL NAME***************" + ratedSkills.getSkillName());
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
