package org.notice.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import org.notice.beans.CommonStuff;
import org.notice.client.SkillClient;

public class MySkillsGUI extends JFrame {

	private JPanel basePanel;
	private LoginScreenGUI loginScreen = null;
	private static CommonStuff commonstuff;
	private JPanel northPanel = null;
        private JLabel picLabel = null ;
        private JScrollPane scrollPane= null;
        private ImageIcon ii = null;

	public MySkillsGUI() {
		commonstuff = new CommonStuff();
		commonstuff.setClient(new SkillClient());

		this.setTitle("My Skills");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1000, 900);

		basePanel = new JPanel();
		basePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

		basePanel.setLayout(new GridLayout(1, 1));

		loginScreen = new LoginScreenGUI(basePanel, commonstuff);
		basePanel.add(loginScreen);
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(50,100));
		northPanel.setLayout(new GridLayout(1, 1));
		northPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		ii = new ImageIcon("resources/Title.jpg");
		picLabel = new JLabel(ii);
		scrollPane = new JScrollPane(picLabel);
		northPanel.add(scrollPane);
		
	//	this.setContentPane(basePanel);
		this.setTitle("Skills Application");
		
		
		this.setLayout(new BorderLayout(0,0));
		this.add(basePanel,BorderLayout.CENTER);
		this.add(northPanel,BorderLayout.NORTH);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		MySkillsGUI skillsGUI = new MySkillsGUI();
		skillsGUI.setVisible(true);

	}

}