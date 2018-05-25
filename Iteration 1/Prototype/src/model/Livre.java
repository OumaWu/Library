package model;

import java.util.Comparator;

// TO DO ...
public class Livre extends Document implements InterfaceAuteur {

	private String auteur;
	private int nbPages;
	
	/**
	 * Comparateur pour trier les livres selon leurs auteurs
	 */
	public static final Comparator<Livre> TRI_AUTEUR = new Comparator<Livre>() {

		@Override
		public int compare(Livre l1, Livre l2) {
			return l1.getAuteur().compareTo(l2.getAuteur());
		}
		
	};

	public Livre(String titre, String auteur, int nbPages) {
		super(titre);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	public Livre(int numEnreg, String titre, String auteur, int nbPages) {
		super(numEnreg, titre);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	

	@Override
	public String getAuteur() {
		return auteur;
	}
	
	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	/**
	 * @return the nbPages
	 */
	public int getNbPages() {
		return nbPages;
	}

	/**
	 * @param nbPages the nbPages to set
	 */
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNumEnreg() + " - \"" + this.getTitre() + "\""
				+ ", \"" + auteur + "\", " + nbPages + " p\n";
	}
	
	@Override
	public int compareTo(Document doc) {
		if (!this.getTitre().equals(doc.getTitre()))
			return this.getTitre().compareTo(doc.getTitre());
		return this.getAuteur().compareTo(((Livre)doc).getAuteur());
	}
	
	@Override
	public Document clone() {
		return new Livre(getTitre(), auteur, nbPages);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + nbPages;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.equals(other.auteur))
			return false;
		if (nbPages != other.nbPages)
			return false;
		return true;
	}	
}
