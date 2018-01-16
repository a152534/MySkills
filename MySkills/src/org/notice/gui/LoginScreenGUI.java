package org.notice.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginScreenGUI extends JPanel implements ActionListener
{
	private JPanel basePanel = null;
	private JLabel userIDLabel = null, screenTitle = null, passwordLabel = null;
	private JTextField userIDField = null;
	private JPasswordField passwordField = null;
	private JButton loginButton = null;
//	private Font loginFont = null;
	private GridBagLayout loginScreenLayout = null;
	private GridBagConstraints titleConstraints = null, userIDLabelConstraints = null, userIDTextConstraints = null, passwordLabelConstraints = null,
							passwordFieldConstraints = null, loginbuttonConstraints = null;
	private MySkillsTabbedPane skillsMainScreen = null;


	
	public LoginScreenGUI(JPanel basePanel)
	{
		this.basePanel = basePanel;
//		loginScreenConstraints = new GridBagConstraints();
//		loginFont = new Font("Arial", Font.BOLD, 25);
		loginScreenLayout = new GridBagLayout();
		loginScreenLayout.columnWidths = new int[]{325, 325, 325, 0};
		loginScreenLayout.rowHeights = new int[]{75, 75, 75, 75, 75, 75, 75, 75, 75, 0};
		loginScreenLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		loginScreenLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(loginScreenLayout);
		

		screenTitle = new JLabel("MySkills Login");
//		screenTitle.setFont(loginFont);
		titleConstraints  = new GridBagConstraints();
		titleConstraints.fill = GridBagConstraints.BOTH;
		titleConstraints.insets = new Insets(0, 0, 5, 0);
		titleConstraints.gridx = 1;
		titleConstraints.gridy = 0;
		this.add(screenTitle, titleConstraints);
		
				
		userIDLabel = new JLabel("User ID");
//		userIDLabel.setFont(loginFont);
		userIDLabelConstraints = new GridBagConstraints();
		userIDLabelConstraints.fill = GridBagConstraints.BOTH;
		userIDLabelConstraints.insets = new Insets(0, 0, 5, 0);
		userIDLabelConstraints.gridx = 0;
		userIDLabelConstraints.gridy = 3;
		this.add(userIDLabel, userIDLabelConstraints);
				
				
		userIDField = new JTextField();
//		userIDField.setFont(loginFont);
		userIDTextConstraints = new GridBagConstraints();
		userIDTextConstraints.fill = GridBagConstraints.BOTH;
		userIDTextConstraints.insets = new Insets(0, 0, 5, 0);
		userIDTextConstraints.gridx = 2;
		userIDTextConstraints.gridy = 3;
		this.add(userIDField, userIDTextConstraints);
		
		
		passwordLabel = new JLabel("Password");
//		passwordLabel.setFont(loginFont);
		passwordLabelConstraints = new GridBagConstraints();
		passwordLabelConstraints.fill = GridBagConstraints.BOTH;
		passwordLabelConstraints.insets = new Insets(0, 0, 5, 0);
		passwordLabelConstraints.gridx = 0;
		passwordLabelConstraints.gridy = 5;
		this.add(passwordLabel, passwordLabelConstraints);
				
		
		passwordField = new JPasswordField();
//		passwordField.setFont(loginFont);
		passwordFieldConstraints = new GridBagConstraints();
		passwordFieldConstraints.fill = GridBagConstraints.BOTH;
		passwordFieldConstraints.insets = new Insets(0, 0, 5, 0);
		passwordFieldConstraints.gridx = 2;
		passwordFieldConstraints.gridy = 5;
		this.add(passwordField, passwordFieldConstraints);
				
				
		loginButton = new JButton("Log In");
//		loginButton.setFont(loginFont);
		loginButton.addActionListener(this);
		loginbuttonConstraints = new GridBagConstraints();
		loginbuttonConstraints.insets = new Insets(5, 5, 5, 5);
		loginbuttonConstraints.fill = GridBagConstraints.BOTH;
		loginbuttonConstraints.gridx = 1;
		loginbuttonConstraints.gridy = 8;
		this.add(loginButton, loginbuttonConstraints);
	

	 }

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if(source == loginButton)
		{
//			if(loggedOnUser.validateUser(user, passwordString()) == true)
//			{
				
				skillsMainScreen = new MySkillsTabbedPane(basePanel);
				this.basePanel.removeAll();
				this.basePanel.validate();
				this.basePanel.repaint();
				this.basePanel.add(skillsMainScreen);
				this.basePanel.validate();
				this.basePanel.repaint();

				

//			}
		}
		
	}
	
}
