package model;

// TO DO ...
public class Manuel extends Livre implements InterfaceAuteur {

	private int niveau;

	public Manuel(String titre, String auteur, int nbPages, int niveau) {
		super(titre, auteur, nbPages);
		this.niveau = niveau;
	}
	
	public Manuel(int numEnreg, String titre, String auteur, int nbPages, int niveau) {
		super(numEnreg, titre, auteur, nbPages);
		this.niveau = niveau;
	}

	/**
	 * @return the niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNumEnreg() + " - \"" + this.getTitre() + "\""
				+ ", \"" + getAuteur() + "\", " + getNbPages() + " p, niveau " + this.niveau + "\n";
	}
	
	@Override
	public Document clone() {
		return new Manuel(getTitre(), getAuteur(), getNbPages(), niveau);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + niveau;
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
		Manuel other = (Manuel) obj;
		if (niveau != other.niveau)
			return false;
		return true;
	}
}
