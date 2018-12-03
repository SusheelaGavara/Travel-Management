package proj.main.features;

import proj.dbconnctor.*;
import java.sql.*;
import java.util.*;
public class Notifications
{
	
	private ConnectionProvider dbConnector;
	public Notifications(Scanner in)
	{
		dbConnector=ConnectionProvider.getInstance();
	}
	public void viewUserNotification(int userID)
	{
		String sql="SELECT * FROM notifications WHERE user_id='"+userID+"'";
		try
		{
		ResultSet notiResultSet= dbConnector.selectQuery(sql);
	while (notiResultSet.next()) 
	{
		
		System.out.println("\nFeedbackID.: " + notiResultSet.getInt("feedback_id"));
		System.out.println("UserID: " + notiResultSet.getInt("user_id"));
	//	System.out.println("Requested USER ID: " + notiResultSet.getInt("req_userId"));
		System.out.println("Requested User EMAIL.: " + notiResultSet.getString("req_userEmail"));
	}
		}catch(Exception e){System.out.println(e);}
	}
	
	
}	