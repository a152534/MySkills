package org.notice.gui;


import java.awt.*;
import org.notice.gui.panels.*;
import java.awt.event.*;
import javax.swing.*;

import org.notice.beans.CommonStuff;

public class MySkillsTabbedPane extends JTabbedPane implements ActionListener
{
	private JPanel basePanel = null,  skillsAdmin = null,  logOff = null;
	private MyProfile1  myProfile = null; 
	private SkillsSearch skillsSearch = null;
	private Reports reports = null;
	private JButton logoffButton = null;
	private Colleague colleague;
	
	public MySkillsTabbedPane(JPanel basePanel, CommonStuff commonStuff)
	{
		this.basePanel = basePanel;
		
		myProfile =  new MyProfile1(commonStuff );
		colleague = new Colleague(commonStuff);
		
		skillsSearch = new SkillsSearch(commonStuff);
		
		
		skillsAdmin = new JPanel();
		reports = new Reports(commonStuff);
		logOff = new JPanel();
		
		logoffButton = new JButton("Log Off");
		logoffButton.addActionListener(this);
		logOff.add(logoffButton);	
		
		
		this.addTab("My Profile", myProfile);
		this.addTab("Colleague", colleague);
		this.addTab("Skills Search", skillsSearch);
		this.addTab("Reports", reports);
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
