package org.notice.gui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.notice.beans.CommonStuff;
import org.notice.beans.Skill;
import org.notice.beans.UserSkillEndorsements;
import org.notice.client.Transaction;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Graphs extends JPanel implements ActionListener {

	private CommonStuff commonStuff;
	private SkillSelector skillSelector;
	private JButton btnSelectSkill;
	private JPanel graphPanel;
	private DefaultPieDataset dataset;
	private JFreeChart chart;
	private Skill selectedSkill;
	private ArrayList<Skill> skillList;

	public Graphs(CommonStuff commonStuff) {
		super();
		setLayout(new BorderLayout(0, 0));
		this.commonStuff = commonStuff;
		skillList = new ArrayList<Skill>();

		Vector<String> allskills = new Vector<String>();
		for (Skill skill : commonStuff.getSkillsList()) {
			allskills.add(skill.getSkillName() + "    ID_" + skill.getSkillID());
		}
		Collections.sort(allskills);

		JComboBox<String> comboSkill = new JComboBox<String>(allskills);

		btnSelectSkill = new JButton("Select a skill");

		add(btnSelectSkill, BorderLayout.NORTH);

		btnSelectSkill.addActionListener(this);

		// btnSelectSkill.doClick();

	}

	private void SelectSkill() {

		skillSelector = new SkillSelector(commonStuff.getSkillsList());

		int result =  0 ; 
		result = JOptionPane.showConfirmDialog(this, skillSelector, "Select a skill ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.CANCEL_OPTION) {

			return;
		}
		if (result == JOptionPane.OK_OPTION) {

			Skill SelectedSkill = skillSelector.getSelectedSkill();
			if (SelectedSkill.getSkillID() == -1) {
				JOptionPane.showMessageDialog(this, "No skill has been selected");
				return;

			}
			createDataset();
			createPanel();
		}
	}

	private void createPanel() {

		graphPanel = createDemoPanel();
		add(graphPanel, BorderLayout.CENTER);
		validate();

	}

	private PieDataset createDataset() {

		Transaction transaction = new Transaction("getUserEndorsementsPerSkill", skillList);

		transaction = commonStuff.getClient().sendTransaction(transaction);

		ArrayList<UserSkillEndorsements> userSkillEndorsements = (ArrayList<UserSkillEndorsements>) transaction
				.getObject();
		dataset = new DefaultPieDataset();
		int[] band = new int[] { 0, 0, 0, 0, 0, 0 };
		double rating;
		for (UserSkillEndorsements endorsements : userSkillEndorsements) {
			rating = endorsements.getAvgEndorsement().doubleValue();
			if (endorsements.getNumOfEndorsements() == 0) {
				band[0]++;
			} else {
				if (rating > 0 && rating < 1) {
					band[1]++;
				}
				if (rating >= 1 && rating < 2) {
					band[2]++;
				}
				if (rating >= 2 && rating < 3) {
					band[3]++;
				}
				if (rating >= 3 && rating < 4) {
					band[4]++;
				}

				if (rating >= 4) {
					band[5]++;
				}

			}

		}
		dataset = new DefaultPieDataset();

		dataset.setValue("No Endorsements", band[0]);
		dataset.setValue("0 <  rating < 1 ", band[1]);
		dataset.setValue("1 <  rating < 1 ", band[2]);
		dataset.setValue("2 <  rating < 1 ", band[3]);
		dataset.setValue("3 <  rating < 1 ", band[4]);
		dataset.setValue("4 <  rating < 1 ", band[5]);

		return dataset;
	}

	private JFreeChart createChart(PieDataset dataset) {
		chart = ChartFactory.createPieChart("Mobile Sales", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	public JPanel createDemoPanel() {
		JFreeChart chart = createChart(dataset);
		return new ChartPanel(chart);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelectSkill) {
			createPanel();
		}

	}

}