package proj.mainApp;

import java.util.Scanner;

import proj.main.admin.AdminController;
import proj.main.user.UserController;

public class MainApp {

	public static void main(String arr[]) throws Exception 
	{

		boolean mainFlag = true;
		Scanner in = new Scanner(System.in);
		System.out.println("------------------------------------WELCOME TO TRIP MANAGEMNT-----------------------------------------");
		do {
			int choice = 0;

			System.out.println("\n-----------------------CHOOSE---------------------- \n1.User\n2.ADmin\n3.Sign-Up\n4.Exit");
			try {

				choice = Integer.parseInt(in.nextLine());
				
				} catch (NumberFormatException e) 
				{
					System.out.println("INVALID INPUT:"+e.getMessage());
				}
			switch (choice) {

			case 1:
					Login login1 = new Login();
					if (login1.userLogin()) 
					{
						UserController user = new UserController(in, login1.getUserPOJO());
						user.startUserOperations();
					break;
					} else 
					{
						System.out.println("LOGIN FAILED");
					}
					break;

			case 2:
					Login login2 = new Login();
					boolean loginFlag2 = login2.adminLogin();
					if (loginFlag2 == true) 
					{
						AdminController admin = new AdminController(in);
						admin.startAdminOperations();
					break;
					} else
					{
						System.out.println("LOGIN FAILED");
					}
					break;

			case 3:	UserRegistration reg=new UserRegistration();
					reg.insertUserDetails();
					break;
		
			case 4: mainFlag=false;
					break;
			default:
					System.out.println("WRONG KEY PRESSED");
					break;

			}

		} while (mainFlag);
		System.out.println("Terminated Successfully");

		in.close();
	}
}