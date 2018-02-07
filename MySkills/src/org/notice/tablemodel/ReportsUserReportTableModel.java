package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.notice.beans.UserSkillEndorsements;

public class ReportsUserReportTableModel extends AbstractTableModel
{
    private boolean DEBUG = false;

    ArrayList<UserSkillEndorsements> userSkillReport;
    private String[] columnNames =
    { "Skills", "Number of endorsements", "Average endorsement", "Number of Resources" };

    public ReportsUserReportTableModel(ArrayList<UserSkillEndorsements> userSkillReport)
    {
	super();
	this.userSkillReport = userSkillReport;
	//System.out.println("MyProfileRatedSkillTableModel :  rows in rated skills " + userSkillReport.size());
    }

    @Override
    public int getRowCount()
    {
	// TODO Auto-generated method stub
	// return ratedSkills.size();
	return userSkillReport.size();
    }

    @Override
    public int getColumnCount()
    {
	// TODO Auto-generated method stub
	return 4;
    }

    public String getColumnName(int col)
    {
	return columnNames[col];
    }

    public boolean isCellEditable(int row, int col)
    {

	return false;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
	// System.out.println("MyProfileRatedSkillTableModel getValueAt ");
	UserSkillEndorsements skillsReport = userSkillReport.get(rowIndex);
	if (columnIndex == 0)
	{
	    return skillsReport.getSkillName();
	}

	if (columnIndex == 1)
	{
	    return skillsReport.getNumOfEndorsements();
	}

	if (columnIndex == 2)
	{
	    return skillsReport.getAvgEndorsement();
	}

	return "1";
    }

}