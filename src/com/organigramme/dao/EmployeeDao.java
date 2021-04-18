package com.organigramme.dao;

import com.organigramme.beans.Employee;
import java.util.List;

public interface EmployeeDao {
	void create( Employee employee ) throws DAOException;
	
	List<Integer> getSubordinatesAmount( int id ) throws DAOException;
	
	List<Employee> getSubordinates(Employee employee) throws DAOException;
	Employee getSupervisor(Employee employee) throws DAOException;
	Employee find( int id ) throws DAOException;
	Employee find( String username) throws DAOException;
	boolean exists(Employee employee) throws DAOException;
	
	boolean checkPwd(Employee employee);
	
    List<Employee> getAll() throws DAOException;

    void delete( Employee employee ) throws DAOException;
    
    String findId(String username, String password) throws DAOException;
}
