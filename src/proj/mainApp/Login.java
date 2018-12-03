package proj.mainApp;

import proj.dbconnctor.*;
import java.util.*;
import java.sql.*;
import pojo.*;

public class Login 
{
	private ConnectionProvider dbConnector;
	private UserPOJO userPOJO;

	public Login() 
	{
		dbConnector = ConnectionProvider.getInstance();

	}

	public boolean userLogin() 
	{
		boolean userlogin = false;
		System.out.println("-------------------------------------------Login:---------------------------------------");
		
	
		@SuppressWarnings("resource")
		Scanner scobj1 = new Scanner(System.in);

		System.out.println("Username: ");
		String uname = scobj1.next();

		System.out.println("Password: ");
		String pwd = scobj1.next();

		try {

			String sql = "SELECT * FROM user_details WHERE user_login_id='" + uname + "' AND user_password='" + pwd
					+ "';";
			ResultSet loginset = dbConnector.selectQuery(sql);

			userPOJO = new UserPOJO();

			while (loginset.next()) {
				userlogin = true;

				userPOJO.setUser_id(loginset.getInt("user_id"));
				userPOJO.setUser_login_id(loginset.getString("user_login_id"));
				userPOJO.setUser_password(loginset.getString("user_password"));
				userPOJO.setUser_address(loginset.getString("user_address"));
				userPOJO.setUser_fname(loginset.getString("user_fname"));
				userPOJO.setUser_lname(loginset.getString("user_lname"));

				userPOJO.setUser_bdate(loginset.getString("user_bdate"));

				userPOJO.setUser_gender(loginset.getString("user_gender"));
				userPOJO.setUser_emailid(loginset.getString("user_emailid"));
				userPOJO.setUser_contact(loginset.getLong("user_contact"));
				userPOJO.setUser_address(loginset.getString("user_address"));
				userPOJO.setUser_city(loginset.getString("user_city"));

				userPOJO.setUser_zipcode(loginset.getInt("user_zipcode"));
				userPOJO.setUser_country(loginset.getString("user_country"));
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userlogin;
		
	}

	public boolean adminLogin() 
	{
		boolean adminlogin = false;
		System.out.println("Enter Login Details:");
		
		@SuppressWarnings("resource")
		Scanner scobj2 = new Scanner(System.in);
		System.out.println("Username: ");
		String uname = scobj2.next();

		System.out.println("Password: ");
		String pwd = scobj2.next();

		try {

				String sql = "SELECT * FROM admin WHERE admin_loginid='" + uname + "' AND admin_password='" + pwd + "';";
				ResultSet loginset = dbConnector.selectQuery(sql);
				if (loginset.next())
						adminlogin = true;
			
				
			} catch (Exception e) 
			{
				System.out.println(e);
			}
		return adminlogin;
	}

	public UserPOJO getUserPOJO() 
	{
		return userPOJO;
	}

	public void setUserPOJO(UserPOJO userPOJO) 
	{
		this.userPOJO = userPOJO;
	}

}