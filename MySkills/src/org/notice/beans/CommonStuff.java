package org.notice.beans;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import org.notice.client.SkillClient;

public class CommonStuff
{
	private User loggedOnUser;
	private SkillClient client;
	private ArrayList<Skill> skillsList;
	private JTabbedPane tabbedPane;
	private User colleague ; 
	private Skill skill ; 
	private Font fontLabel;
	private Font fontButton;
	private Font fontTextArea;
	private Font fontTextBox;
	private Font fontComboBox;
	
	public CommonStuff(User loggedOnUser, SkillClient client, ArrayList<Skill> skillsList)
	{
		super();
		this.loggedOnUser = loggedOnUser;
		this.client = client;
		this.skillsList = skillsList;
		
		fontLabel = (new Font("Arial", Font.BOLD, 14));
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontTextArea = (new Font("Arial", Font.PLAIN, 14));
		fontTextBox = (new Font("Arial", Font.PLAIN, 12));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));
		
	}

	public CommonStuff() 
	{
		this(null, null, null);
	}

	public User getLoggedOnUser() 
	{
		return loggedOnUser;
	}

	public void setLoggedOnUser(User loggedOnUser)
	{
		this.loggedOnUser = loggedOnUser;
	}

	public SkillClient getClient() 
	{
		return client;
	}

	public void setClient(SkillClient client) 
	{
		this.client = client;
	}

	public ArrayList<Skill> getSkillsList() 
	{
		return skillsList;
	}

	public void setSkillsList(ArrayList<Skill> skillsList) 
	{
		this.skillsList = skillsList;
	}

	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane)
	{
		this.tabbedPane = tabbedPane;
	}

	public User getColleague() 
	{
		return colleague;
	}

	public void setColleague(User colleague) 
	{
		this.colleague = colleague;
	}

	public Skill getSkill() 
	{
		return skill;
	}

	public void setSkill(Skill skill) 
	{
		this.skill = skill;
	}

	public Font getFontLabel()
	{
		return fontLabel;
	}

	public void setFontLabel(Font fontLabel)
	{
		this.fontLabel = fontLabel;
	}

	public Font getFontButton()
	{
		return fontButton;
	}

	public void setFontButton(Font fontButton)
	{
		this.fontButton = fontButton;
	}

	public Font getFontTextArea()
	{
		return fontTextArea;
	}

	public void setFontTextArea(Font fontTextArea)
	{
		this.fontTextArea = fontTextArea;
	}

	public Font getFontTextBox()
	{
		return fontTextBox;
	}

	public void setFontTextBox(Font fontTextBox)
	{
		this.fontTextBox = fontTextBox;
	}

	public Font getFontComboBox()
	{
		return fontComboBox;
	}

	public void setFontComboBox(Font fontComboBoxSearch)
	{
		this.fontComboBox = fontComboBoxSearch;
	}
}
