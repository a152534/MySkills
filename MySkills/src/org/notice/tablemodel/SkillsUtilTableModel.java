package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;

public class SkillsUtilTableModel extends AbstractTableModel {
	private ArrayList<Skill> skills ;
	private String[] columnNames = { "Skill", "Skill ID" };
	
	public SkillsUtilTableModel(ArrayList<Skill> inSkills) {
		this.skills = inSkills ; 
		System.out.println("Skills list size " + this.skills.size());
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return skills.size();
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
		Skill skill = skills.get(rowIndex);
		if (columnIndex == 0) {
			return skill.getSkillName();
		}
		if (columnIndex == 1) {
			return skill.getSkillID();
		}

		return null;
	}

	public ArrayList<Skill> getSkills()
	{
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills)
	{
		this.skills = skills;
	}

}
