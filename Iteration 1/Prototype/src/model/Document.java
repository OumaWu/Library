package model;

// TO DO ...
public abstract class Document implements Comparable<Document>, Cloneable {
	
	private static int compteur = 1;
	private int numEnreg;
	private String titre;
	
	public Document(String titre) {
		this.titre = titre;
		setNumEnreg(compteur++);
	}
	
	public Document(int numEnreg, String titre) {
		this.titre = titre;
		setNumEnreg(numEnreg);
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNumEnreg() {
		return numEnreg;
	}

	public void setNumEnreg(int numEnreg) {
		this.numEnreg = numEnreg;
	}
	
	@Override
	public int compareTo(Document doc) {
		return this.titre.compareTo(doc.titre);
	}
	
	@Override
	public Document clone() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numEnreg;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (numEnreg != other.numEnreg)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
}
