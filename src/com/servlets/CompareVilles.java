package com.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.util.SloppyMath;

import com.beans.Ville;
import com.client.VilleClient;

/**
 * Servlet implementation class Test
 */
@WebServlet("/CompareVilles")
public class CompareVilles extends HttpServlet {
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
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/compareVilles.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insee1 = request.getParameter("insee1");
        String insee2 = request.getParameter("insee2");
        
        Ville ville1 = villeClient.getVille(Integer.parseInt(insee1)).get(0);
        Ville ville2 = villeClient.getVille(Integer.parseInt(insee2)).get(0);
        
        double longitude1, latitude1, longitude2, latitude2;
        latitude1 = Double.parseDouble(ville1.getLatitude());
        longitude1 = Double.parseDouble(ville1.getLongitude());
        latitude2 = Double.parseDouble(ville2.getLatitude());
        longitude2 = Double.parseDouble(ville2.getLongitude());
        
        double RawDelta = Math.acos(Math.cos(latitude1)*Math.cos(latitude2)*Math.cos(longitude1)*Math.cos(longitude2) + Math.cos(latitude1)*Math.sin(longitude1)*Math.cos(latitude2)*Math.sin(longitude2) + Math.sin(latitude1)*Math.sin(latitude2));
        //double distance =  RawDelta * 6378.0;
        
        double distanceMeters = SloppyMath.haversinMeters(latitude1, longitude1, latitude2, longitude2);
        double distanceKm = distanceMeters/1000;
        DecimalFormat df = new DecimalFormat("#.##");
        String distance = df.format(distanceKm);
        request.setAttribute("ville1", ville1);
        request.setAttribute("ville2", ville2);
        request.setAttribute("distance", distance);
        System.out.println("La distance entre la ville "+ ville1.getNom() + " et la ville "+ ville2.getNom() + " est : "+ distance + "km.");
        doGet(request, response);
	}
    
}
