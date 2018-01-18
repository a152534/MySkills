package org.notice.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.notice.beans.User;
import org.notice.client.SkillClient;
import org.notice.client.Transaction;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MySkills extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField passwordField;
	private User loggedOnUser ; 
	private SkillClient client ; 
	private JButton btnLogin;
	private Transaction transaction ; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySkills frame = new MySkills();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MySkills() {
		client = new SkillClient();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
//	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(100, 83, 70, 15);
		panel.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(100, 128, 97, 15);
		panel.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(201, 81, 114, 19);
		panel.add(txtName);
		txtName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 126, 114, 19);
		panel.add(passwordField);
		
	    btnLogin = new JButton("Login");
		btnLogin.setBounds(147, 198, 117, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnLogin)
		{
			validateUser();
		}
		
		 
		
	}
	private void validateUser() {
		String userId = txtName.getText();
		transaction = new Transaction("getUser", userId);
		transaction = client.sendTransaction(transaction);
		
		if(transaction.getObject() == null ) {
			JOptionPane.showConfirmDialog(this, "Failed to validate user");
		} else {
			loggedOnUser = (User)transaction.getObject() ; 
			JOptionPane.showConfirmDialog(this, "Logged on " + loggedOnUser.getFirstName());
		}
		
	}

	private void addPanels() {
		//Create JtabbedPane
		//create myprofile panel
		//create colleauge panel 
		//create skillSearchPanel
		//create reportsPanel
		//remove loginPanel
		//add JtabbedPane
		//add panels
	}
}

