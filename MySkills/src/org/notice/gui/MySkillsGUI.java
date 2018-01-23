package org.notice.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import org.notice.beans.CommonStuff;
import org.notice.client.SkillClient;

public class MySkillsGUI extends JFrame
{

	private JPanel basePanel;
	private LoginScreenGUI loginScreen = null;
	private static CommonStuff commonstuff ; 
	
	public MySkillsGUI()
	{
		commonstuff = new CommonStuff() ;
		commonstuff.setClient(new SkillClient());
		
		this.setTitle("MySkills");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1000, 800);
	
		basePanel = new JPanel();
		basePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		basePanel.setLayout(new GridLayout(1,1));

		loginScreen = new LoginScreenGUI(basePanel, commonstuff);
		basePanel.add(loginScreen);
		this.setContentPane(basePanel);

		
	}
	
	public static void main(String[] args)
	{
		MySkillsGUI skillsGUI =  new MySkillsGUI();
		skillsGUI.setVisible(true);
		

	}

}