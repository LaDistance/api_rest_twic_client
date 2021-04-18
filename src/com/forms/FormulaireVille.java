package com.forms;

import javax.servlet.http.HttpServletRequest;

import com.beans.Ville;

public class FormulaireVille {
	
	private Ville desiredVille;
	private String status;
	
	public FormulaireVille(HttpServletRequest r) {
		String codeInsee = r.getParameter("codeInsee").toString();
		String nom = r.getParameter("nom").toString();
		String codePostal = r.getParameter("codePostal").toString();
		String libelle = r.getParameter("libelle").toString();
		String longitude = r.getParameter("longitude").toString();
		String latitude = r.getParameter("latitude").toString();
		
		Ville ville = new Ville(Integer.parseInt(codeInsee), nom, Integer.parseInt(codePostal), libelle, longitude, latitude);
		
		this.desiredVille = ville;
	}

	public Ville getDesiredVille() {
		return this.desiredVille;
	}
	
	public String getStatus() {
		return this.status;
	}
}
