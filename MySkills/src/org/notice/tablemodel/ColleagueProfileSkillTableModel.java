package org.notice.tablemodel;

import java.util.ArrayList;
import javax.swing.table.*;
import org.notice.beans.*;
import org.notice.enums.Skill_Levels;

/**
 * @author tony
 * as copied from philip's original
 */
@SuppressWarnings("serial")
public class ColleagueProfileSkillTableModel extends AbstractTableModel
{

	private ArrayList<ColleagueRatings> ratedSkills;
	private String[] columnNames = { "Skill", "Skill ID", "Level", "Average Rating","Number of endorsements", "Endorse" };
	private LevelDescription levelDescription = null;
	
	
	public ColleagueProfileSkillTableModel()
	{
						
	}
	
	public ColleagueProfileSkillTableModel(ArrayList<ColleagueRatings> ratedSkills)
	{
		super();
		this.ratedSkills = ratedSkills;
		//System.out.println("ColleagueProfileSkillTableModel :  rows in User Skills " + ratedSkills.size());
		
	}
	

	@Override
	public int getRowCount()
	{
		return ratedSkills.size();
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length; 
	}
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public boolean isCellEditable(int row, int col)
	{
		// Note that the data/cell address is constant,
		// no matter where the cell appears on screen.
		if (col == 5)
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
		
		Skill_Levels level = (Skill_Levels)value;
//      ratedSkills.get(row).setLevel(Integer.parseInt((String)value)); 
      
		//System.out.println("Object: " +  value + " Row: " + row + " Col: " + col);
     
		ratedSkills.get(row).setLevel((int)level.ordinal() + 1);

   //     System.out.println("New value: " + ratedSkills.get(row).getLevel() + " " + value);		
        		
//      fireTableCellUpdated(row, col);
        fireTableDataChanged();

 
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		ColleagueRatings ratedSkill = ratedSkills.get(rowIndex);
		levelDescription = new LevelDescription();
		
		if (columnIndex == 0)
		{
			return ratedSkill.getSkillName();
		}
		
		if (columnIndex == 1) 
		{
			return ratedSkill.getSkillId();
		}
		
		if (columnIndex == 2) 
		{
			return ratedSkill.getLevel();
		}
		
		if (columnIndex == 3)
		{
			return ratedSkill.getAvgEndorsement();
		}
		if (columnIndex == 4)
		{
			return ratedSkill.getNumOfEndorsements();
		}

		if (columnIndex == 5)
		{	
	
//			return ratedSkill.getNumEndorsement();
			//System.out.println("colleagye model level"  + ratedSkill.getMyRating());
			byte levelValueFromDB = (byte) ratedSkill.getMyRating();
			return levelDescription.getLevelDescription(levelValueFromDB);
		}

		return "1";
	}


}
