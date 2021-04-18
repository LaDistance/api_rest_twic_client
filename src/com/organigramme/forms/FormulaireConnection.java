package com.organigramme.forms;

import javax.servlet.http.HttpServletRequest;

import com.organigramme.beans.Employee;
import com.organigramme.dao.DAOException;
import com.organigramme.dao.DAOFactory;
import com.organigramme.dao.EmployeeDao;

public class FormulaireConnection {
	
	private Employee connectedUser;
	private String status;
	private EmployeeDao employeeDao;
	
	public FormulaireConnection(HttpServletRequest r) throws DAOException {
		String username = r.getParameter("username").toString();
		String password = r.getParameter("password").toString();
		
		Employee employee = new Employee(username,password);
		
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	this.employeeDao = daoFactory.getEmployeeDao();
		// has this user?
		if(this.employeeDao.exists(employee)) {
			// is it the right password?
			if(this.employeeDao.checkPwd(employee)) {
				this.connectedUser = this.employeeDao.find(employee.getUsername());
				status = "good";
			} else status = "wrongPwd";
		} else status = "notFound";
	}

	public Employee getConnectedUser() {
		return this.connectedUser;
	}
	
	public String getStatus() {
		return this.status;
	}
}
