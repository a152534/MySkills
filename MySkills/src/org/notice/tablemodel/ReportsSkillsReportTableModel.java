package org.notice.tablemodel;

import java.math.MathContext;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.*;


public class ReportsSkillsReportTableModel extends AbstractTableModel
{
	private boolean DEBUG = false;
	
	
	ArrayList<EndorsementsPerSkill> skillReport;
	private String[] columnNames = { "Skills", "Skill ID", "Number of endorsements", "Average endorsement", "Number of Resources" };

	public ReportsSkillsReportTableModel(ArrayList<EndorsementsPerSkill> skillReport) 
	{
		super();
		this.skillReport = skillReport;
		//System.out.println("MyProfileRatedSkillTableModel :  rows in rated skills " + skillReport.size());
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// return ratedSkills.size();
		return skillReport.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// System.out.println("MyProfileRatedSkillTableModel getValueAt ");
	    	EndorsementsPerSkill skillsReport = skillReport.get(rowIndex);
		if (columnIndex == 0) {
			return skillsReport.getSkillName();
		}
		
		if (columnIndex == 1) {
			return skillsReport.getSkillId();
		}
		
		if (columnIndex == 2) {
			return skillsReport.getNumEndorsements();
		}

		if (columnIndex == 3) {
			return skillsReport.getAvgEndorsement().stripTrailingZeros();
		}
		if (columnIndex == 4) {
			return skillsReport.getNumResources();
		}

		return "1";
	}

}