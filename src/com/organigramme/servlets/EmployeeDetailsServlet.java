package com.organigramme.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.organigramme.beans.Employee;
import com.organigramme.dao.DAOFactory;
import com.organigramme.dao.EmployeeDao;

/**
 * Servlet implementation class EmployeeDetails
 */
@WebServlet("/EmployeeDetails")
public class EmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	this.employeeDao = daoFactory.getEmployeeDao();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Employee employee = this.employeeDao.find(Integer.parseInt(id));
		request.setAttribute("employee", employee);
		List<Integer> amounts = this.employeeDao.getSubordinatesAmount(Integer.parseInt(id));
		request.setAttribute("directChildren", amounts.get(0));
		request.setAttribute("totalChildren", amounts.get(1));
		request.setAttribute("supervisor", this.employeeDao.getSupervisor(employee));
		Enumeration<String> attributes = request.getAttributeNames();
		while(attributes.hasMoreElements()) {
			String attributeName = attributes.nextElement();
			System.out.println("Attributes name : " + attributeName + ", Value : " + request.getAttribute(attributeName));
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/employee-details.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
