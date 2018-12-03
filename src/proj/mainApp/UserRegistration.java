package proj.mainApp;
import pojo.*;
import proj.dbconnctor.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration
{
	private ConnectionProvider dbConnector;
	private Scanner in = new Scanner(System.in);
	boolean check = false;
	String password,email;
	String bdate;
	//private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public UserRegistration()
	{
		//		this.in=in;
		dbConnector=ConnectionProvider.getInstance();

	}

	UserPOJO userPojo = new UserPOJO();

	public void insertUserDetails() throws Exception
	{

		System.out.println("Enter Registration Details:");
		System.out.println("User Id: ");
		userPojo.setUser_id(in.nextInt());

		System.out.println("Login id: ");
		userPojo.setUser_login_id(in.next());

		System.out.println("Password: ");

		while(check == false)
		{
			password = in.next();
			check = passwordCheck(password);		
		}
		userPojo.setUser_password(password);

		System.out.println("FirstName:");
		userPojo.setUser_fname(in.next());

		System.out.println("LastName: ");
		userPojo.setUser_lname(in.next());

		System.out.println("DOB(YYYY/MM/DD): ");
		check = false;
		while(check == false)
		{
			bdate = in.next();
			
			check = dateCheck(bdate);		
		}
		userPojo.setUser_bdate(bdate);

		System.out.println("Gender(M/F): ");
		userPojo.setUser_gender(in.next());

		System.out.println("EmailId: ");
		check = true;
		while(check == true)
		{
			email = in.next();
			check = emailCheck(email);		
		}
		userPojo.setUser_emailid(email);

		System.out.println("Contact: ");
		userPojo.setUser_contact(in.nextLong());

		System.out.println("Country: ");
		userPojo.setUser_country(in.next());


		System.out.println("Address: ");
		userPojo.setUser_address(in.next());

		System.out.println("City: ");
		userPojo.setUser_city(in.next());

		System.out.println("ZipCode: ");
		userPojo.setUser_zipcode(in.nextInt());


		try{

			int resultCount = dbConnector.insert(" insert into user_details(user_id, user_login_id,user_password, user_fname, user_lname, user_bdate, user_gender, user_emailid, user_contact, user_country, user_address,user_city,user_zipcode)"
					+ " values ('"+userPojo.getUser_id()+"','"+userPojo.getUser_login_id()+"','"+userPojo.getUser_password()+"','"+userPojo.getUser_fname()+"','"+userPojo.getUser_lname()+"','"+userPojo.getUser_bdate()+"','"+userPojo.getUser_gender()+"',"
					+ "'"+userPojo.getUser_emailid()+"','"+userPojo.getUser_contact()+"','"+userPojo.getUser_country()+"','"+userPojo.getUser_address()+"','"+userPojo.getUser_city()+"','"+userPojo.getUser_zipcode()+"')");


			if (resultCount > 0)
				System.out.println("Record Inserted Successfully");
			else
				System.out.println("Error Inserting Record Please Try Again");
		}

		catch(SQLException e){
			System.out.println("ERROR:"+e.getMessage());
		}}

	private boolean emailCheck(String email) {
			String mydomain = email;
			String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
			Boolean b = mydomain.matches(emailregex);
			
			if (b == false)
			{
				System.out.println("Email Address is Invalid");
				return true;
			}
			else
			{
				return false;
			}		

	}

private boolean dateCheck(String bdate) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			date = sdf.parse(bdate);
			if (!bdate.equals(sdf.format(date))) {
				return false;
			}
			else {
				System.out.println("Enter valid date");
				return true;
			}
		} catch (ParseException ex) {
			System.out.println("Enter valid date-cache");
			return true;
		}
		//		if(bdate.length() == 10)
		//		{
		//			String[] bdateArray = bdate.split("/");
		//
		//			int bdateDate,bdateMonth;
		//			bdateDate = Integer.parseInt(bdateArray[2]);
		//			bdateMonth = Integer.parseInt(bdateArray[1]);
		//
		//			if(bdateDate > 31 || bdateMonth > 13)
		//			{
		//				return false;
		//			}
		//			else
		//			{
		//				return true;
		//			}
		//		}
		//		else
		//		{
		//			return false;
		//		}

	}

	private boolean passwordCheck(String password) {
		if(password.length() >8 && password.length() <16)
		{
			Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(password);
			boolean b = m.find();
			return b;
		}
		else
		{
			System.out.println("Password should be in between 8 and 16 character length with special characters");
			return false;
		}

	}
}

