package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Bibliotheque;
import model.Document;
import model.Livre;

public class SaisieLivre extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 925935274906087423L;
	private Bibliotheque bbth;
	private JButton jbValider, jbAnnuler;
	private JTabbedPane tabPanel;
	private JPanel bottom;
	private JPanel panelRoman, panelManuel, panelRevue;
	private int tableIndex;
	
	public SaisieLivre(Bibliotheque bbth) throws HeadlessException {
		super();
		this.bbth = bbth;
		initComponents();
		setListeners();
		initSettings();
	}
	
	public void initComponents() {
		
		tabPanel = new JTabbedPane();
		bottom = new JPanel();
		panelRoman = new PanelRoman();
		panelManuel = new PanelManuel();
		panelRevue = new PanelRevue();
		jbValider = new JButton("Valider");
		jbAnnuler = new JButton("Annuler");
		
		tabPanel.addTab("Roman", null, panelRoman, "Page pour saisir un roman");
		tabPanel.addTab("Manuel", null, panelManuel, "Page pour saisir un manuel");
		tabPanel.addTab("Revue", null, panelRevue, "Page pour saisir une revue");
		bottom.setLayout(new FlowLayout(SwingConstants.RIGHT));
		bottom.add(jbAnnuler);
		bottom.add(jbValider);
		this.setLayout(new BorderLayout());
		this.add(tabPanel, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
	}
	
	public void initSettings() {
		this.setTitle("Saisie document");
		this.setLocation(400,250);
		this.setSize(550, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
		
	public void setListeners() {
		
		tabPanel.addChangeListener(new ChangeListener() {
			
		      public void stateChanged(ChangeEvent changeEvent) {
		          JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		          tableIndex = sourceTabbedPane.getSelectedIndex();
		      } 
		});
		
		jbValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Saisir " + tabPanel.getTitleAt(tableIndex));
				
				switch(tabPanel.getTitleAt(tableIndex).toUpperCase()) {
					case "MANUEL" :
						addManuel();
						break;
					case "REVUE" :
						addRevue();
						break;
					default :
						addRoman();
						break;
				}
			}
		});
		
		jbAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SaisieLivre.this.dispose();
			}
		});
	}
	
	private void addRoman() {
		switch(((PanelRoman) SaisieLivre.this.panelRoman).checkParam()) {
			case -1 :
				new DialogNotif("Erreur ! Veuillez saisir tous les champs necessaire !");
				break;
			case -2 :
				new DialogNotif("Erreur ! Veuillez saisir un entier pour le nombre de page !");
				break;
			default :
				Livre roman = ((PanelRoman) SaisieLivre.this.panelRoman).getRoman();
				
				System.out.println(roman);
				if (bbth.addDocument(roman)) {
					new DialogNotif("Ajout avec success !");
					
				}
				SaisieLivre.this.dispose();
				break;
		}
	}
	
	private void addManuel() {
		switch(((PanelManuel) SaisieLivre.this.panelManuel).checkParam()) {
			case -1 :
				new DialogNotif("Erreur ! Veuillez saisir tous les champs necessaire !");
				break;
			case -2 :
				new DialogNotif("Erreur ! Veuillez saisir un entier pour le nombre de page !");
				break;
			default :
				Livre manuel = ((PanelManuel) SaisieLivre.this.panelManuel).getManuel();
				
				System.out.println(manuel);
				if (bbth.addDocument(manuel)) {
					new DialogNotif("Ajout avec success !");
					
				}
				SaisieLivre.this.dispose();
				break;
		}
	}
	
	private void addRevue() {
		switch(((PanelRevue) SaisieLivre.this.panelRevue).checkParam()) {
			case -1 :
				new DialogNotif("Erreur ! Veuillez saisir tous les champs necessaire !");
				break;
			case -2 :
				new DialogNotif("Erreur ! Veuillez saisir un entier le mois et l'annee !");
				break;
			default :
				Document revue = ((PanelRevue) SaisieLivre.this.panelRevue).getRevue();
				
				System.out.println(revue);
				if (bbth.addDocument(revue)) {
					new DialogNotif("Ajout avec success !");
					
				}
				SaisieLivre.this.dispose();
				break;
		}
	}
}
