package proj.main.features;
import java.io.*;
import proj.dbconnctor.*;
import java.sql.*;
import java.util.*;
public class Feedback
{
	
	private Scanner in;
	private ConnectionProvider dbConnector;
	public Feedback(Scanner in)
	{
		dbConnector=ConnectionProvider.getInstance();
		this.in=in;
	}
	public void giveFeed(int inPackageID, int userDbId)
	{
		
		try{	
				System.out.println("WHICH TRIP YOU WANT TO GIVE FEEDBACK????\nEnter RATING");
				int rating = Integer.parseInt(in.nextLine());
				System.out.println("WHICH TRIP YOU WANT TO GIVE FEEDBACK????\nEnter FEEDBACK");
				String feedback =in.nextLine();
			
			int resultCount=dbConnector.insert("INSERT INTO feedback(user_id,package_id,rating,feedback)"
					+ " VALUES('"+userDbId+"','"+inPackageID+"','"+rating+"','"+feedback+"' );");
			
			if (resultCount > 0)
			{
				System.out.println("FEEDBACK INSERTED Successfully");
			}
			
			else
				System.out.println("Error Inserting Record Please Try Again");
			
		}catch(SQLException e){
			System.out.println(e);
		}
	}
	
	
	public void viewFeed(int userDbId,String userDbEmail)
	{
		try { int flag=0;
		System.out.println("n1.HAVE CODE\n1.YES\n2.NO");
		int choice1=Integer.parseInt(in.nextLine());
		if(choice1==1)
		{
			try{
			System.out.println("ENTER CODE");
			String code=in.nextLine();
			
			File file = new File("C:\\Users\\purur\\workspace\\TestProj\\src\\code.txt");
			final Scanner inFile = new Scanner(file);
			while (inFile.hasNextLine()) {
			   final String lineFromFile = inFile.nextLine();
			   if(lineFromFile.contains(code))
			   { 
			       // a match!
			       System.out.println("I found " +code+ " in file " +file.getName());
			       flag=1;
			       break;
			   }
			   System.out.println("COUPON CODE IS INVALID");
			}
			inFile.close();
			}catch(Exception e){System.out.println(e);}
		}
			
		
		
		System.out.println("YOUR REVIEWS............................");
			String sql="SELECT * FROM feedback WHERE user_id='"+userDbId+"'";

				ResultSet feedbackResultSet= dbConnector.selectQuery(sql);
			while (feedbackResultSet.next()) 
			{
				
				System.out.println("\nPackageID.: " + feedbackResultSet.getInt("package_id"));
				System.out.println("UserID: " + feedbackResultSet.getInt("user_id"));
				System.out.println("Rating: " + feedbackResultSet.getInt("rating"));
				System.out.println("FEEDBACK.: " + feedbackResultSet.getString("feedback"));
			}
			System.out.println("ALL REVIEWS............................");
			String sql1="SELECT * FROM feedback ";

				ResultSet feedbackResultSet1= dbConnector.selectQuery(sql1);
			while (feedbackResultSet1.next()) 
			{

				System.out.println("\nFeedbackID.: " + feedbackResultSet1.getInt("feedback_id"));
				
				System.out.println("\nPackageID.: " + feedbackResultSet1.getInt("package_id"));
				System.out.println("UserID: " + feedbackResultSet1.getInt("user_id"));
				System.out.println("Rating: " + feedbackResultSet1.getInt("rating"));
				String wholeFeedback=feedbackResultSet1.getString("feedback");
				if(flag==0)
				{
				String limitedFeedback;
				if (wholeFeedback.length() <= 20) {
				    limitedFeedback = wholeFeedback;
				} else { 
				    limitedFeedback = wholeFeedback.substring(0, 20);
				}
				
				System.out.println("FEEDBACK: " + limitedFeedback+"........view more by entering coupon code");
				}else if(flag==1)
				{
					System.out.println("FEEDBACK: " + wholeFeedback);
				}
			}
			
			System.out.println("WANT COUPON Press 1");
			int choice=Integer.parseInt(in.nextLine());
			if(choice==1&&choice1!=1)
			{
				System.out.println("ENTER the REQUEST ");
				System.out.println("ENTER the feedback_id ");
				int input1=Integer.parseInt(in.nextLine());
				System.out.println("ENTER the user_id");
				int input2=Integer.parseInt(in.nextLine());
				String sql3="INSERT INTO notifications(feedback_id,user_id,req_userEmail) VALUES('"+input1+"','"+input2+"','"+userDbEmail+"')";

				int reqCount= dbConnector.insert(sql3);
				if(reqCount>=1)
				{
					System.out.println("REQUEST SEND YOU GET CODE IN SOMETIME IN YOUR EMAIL ID");
				}else
					{
					System.out.println("REQUEST NOT SEND--PROBLEM");
					}
			}else{System.out.println("YOU ALREADY APPLIED COUPON");}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ResultSet searchFeed(String keyword1)
	{		
	
			
			try {
										
			String sql="SELECT * FROM trips,feedback ";

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
				
				
				}	
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}	