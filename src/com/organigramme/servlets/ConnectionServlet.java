package com.organigramme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.organigramme.beans.Employee;
import com.organigramme.dao.DAOException;
import com.organigramme.dao.DAOFactory;
import com.organigramme.dao.EmployeeDao;
import com.organigramme.forms.FormulaireConnection;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/ConnectionServlet")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 10L;
    private EmployeeDao employeeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	this.employeeDao = daoFactory.getEmployeeDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Session
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		
		request.setAttribute("name", name);
		request.setAttribute("surname", surname);
		System.out.println("User connected : " + request.getAttribute("user"));
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    request.setAttribute("cPrenom", cookie.getValue().split(",")[0]);
                    request.setAttribute("cNom", cookie.getValue().split(",")[1]);
                }
            }
        }
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/connection.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        System.out.println("session : " + session.toString());
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        Employee employee = employeeDao.find(username);
        
        // this.getServletContext().getRequestDispatcher( "/WEB-INF/homepage.jsp" ).forward( request, response );
        System.out.println("Connect : " + request.getParameter("connect"));
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
    //  IF CONNECTION FORM
    			if(request.getParameter("connect") != null) {
    			 	FormulaireConnection form = null;
    				try {
    					form = new FormulaireConnection(request);
    				} catch (DAOException e) {
    					request.setAttribute("errorMsg", e.getMessage());
    					e.printStackTrace();
    				}
    			 	// if connection went well
    			 	if(form.getStatus().equals("good")) {
    			 		System.out.println("Form status is good");
    			 		Employee uConnected = form.getConnectedUser();
    			        session.setAttribute("name", uConnected.getName());
    			        session.setAttribute("surname", uConnected.getSurname());
    			        if(request.getParameter("remember") != null) {
    			        	Cookie cookie = new Cookie("login",uConnected.getName()+","+uConnected.getSurname());
    			        	cookie.setMaxAge(3600*24*30*3);
    			        	response.addCookie(cookie);
    			        }
    			        request.setAttribute("form", form);
    			        request.setAttribute("user", uConnected);
    			 	} else {
    			 		request.setAttribute("errorMsg", form.getStatus());
    			 	}
    			} else {
    				// IF DISCONECTION FORM
    				if(request.getParameter("disconnect") != null) {
    					if(session.getAttribute("name") != null) {
    						session.invalidate();
    					}
    				} else {
    					System.out.println("Problème avec le paramètre connect de la requête : êtes-vous sûr d'être arrivé ici comme il le fallait ?");
    				}
    			}
    	        
    		 	this.doGet(request, response);
    		 	// this.getServletContext().getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);
	}

}
