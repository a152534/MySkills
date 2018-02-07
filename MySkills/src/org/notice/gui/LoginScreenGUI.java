package org.notice.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.notice.beans.CommonStuff;
import org.notice.beans.Skill;
import org.notice.beans.User;
import org.notice.client.SkillClient;
import org.notice.client.Transaction;

public class LoginScreenGUI extends JPanel implements ActionListener {
	private JPanel basePanel = null;
	private JLabel userIDLabel = null, screenTitle = null, passwordLabel = null;
	private JTextField userIDField = null;
	private JPasswordField passwordField = null;
	private JButton loginButton = null;
	// private Font loginFont = null;
	private GridBagLayout loginScreenLayout = null;
	private GridBagConstraints titleConstraints = null, userIDLabelConstraints = null, userIDTextConstraints = null,
			passwordLabelConstraints = null, passwordFieldConstraints = null, loginbuttonConstraints = null;
	private MySkillsTabbedPane skillsMainScreen = null;

	private SkillClient client;
	private CommonStuff commonStuff;
	private Font fontLabel;
	private Font fontJPasswordField;
	private Font fontButton;
	private Font fontComboBox;
	private Font fontTextBox;

	public LoginScreenGUI(JPanel basePanel, CommonStuff commonStuff) {
		fontLabel = (new Font("Arial", Font.BOLD, 14));
		fontTextBox = (new Font("Arial", Font.PLAIN, 12));
		fontJPasswordField = (new Font("Arial", Font.PLAIN, 12));
		fontButton = (new Font("Arial", Font.BOLD, 18));
		fontComboBox = (new Font("Arial", Font.PLAIN, 14));

		this.basePanel = basePanel;
		this.commonStuff = commonStuff;

		setLayout(null);

		screenTitle = new JLabel("MySkills Login");

		this.add(screenTitle);

		userIDLabel = new JLabel("User ID:");
		userIDLabel.setBounds(240, 50, 180, 20);
		userIDLabel.setFont(fontLabel);
		this.add(userIDLabel);

		userIDField = new JTextField();
		userIDField.setBounds(350, 50, 300, 20);
		userIDField.setFont(fontTextBox);
		this.add(userIDField);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(240, 90, 180, 20);
		passwordLabel.setFont(fontLabel);
		this.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(350, 90, 300, 20);
		passwordField.setFont(fontJPasswordField);
		this.add(passwordField);

		loginButton = new JButton("Log In");
		loginButton.setBounds(350, 130, 145, 25);
		loginButton.setFont(fontButton);
		// loginButton.setFont(loginFont);
		loginButton.addActionListener(this);
		this.add(loginButton);

		Transaction transaction = new Transaction("getSkillList", null);
		transaction = commonStuff.getClient().sendTransaction(transaction);

		if (transaction.getObject() == null) {
			System.out.println("LoginScreenGui skillslist == null ");

		} else {
			commonStuff.setSkillsList((ArrayList<Skill>) transaction.getObject());
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == loginButton) {
			validateUser();

		}

	}

	private void validateUser() {
		String userId = userIDField.getText();
		//if (userId.equals("")) {
		//	userId = "A000123";
		//} else {
			userId = userIDField.getText();
		//}
		Transaction transaction = new Transaction("getUser", userId);
		transaction = commonStuff.getClient().sendTransaction(transaction);

		if (transaction.getObject() == null) {
			JOptionPane.showMessageDialog(this, "Invalid user", "Warning", JOptionPane.OK_OPTION);
			 // showInputDialog(this, "Failed to validate user",JOptionPane.OK_OPTION);
		} else {
			ArrayList<User> users = (ArrayList<User>) transaction.getObject();
			User loggedOnUser = users.get(0);
			commonStuff.setLoggedOnUser(loggedOnUser);
			
				
			
			
			skillsMainScreen = new MySkillsTabbedPane(basePanel, commonStuff);
			commonStuff.setTabbedPane(skillsMainScreen);
			
//			skillsMainScreen.addChangeListener(new ChangeListener() {
//		        
//
//				@Override
//				public void stateChanged(ChangeEvent e) {
//					System.out.println("Tab: " + skillsMainScreen.getSelectedIndex());
//					if( skillsMainScreen.getSelectedIndex() == 1 ) {
//						
//					}
//					
//				}
//		    });
			
			this.basePanel.removeAll();
			this.basePanel.validate();
			this.basePanel.repaint();
			this.basePanel.add(skillsMainScreen);
			this.basePanel.validate();
			this.basePanel.repaint();

		}

	}

}
