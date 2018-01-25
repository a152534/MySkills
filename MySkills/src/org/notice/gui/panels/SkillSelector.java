package org.notice.gui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import org.notice.beans.Skill;
import org.notice.tablemodel.SkillsUtilTableModel;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;
import java.awt.FlowLayout;

public class SkillSelector extends JPanel implements ListSelectionListener {
	private JTextField txtSearch;
	private JTable skillTable;
	private Skill selectedSkill;
	private TableRowSorter<SkillsUtilTableModel> sorter;

	/**
	 * Create the panel.
	 */
	public SkillSelector(ArrayList<Skill> skills) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtSearch = new JTextField();
		txtSearch.setText("Search");
		add(txtSearch);

		txtSearch.setColumns(10);

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// System.out.println(" remove update filter");
				newFilter();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// System.out.println(" insert update filter");
				newFilter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// System.out.println("change update filter");
				newFilter();
			}
			
		});

		SkillsUtilTableModel skillModel = new SkillsUtilTableModel(skills);
		skillTable = new JTable(skillModel);
		skillTable.getSelectionModel().addListSelectionListener(this);

		sorter = new TableRowSorter<SkillsUtilTableModel>(skillModel);

		skillTable.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(skillTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

	}

	private void newFilter() {
		//System.out.println("In newFilter");
		RowFilter<? super SkillsUtilTableModel, ? super Integer> rf = null;

		rf = RowFilter.regexFilter("(?i)" + txtSearch.getText());

		sorter.setRowFilter(rf);
		selectedSkill = new Skill(-1, txtSearch.getText());
	}

	public Skill getSelectedSkill() {
		return selectedSkill;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (skillTable.getSelectedRow() > 0) {
			int skillId = (int) skillTable.getValueAt(skillTable.getSelectedRow(), 1);
			String skillName = (String) skillTable.getValueAt(skillTable.getSelectedRow(), 0);
			selectedSkill = new Skill(skillId, skillName);
			System.out.println("SkillUtil Selected Skill" + selectedSkill.getSkillName());
		} else {
			selectedSkill = new Skill(-1, txtSearch.getText());
		}
			
	}

}
