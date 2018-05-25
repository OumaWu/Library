package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class GridBagPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7984027060299818475L;
	protected GridBagConstraints gbc;
	
	protected void addComponent(Container con, JComponent comp, int xPos, int yPos,
			int compWidth, int compHeight, int ipadx, int ipady, int anchor, int fill) {
		gbc = new GridBagConstraints();
		gbc.gridx = xPos;
		gbc.gridy = yPos;
		gbc.gridwidth = compWidth;
		gbc.gridheight = compHeight;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.insets = new Insets(15, 12, 15, 12);
		gbc.anchor = anchor;
		gbc.fill = fill;
		con.add(comp, gbc);
	}
	
	public abstract int checkParam();
}
