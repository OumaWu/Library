package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Bibliotheque;
import model.Document;
import model.Livre;
import model.ReadDocument;
import model.Roman;

public class Afficheur extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6737888159021371280L;
	private Bibliotheque bibliotheque;
	private List<Document> documentsRecherches;	// Liste des documents recherches par l'utilisateur
	private JLabel jlb, jlb2;
	private JTextField jtf;
	private JComboBox<String> jcb, jcb2;
	private JTextArea jta;
	private JScrollPane sjp;
	private JPanel top, bottom;
	private JButton affDoc, affAut, afficher, addDoc, triLexico, recherche, supprimer;
	private JMenuBar jmb;
	private JMenu jm1;
	private JMenuItem jmi1, jmi2;
	
	public Afficheur(Bibliotheque bibliotheque) throws HeadlessException {
		super();
		this.bibliotheque = bibliotheque;
		this.documentsRecherches = null;
		initComponents();
		setListeners();
		initSettings();
	}
	
	public void initComponents() {
		this.jmb = new JMenuBar();
		this.jm1 = new JMenu("Fichier");
		this.jmi1 = new JMenuItem("Importer");
		this.jmi2 = new JMenuItem("Exporter");
		this.jm1.add(jmi1);
		this.jm1.add(jmi2);
		this.jmb.add(jm1);
		this.jta = new JTextArea();
		this.sjp = new JScrollPane(jta);
		this.top = new JPanel();
		this.bottom = new JPanel();
		this.jcb2 = new JComboBox<>();
		this.jcb2.setModel(new DefaultComboBoxModel<>(new String[] {
				"Livres",
				"Auteurs",
		}));
		this.afficher = new JButton("Afficher");
		this.affDoc = new JButton("Afficher Livres");
		this.affAut = new JButton("Afficher Auteurs");
		this.triLexico = new JButton("Tri Lexicographique");
		this.addDoc = new JButton("Ajouter");
		this.recherche = new JButton("Search");
		this.jtf = new JTextField(20);
		this.jcb = new JComboBox<>();
		this.jcb.setModel(new DefaultComboBoxModel<>(new String[] {
				"titre",
				"prix littéraire",
		}));
//		this.titre = new JRadioButton("par titre");
//		this.prixLitt = new JRadioButton("par prix littéraire");
		this.jlb = new JLabel("Recheche  :");
		this.jlb2 = new JLabel("Par");
		this.supprimer = new JButton("Supprimer");
		
		this.top.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		this.top.add(jlb);
		this.top.add(jtf);
		this.top.add(recherche);
		this.top.add(Box.createHorizontalStrut(10));
		this.top.add(jlb2);
		this.top.add(jcb);
		
		this.bottom.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		this.bottom.add(jcb2);
		this.bottom.add(afficher);
//		this.bottom.add(affDoc);
//		this.bottom.add(affAut);
		this.bottom.add(Box.createHorizontalStrut(15));
		this.bottom.add(triLexico);
		this.bottom.add(Box.createHorizontalStrut(15));
		this.bottom.add(supprimer);
		this.bottom.add(addDoc);
		this.setJMenuBar(this.jmb);
		this.add(top, BorderLayout.NORTH);
		this.add(sjp, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
	}
	
	public void initSettings() {
		this.setTitle("Afficheur bibliotheque");
		this.setLocation(400,250);
		this.setSize(600, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setListeners() {
		
		this.supprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ces documents recherchés ?","Supprimer documents", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					Afficheur.this.bibliotheque.removeDocuments(documentsRecherches);
					new DialogNotif("Suppression avec success !");
				}
			}
		});
		
		// Importer un fichier CSV a partir du repertoire
		this.jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv","csv");
				jfc.setFileFilter(filter);
				jfc.setCurrentDirectory(new File("."));
				jfc.setDialogTitle("Veuillez choisir le format CSV");
				int result = jfc.showOpenDialog(Afficheur.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						Afficheur.this.bibliotheque.setDocuments(ReadDocument.read(jfc.getSelectedFile().getName()));
						new DialogNotif("Importer avec success !");
					} catch (Exception e2) {
						new DialogNotif("Erreur, importation a echoue !");
					}
				}
			}
		});
		
		// Exporter la bibliotheque dans du repertoire
		this.jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv","csv");
				jfc.setFileFilter(filter);
				jfc.setCurrentDirectory(new File("."));
				jfc.setDialogTitle("Exporter la bibliotheque en format CSV");
				int result = jfc.showSaveDialog(Afficheur.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						/** 
						 * pour que le FileChooser accepte un nom de fichier
						 * avec/sans l'extension .csv
						 */
						String filename = jfc.getSelectedFile().getName().trim();
						if (!filename.endsWith(".csv")) {
							filename += ".csv";
						}
						ReadDocument.write(filename, Afficheur.this.bibliotheque.getDocuments());
						new DialogNotif("Exporter avec success !");
					} catch (Exception e2) {
						new DialogNotif("Erreur, exportation a echoue !");
					}
				}
			}
		});
		
		recherche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (jcb.getSelectedItem().toString().toUpperCase()) {
					case "TITRE" :
						documentsRecherches = bibliotheque.rechercheParTitre(jtf.getText());
						break;
					default :
						int prixLitt = 0;
						switch (jtf.getText().trim().toUpperCase()) {
							case "GONCOURT" :
								prixLitt = Roman.GONCOURT;
								break;
							case "MEDICIS" :
								prixLitt = Roman.MEDICIS;
								break;
							case "INTERALLIE" :
								prixLitt = Roman.INTERALLIE;
								break;
							case "AUCUN" :
								prixLitt = 0;
								break;
							default:
								new DialogNotif("Veuillez saissir un prix valide(Goncourt, Medicis, Interallie, Aucun)");
								break;
						}
						documentsRecherches = bibliotheque.rechercheParPrix(prixLitt);
						break;
				};
				Afficheur.this.afficherDocuments(documentsRecherches);
			}
		});
		
		afficher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (jcb2.getSelectedItem().toString().trim().toUpperCase()) {
					case "LIVRES" :
						Afficheur.this.afficherDocuments(bibliotheque.getDocuments());
						break;
					case "AUTEURS" :
						Afficheur.this.afficherAuteurs(bibliotheque.getDocuments());
						break;
					default :
						break;
				}
			}
		});
		
//		//Action pour le bouton afficherDocument
//		affDoc.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Afficheur.this.afficherDocuments(bibliotheque.getDocuments());
//			}
//		});
//		
//		//Action pour le bouton afficherAuteur
//		affAut.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Afficheur.this.afficherAuteurs(bibliotheque.getDocuments());
//			}
//		});
		
		//Action pour le bouton triLexicographique
		triLexico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Afficheur.this.bibliotheque.triLexico();
				Afficheur.this.afficherDocuments(bibliotheque.getDocuments());
			}
		});
		
		//Action pour le bouton addDocument
		addDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaisieLivre(bibliotheque);
			}
		});
	}
	
	public void afficherDocuments(Collection<Document> docs) {
		jta.append("LIVRES:\n\n");
		for (Document doc : docs) {
			jta.append(doc.toString());
		}
		jta.append("\n");
		this.setVisible(true);
	}
	
	public void afficherAuteurs(Collection<Document> docs) {
		jta.append("AUTEURS:\n\n");
		List<Livre> avecAuteurs = new ArrayList<Livre>();
		for (Document doc : docs) {
			if (doc instanceof Livre) {
				avecAuteurs.add((Livre) doc);
			}
		}
		
		Collections.sort(avecAuteurs, Livre.TRI_AUTEUR);
		List<String> auteurs = new ArrayList<String>();
		
		for (Document doc : avecAuteurs) {
			if (!auteurs.contains(((Livre) doc).getAuteur())) {
				auteurs.add(((Livre) doc).getAuteur());
			}
		}
		
		for (String auteur : auteurs) {
			jta.append(auteur + "\n");
		}
		jta.append("\n");
		this.setVisible(true);
	}
}
