package view;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Document;
import model.Revue;

public class PanelRevue extends GridBagPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -145844288230471150L;
	private Font font;
	private JLabel lbTitre;
	private JLabel lbMois;
	private JLabel lbAnnee;
	private JTextField tfTitre;
	private JTextField tfMois;
	private JTextField tfAnnee;
	
	public PanelRevue() {
		
		lbTitre = new JLabel("Titre : ");
		lbMois = new JLabel("Mois : ");
		lbAnnee = new JLabel("Annee : ");
		tfTitre = new JTextField(10);
		tfMois = new JTextField(10);
		tfAnnee = new JTextField(5);
		font = new Font("Consolas", Font.BOLD, 18);
		
		lbTitre.setFont(font);
		lbMois.setFont(font);
		lbAnnee.setFont(font);
		
		this.setLayout(new GridBagLayout());
		addComponent(this, lbTitre, 0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfTitre, 1, 0, 5, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbMois, 0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfMois, 1, 1, 5, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		addComponent(this, lbAnnee, 0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(this, tfAnnee, 1, 2, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
		
	}
	
	public Document getRevue() {
		
		String titre = tfTitre.getText();
		int mois = Integer.parseInt(tfMois.getText());
		int annee = Integer.parseInt(tfAnnee.getText());	
		Document revue = new Revue(titre, mois, annee);
		
		return revue;
	}
	
	@Override
	public int checkParam() {
		if (!tfMois.getText().isEmpty() &&
				!tfTitre.getText().isEmpty() &&
				!tfAnnee.getText().isEmpty()){
			try {
				Integer.parseInt(tfMois.getText());
				Integer.parseInt(tfAnnee.getText());
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
