package edu.ubb.cs.idde.client.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import edu.ubb.cs.idde.server.main.GenericDao;
import edu.ubb.cs.idde.server.main.GenericJdbcDao;
import edu.ubb.cs.idde.server.pojo.States;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
    	
    	ArrayList<String> columnNames = new ArrayList<String>();
    	for (Field field : l2.get(0).getClass().getDeclaredFields()) {
			columnNames.add(messages.getString(field.getName()));
		}
    	
    	while( i2.hasNext())
    	{
    		States c = i2.next();
    		for (Field field : c.getClass().getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor;
				try {
					propertyDescriptor = new PropertyDescriptor(field.getName(), c.getClass());
    				Method method = propertyDescriptor.getReadMethod();
    				
    				// ezt hozzaadom a tablehez amit meg akarok jeleniteni
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
    	}
        
    	CustomModel<States>  model = new CustomModel<States>(l2);
        JTable table = new JTable();
        table.setModel(model);
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

class CustomModel<T> extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    String[] columnNames = {"ID", "State Name", "Population"};
    List<T> data;
    
    public CustomModel(List<T> list) {
        data = list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T stateInfo = data.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return ((States) stateInfo).getId();
        case 1:
            return ((States) stateInfo).getState();
        case 2:
            return ((States) stateInfo).getPopulation();
        default:
            return null;
        }
    }

}
