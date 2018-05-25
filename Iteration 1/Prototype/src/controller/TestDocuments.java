package controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Bibliotheque;
import model.Document;
import model.Manuel;
import model.Revue;
import model.Roman;
import view.Affichage;

public class TestDocuments {

	public static final String fichier = "document.csv";
	
	/**
	 * Programme de test.
	 */
	public static void main(String[] args) {
	
		Document[] documents = {
			new Roman("L'archipel du Goulag", "Soljenitsyne", 250, 0),
			new Roman("Rouge Brésil", "Rufin", 120, Roman.GONCOURT),
			new Revue("Le point", 03, 2014),
			new Roman("Le mendiant", "Wiesel", 150, Roman.MEDICIS),
			new Roman("La condition humaine", "Malraux", 130, 0),
			new Manuel("Manuel qualité ISO 9001", "AFNOR", 50, 3)
		};
	
//		Affichage affichage = new Affichage();
//		affichage.afficherDocuments(Arrays.asList(documents));
//		affichage.afficherAuteurs(Arrays.asList(documents));
		
		List<Document> docs = new ArrayList<Document>(Arrays.asList(documents));
		
		// Ecrire les documents dans le fichier csv
//		try {
//			ReadDocument.write(fichier, docs);
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		// Lire les documents a partir du fichier csv
//		docs = ReadDocument.read(fichier);
		Bibliotheque b = new Bibliotheque(docs);
		
		Affichage aff = new Affichage(b);
		aff.afficherDocuments(aff.getBibliotheque().getDocuments());
		aff.afficherAuteurs(aff.getBibliotheque().getDocuments());
		
	}

}
