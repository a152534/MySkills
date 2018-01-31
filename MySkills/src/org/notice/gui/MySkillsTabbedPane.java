package org.notice.gui;


import java.awt.*;
import org.notice.gui.panels.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.notice.beans.CommonStuff;

public class MySkillsTabbedPane extends JTabbedPane implements ActionListener
{
	private JPanel basePanel = null,  skillsAdmin = null,  logOff = null;
	private MyProfile1  myProfile = null; 
	private SkillsSearch skillsSearch = null;
	private Reports2 reports = null;
	private JButton logoffButton = null;
	private ColleagueProfile colleague;
	private Colleague colleague1;
	private Admin admin;
	//private Graphs graphs ;
	
	public MySkillsTabbedPane(JPanel basePanel, CommonStuff commonStuff)
	{
		this.basePanel = basePanel;
		
		myProfile =  new MyProfile1(commonStuff );
		colleague = new ColleagueProfile(commonStuff);
		colleague1 = new Colleague(commonStuff);
		admin = new Admin(commonStuff);
		skillsSearch = new SkillsSearch(commonStuff);
		//graphs = new Graphs( commonStuff);
				
		skillsAdmin = new JPanel();
		reports = new Reports2(commonStuff);
		logOff = new JPanel();
		
		logoffButton = new JButton("Log Off");
		logoffButton.addActionListener(this);
		logOff.add(logoffButton);	
		
		
		this.addTab("My Profile", myProfile);
		this.addTab("Colleague", colleague);
		this.addTab("Colleague_T", colleague1);
		this.addTab("Skills Search", skillsSearch);
		this.addTab("Reports", reports);
		this.addTab("Admin", admin);
		//this.addTab("Pretty Pictoures" , graphs);

		addChangeListener(new ChangeListener() {
	    

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tab: " + getSelectedIndex());
				if( getSelectedIndex() == 1 ) {
					colleague.reload();
				}
				
			}
	    });
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
