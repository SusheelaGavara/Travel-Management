package proj.main.admin;
import pojo.*;
import proj.dbconnctor.*;
import java.sql.*;

public class AdminServices
{
	
	private ConnectionProvider dbConnector;
	public AdminServices()
	{
		dbConnector=ConnectionProvider.getInstance();
	
	}
	
	
	public void viewBooking() throws Exception
	{
		try
		{	
			ResultSet allBookingResultSet = dbConnector.selectQuery("SELECT * FROM ticket");
			if(allBookingResultSet==null)
				System.out.println("NULLLLLLLLLLLLLLLLLLLL");
			
			while(allBookingResultSet.next())
			{	
				System.out.println("\nPackageID.: " + allBookingResultSet.getInt("package_id"));
				System.out.println("UserID: " + allBookingResultSet.getInt("user_id"));
				System.out.println("TICKET date: " + allBookingResultSet.getInt("ticket_date"));
				System.out.println("Ticket no.: " + allBookingResultSet.getInt("ticket_no"));
			}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	public void insertTrips(TripPOJO tripPojo)throws Exception
	{	
		try{
			int resultCount=dbConnector.insert("INSERT INTO trips(package_id,package_name,source,destination,departure_date,hotel_name,no_of_nights,mode_of_transport,trip_desc,package_cost,discount)"
					+ " VALUES('"+tripPojo.getPackage_id()+"','"+tripPojo.getPackage_name()+"',"
							+ "'"+tripPojo.getSource()+"','"+tripPojo.getDestination()+"','"+tripPojo.getDeparture_date()+"',"
									+ "'"+tripPojo.getHotel_name()+"','"+tripPojo.getNo_of_nights()+"','"+tripPojo.getMode_of_transport()+"'"
											+ ",'"+tripPojo.getTrip_desc()+"','"+tripPojo.getPackage_cost()+"'"
													+ ",'"+tripPojo.getDiscount()+ "')");
			if (resultCount > 0)
				System.out.println("Record Inserted Successfully");
			else
				System.out.println("Error Inserting Record Please Try Again");
		
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}

	public void updateTrips(TripPOJO tripPOJO) throws SQLException {
		try{


			int resultCount= dbConnector.update("UPDATE trips set package_name = '"+tripPOJO.getPackage_name()+"', "
					+ "source = '"+tripPOJO.getSource()+"', destination = '"+tripPOJO.getDestination()+"',departure_date = '"+tripPOJO.getDeparture_date()+"', "
					+ "hotel_name='"+tripPOJO.getHotel_name()+"', no_of_nights='"+tripPOJO.getNo_of_nights()+"', mode_of_transport='"+tripPOJO.getMode_of_transport()+"', "
					+ "trip_desc='"+tripPOJO.getTrip_desc()+"', package_cost='"+tripPOJO.getPackage_cost()+"', discount='"+tripPOJO.getDiscount()+"' "
					+ "where package_id = '"+tripPOJO.getPackage_id()+"' ");


			if (resultCount > 0)
				System.out.println("Record Updated Successfully");
			else
				System.out.println("Error Upting Record Please Try Again");
		}
		catch (Exception e)
		{
			System.out.println("ERROR:"+e.getMessage());
		}
	}


	public void deleteTrips(int userDbId) {
		{
			try{
				int resultCount=dbConnector.delete("DELETE FROM trips WHERE package_id = '"+userDbId+"'");
				if(resultCount > 0){
					System.out.println("Record Deleted Successfully");
				}	else{
					System.out.println("Error Deleting Record Please Try Again");
				}}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}

		}

	}

	
}

