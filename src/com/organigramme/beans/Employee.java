package com.organigramme.beans;

public class Employee {
    /* Propriétés du bean */
    private int id;
    private int supervisor;
    private String name;
    private String surname;
    private String username;
    private String password;
    private int bureau;
    private String service;
    private String poste;
    
    public Employee() {
    	
    }
    public Employee(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBureau() {
		return bureau;
	}
	public void setBureau(int bureau) {
		this.bureau = bureau;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}

}