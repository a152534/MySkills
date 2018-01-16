package org.notice.beans;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class MyProfile extends JFrame
{

	private JPanel contentPane;
	private JPanel myProfilePanel;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MyProfile frame = new MyProfile();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyProfile()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setBounds(100, 100, 450, 300);
		setBounds(450, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		myProfilePanel = new JPanel();
		contentPane.add(myProfilePanel, BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		myProfilePanel.add("My Profile", tabbedPane);
		
		
	}

}
