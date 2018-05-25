package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Bibliotheque implements Cloneable {

	// Liste des documents de la bibliotheque
	private List<Document> documents;

	/**
	 * Constructeur d'une bibliotheque dont la liste de documents est vide.
	 */
	public Bibliotheque() {
		// TO DO ... ( "vide" ne veut pas dire null ! )
//		throw new RuntimeException("Bibliotheque() not yet implemented"); 
		this.documents = new ArrayList<Document>();
	}
	
	/**
	 * Réaliser un clonage profond de votre bibliothèque 
	 */
	public Bibliotheque(List<Document> documents) {
		this.documents = documents;
	}
	
	/**
	 * Renvoie la liste des documents de la bibliotheque.
	 */
	public List<Document> getDocuments() {
		return documents;
	}
	
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * Renvoie le ieme document de la liste des documents, s'il existe, 
	 * ou null sinon.
	 */
	public Document getDocument(int i) {
		// TO DO ...
//		throw new RuntimeException("getDocument() not yet implemented"); 
		return this.documents.get(i);
	}
	
	/**
	 * Si doc est non null et n'appartient pas d�j� � la liste des documents,
	 * alors ajoute doc a cette liste et renvoie true ;
	 * sinon renvoie faux.
	 */
	public boolean addDocument(Document doc) {
		// TO DO ...
		if (doc!=null && !this.documents.contains(doc)) {
			this.documents.add(doc);
			return true;
		}
		return false;
	}
	
	/**
	 * suppression de documents par titre
	 */
	public boolean removeParTitre(String titre) {
		for (Document doc : this.documents) {
			if (doc.getTitre().equals(titre)) {
				this.documents.remove(doc);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * supprimer une liste de documents
	 */
	public void removeDocuments(List<Document> docs) {
		for (Document doc : docs) {
			this.removeDocument(doc);
		}
	}
	
	/**
	 * suppression de roman par type de prix littéraire (GONCOURT...)
	 */
	public boolean removeParTitre(int prixLitt) {
		for (Document doc : this.documents) {
			if (doc instanceof Roman) {
				if (((Roman)doc).getPrixLitteraire() == prixLitt) {
					this.documents.remove(doc);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * recherche de documents par titre
	 * @param titre
	 */
	public List<Document> rechercheParTitre(String titre) {
		List<Document> res = new ArrayList<Document>();
		for (Document doc : this.documents) {
			if (doc.getTitre().equals(titre)) {
				res.add(doc);
			}
		}
		return res;
	}
	
	/**
	 * recherche de roman par type de prix littéraire (GONCOURT...)
	 * @param titre
	 */
	public List<Document> rechercheParPrix(int prixLitt) {
		List<Document> res = new ArrayList<Document>();
		for (Document doc : this.documents) {
			if (doc instanceof Roman) {
				if (((Roman)doc).getPrixLitteraire() == prixLitt) {
					res.add(doc);
				}
			}
		}
		return res;
	}
	
	/**
	 * Si doc est dans la liste des documents, alors l'y supprime et renvoie true;
	 * sinon renvoie false.
	 */
	public boolean removeDocument(Document doc) {
		// TO DO ...
//		throw new RuntimeException("removeDocument() not yet implemented");
		if (this.documents.contains(doc)) {
			this.documents.remove(doc);
			return true;
		}
		return false;
	}
	 /**
	  * Opérer un tri lexicographique des documents de la bibliothèque
	  */
	public void triLexico() {
		Collections.sort(this.documents);
	}
	
	/**
	 * Réaliser un clonage profond de votre bibliothèque 
	 */
	@Override
	public Bibliotheque clone() {
		Bibliotheque bClone = new Bibliotheque();
		for (Document doc : documents) {
			bClone.documents.add(doc.clone());
		}
		return bClone;
	}

	@Override
	public String toString() {
		return "Bibliotheque de " + documents.size() + " documents\n" + this.documents.toString();
	}
}






