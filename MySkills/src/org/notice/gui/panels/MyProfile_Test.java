package org.notice.gui.panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.notice.beans.RatedSkills;
import org.notice.client.SkillClient;
import org.notice.client.Transaction;
import org.notice.tablemodel.MyProfileRatedSkillTableModel;
import javax.swing.ScrollPaneConstants;

class MyProfile_Test extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MyProfile_Test() {

		SkillClient client = new SkillClient();
		
		ArrayList<RatedSkills> ratedSkills;
		Transaction transaction = new Transaction("getUserSkills", "A000123");
		transaction = client.sendTransaction(transaction);
		ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		MyProfileRatedSkillTableModel myModel = new MyProfileRatedSkillTableModel(ratedSkills);

		table = new JTable();
		  table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	        table.setFillsViewportHeight(true);
		 
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);


	}

	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("TableRenderDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyProfile_Test testPanel = new  MyProfile_Test();
		frame.getContentPane().add(testPanel);
		frame.setBounds(10, 10, 200, 200);;
		frame.setVisible(true);
		
	}
}