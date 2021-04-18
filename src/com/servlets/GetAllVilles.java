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
@WebServlet("/GetAllVilles")
public class GetAllVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VilleClient villeClient;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException {
    	this.villeClient = new VilleClient();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Ville> listeVilles = villeClient.getAll();
		
		request.setAttribute("villes", listeVilles);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/listeVilles.jsp" ).forward( request, response );
	}
    
}
