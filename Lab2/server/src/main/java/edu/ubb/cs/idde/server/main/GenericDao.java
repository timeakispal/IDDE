package edu.ubb.cs.idde.server.main;
import java.util.List;

public interface GenericDao<T> {
	
	List<T> getAllDataRows();
	void insertObjects(List<T> objects );
	void updateObject( T obj );
	void deleteObject( T obj );
	
}
