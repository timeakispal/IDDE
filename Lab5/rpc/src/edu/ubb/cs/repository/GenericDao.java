package edu.ubb.cs.repository;
import java.util.List;

public interface GenericDao<T> {
	
	List<T> getAllDataRows() throws DatabaseException;;
	void insertObjects(List<T> objects ) throws DatabaseException;;
	void updateObject( T obj ) throws DatabaseException;;
	void deleteObject( T obj ) throws DatabaseException;;
	
}
