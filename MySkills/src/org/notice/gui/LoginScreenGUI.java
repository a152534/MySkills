package org.notice.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.notice.beans.CommonStuff;
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

	public LoginScreenGUI(JPanel basePanel, CommonStuff commonStuff) {
		this.basePanel = basePanel;
		this.commonStuff = commonStuff;
		
		setLayout(null);

		screenTitle = new JLabel("MySkills Login");

		this.add(screenTitle);

		userIDLabel = new JLabel("User ID");
		userIDLabel.setBounds(50, 20, 180, 20);
		this.add(userIDLabel);

		userIDField = new JTextField();
		userIDField.setBounds(250, 20, 180, 20);
		this.add(userIDField);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 60, 180, 20);
		this.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(250, 60, 180, 20);
		this.add(passwordField);

		loginButton = new JButton("Log In");
		loginButton.setBounds(150, 100, 180, 40);
		// loginButton.setFont(loginFont);
		loginButton.addActionListener(this);
		this.add(loginButton);

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
		Transaction transaction = new Transaction("getUser", userId);
		transaction = commonStuff.getClient().sendTransaction(transaction);

		if (transaction.getObject() == null) {
			JOptionPane.showConfirmDialog(this, "Failed to validate user");
		} else {
			ArrayList<User> users = (ArrayList<User>) transaction.getObject();
			User loggedOnUser = users.get(0);
			commonStuff.setLoggedOnUser(loggedOnUser);
			JOptionPane.showConfirmDialog(this, "Logged on " + loggedOnUser.getFirstName());
			skillsMainScreen = new MySkillsTabbedPane(basePanel,   commonStuff);
			this.basePanel.removeAll();
			this.basePanel.validate();
			this.basePanel.repaint();
			this.basePanel.add(skillsMainScreen);
			this.basePanel.validate();
			this.basePanel.repaint();

		}
	}

}
