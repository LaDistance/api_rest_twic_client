package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Ville;
import com.client.VilleClient;

/**
 * Servlet implementation class Test
 */
@WebServlet("/GetVilleByParameter")
public class GetVilleByParameter extends HttpServlet {
	private static final long serialVersionUID = 4L;
	private VilleClient villeClient;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException {
    	this.villeClient = new VilleClient();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Ville> listeVilles = villeClient.getVille(Integer.parseInt(request.getParameter("insee")));
		
		if(listeVilles.size() > 0) {
			request.setAttribute("ville", listeVilles.get(0));
		}
		else {
			request.setAttribute("erreur", "Erreur lors du chargement de la ville.");
		} 
		this.getServletContext().getRequestDispatcher( "/WEB-INF/ville_details.jsp" ).forward( request, response );
	}
    
}
