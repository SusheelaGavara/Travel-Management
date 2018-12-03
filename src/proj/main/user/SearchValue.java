package proj.main.user;

import proj.dbconnctor.*;
import java.sql.*;


public class SearchValue
{
	
	private ConnectionProvider dbConnector;
	public SearchValue(){
		dbConnector=ConnectionProvider.getInstance();
	
	}
	public int valueOfHotel(int hotelID)
	{
		int hotelPrice=0;
		try {
			ResultSet resultPriceHotels=dbConnector.selectQuery("SELECT * FROM c_Stay WHERE c_hotel_id='"+hotelID+"'");
		
		while (resultPriceHotels.next())
		{
			 hotelPrice=resultPriceHotels.getInt("c_hotel_price");
			 System.out.println("HOTEL 1 NIGHT RENT:"+hotelPrice);
		}
		} catch (SQLException e) 
		{
			System.out.println("ERROR:"+e.getMessage());
			
		} 
		return hotelPrice;
	}
	public double valueOfFlight(int flightID)
	{
		double flightPrice=0;
		try {
			
			ResultSet resultFlightHotels=dbConnector.selectQuery("SELECT * FROM c_flights WHERE c_flight_id='"+flightID+"'");
		
		while (resultFlightHotels.next())
		{
			 flightPrice=resultFlightHotels.getDouble("c_flight_cost");
		}
		} catch (SQLException e)
		{
			System.out.println("ERROR:"+e.getMessage());
			
		} 
		return flightPrice;
	}
	
	
}	