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

	private boolean DEBUG = false;
	private Transaction transaction = null;
	private CommonStuff commonStuff = null;
	private ArrayList<RatedSkills> ratedSkills;
	private String[] columnNames = { "Skill", "Level", "Endorse" };

	public ColleagueProfileSkillTableModel(ArrayList<RatedSkills> ratedSkills)
	{
		super();
		this.ratedSkills = ratedSkills;
		System.out.println("ColleagueProfileSkillTableModel :  rows in User Skills " + ratedSkills.size());
		
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
		if (col == 2)
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
		int sl =0;
        if (DEBUG)
        {
            System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
                              + value.getClass() + ")");
        }

      ratedSkills.get(row).setLevel(Integer.parseInt((String)value)); 
      //Tony - issue casting to string, with enums.
        
//		for(Skill_Levels skillLevel : Skill_Levels.values())
//		{
//			sl = skillLevel.getValue();
//		}
//		
//        ratedSkills.get(row).setLevel(sl);

        		
        		
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
			return ratedSkill.getNumEndorsement();
		}

		return "1";
	}


}
