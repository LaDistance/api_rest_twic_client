package com.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.util.SloppyMath;

import com.beans.Ville;
import com.client.VilleClient;
import com.forms.FormulaireVille;

/**
 * Servlet implementation class Test
 */
@WebServlet("/PutVille")
public class PutVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VilleClient villeClient;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException {
    	this.villeClient = new VilleClient();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Ville ville = villeClient.getVille(Integer.parseInt(request.getParameter("codeInsee"))).get(0);
		
		/* 
		request.setAttribute("nom", ville.getNom());
		request.setAttribute("codePostal", ville.getCodePostal());
		request.setAttribute("libelle", ville.getLibelleAcheminement());
		request.setAttribute("longitude", ville.getLongitude());
		request.setAttribute("latitude", ville.getLatitude());
		*/
		request.setAttribute("ville", ville);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/ville_details.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("send : " + request.getParameter("send"));
        Enumeration<String> parameterNames = request.getParameterNames();
        
        while(parameterNames.hasMoreElements()) {
        	String paramName = parameterNames.nextElement();
        	System.out.println("Param name : " + paramName);
        	
        	String[] paramValues = request.getParameterValues(paramName);
        	for(int i = 0; i < paramValues.length; i++) {
        		String paramValue = paramValues[i];
        		System.out.println("Param value : " + paramValue);
        	}
        }
    //  IF PUT FORM
    			if(request.getParameter("send") != null) {
    			 	FormulaireVille form = null;
    				try {
    					form = new FormulaireVille(request);
    				} catch (RuntimeException e) {
    					System.out.println("Runtime exception when trying the put method : "+ e);
    					request.setAttribute("errorMsg", e.getMessage());
    					e.printStackTrace();
    				}
    			 	// if connection went well
			 		System.out.println("Form status is good");
			 		Ville ville = form.getDesiredVille();
			 		villeClient.putVille(ville, ville.getCodeInsee());
    			} else {
    					System.out.println("Problème avec le paramètre send de la requête : êtes-vous sûr d'être arrivé ici comme il le fallait ?");
    				}
    	        
    		 	this.doGet(request, response);
    		 	// this.getServletContext().getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);
	}
    
}
