package proj.main.user;

import proj.dbconnctor.*;
import java.sql.*;
import java.util.Scanner;

import pojo.CustomisedPackagePOJO;
import pojo.FlightPOJO;
import pojo.StayPOJO;
import pojo.UserPOJO;
import proj.main.features.*;
public class UserServices
{
	Check chk=new Check();
	private ConnectionProvider dbConnector;
	public UserServices(){
		dbConnector=ConnectionProvider.getInstance();
	
	}
	
	public void viewTrips(String sql) throws Exception
	{
		
		try
		{	
			ResultSet allTripsResultSet = dbConnector.selectQuery(sql);
			if(allTripsResultSet==null)
				System.out.println("NULLLLLLLLLLLLLLLLLLLL");
			while (allTripsResultSet.next()) 
			{
				System.out.println("\nPackageNo.: " + allTripsResultSet.getInt("package_id"));
				System.out.println("Package Name: " + allTripsResultSet.getString("package_name"));
				System.out.println("Source: " + allTripsResultSet.getString("source"));
				System.out.println("Destination: " + allTripsResultSet.getString("destination"));
				System.out.println("Dep Date:" + allTripsResultSet.getString("departure_date"));
				System.out.println("HOTEL: " + allTripsResultSet.getString("hotel_name"));
				System.out.println("NO.OF Nights: " + allTripsResultSet.getInt("no_of_nights"));
				System.out.println("Mode of Transportation: " + allTripsResultSet.getString("mode_of_transport"));
				System.out.println("Description: " + allTripsResultSet.getString("trip_desc"));
				System.out.println("Cost: " + allTripsResultSet.getInt("package_cost"));
				System.out.println("Discount: " + allTripsResultSet.getInt("discount"));
				
			
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	public ResultSet searchTrips(String inDestination,String inDate,String inPrice)
	{		
	
			
			try {
										
					String sql="SELECT * FROM trips WHERE destination='"+inDestination+"' OR departure_date='"+inDate+"' OR package_cost<='"+inPrice+"';";

					ResultSet searchResultSet= dbConnector.selectQuery(sql);
	
			while (searchResultSet.next()) 
			{
				System.out.println("\nPackageNo.: " + searchResultSet.getInt("package_id"));
				System.out.println("Package Name: " + searchResultSet.getString("package_name"));
				System.out.println("Source: " + searchResultSet.getString("source"));
				System.out.println("Destination: " + searchResultSet.getString("destination"));
				System.out.println("Dep Date:" + searchResultSet.getString("departure_date"));
				System.out.println("HOTEL: " + searchResultSet.getString("hotel_name"));
				System.out.println("NO.OF Nights: " + searchResultSet.getInt("no_of_nights"));
				System.out.println("Mode of Transportation: " + searchResultSet.getString("mode_of_transport"));
				System.out.println("Description: " + searchResultSet.getString("trip_desc"));
				System.out.println("Cost: " + searchResultSet.getInt("package_cost"));
				System.out.println("Discount: " + searchResultSet.getInt("discount"));
				
			
			}
			
			
			}catch (SQLException e) {
			System.out.println("ERROR:"+e.getMessage());
		}
		return null;
	}


	public void bookTicket(int inPackageID, int userDbId)
	{
		int checkFlag=0;
		checkFlag=chk.checkPackage(inPackageID);
		
		if(checkFlag==1)
		{
		
		try{	
			
			int resultCount=dbConnector.insert("INSERT INTO ticket(user_id,package_id,ticket_date)"
					+ " VALUES('"+userDbId+"','"+inPackageID+"',CURRENT_DATE());");
			
			if (resultCount > 0)
			{	//System.out.println("Confirmation EMAIL SEND ");
				System.out.println("TICKET BOOKED Successfully");
			}
			else
				System.out.println("Error Inserting Record Please Try Again");
			
		}catch(Exception e)
		{
			System.out.println("ERROR:"+e.getMessage());
		}
		}else
			System.out.println("Package Not Found");
		
	}
	
	
	public void viewUserTicket(int userDbId)
	{
		try { 
			
				String sql="SELECT * FROM ticket WHERE user_id='"+userDbId+"';";
				ResultSet ticketResultSet= dbConnector.selectQuery(sql);
				
			while (ticketResultSet.next()) 
			{
				
				System.out.println("\nPackageID.: " + ticketResultSet.getInt("package_id"));
				System.out.println("UserID: " + ticketResultSet.getInt("user_id"));
				System.out.println("TICKET date: " + ticketResultSet.getInt("ticket_date"));
				System.out.println("Ticket no.: " + ticketResultSet.getInt("ticket_no"));
				
			
			}
			
			
		} catch (SQLException e) 
		{
			System.out.println("ERROR:"+e.getMessage());
		}
	}
	public void viewUserNotification(int userDbId)
	{
		try { 
			
			String sql="SELECT * FROM notifications WHERE user_id='"+userDbId+"';";
			ResultSet notiResultSet= dbConnector.selectQuery(sql);
			while (notiResultSet.next()) 
			{
				System.out.println("\nFeedbackID.: " + notiResultSet.getInt("feedback_id"));
				System.out.println("UserID: " + notiResultSet.getInt("user_id"));
				System.out.println("Requested User EMAIL.: " + notiResultSet.getString("req_userEmail"));
			}
			
			
		} catch (SQLException e) 
		{
			System.out.println("ERROR:"+e.getMessage());
		}
	}
	public ResultSet searchKeyword(String keyword1)
	{		
	
			
			try {
										
			String sql="SELECT * FROM trips ";

				ResultSet searchResultSet= dbConnector.selectQuery(sql);
			while (searchResultSet.next()) 
			{
				String s1=searchResultSet.getString(9);
				boolean flag=s1.contains(keyword1);
				if(flag==true)
				{
				
				System.out.println("\nPackageNo.: " + searchResultSet.getInt("package_id"));
				System.out.println("Package Name: " + searchResultSet.getString("package_name"));
				System.out.println("Source: " + searchResultSet.getString("source"));
				System.out.println("Destination: " + searchResultSet.getString("destination"));
				System.out.println("Dep Date:" + searchResultSet.getString("departure_date"));
				System.out.println("HOTEL: " + searchResultSet.getString("hotel_name"));
				System.out.println("NO.OF Nights: " + searchResultSet.getInt("no_of_nights"));
				System.out.println("Mode of Transportation: " + searchResultSet.getString("mode_of_transport"));
				System.out.println("Description: " + searchResultSet.getString("trip_desc"));
				System.out.println("Cost: " + searchResultSet.getInt("package_cost"));
				System.out.println("Discount: " + searchResultSet.getInt("discount"));
				
				
				}	else{System.out.println("Keyword not found");}
			
			}
			
			
		} catch (SQLException e) 
		{
			System.out.println("ERROR:"+e.getMessage());
		}
		return null;
	}

	
	public void update_users(int ch, UserPOJO userPOJO)
	{
		try
		{

			switch(ch)
			{
				case 1: 
						System.out.println("Enter new password:");
						Scanner sc1=new Scanner(System.in);
						String pass=sc1.nextLine();
						String up_pass="update user_details set user_password='"+pass+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount1=dbConnector.update(up_pass); 
						if(resultCount1==1)
									System.out.println("Updated Successfully");
						else
									System.out.println("Error updating password");

						break;
				case 2:
						System.out.println("Enter the first name to be updated:");
						Scanner sc2=new Scanner(System.in);
						String fname=sc2.nextLine();
						String up_fname="update user_details set user_fname='"+fname+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount2=dbConnector.update(up_fname); 
						if(resultCount2==1)
								System.out.println("Updated Successfully");
						else
								System.out.println("Error updating first name");

						break;
				case 3:
						System.out.println("Enter the last name to be updated:");
						Scanner sc3=new Scanner(System.in);
						String lname=sc3.nextLine();
						String up_lname="update user_details set user_lname='"+lname+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount3=dbConnector.update(up_lname); 
						if(resultCount3==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating last name");

						break;
				case 4:
						System.out.println("Enter the birth date to be updated:");
						Scanner sc4=new Scanner(System.in);
						String bdate=sc4.nextLine();
						String up_bdate="update user_details set user_bdate='"+bdate+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount4=dbConnector.update(up_bdate); 
						if(resultCount4==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating birth date");
						break;
				case 5:
						System.out.println("Enter the email to be updated:");
						Scanner sc5=new Scanner(System.in);
						String email=sc5.nextLine();
						String up_email="update user_details set user_emailid='"+email+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount5=dbConnector.update(up_email); 
						if(resultCount5==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating password");

						break;
				case 6:
						System.out.println("Enter the contact number to be updated:");
						Scanner sc6=new Scanner(System.in);
						String contact=sc6.nextLine();
						String up_contact="update user_details set user_contact='"+contact+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount6=dbConnector.update(up_contact); 
						if(resultCount6==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating contact number");

						break;
				case 7:
						System.out.println("Enter the country to be updated:");
						Scanner sc7=new Scanner(System.in);
						String country=sc7.nextLine();
						String up_country="update user_details set user_country='"+country+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount7=dbConnector.update(up_country); 
						if(resultCount7==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating country");

						break;
				case 8:
						System.out.println("Enter the address to be updated:");
						Scanner sc8=new Scanner(System.in);
						String address=sc8.nextLine();
						String up_address="update user_details set user_address='"+address+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount8=dbConnector.update(up_address); 
						if(resultCount8==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating address");

						break;
				case 9:
						System.out.println("Enter the city to be updated:");
						Scanner sc9=new Scanner(System.in);
						String city=sc9.nextLine();
						String up_city="update user_details set user_city='"+city+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount9=dbConnector.update(up_city); 
						if(resultCount9==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating city");

						break;
				case 10:
						System.out.println("Enter the zipcode to be updated:");
						Scanner sc10=new Scanner(System.in);
						String zipcode=sc10.nextLine();
						String up_zipcode="update user_details set user_zipcode='"+zipcode+"' where user_id='"+userPOJO.getUser_id()+"'";
						int resultCount10=dbConnector.update(up_zipcode); 
						if(resultCount10==1)
							System.out.println("Updated Successfully");
						else
							System.out.println("Error updating zipcode");

						break;
			}
			
		}catch(Exception e)
		{
			System.out.println("Error:"+e.getMessage());
		}
	}
	public void viewUserDetails(String sql) throws Exception
	{
		
		try
		{	
			ResultSet allUserDetails = dbConnector.selectQuery(sql);
			if(allUserDetails==null)
				System.out.println("Empty set");
			while (allUserDetails.next()) 
			{
				System.out.println("\nLogin Id.: " + allUserDetails.getString("user_login_id"));
				System.out.println("First name: " + allUserDetails.getString("user_fname"));
				System.out.println("Last name: " + allUserDetails.getInt("user_lname"));
				System.out.println("Birth Date: " + allUserDetails.getString("user_bdate"));
				System.out.println("Gender:" + allUserDetails.getString("user_gender"));
				System.out.println("Email Id: " + allUserDetails.getString("user_emailid"));
				System.out.println("Contact Number: " + allUserDetails.getInt("user_contact"));
				System.out.println("Address: " + allUserDetails.getString("user_address"));
				System.out.println("City: " + allUserDetails.getString("user_city"));
				System.out.println("Country: " + allUserDetails.getString("user_country"));
				System.out.println("Zipcode: " + allUserDetails.getString("user_zipcode")+"\n");
				
			
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	public void buildPackage(UserPOJO userPOJO)
	{

		StayPOJO stayPOJO = new StayPOJO();
		FlightPOJO flightPOJO=new FlightPOJO();
		try
		{

			String sqlStay="SELECT c_hotel_price FROM c_stay;";
			ResultSet getStayPojo = dbConnector.selectQuery(sqlStay);
			if(getStayPojo.next()) 
			{
				stayPOJO.setC_hotel_price(getStayPojo.getDouble("c_hotel_price"));
			}


			String sqlFlight="SELECT c_flight_cost FROM c_flights;";
			ResultSet getFlightPojo = dbConnector.selectQuery(sqlFlight);
			if(getFlightPojo.next()) 
			{
				flightPOJO.setC_flight_cost(getFlightPojo.getDouble("c_flight_cost"));
			}

			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the departure city:");
			String cDeptCity=sc.next();
			System.out.println("Enter the city you wish to travel:");
			String cDestCity=sc.next();
			System.out.println("Enter your date of departure(yyyy-mm-dd):");
			String cDeptDate=sc.next();
			String searchFlights="select * from c_flights where c_dept_city='"+cDeptCity+"' and c_dest_city='"+cDestCity+"'";
			ResultSet resultSet=dbConnector.selectQuery(searchFlights); 
			System.out.println("We have following flights to offer");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString("c_flight_id")+" "+resultSet.getString("c_dept_date")+" "+resultSet.getString("c_dept_city")+"  o-------o  "+resultSet.getString("c_dest_city")+"  "+resultSet.getString("c_flight_cost")+"$");
			}
			System.out.println("Enter the flight id of the flights that you want to select.");
			int cFlightId=sc.nextInt();
			
			
			
			System.out.println("Do you want to book a hotel(Y/N)?");
			String cHotelNAme=sc.next();
					
					
			SearchValue s2=new SearchValue();
			double cFlightCostTotal=s2.valueOfFlight(cFlightId);
					
			
			double totalPackageCost=0;
			
			if(cHotelNAme.equals("y")|| cHotelNAme.equals("Y"))
			{
				System.out.println("Select the hotel name that you want to book?");
				String searchHotels="select * from c_stay";
				ResultSet resultSetHotels=dbConnector.selectQuery(searchHotels); 
				System.out.println("We have following hotels to offer");
				while (resultSetHotels.next())
				{
					System.out.println(resultSetHotels.getString("c_hotel_id")+"  "+resultSetHotels.getString("c_hotel_name")+" --> "+resultSetHotels.getString("c_hotel_price"));
				}
				
				System.out.println("Enter the id of hotel which you want to select:");
				int cHotelId=sc.nextInt();
				
				
				SearchValue s1=new SearchValue();
				int forCost=s1.valueOfHotel(cHotelId);
				
				
				System.out.println("Enter the number of nights you want to stay:");
				int cNoOfNights=sc.nextInt();
				double totalCost=forCost*cNoOfNights;	
				System.out.println("FlightCOST:"+cFlightCostTotal);
				totalPackageCost=totalCost+cFlightCostTotal;
				System.out.println("Your total package cost is:"+totalPackageCost);
				System.out.println("Do you want to book your package consisting of flight and hotel(Y/N)?");
				String cBookPackage=sc.next();
				if(cBookPackage.equals("y")|| cBookPackage.equals("Y"))
				{
					
					bookPackage(cFlightId,cDeptDate,cHotelId,totalCost,cNoOfNights,userPOJO,totalPackageCost);
				}
			}
			else
			{
				totalPackageCost=cFlightCostTotal;
				System.out.println("Your total package cost is:"+cFlightCostTotal);
				System.out.println("Do you want to book your package(Y/N)?");
				String cBookPackage=sc.next();
				if(cBookPackage.equals("y")|| cBookPackage.equals("Y"))
				{
					bookPackage(cFlightId,cDeptDate,0,0,0,userPOJO,totalPackageCost);
				}
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void bookPackage(int cfID, String cDeptDate, int cHotelId,double totalCost,int cNoOfNights,UserPOJO userPOJO,double totalPackageCost)
	{
		try{	
				StayPOJO stayPOJO = new StayPOJO();
				FlightPOJO flightPOJO=new FlightPOJO();
				CustomisedPackagePOJO cPPOJO=new CustomisedPackagePOJO();
				int u_id=userPOJO.getUser_id();
				String sqlFlights="SELECT c_flight_cost FROM c_flights;";
				ResultSet getFlightPojo = dbConnector.selectQuery(sqlFlights);
				if(getFlightPojo.next()) 
				{
					flightPOJO.setC_flight_cost(getFlightPojo.getDouble("c_flight_cost"));
				}
				String sqlStay="SELECT c_hotel_price FROM c_stay;";
				ResultSet getStayPojo = dbConnector.selectQuery(sqlStay);
				if(getStayPojo.next()) 
				{
					stayPOJO.setC_hotel_price(getStayPojo.getDouble("c_hotel_price"));
				}


			if(cHotelId!=0)
			{

				int resultCount=dbConnector.insert("INSERT INTO cust_package(c_id,user_id,c_flight_id,cp_flight_cost,c_hotel_id,dept_date,c_no_of_nights,c_hotel_cost,c_hotel_total_cost,booking_date,c_total_package_cost)"
						+ " VALUES('"+cPPOJO.getcId()+"','"+u_id+"','"+cfID+"','"+flightPOJO.getC_flight_cost()+"','"+cHotelId+"','"+cDeptDate+"','"+cNoOfNights+"','"+stayPOJO.getC_hotel_price()+"','"+totalCost+"',CURRENT_DATE(),'"+totalPackageCost+"');");
				
				if (resultCount > 0)
				{
					System.out.println("Your package has been booked successfully!!");
				}
				
				else
					System.out.println("Error Inserting Record. Please Try Again!");
			}
			else
			{

				int resultCount=dbConnector.insert("INSERT INTO cust_package(c_id,user_id,c_flight_id,cp_flight_cost,c_hotel_id,dept_date,c_no_of_nights,c_hotel_cost,c_hotel_total_cost,booking_date,c_total_package_cost)"
						                                             + " VALUES('"+cPPOJO.getcId()+"','"+u_id+"','"+cfID+"','"+flightPOJO.getC_flight_cost()+"','0','"+cDeptDate+"','0','0','0',CURRENT_DATE(),'"+totalPackageCost+"');");
				
				if (resultCount > 0)
				{
					System.out.println("Your flight has been booked successfully!!");
				}
				
				else
					System.out.println("Error Inserting Record. Please Try Again!");
			}
		}catch(SQLException e){
			System.out.println(e);
		}
	}
	public void viewBuildPackage(UserPOJO userPOJO, String sql)
	{
		try
		{	
			ResultSet allPackageDetails = dbConnector.selectQuery(sql);
			if(allPackageDetails==null)
				System.out.println("Empty set");
			while (allPackageDetails.next()) 
			{
				System.out.println("-------------------------------------------");
				System.out.println("User Id.: " + allPackageDetails.getString("user_id"));
				System.out.println("Flight Id: " + allPackageDetails.getString("c_flight_id"));
				System.out.println("Cost of the flight: " + allPackageDetails.getInt("cp_flight_cost"));
				System.out.println("Hotel id: " + allPackageDetails.getString("c_hotel_id"));
				System.out.println("Departure date:" + allPackageDetails.getString("dept_date"));
				System.out.println("No of nights: " + allPackageDetails.getString("c_no_of_nights"));
				System.out.println("Hotel cost: " + allPackageDetails.getInt("c_hotel_cost"));
				System.out.println("Total hotel cost: " + allPackageDetails.getString("c_hotel_total_cost"));
				System.out.println("Booking date: " + allPackageDetails.getString("booking_date"));
				System.out.println("Total package cost: " + allPackageDetails.getString("c_total_package_cost"));
				System.out.println("-------------------------------------------\n");

			
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}




