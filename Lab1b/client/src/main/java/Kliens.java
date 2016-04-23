import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.List;

public class Kliens extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
    JPanel panel = new JPanel();
    JButton button = new JButton("Get Data");

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
        Szerver server = null;
        try {
			server = new Szerver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CustomModel  model = new CustomModel(server.list);
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

class CustomModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    String[] columnNames = {"ID", "State Name", "Population"};
    List<Data> data;
    
    public CustomModel(List<Data> list) {
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
        Data stateInfo = data.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return stateInfo.getId();
        case 1:
            return stateInfo.getStateName();
        case 2:
            return stateInfo.getPopulation();
        default:
            return null;
        }
    }

}
