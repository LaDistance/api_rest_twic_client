package com.organigramme.dao;


import static com.organigramme.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.organigramme.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.organigramme.beans.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final String SQL_SELECT        = "SELECT id, supervisor, name, surname, bureau, service, poste FROM Employees ORDER BY id";
    private static final String SQL_SELECT_PAR_ID = "SELECT id, supervisor, name, surname, bureau, service, poste FROM Employees WHERE id = ?";
    private static final String SQL_SELECT_PAR_USERNAME = "SELECT id, supervisor, name, surname, bureau, service, poste FROM Employees WHERE username = ?";
    private static final String SQL_INSERT        = "INSERT INTO Employees (id, supervisor, name, surname, password, bureau, service, poste) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Employees WHERE id = ?";
    private static final String SQL_GET_SUBORDINATES = "SELECT id, supervisor, name, surname, bureau, service, poste FROM Employees WHERE supervisor = ?";

    private DAOFactory          daoFactory;

    EmployeeDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface EmployeeDao */
    @Override
    public Employee find( int id ) throws DAOException {
        return find( SQL_SELECT_PAR_ID, id );
    }
    
    @Override
    public Employee find( String username ) throws DAOException {
        return find( SQL_SELECT_PAR_USERNAME, username);
    }
    @Override
    public Employee getSupervisor(Employee employee) throws DAOException{
    	return find(employee.getSupervisor());
    }
    @Override
    public List<Integer> getSubordinatesAmount( int id ) throws DAOException {
    	// Fonction qui va chercher le nombre d'employés sous la supervision de l'employé dont l'id est 'id'
    	
    	// {direct subordinates amount, total subordinates amount}
    	System.out.println("Testing id : " + id );
    	List<Integer> amounts = new ArrayList<Integer>();
    	List<Integer> result = getSubordinatesSql(SQL_GET_SUBORDINATES, id);
    	System.out.println("children of id " + id + " = " + result);
    	
    	amounts.add( 0);
    	amounts.add(0);
    	
    	amounts.set(0, result.size());
        amounts.set(1, amounts.get(1)+  result.size());
        
		for(Integer childId : result) {
			List<Integer> subordinatesList = getSubordinatesAmount(childId);
			System.out.println("Adding " + subordinatesList.get(1) + " children to the count of id " + id );
			amounts.set(1, amounts.get(1)+  subordinatesList.get(1));
    	}
    	//return getSubordinatesAmount(SQL_GET_SUBORDINATES, id);
		System.out.println("AMOUNTS OF ID " + id + " = " +amounts);
		return amounts;
    }
    @Override 
    public List<Employee> getSubordinates(Employee employee){
    	int id = employee.getId();
    	List<Employee> employeesList = new ArrayList<Employee>();
    	List<Integer> idList = getSubordinatesSql(SQL_GET_SUBORDINATES, id);
    	for(int childId : idList) {
    		employeesList.add(this.find(childId));
    	}
    	System.out.println("Subordinates of employee : " + employee.getName() + " " + employee.getSurname());
    	for(Employee tempEmployee : employeesList) {
    		System.out.println(" - " + tempEmployee.getName() + " " + tempEmployee.getSurname());
    	}
    	return employeesList;
    }
    // Returns [direct subordinates amount, ...subordinates IDs]
    private List<Integer> getSubordinatesSql(String sql, Object... objets) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        List<Integer> ids = new ArrayList<Integer>();
        List<Employee> employees = new ArrayList<Employee>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
             while( resultSet.next() ) {
            	employee = map( resultSet );
            	System.out.println("Adding Employee : " + employee.getName() + " " + employee.getSurname() + " with id : " + employee.getId() +  " to children of that id");
            	ids.add(employee.getId());
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return ids;
        
	}
	@Override
	public boolean exists(Employee employee) {
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	    	statement = connexion.createStatement();
	    	PreparedStatement preparedStatement = connexion.prepareStatement("SELECT COUNT(*) FROM Employees WHERE LOWER(username) = LOWER(?);");
	    	preparedStatement.setString(1, employee.getUsername());
	    	resultat = preparedStatement.executeQuery();
	    	if(resultat.next() && resultat.getInt(1)==1) {
	    		return true;
	    	}
	    	System.out.println("exists() matches with "+resultat.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean checkPwd(Employee employee) {
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    String hash = "";
	    try {
	    	connexion = daoFactory.getConnection();
	    	statement = connexion.createStatement();
	    	PreparedStatement preparedStatement = connexion.prepareStatement("SELECT username, password FROM Employees WHERE LOWER(username) = LOWER(?);");
	    	preparedStatement.setString(1, employee.getUsername());
	    	resultat = preparedStatement.executeQuery();
	    	if(resultat.next()) {
	    		return employee.getPassword().equals(resultat.getString(2));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();}
		return false;
	}
	
	/* Implémentation de la méthode définie dans l'interface EmployeeDao */
    @Override
    public void create( Employee employee ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
            		employee.getId(), employee.getPassword(),
            		employee.getName(), employee.getSurname(),
            		employee.getUsername(), employee.getBureau(),
            		employee.getPoste(), employee.getService(),
            		employee.getSupervisor());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du employee, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                employee.setId( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new DAOException( "Échec de la création du employee en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /* Implémentation de la méthode définie dans l'interface EmployeeDao */
    @Override
    public List<Employee> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> employees = new ArrayList<Employee>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                employees.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return employees;
    }
    
    public String findId(String username, String password) {
    	Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String SQL = "SELECT id FROM Employees WHERE username = " + username + "AND password = "+ password +";";
        String id = null;
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(SQL);
            id = resultat.getString("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
 
    }

    /* Implémentation de la méthode définie dans l'interface EmployeeDao */
    @Override
    public void delete( Employee employee ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, employee.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du employee, aucune ligne supprimée de la table." );
            } else {
                employee.setId(-1);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un employee depuis la base de
     * données, correspondant à la requête SQL donnée prenant en paramètres les
     * objets passés en argument.
     */
    private Employee find( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
            	employee = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return employee;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des employees (un ResultSet) et
     * un bean Employee.
     */
    private static Employee map( ResultSet resultSet ) throws SQLException {
    	Employee employee = new Employee();
    	employee.setId( resultSet.getInt( "id" ) );
    	employee.setSupervisor(resultSet.getInt("supervisor"));
    	employee.setName( resultSet.getString( "name" ) );
    	employee.setSurname( resultSet.getString( "surname" ) );
    	employee.setBureau( resultSet.getInt( "bureau" ) );
    	employee.setService( resultSet.getString( "service" ) );
    	employee.setPoste( resultSet.getString( "poste" ) );
        return employee;
    }

}