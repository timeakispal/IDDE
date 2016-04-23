import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Szerver {
	List<Data> list;
	
	public Szerver() throws SQLException {
		list = new ArrayList<Data>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                "user=timi&password=1234");
		Statement st = con.createStatement();
		String sql = ("SELECT * FROM states;");
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			Data row = new Data();
			row.setId(rs.getInt("id")); 
			row.setStateName(rs.getString("state"));
			row.setPopulation(rs.getInt("population")); 
			list.add(row);
		}
		con.close();
	}

}
