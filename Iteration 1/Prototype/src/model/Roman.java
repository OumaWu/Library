package model;

// TO DO ...
public class Roman extends Livre implements InterfaceAuteur {

	public static final int GONCOURT = 1;
	public static final int MEDICIS = 2;
	public static final int INTERALLIE = 3;
	private int prixLitteraire;
	
	public Roman(String titre, String auteur, int nbPages, int prixLitt) {
		super(titre, auteur, nbPages);
		this.prixLitteraire = prixLitt;
	}
	
	public Roman(int numEnreg, String titre, String auteur, int nbPages, int prixLitt) {
		super(numEnreg, titre, auteur, nbPages);
		this.prixLitteraire = prixLitt;
	}

	/**
	 * @return the prixLitteraire
	 */
	public int getPrixLitteraire() {
		return this.prixLitteraire;
	}
	
	public String getNomPrixLitteraire() {
		String prix = "";
		switch (prixLitteraire) {
			case 0 :
				prix = "aucun prix";
				break;
			case 1 :
				prix = "prix GONCOURT";
				break;
			case 2 :
				prix = "prix MEDICIS";
				break;
			default :
				prix = "prix INTERALLIE";
				break;
		}
		return prix;
	}

	/**
	 * @param prixLitteraire the prixLitteraire to set
	 */
	public void setPrixLitteraire(int prixLitteraire) {
		this.prixLitteraire = prixLitteraire;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNumEnreg() + " - \"" + this.getTitre() + "\""
				+ ", \"" + getAuteur() + "\", " + getNbPages() + " p, "
				+ getNomPrixLitteraire() + "\n";
	}
	
	@Override
	public Document clone() {
		return new Roman(getTitre(), getAuteur(), getNbPages(), prixLitteraire);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + prixLitteraire;
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
		Roman other = (Roman) obj;
		if (prixLitteraire != other.prixLitteraire)
			return false;
		return true;
	}
}
