package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Livre;
import model.Manuel;

public class PanelManuel extends GridBagPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1337958918119522631L;
	private Font font;
	private JLabel lbTitre;
	private JLabel lbAuteur;
	private JLabel lbNbPage;
	private JLabel lbNiveau;
	private JTextField tfTitre;
	private JTextField tfAuteur;
	private JTextField tfNbPage;
	private JRadioButton niveauChoix[];
	private ButtonGroup niveauGroup;
	private Box box;
	
	public PanelManuel() {
		
		lbTitre = new JLabel("Titre : ");
		lbAuteur = new JLabel("Auteur : ");
		lbNbPage = new JLabel("NB pages : ");
		lbNiveau = new JLabel("Niveau : ");
		tfTitre = new JTextField(5);
		tfAuteur = new JTextField(5);
		tfNbPage = new JTextField(5);
		font = new Font("Consolas", Font.BOLD, 18);
		box = Box.createHorizontalBox();
		niveauChoix = new JRadioButton[4];
		niveauGroup = new ButtonGroup();
		for (int i = 0; i < niveauChoix.length; i++) {
			niveauChoix[i] = new JRadioButton(i + 1 + "");
			niveauGroup.add(niveauChoix[i]);
			box.add(niveauChoix[i]);
		}
		niveauChoix[0].setSelected(true);
		lbTitre.setFont(font);
		lbAuteur.setFont(font);
		lbNbPage.setFont(font);
		lbNiveau.setFont(font);
		
		this.setLayout(new GridBagLayout());
		addComponent(this, lbTitre, 0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfTitre, 1, 0, 5, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbAuteur, 0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfAuteur, 1, 1, 5, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbNbPage, 0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfNbPage, 1, 2, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, lbNiveau, 0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, box, 1, 3, 5, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		
	}
	
	public Livre getManuel() {
		String auteur = tfAuteur.getText();
		String titre = tfTitre.getText();
		int nbPage = Integer.parseInt(tfNbPage.getText());
		int niveau = 0;
		
		for (JRadioButton jbr : niveauChoix) {
			if (jbr.isSelected()) {
				switch (jbr.getText()) {
				case "1" :
					niveau = 1;
					break;
				case "2" :
					niveau = 2;
					break;
				case "3" :
					niveau = 3;
					break;
				case "4" :
					niveau = 4;
					break;
				}
			}
		}
		Livre manuel = new Manuel(titre, auteur, nbPage, niveau);
		
		return manuel;
	}
	
	@Override
	public int checkParam() {
		
		if (!tfAuteur.getText().isEmpty() &&
				!tfTitre.getText().isEmpty() &&
				!tfNbPage.getText().isEmpty()){
			try {
				Integer.parseInt(tfNbPage.getText());
			} catch (Exception e) {
				return -2;
			}
		}
		else {
			return -1;
		}
		return 0;
	}
}
