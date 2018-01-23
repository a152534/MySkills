/**
 * 
 */
package org.notice.tablemodel;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.notice.beans.RatedSkills;

/**
 * @author philip
 *
 */
@SuppressWarnings("serial")
public class MyProfileRatedSkillTableModel extends AbstractTableModel {
	private boolean DEBUG = false;
	
	ArrayList<RatedSkills> ratedSkills;
	private String[] columnNames = { "Skill", "My rating", "Number of endorsements", "Average endorsement" };

	public MyProfileRatedSkillTableModel(ArrayList<RatedSkills> ratedSkills) {
		super();
		this.ratedSkills = ratedSkills;
		System.out.println("MyProfileRatedSkillTableModel :  rows in rated skills " + ratedSkills.size());
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// return ratedSkills.size();
		return ratedSkills.size();
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
		if (col == 1) {
			return true ;
		} else {
			return false;
		}
	}
	public void setValueAt(Object value, int row, int col) {
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
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// System.out.println("MyProfileRatedSkillTableModel getValueAt ");
		RatedSkills ratedSkill = ratedSkills.get(rowIndex);
		if (columnIndex == 0) {
			return ratedSkill.getSkillName();
		}

		if (columnIndex == 1) {
			return ratedSkill.getLevel();
		}

		if (columnIndex == 2) {
			return ratedSkill.getNumEndorsement();
		}
		if (columnIndex == 3) {
			return ratedSkill.getNumEndorsement();
		}

		return "1";
	}

}
