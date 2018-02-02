package org.notice.graphs;

import javax.swing.JPanel;

import org.notice.beans.CommonStuff;
import org.notice.beans.RatedSkills;
import org.notice.beans.Skill;
import org.notice.beans.SkillDistribution;
import org.notice.beans.SkillRatingDistribution;
import org.notice.beans.User;
import org.notice.client.Transaction;
import org.notice.gui.panels.SkillSelector;
import org.notice.gui.panels.UserSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Graphs extends JPanel implements ActionListener {
	private JButton btnSkill;
	private JButton btnUser;
	private JPanel panel;
	private JPanel panel1;
	private CommonStuff commonStuff;
	private UserSelector userSelector ; 
	private SkillSelector skillSelector ; 
	private JLabel lblDescription;
	// private PieDataset dataSet;
	// private JFreeChart chart;

	/**
	 * Create the panel.
	 */
	public Graphs(CommonStuff commonStuff) {
		this.commonStuff = commonStuff;
		setLayout(null);

		btnSkill = new JButton("Skill");
		btnSkill.addActionListener(this);
		btnSkill.setBounds(20, 20, 145, 25);
		add(btnSkill);

		btnUser = new JButton("User");
		btnUser.addActionListener(this);
		btnUser.setBounds(185, 20, 150, 25);
		add(btnUser);

		// Will be replaced by the graph panel
		panel = createChartPanel(getUserSkillsDatasetNumEndorsements(), "Skill by Number of ratings " );
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 145, 425, 425);
		add(panel);

		// Will be replaced by the graph panel
		panel1 = createChartPanel(getDataSetUserSkillAvg(), "Skill by average rating " );
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.setBounds(475, 145, 425, 425);
		add(panel1);

		lblDescription = new JLabel("Graphs for " + commonStuff.getLoggedOnUser().getFirstName() + " " + commonStuff.getLoggedOnUser().getSurName());
		lblDescription.setBounds(20, 85, 383, 40);
		add(lblDescription);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnUser) {
			selectUser();
		}
		if (source == btnSkill) {
			selectSkill();
		}

	}

	private void selectSkill() {
		
		skillSelector = new SkillSelector(commonStuff.getSkillsList());
		
		int result = JOptionPane.showConfirmDialog(null, skillSelector, "Select a skill ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {

			return;
		}
		if (result == JOptionPane.OK_OPTION) {

		}

		Skill SelectedSkill = skillSelector.getSelectedSkill();
		if(SelectedSkill.getSkillID() == -1 ) {
			JOptionPane.showMessageDialog(this, "No skill has been selected");
			return ; 
			
		}
		commonStuff.setSkill(SelectedSkill );
		reloadSkill(); 
	}

	private void selectUser() {
		Transaction transaction = new Transaction("getUserList", "A");
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<User> users = (ArrayList<User>) transaction.getObject();
		userSelector = new UserSelector(users);

		int result = JOptionPane.showConfirmDialog(null, userSelector, "Select a user ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {

			return;
		}
		if (result == JOptionPane.OK_OPTION) {

		}

		User selectedUser = userSelector.getSelectedUser();
		if (selectedUser.getUserID() == "-1") {
			JOptionPane.showMessageDialog(this, "No User has been selected");
			return;

		} else {
			selectedUser = fetchuser(selectedUser);
		}
		commonStuff.setColleague(selectedUser);
		//reload(); 
		
	}

	public void reload() {
		// Will remove be replaced by the graph panel
		
				remove(panel);
				remove(panel1);
				validate(); 
				if(commonStuff.getColleague() != null) {
					lblDescription.setText("Graphs for " + commonStuff.getColleague().getFirstName() + " " + commonStuff.getColleague().getSurName());
				}
				
				panel = createChartPanel(getUserSkillsDatasetNumEndorsements(), "Skill by Number of Ratings " );
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setBounds(20, 145, 425, 425);
				add(panel);

				// Will be replaced by the graph panel
				panel1 = createChartPanel(getDataSetUserSkillAvg(), "Skill by average rating " );
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.setBounds(475, 145, 425, 425);
				add(panel1);// to be called when tab is selected
				
				validate(); 
				repaint(); 
	}
	
	public void reloadSkill() {
		// Will remove be replaced by the graph panel
		
				remove(panel);
				remove(panel1);
				validate(); 
			 
				lblDescription.setText("Skill distributions for " + commonStuff.getSkill().getSkillName());
				 
				
				panel = createChartPanel(getSkillRatingDistribution(), "Number of resources by average rating" );
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setBounds(20, 145, 425, 425);
				add(panel);

				// Will be replaced by the graph panel
				panel1 = createChartPanel(getSkillDistribution() , " Weighted distribution  " );
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.setBounds(475, 145, 425, 425);
				add(panel1);// to be called when tab is selected
				
				validate(); 
				repaint(); 
	}

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("One", new Double(43.2));
		dataset.setValue("Two", new Double(10.0));
		dataset.setValue("Three", new Double(27.5));
		dataset.setValue("Four", new Double(17.5));
		dataset.setValue("Five", new Double(11.0));
		dataset.setValue("Six", new Double(19.4));
		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset, String chartName) {

		JFreeChart chart = ChartFactory.createPieChart(chartName, // chart title
				dataset, // data
				false, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;

	}

	public JPanel createChartPanel(PieDataset dataset, String chartName) {
		JFreeChart chart = createChart(dataset , chartName);
		return new ChartPanel(chart);

	}

	private PieDataset getDataSetUserSkillAvg() {
		Transaction transaction;
		if (commonStuff.getColleague() != null && commonStuff.getColleague().getUserID() != null) {
			transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		} else {
			transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		}
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<RatedSkills> ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (RatedSkills skill : ratedSkills) {
			dataset.setValue(skill.getSkillName(), skill.getAvgEndorsement());
		}
		return dataset;
		

	}

	private PieDataset getUserSkillsDatasetNumEndorsements() {
		Transaction transaction;
		if (commonStuff.getColleague() != null && commonStuff.getColleague().getUserID() != null) {
			transaction = new Transaction("getUserSkills", commonStuff.getColleague().getUserID());
		} else {
			transaction = new Transaction("getUserSkills", commonStuff.getLoggedOnUser().getUserID());
		}
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<RatedSkills> ratedSkills = (ArrayList<RatedSkills>) transaction.getObject();
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (RatedSkills skill : ratedSkills) {
			dataset.setValue(skill.getSkillName(), skill.getNumEndorsement());
		}
		return dataset;

		

	}
	
	private PieDataset getSkillRatingDistribution() {
		Transaction transaction = null;
		if (commonStuff.getSkill() != null ) {
			transaction = new Transaction("getSkillRatingDistribution", commonStuff.getSkill());
		} else {
			return null ; 
		}
		
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<SkillRatingDistribution> ratings = (ArrayList<SkillRatingDistribution>) transaction.getObject();
		DefaultPieDataset dataset = new DefaultPieDataset();
		int[] bands = new int[] {0,0,0,0,0}; 
		for ( SkillRatingDistribution rating : ratings) {
			double  avg = rating.getAverageRating() ; 
			if(avg  < 1 ) {bands[0] ++ ;  }
			if(avg >= 1 && avg < 2 ) {bands[1] ++ ;  }
			if(avg >= 2 && avg < 3 ) {bands[2] ++ ;  }
			if(avg >= 3 && avg < 4 ) {bands[3] ++ ;  }
			if(avg >= 4 && avg <= 5 ) {bands[4] ++ ;  }
			
			
		}
		
		
			dataset.setValue("Novice/Not rated ", bands[0]);
			dataset.setValue("Advanced Beginner", bands[1]);
			dataset.setValue("Competency", bands[2]);
			dataset.setValue("Proficiency", bands[3]);
			dataset.setValue("Expertise", bands[4]);
			
			
		
		return dataset;

		

	}
	
	private PieDataset getSkillDistribution() {
		Transaction transaction = null;
		if (commonStuff.getSkill() != null ) {
			transaction = new Transaction("getSkillDistribution", commonStuff.getSkill());
		} else {
			return null; 
		}
		
		transaction = commonStuff.getClient().sendTransaction(transaction);
		ArrayList<SkillDistribution> ratings = (ArrayList<SkillDistribution>) transaction.getObject();
		DefaultPieDataset dataset = new DefaultPieDataset();
		int[] bands = new int[] {0,0,0,0,0}; 
		for ( SkillDistribution rating : ratings) {
			
			String longName = rating.getFirstName() + " " + rating.getSurname() + "(" + rating.getAliasName() + ")" ; 
		
			dataset.setValue(longName , rating.getUserValue());
			
		}
		
		return dataset;

		

	}
	
	
	private User fetchuser(User selectedUser) {
		Transaction transaction = new Transaction("getUser", selectedUser.getUserID());
		transaction = commonStuff.getClient().sendTransaction(transaction);

		if (transaction.getObject() == null) {
			JOptionPane.showConfirmDialog(this, "Failed to find user in database ");
		} else {
			ArrayList<User> users = (ArrayList<User>) transaction.getObject();
			selectedUser = users.get(0);
			commonStuff.setColleague(selectedUser);
		}
		return selectedUser;
	}
}
