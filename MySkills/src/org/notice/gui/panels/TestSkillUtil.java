package org.notice.gui.panels;

import javax.swing.JPanel;

import org.notice.beans.CommonStuff;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestSkillUtil extends JPanel implements ActionListener {
	SkillSelector sk ; 
	JPanel panel  ;
	public TestSkillUtil(CommonStuff inCommonStuff)  {
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		btnNewButton.addActionListener(this);
		sk = new SkillSelector(inCommonStuff.getSkillsList());
		sk.setPreferredSize(new Dimension(500, 500));
		
		panel = new JPanel(new GridBagLayout());
		//panel.setBackground(Color.RED);
		panel.setPreferredSize(new Dimension(500, 500));
		panel.add(sk);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
//		JOptionPane.showConfirmDialog(this, sk, "Select Skill", JOptionPane.OK_CANCEL_OPTION,
//		            JOptionPane.INFORMATION_MESSAGE);
//		
		
		JOptionPane.showConfirmDialog(null,
                sk,
                "JOptionPane Example : ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		
		if(sk == null) {
			System.out.println("Sk is null");
		}
		JOptionPane.showMessageDialog(this, sk.getSelectedSkill().getSkillName());
	}

}
