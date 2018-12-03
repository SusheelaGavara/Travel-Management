package proj.main.features;

import proj.dbconnctor.*;
import java.sql.*;


public class Check
{
	
	private ConnectionProvider dbConnector;
	public Check()
	{
		dbConnector=ConnectionProvider.getInstance();
	
	}
	public int checkPackage(int packageID) 
	{	int checkFlag=0;
		String sql="SELECT * FROM trips WHERE package_id='"+packageID+"';";

		try
		{
			ResultSet checkRset= dbConnector.selectQuery(sql);
		
			while(checkRset.next())
			{	
				checkFlag=1;
				
			}
		} catch (Exception e) 
		{
		System.out.println("ERROR"+e.getMessage());
		}
		return checkFlag;
	}

}	