/**
 * 
 */
package org.notice.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import org.notice.beans.RatedSkills;

/**
 * @author philip
 *
 */
@SuppressWarnings("serial")
public class MyProfileRatedSkillTableModel extends AbstractTableModel {
	
	ArrayList<RatedSkills> ratedSkills;
	 private String[] columnNames = {"Skill","My rating","Number of endorsements" , "Average endorsement"};
	
	public MyProfileRatedSkillTableModel(ArrayList<RatedSkills> ratedSkills) {
		super();
		this.ratedSkills = ratedSkills;
		System.out.println("MyProfileRatedSkillTableModel :  rows in rated skills "+ ratedSkills.size() );
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
//		return ratedSkills.size();
		return 10;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	 public String getColumnName(int col) {
         return columnNames[col];
     }
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("MyProfileRatedSkillTableModel getValueAt ");
		RatedSkills ratedSkill = ratedSkills.get(rowIndex);
		if(columnIndex == 0 ) {
			return ratedSkill.getSkillName();
		}
		
		if(columnIndex == 1 ) {
			return ratedSkill.getLevel();
		}
		
		if(columnIndex == 2 ) {
			return ratedSkill.getNumEndorsement();
		}
		if(columnIndex == 3 ) {
			return ratedSkill.getNumEndorsement();
		}
		
		return "1" ; 
	}
	 
}
