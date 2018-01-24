package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.EndorsementsGroupedBySkill;
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
 

 
@SuppressWarnings("serial")
public class SkillsRatedTableModel extends AbstractTableModel {
	private boolean DEBUG = false;
	
	ArrayList<RatedSkills> EndorsementsGroupedBySkill;
	private String[] columnNames = { "Name", "Number of endorsements", "Endorsement Average" };

	public SkillsRatedTableModel(ArrayList<RatedSkills> ratedSkills) {
		super();
		this.EndorsementsGroupedBySkill = ratedSkills;
		System.out.println("SkillsRatedTableModel :  rows in rated skills " + ratedSkills.size());
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// return ratedSkills.size();
		return EndorsementsGroupedBySkill.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
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
/*	public void setValueAt(Object value, int row, int col) {
       if (DEBUG) {
           System.out.println("Setting value at " + row + "," + col
                              + " to " + value
                              + " (an instance of "
                              + value.getClass() + ")");
       }

       ratedSkills.get(row).setLevel(Integer.parseInt((String)value));
       fireTableCellUpdated(row, col);

       if (DEBUG) {
           System.out.println("New value of data:");
        
       }
   } */

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// System.out.println("MyProfileRatedSkillTableModel getValueAt ");
		RatedSkills ratedSkill = EndorsementsGroupedBySkill.get(rowIndex);
		if (columnIndex == 0) {
			return ratedSkill.getSkillName();
		}

			if (columnIndex == 1) {
			
			return ratedSkill.getNumEndorsement();
		}
		if (columnIndex == 2) {
			return ratedSkill.getAvgEndorsement();
		}

		return "1";
	}

}
