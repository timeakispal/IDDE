package edu.ubb.cs.idde.server.main;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ubb.cs.idde.server.util.PropertyLoader;


public class GenericJdbcDao<T> implements GenericDao<T> {

	private Class<T> persistentClass;
	private String connectionString;
	
	public GenericJdbcDao(final Class<T> persistentClass){
		this.persistentClass = persistentClass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Handle this exception
			e.printStackTrace();
		};
		connectionString = PropertyLoader.getProperty("url");
			// TODO Change to proper connection string 
  
	}
	public List<T> getAllDataRows() {
		List<T> results ;
		ResultSet r;
        String user,password;
        user = PropertyLoader.getProperty("username");
        password = PropertyLoader.getProperty("password");
		try{
        	Connection connect = DriverManager.getConnection(connectionString,user,password);
        	Statement statement = connect.createStatement();
			r = statement.executeQuery("SELECT * FROM "+persistentClass.getSimpleName());
			results= createObjects(r);
			connect.close();
			
			return results;
        	
		} 
		catch ( Exception e  ) { 
			e.printStackTrace();
		};
		return null;
	}
	
	protected String getColumns() {
	
		StringBuilder columns = new StringBuilder();
		Field[] fieldContainer = persistentClass.getDeclaredFields();
		for ( int i=0;i<fieldContainer.length; i++){
			if ( i> 0) {
				columns.append(",");
			}
			columns.append(fieldContainer[i].getName());
		};
		return columns.toString();
	
	}
	
	private List<T> createObjects(ResultSet resultSet) throws InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException
	{

		List<T> list = new ArrayList<T>();
		while (resultSet.next()) {

			T instance = persistentClass.newInstance();
			for (Field field : persistentClass.getDeclaredFields()) {

				Object value = resultSet.getObject(field.getName());
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), persistentClass);
				Method method = propertyDescriptor.getWriteMethod();
				Class<?> type = method.getParameterTypes()[0];
				
				if ( type == int.class ) {
					method.invoke(instance, Integer.parseInt(value.toString()));
					
				} else {
					method.invoke(instance,value );
				}
			}
			list.add(instance);
		}
		return list;
	}
	public void insertObjects(List<T> objects) {
		// TODO Implement this method using java reflection api
		
	}
	public void updateObject(T obj) {
		// TODO Implement this method using java reflection api
		
	}
	public void deleteObject(T obj) {
		// TODO Implement this method using java reflection api
		
	}
}
