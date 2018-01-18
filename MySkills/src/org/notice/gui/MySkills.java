package org.notice.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MySkills extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		
		textField = new JTextField();
		textField.setBounds(201, 81, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 126, 114, 19);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(147, 198, 117, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setBounds(100, 100, 725, 392);
		
	}
}
