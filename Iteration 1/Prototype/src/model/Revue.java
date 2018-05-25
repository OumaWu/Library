package model;

// TO DO ...
public class Revue extends Document {

	private int mois;
	private int annee;
	
	public Revue(String titre, int mois, int annee) {
		super(titre);
		this.mois = mois;
		this.annee = annee;
	}
	
	public Revue(int numEnreg, String titre, int mois, int annee) {
		super(numEnreg, titre);
		this.mois = mois;
		this.annee = annee;
	}

	/**
	 * @return the mois
	 */
	public int getMois() {
		return mois;
	}

	/**
	 * @param mois the mois to set
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}

	/**
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNumEnreg() + " - \"" + this.getTitre() + "\""
				+ ", " + mois + ", " + annee + ".\n";
	}

	@Override
	public Document clone() {
		return new Revue(getTitre(), mois, annee);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + annee;
		result = prime * result + mois;
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
		Revue other = (Revue) obj;
		if (annee != other.annee)
			return false;
		if (mois != other.mois)
			return false;
		return true;
	}
}
