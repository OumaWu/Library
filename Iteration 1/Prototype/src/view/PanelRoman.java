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
import model.Roman;

public class PanelRoman extends GridBagPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5000396173126795657L;
	private Font font;
	private JLabel lbTitre;
	private JLabel lbAuteur;
	private JLabel lbNbPage;
	private JLabel lbPrix;
	private JTextField tfTitre;
	private JTextField tfAuteur;
	private JTextField tfNbPage;
	private JRadioButton prixChoix[];
	private ButtonGroup prixGroup;
	private Box box;
	private String prixTitle[] = {"Aucun","Goncourt","Medicis","Interallie"};
	
	public PanelRoman() {
		
		lbTitre = new JLabel("Titre : ");
		lbAuteur = new JLabel("Auteur : ");
		lbNbPage = new JLabel("NB pages : ");
		lbPrix = new JLabel("Prix litteraire : ");
		tfTitre = new JTextField(5);
		tfAuteur = new JTextField(5);
		tfNbPage = new JTextField(5);
		font = new Font("Consolas", Font.BOLD, 18);
		box = Box.createHorizontalBox();
		prixChoix = new JRadioButton[4];
		prixGroup = new ButtonGroup();
		for (int i = 0; i < prixChoix.length; i++) {
			prixChoix[i] = new JRadioButton(prixTitle[i]);
			prixGroup.add(prixChoix[i]);
			box.add(prixChoix[i]);
		}
		prixChoix[0].setSelected(true);
		lbTitre.setFont(font);
		lbAuteur.setFont(font);
		lbNbPage.setFont(font);
		lbPrix.setFont(font);
		
		this.setLayout(new GridBagLayout());
		addComponent(this, lbTitre, 0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE);
		addComponent(this, tfTitre, 1, 0, 3, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbAuteur, 0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfAuteur, 1, 1, 3, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbNbPage, 0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfNbPage, 1, 2, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, lbPrix, 0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, box, 1, 3, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		
	}
	
	public Livre getRoman() {
		String auteur = tfAuteur.getText();
		String titre = tfTitre.getText();
		int nbPage = Integer.parseInt(tfNbPage.getText());
		int prixLitt = 0;
		
		for (JRadioButton jbr : prixChoix) {
			if (jbr.isSelected()) {
				switch (jbr.getText().toUpperCase()) {
				case "AUCUN" :
					prixLitt = 0;
					break;
				case "GONCOURT" :
					prixLitt = Roman.GONCOURT;
					break;
				case "MEDICIS" :
					prixLitt = Roman.MEDICIS;
					break;
				case "INTERALLIE" :
					prixLitt = Roman.INTERALLIE;
					break;
				}
			}
		}
		Livre roman = new Roman(titre, auteur, nbPage, prixLitt);
		
		return roman;
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
