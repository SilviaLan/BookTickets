package ProjectAirplaneBookSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Passenger {
//	int passengerID = 0;
//	String realName = "";
//	String identityID = "";
//	String password = "";
	static boolean ifSignIn = false;
	
	public static void SignUp(){
		Scanner in = new Scanner(System.in);
		System.out.println("please input your identityID:");
		String identityID = in.next();
		System.out.println("please input your realname:");
		String realName = in.next();
		System.out.println("Please input your password:");
		String password = in.next();
		System.out.println("Please check your password:");
		String passwordCheck = in.next();
		while (!password.equals(passwordCheck)){
			System.out.println("Sorry, the two passwords are not identical.\nPlease check your password again:\n");
			passwordCheck = in.next();
		}
		
			int passengerID = (int)(Math.random()*90000 + 100000);
			//confirm passengerID is unique
//			for(Passenger p : arrayP){
//				if (passengerID ==arrayP.passengerId ){
//					int passengerID = (int)(Math.random()*90000 + 100000);
//				}
			
			System.out.printf("Congratulations, %s!\nYou have signed up as an passenger!",realName);
			System.out.printf("Your passengerID is %d.\n",passengerID);
			
		}
		

	public static void SignIn(ArrayList<Passenger> passengers){
		Scanner in = new Scanner(System.in);
		System.out.println("identityID/real name:");
		String input = in.next();
		success = signInConfirm(input,passengers);
			
			while(!success){
				System.out.println("Sorry,wrong password or username, please check!");
				System.out.println("Enter 1 to check the information.\nEnter 2 to sign up as a new passenger.");
				int opt = in.nextInt();
				if (opt>2){
					System.out.println("No such command!");
				}
				switch(opt){
				case 1:
					System.out.println("identityID/real name:");
					input = in.next();
					success = signInConfirm(input,passengers);
					break;
					
				case 2:
					SignUp();
					break;
					
				default: break;
				}
			}

		}
		
	//signin confirm
	public static boolean signInConfirm(String input,ArrayList<Passenger> passengers){
		Scanner in = new Scanner(System.in);
		boolean success = false;
		for (Passenger p : passengers){
			if(input.equals(p.identityID)){
				System.out.println("password:");
				String password = in.next();
				if(password.equals(p.password)){
					 System.out.printf("Welcome back, %s!",input);
					 p.ifSignIn = true;
					 success = true; 
				}

			}
			if(input.equals(p.realName)){
				System.out.println("password:");
				String password = in.next();
				if(password.equals(p.password)){
					 System.out.printf("Welcome back, %s!",input);
					 p.ifSignIn = true;
					 success = true; 
				}
			}
	
		}
		return success;
	}
	
		
		
		//query Flight
//		public static void queryFlight(){
//			Scanner in = new Scanner(System.in);
//			System.out.println("Welcome to query the flight:");
//			int opt = 0;
//			
//		}
		
		public static void queryOrder(String p.passengerID , ArrayList<Order> orders,ArrayList<Passenger> passengers){
			if (ifSignIn){
				String passengerID = Integer.toString(p.passengerID);
				for(Order o: orders){
					if((o.passengerId).equals(passengerID)){
						System.out.println("Your orders are as follows:");
						System.out.printf("%15s%15s%15s%15s%15s","PassengerID","FlightID","Seat","CreateDate","Status");
						
						
						
//??how to show all the order						
//						System.out.println("%15s%15s%15s%15s%15s",o.passengerID,o.flight,o.seat,o.createDate,status);
				
					}
					else{
						System.out.println("You haven't booked any ticket yet.");
					}
				}	
			}
			else{
				System.out.println("You haven't signed in yet.\nPlease sign in or sign up.\n(Enter 1 for sign in, 2 for sign up)");
				Scanner in = new Scanner(System.in);
				int opt = in.nextInt();
				if(opt == 1){
					SignIn(passengers);
				}
				else{
					SignUp();
				}
				
			}
	
}
		
		

	
	
	
	
			


