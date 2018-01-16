package org.notice.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MySkillsTabbedPane extends JTabbedPane implements ActionListener
{
	private JPanel basePanel = null, myProfile = null, skillsSearch = null, endorseSkills = null, skillsAdmin = null, mySkillsReports = null, logOff = null;
	private JButton logoffButton = null;
	
	public MySkillsTabbedPane(JPanel basePanel)
	{
		this.basePanel = basePanel;
		
		myProfile = new JPanel();
		skillsSearch = new JPanel();
		endorseSkills = new JPanel();
		skillsAdmin = new JPanel();
		mySkillsReports = new JPanel();
		logOff = new JPanel();
		
		logoffButton = new JButton("Log Off");
		logoffButton.addActionListener(this);
		logOff.add(logoffButton);	
		
		
		this.addTab("My Profile", myProfile);
		this.addTab("Search Skills", myProfile);
		this.addTab("Endorse", endorseSkills);
		this.addTab("Administration", skillsAdmin);
		this.addTab("Administration", mySkillsReports);
		this.addTab("Logoff", logOff);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if(source == logoffButton)
		{
			int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
			if(option == JOptionPane.OK_OPTION)
			{
				System.exit(0);
			}
			return;
		}
	}
	
}
