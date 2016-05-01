package edu.ubb.cs.idde.server.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateDao<T> implements GenericDao<T> {
	
	protected Class<T> persistentClass;
	
	public HibernateDao(final Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllDataRows() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	
	    ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    SessionFactory sf = configuration.buildSessionFactory(sr);
	    Session ss=sf.openSession();
	    Transaction t = ss.beginTransaction();
	    Query query = ss.createQuery("from " + persistentClass.getSimpleName().toLowerCase());
	    List<T> list = query.list();
        t.commit();
        ss.close();
        
	    return list;
	}
	
	@Override
	public void insertObjects(List<T> objects) {
		Configuration configuration = new Configuration();
	    configuration.configure();
	
	    ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    SessionFactory sf = configuration.buildSessionFactory(sr);
		Session ss=sf.openSession();
		ss.beginTransaction();
		
		Iterator<T> i2 = objects.listIterator();
		
		while( i2.hasNext()) {
			T c = i2.next();
			ss.save(c);
    	}
		ss.getTransaction().commit();  
	    ss.close();
		
	}
	@Override
	public void updateObject(T obj) {
	
	}
	@Override
	public void deleteObject(T obj) {
		
	}  
    
}
