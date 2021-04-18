package com.organigramme.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organigramme.beans.Employee;
import com.organigramme.dao.DAOFactory;
import com.organigramme.dao.EmployeeDao;

/**
 * Servlet implementation class Test
 */
@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	this.employeeDao = daoFactory.getEmployeeDao();
    }
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employees = this.employeeDao.getAll();
		List<Employee> subordinates = new ArrayList<Employee>();
		List<Object> subRelationship = new ArrayList<Object>();
		List<List> relationships = new ArrayList<List>();
		
		for(Employee employee : employees) {
			System.out.println("");
			System.out.println("Currently treating employee : " + employee.getName() + " " + employee.getSurname());
			subRelationship.add(employee);
			
			subordinates = this.employeeDao.getSubordinates(employee);
			if(!subordinates.isEmpty()) {
				subRelationship.add(subordinates);
				// request.setAttribute("sub" + i, subRelationship);
				relationships.add(subRelationship);
			}
			subRelationship = new ArrayList<Object>();
		}
		request.setAttribute("relationships", relationships);
		Enumeration<String> attributes = request.getAttributeNames();
		while(attributes.hasMoreElements()) {
			String attributeName = attributes.nextElement();
			System.out.println("Attributes name : " + attributeName + ", Value : " + request.getAttribute(attributeName));
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/homepage.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
