package edu.ubb.cs.idde.client.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import edu.ubb.cs.idde.server.main.GenericDao;
import edu.ubb.cs.idde.server.main.GenericJdbcDao;
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

public class Kliens extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
    JPanel panel = new JPanel();
    JButton button = new JButton("Get States");

    public Kliens(){
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
        
        Locale currentLocale = new Locale("hu");
		ResourceBundle messages;
		messages = ResourceBundle.getBundle("ApplicationResources",currentLocale);
        GenericDao<States> dao2 = new GenericJdbcDao<States>(States.class);
        List<States> l2 = dao2.getAllDataRows();
    	Iterator<States> i2 = l2.listIterator();
    	
    	Vector<String> columnNames = new Vector<String>();
    	for (Field field : l2.get(0).getClass().getDeclaredFields()) {
			columnNames.add(messages.getString(field.getName()));
		}
    	
    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		while( i2.hasNext())
    	{
    		States c = i2.next();
    		Vector<Object> row = new Vector<Object>();
    		for (Field field : c.getClass().getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor;
				try {
					propertyDescriptor = new PropertyDescriptor(field.getName(), c.getClass());
    				Method method = propertyDescriptor.getReadMethod();
    				
    				row.add(method.invoke(c));
    				System.out.println(messages.getString(field.getName())+" = "+ method.invoke(c));

				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
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
    	// TODO Auto-generated method stub
        Kliens s = new Kliens();
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        s.pack();
        s.setVisible(true);
    }
}