package org.notice.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MySkillsGUI extends JFrame
{

	private JPanel basePanel;
	private LoginScreenGUI loginScreen = null;
	
	public MySkillsGUI()
	{
		this.setTitle("MySkills");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1000, 800);
		this.setLocation(400, 100);
		this.setResizable(false);


		
		basePanel = new JPanel();
		basePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		
	
		basePanel.setLayout(new GridLayout(1,1));
//		baseTabbedPane.add(basePanel);
		
		loginScreen = new LoginScreenGUI(basePanel);
		basePanel.add(loginScreen);
		this.setContentPane(basePanel);
		
		
	}
	
	public static void main(String[] args)
	{
		MySkillsGUI skillsGUI =  new MySkillsGUI();
		skillsGUI.setVisible(true);
		

	}

}