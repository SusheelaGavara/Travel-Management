package proj.main.admin;

import proj.dbconnctor.ConnectionProvider;
import proj.main.report.Report;
import proj.main.user.*;
import pojo.*;


import java.sql.*;
import java.util.*;
public class AdminController{
	
	private Scanner in;
	boolean mainFlag=true;
	
	AdminServices adminService=new AdminServices();
	private ConnectionProvider dbConnector;
	public AdminController(Scanner in)
	{
		this.in=in;
		dbConnector=ConnectionProvider.getInstance();
	}
	
	public void startAdminOperations() throws Exception
	{
		TripPOJO tripPojo = new TripPOJO();
		do
		{
			int pressed=0;
			
			System.out.println("CHOOSE\n1.VIEW TRIPS\n2.ADD\n3.UPDATE \n4.DELETE\n5.ViewBooked Trips\n6.REPORT\n7.Logout");
			try
			{
				pressed=Integer.parseInt(in.nextLine());
				
			}catch(Exception e){
				System.out.println("ERROR"+e.getMessage());
				}
			switch(pressed)
			{
			
			case 1: String sql="SELECT * FROM trips WHERE destination IS NOT NULL";
			
					try{
							UserServices userService1= new UserServices();
							userService1.viewTrips(sql);
						}catch(Exception e)
						{
							System.out.println("ERROR"+e.getMessage());
						}
						break;
					
			
			case 2:	
					System.out.println("Enter Package name :");
					tripPojo.setPackage_name(in.nextLine());
					System.out.println("Enter Package id :");
					tripPojo.setPackage_id(Integer.parseInt(in.nextLine()));
					System.out.println("Enter source :");
					tripPojo.setSource(in.nextLine());
					System.out.println("Enter destination :");
					tripPojo.setDestination(in.nextLine());
					System.out.println("Enter departure date  :");
					tripPojo.setDeparture_date(in.nextLine());
					System.out.println("Enter hotel name :");
					tripPojo.setHotel_name(in.nextLine());
					System.out.println("Enter no. of nights :");
					tripPojo.setNo_of_nights(Integer.parseInt(in.nextLine()));
					System.out.println("Enter mode of transport :");
					tripPojo.setMode_of_transport(in.nextLine());
					System.out.println("Enter Trip Description :");
					tripPojo.setTrip_desc(in.nextLine());
					System.out.println("Enter Price of Package :");
					tripPojo.setPackage_cost(Integer.parseInt(in.nextLine()));
					System.out.println("Enter Discount :");
					tripPojo.setDiscount(Integer.parseInt(in.nextLine()));
					try 
					{
						AdminServices adminServices =new AdminServices();
						adminServices.insertTrips(tripPojo);
					} catch (Exception e) 
					{
						System.out.println("ERROR"+e.getMessage());
					}
					break;	
			case 3:	int updateChoice = 0;

					try {
							System.out.println("Provide the package id to update");
							tripPojo.setPackage_id(in.nextInt());
							ResultSet package_details= dbConnector.selectQuery("select * from trips where package_id ='"+tripPojo.getPackage_id()+"'");
							if (package_details.next()) 
							{
								tripPojo.setPackage_id(package_details.getInt("package_id"));
								tripPojo.setPackage_name(package_details.getString("package_name"));
								tripPojo.setSource(package_details.getString("source"));
								tripPojo.setDestination(package_details.getString("destination"));
								tripPojo.setDeparture_date(package_details.getString("departure_date"));
								tripPojo.setHotel_name(package_details.getString("hotel_name"));
								tripPojo.setNo_of_nights(package_details.getInt("no_of_nights"));
								tripPojo.setMode_of_transport(package_details.getString("mode_of_transport"));
								tripPojo.setPackage_cost(package_details.getInt("package_cost"));
								tripPojo.setTrip_desc(package_details.getString("trip_desc"));
								tripPojo.setDiscount(package_details.getInt("discount"));

								System.out.println("\nPackageNo.: " + package_details.getInt("package_id"));
								System.out.println("Package Name: " + package_details.getString("package_name"));
								System.out.println("Source: " + package_details.getString("source"));
								System.out.println("Destination: " + package_details.getString("destination"));
								System.out.println("Dep Date:" + package_details.getString("departure_date"));
								System.out.println("HOTEL: " + package_details.getString("hotel_name"));
								System.out.println("NO.OF Nights: " + package_details.getInt("no_of_nights"));
								System.out.println("Mode of Transportation: " + package_details.getString("mode_of_transport"));
								System.out.println("Cost: " + package_details.getInt("package_cost"));
								System.out.println("Discount: " + package_details.getInt("discount"));
							}
							boolean updatetrip = true;
							while(updatetrip == true)
							{
									System.out.println("Which entry do you want to update?\n"
											+ "1. Package name\n"
											+ "2. Source\n"
											+ "3. Destination\n"
											+ "4. Departure date\n"
											+ "5. Hotel name\n"
											+ "6. No of nights\n"
											+ "7. Mode of transport\n"
											+ "8. Package price\n"
											+ "9. Discount\n"
											+ "10.Description\n"
											+ "11.Update");

									updateChoice = in.nextInt();
									switch(updateChoice)
									{
									case 1:
											System.out.println("Enter Package name :");
											tripPojo.setPackage_name(in.next());
											break;
									case 2:
											System.out.println("Enter source :");
											tripPojo.setSource(in.next());
											break;		
									case 3:
											System.out.println("Enter destination :");
											tripPojo.setDestination(in.next());
											break;
									case 4:
											System.out.println("Enter departure date  :");
											tripPojo.setDeparture_date(in.next());
											break;
									case 5:
											System.out.println("Enter hotel name :");
											tripPojo.setHotel_name(in.next());
											break;
									case 6:
											System.out.println("Enter no. of nights :");
											tripPojo.setNo_of_nights(in.nextInt());
											break;		
									case 7:
											System.out.println("Enter mode of transport :");
											tripPojo.setMode_of_transport(in.next());
											break;
									case 8:
											System.out.println("Enter Price of Package :");
											tripPojo.setPackage_cost(in.nextInt());
											break;
									case 9:
											System.out.println("Enter Discount :");
											tripPojo.setDiscount(in.nextInt());
											break;
									case 10:
											System.out.println("Enter Trip Description :");
											tripPojo.setTrip_desc(in.nextLine());
											break;
									case 11:
											updatetrip = false;
											break;
									default:
											System.out.println("Please enter valid input");
											break;
									}
							}
					} catch (Exception e1) 
					{
						System.out.println("ERROR"+e1.getMessage());
					}

					adminService.updateTrips(tripPojo);

					break;

			case 4:
					try
					{
						System.out.println("Enter Package id :");
						tripPojo.setPackage_id(in.nextInt());
						adminService.deleteTrips(tripPojo.getPackage_id());
					} catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}

			
					break;
				 
			case 5:		try{
							AdminServices adminServices1 =new AdminServices();
					
							adminServices1.viewBooking();
						} catch (Exception e)
						{
							System.out.println("ERROR"+e.getMessage());
						}
					break;
					
			case 6:	Report rep=new Report();
					rep.reportGen();
					System.out.println("REPORT CREATED");
				
			case 7:
					mainFlag=false;
					break;

			default:
				System.out.println("WRONG KEY PRESSED");
				break;		
			}
			
			
		}while(mainFlag);
	}
	
	
}
