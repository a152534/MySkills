package org.notice.gui;

import java.awt.*;
import javax.swing.*;

public class SkillsTabbedPanelGUI
{
	private JPanel basePanel = null;
	private MySkillsTabbedPane skillsTabs = null;
	
	public SkillsTabbedPanelGUI(JPanel basePanel)
	{
		this.basePanel = basePanel;
		skillsTabs = new MySkillsTabbedPane(basePanel);
		basePanel.add(skillsTabs);
		
	}
}
