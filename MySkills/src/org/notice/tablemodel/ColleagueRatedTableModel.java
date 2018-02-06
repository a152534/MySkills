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

import org.notice.beans.ColleagueRatings;
import org.notice.beans.RatedSkills;
import org.notice.enums.Skill_Levels;

/**
 * @author philip
 *
 */
@SuppressWarnings("serial")
public class ColleagueRatedTableModel extends AbstractTableModel {
	private boolean DEBUG = false;

	ArrayList<ColleagueRatings> ratedSkills;
	private String[] columnNames = { "Skill", "Own rating", "Number of endorsements", "Average endorsement",
			"My Rating ", "Skill ID", "UserSkillID" };

	public ColleagueRatedTableModel(ArrayList<ColleagueRatings> ratedSkills) {
		super();
		this.ratedSkills = ratedSkills;
		// System.out.println("MyProfileRatedSkillTableModel : rows in rated skills " +
		// ratedSkills.size());
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
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col == 4) {
			return true;
		} else {
			return false;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		Skill_Levels level = (Skill_Levels) value;

		ratedSkills.get(row).setLevel((int) level.ordinal() + 1);
		// ratedSkills.get(row).setLevel(Integer.parseInt((String)value));

		fireTableCellUpdated(row, col);

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		ColleagueRatings ratedSkill = ratedSkills.get(rowIndex);
		if (columnIndex == 0) {
			return ratedSkill.getSkillName();
		}

		if (columnIndex == 1) {
			return ratedSkill.getLevel();
		}

		if (columnIndex == 2) {
			return ratedSkill.getNumOfEndorsements();
		}
		if (columnIndex == 3) {
			return ratedSkill.getAvgEndorsement();
		}
		if (columnIndex == 4) {
			return ratedSkill.getMyRating();
		}

		if (columnIndex == 5) {
			return ratedSkill.getSkillId();
		}
		if (columnIndex == 6) {
			return ratedSkill.getUserSkillId();
		}
		return "1";
	}

}
