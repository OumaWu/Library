package view;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import model.*;

/**
 * Utilitaires d'affichage
 */
public class Affichage {

	private Bibliotheque bibliotheque;
	public Afficheur afficheur;
	
	public Affichage(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
		this.afficheur = new Afficheur(bibliotheque);
	}
	
	public Bibliotheque getBibliotheque() {
		return bibliotheque;
	}


	// TO DO ...
	public void afficherDocuments(Collection<Document> docs) {
		System.out.println(docs);
	}
	
	public void afficherAuteurs(Collection<Document> docs) {
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
			System.out.println(auteur + "\n");
		}
	}
}















