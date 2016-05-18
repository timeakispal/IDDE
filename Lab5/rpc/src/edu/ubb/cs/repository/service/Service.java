package edu.ubb.cs.repository.service;

import java.util.List;

import edu.ubb.cs.model.States;
//import edu.ubb.cs.repository.GenericDao;
import edu.ubb.cs.repository.jdbc.JdbcDao;

public class Service {
	JdbcDao<States> pdo;
	
	public Service() {
		
		pdo = new JdbcDao<States>(States.class);
	}

	public void insert(List<States> states) {
		pdo.insertObjects(states);
	}

	public void update(States state) {
		pdo.updateObject(state);
	}

	public void delete(States state) {
		pdo.deleteObject(state);
	}

	public List<States> getAllStates() {
		
		return pdo.getAllDataRows();
	}
}
