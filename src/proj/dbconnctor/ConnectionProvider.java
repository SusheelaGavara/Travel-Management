package proj.dbconnctor;
import proj.dbconnctor.DBPropertyLoader;
import java.sql.*;
import java.util.Properties;
public class ConnectionProvider
{
	public Connection con;
	public Statement stmt;
	private static ConnectionProvider dbConnectorObj;
	public static final int path=1;
	
	
	private ConnectionProvider()
	{
		Properties prop=null;
		prop= DBPropertyLoader.getProperties();
		try
		{
		Class.forName(prop.getProperty("driverClass"));
		con=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
		}catch(Exception e)
		{
			System.out.println(e);
			
		}
	}
	
	public static ConnectionProvider getInstance() {
		if (dbConnectorObj == null)
			dbConnectorObj = new ConnectionProvider();
		

		return dbConnectorObj;
	}
	
	public int insert(String insertQuery) throws SQLException {
		stmt= con.createStatement();
		int resultCount = stmt.executeUpdate(insertQuery);
		return resultCount;

	}
	public int delete(String insertQuery) throws SQLException {
		stmt= con.createStatement();
		int resultCount = stmt.executeUpdate(insertQuery);
		return resultCount;

	}
	
	public ResultSet selectQuery(String insertQuery) throws SQLException {
		stmt= con.createStatement();
		ResultSet rset = stmt.executeQuery(insertQuery);
		return rset;

	}
	
	public int update(String insertQuery) throws SQLException {
		stmt= con.createStatement();
		int resultCount = stmt.executeUpdate(insertQuery);
		return resultCount;

	}
	
}