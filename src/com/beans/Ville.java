package com.beans;

import java.io.Serializable;
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Ville implements Serializable {

	public Ville(String nom) {
		super();
		this.nom = nom;
	}
	public Ville() {
		super();
	}
	public Ville(int codeInsee, String nom, int codePostal, String libelleAcheminement, String latitude,
			String longitude) {
		super();
		this.codeInsee = codeInsee;
		this.nom = nom;
		this.codePostal = codePostal;
		this.libelleAcheminement = libelleAcheminement;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * Serial IUD for serialization.
	 */
	private static final long serialVersionUID = -8050478362033217382L;

	int codeInsee;
	String nom;
	int codePostal;
	String libelleAcheminement;
	String latitude;
	String longitude;
	public int getCodeInsee() {
		return codeInsee;
	}
	public void setCodeInsee(int codeInsee) {
		this.codeInsee = codeInsee;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}
	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Ville [codeInsee=" + codeInsee + ", nom=" + nom + ", codePostal=" + codePostal
				+ ", libelleAcheminement=" + libelleAcheminement + ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codeInsee;
		result = prime * result + codePostal;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((libelleAcheminement == null) ? 0 : libelleAcheminement.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (codeInsee != other.codeInsee)
			return false;
		if (codePostal != other.codePostal)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (libelleAcheminement == null) {
			if (other.libelleAcheminement != null)
				return false;
		} else if (!libelleAcheminement.equals(other.libelleAcheminement))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
