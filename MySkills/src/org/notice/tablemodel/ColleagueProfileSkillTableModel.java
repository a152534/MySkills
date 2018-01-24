package org.notice.tablemodel;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.*;

import org.notice.beans.*;
import org.notice.client.Transaction;
import org.notice.enums.Skill_Levels;

/**
 * @author tony
 * as copied from philip's original
 */
@SuppressWarnings("serial")
public class ColleagueProfileSkillTableModel extends AbstractTableModel
{
	private static final Skill_Levels Notice = null, Advanced_Beginner = null, Competent = null, Proficient = null,
			Expert = null;
	private boolean DEBUG = false;
	private Transaction transaction = null;
	private CommonStuff commonStuff = null;
	private JComboBox<Skill_Levels> endorseBox = null;
	
	ArrayList<RatedSkills> ratedSkills;
	private String[] columnNames = { "Skill", "Level", "Endorse" };

	public ColleagueProfileSkillTableModel(ArrayList<RatedSkills> ratedSkills)
	{
		super();
		this.ratedSkills = ratedSkills;
//		System.out.println("ColleagueProfileSkillTableModel :  rows in User Skills " + ratedSkills.size());
		
		endorseBox = new JComboBox<Skill_Levels>();
		endorseBox.addItem(Notice);
		endorseBox.addItem(Advanced_Beginner);
		endorseBox.addItem(Competent);
		endorseBox.addItem(Proficient);
		endorseBox.addItem(Expert);
	}

	@Override
	public int getRowCount()
	{
		return ratedSkills.size();
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col)
	{
		// Note that the data/cell address is constant,
		// no matter where the cell appears on screen.
		if (col == 1)
		{
			return true ;
		}
		else 
		{
			return false;
		}
	}
	public void setValueAt(Object value, int row, int col) 
	{
        if (DEBUG)
        {
            System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
                              + value.getClass() + ")");
        }

        ratedSkills.get(row).setLevel(Integer.parseInt((String)value));
        fireTableCellUpdated(row, col);

        if (DEBUG)
        {
            System.out.println("New value of data:");
        }
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		RatedSkills ratedSkill = ratedSkills.get(rowIndex);
		
		if (columnIndex == 0)
		{
			return ratedSkill.getSkillName();
		}

		if (columnIndex == 1) 
		{
			return ratedSkill.getLevel();
		}

		if (columnIndex == 2)
		{
			return endorseBox;
		}

		return "1";
	}


}
