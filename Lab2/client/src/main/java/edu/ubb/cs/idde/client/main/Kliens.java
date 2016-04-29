package edu.ubb.cs.idde.client.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import edu.ubb.cs.idde.server.main.*;
import edu.ubb.cs.idde.server.pojo.States;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

public class Kliens<T> extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Class<T> persistentClass;
	JTable table;
    JPanel panel;
    JButton button;

    public Kliens(final Class<T> persistentClass) {
    	this.persistentClass = persistentClass;
    	
    	panel = new JPanel();
    	button = new JButton("Get " + this.persistentClass.getSimpleName());
        setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(1024,768));
        button.addActionListener(this);
        add(panel,BorderLayout.CENTER);
        add(button, BorderLayout.NORTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        panel.removeAll();
        
        Locale currentLocale = new Locale("en");
		ResourceBundle messages;
		messages = ResourceBundle.getBundle("ApplicationResources",currentLocale);
		GenericDao<T> dao2 = null;
		// det data with JDBC
//		dao2 = new JdbcDao<T>(persistentClass);
        // get data with HIBERNATE
        dao2 = new HibernateDao<T>(persistentClass);
        
        List<T> l2 = dao2.getAllDataRows();
    	Iterator<T> i2 = l2.listIterator();
    	
    	Vector<String> columnNames = new Vector<String>();
    	for (Field field : l2.get(0).getClass().getDeclaredFields()) {
			columnNames.add(messages.getString(field.getName()));
		}
    	
    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		while( i2.hasNext())
    	{
    		T c = i2.next();
    		Vector<Object> row = new Vector<Object>();
    		for (Field field : c.getClass().getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor;
				try {
					propertyDescriptor = new PropertyDescriptor(field.getName(), c.getClass());
    				Method method = propertyDescriptor.getReadMethod();
    				
    				row.add(method.invoke(c));
//    				System.out.println(messages.getString(field.getName())+" = "+ method.invoke(c));

				} catch (IntrospectionException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
    		data.add(row);
    	}
        
        JTable table = new JTable(data,columnNames);
        panel.add(new JScrollPane(table));
        repaint();
        revalidate();
    }

    public static void main(String [] a){
        Kliens<States> s = new Kliens<States>(States.class);
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        s.pack();
        s.setVisible(true);
        
    }
}