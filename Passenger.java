package ProjectAirplaneBookSystem;

import java.util.*;
import java.util.GregorianCalendar;

public class Passenger {
	ArrayList<Order> Orders = new ArrayList<Order>();
	Flight flight = new Flight("", "", "", "", "", "", 0, 0, 0);
	Date creatDate = new Date();
	Passenger passenger = new Passenger(0,"","","");
	Order order = new Order(passenger,"",flight,creatDate);
	
	int passengerID = 0;
	String realName = " ";
	String identityID = " ";
	String password = " ";
	boolean ifSignIn = false;
	String seat;
	
	public Passenger(int passengerID,String realName, String identityID,String password){
		passengerID = 0;
		realName = " ";
		identityID = " ";
		password = " ";
		ifSignIn = false;
	}

	public int getPassengerID() {
		return passengerID;
	}
	
	public String getIdentityID() {
		return identityID;
	}
	public String getRealName() {
		return realName;
	}
	public String getPassword() {
		return password;
	}
	public boolean getIfSignIn(){
		return ifSignIn;
	}
	public void setIfSignIn(boolean b) {
	ifSignIn = b;
	}
//	public Order getOrder() {
//		return order;
//	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
	public void copy(Passenger p) {
		passengerID = p.getPassengerID();
		realName = p.getRealName();
		identityID = p.getIdentityID();
		password = p.getPassword();
		ifSignIn = p.getIfSignIn();
	}
	
	public static void signUp(ArrayList<Passenger> passengers,Passenger passenger) {
		Scanner in = new Scanner(System.in);
		System.out.println("please input your identityID:");
		String identityID = in.next();

		System.out.println("please input your realname:");
		String realName = in.next();

		System.out.println("Please input your password:");
		String password = in.next();

		System.out.println("Please check your password:");
		String passwordCheck = in.next();

		while (!password.equals(passwordCheck)) {
			System.out.println("Sorry, the two passwords are not identical.\nPlease check your password again:\n");
			passwordCheck = in.next();
		}
		
		int passengerID = (int)(Math.random()*90000 + 100000);
		//confirm passengerID is unique
		for(Passenger p : passengers) {
			if (passengerID == passenger.getPassengerID() )
				passengerID = (int)(Math.random()*90000 + 100000);
			}

		Passenger p1 = new Passenger(passengerID, realName, identityID, password);
        passengers.add(p1);

		System.out.printf("Congratulations, %s!\nYou have signed up as an passenger!",realName);
		System.out.printf("Your passengerID is %d.\n",passengerID);
            	
    }

	public static void signIn(ArrayList<Passenger> passengers,Passenger passenger) {
		Scanner in = new Scanner(System.in);
		System.out.println("identityID/real name:");
		String input = in.next();
		boolean success = signInConfirm(input,passengers);
			
			while(!success){
				System.out.println("Sorry,wrong password or username, please check!");
				System.out.println("1:check the information.\n2:to sign up as a new passenger.");
				int opt = in.nextInt();
				if (opt != 1 || opt != 2)
					System.out.println("No such command!");
				
				
                switch(opt) {
				case 1:
					System.out.println("identityID/real name:");
					input = in.next();
					success = signInConfirm(input,passengers);
					break;
					
				case 2:
					signUp(passengers,passenger);
					break;
					
				default: break;
				}
			}
	
    }
		
	//signin confirm
	public static boolean signInConfirm(String input,ArrayList<Passenger> passengers) {
		Scanner in = new Scanner(System.in);
		boolean success = false;
		for (Passenger p : passengers) {
			if(input.equals(p.identityID)) {
				System.out.printf("password:");
				String password = in.next();
				if(password.equals(p.password)) {
					 System.out.printf("Welcome back, %s!",input);
					 p.setIfSignIn(true); 
					 success = true; 
				}
            }
        
			if(input.equals(p.realName)){
				System.out.println("password:");
				String password = in.next();
				if(password.equals(p.password)){
					 System.out.printf("Welcome back, %s!",input);
					 p.setIfSignIn(true);
					 success = true; 
				}
			}
	
        }
		return success;
    }
			
	public static void queryOrder(Passenger passenger , ArrayList<Order> orders,ArrayList<Passenger> passengers,ArrayList<Flight> flights) {
		if (passenger.getIfSignIn()) {
			int passengerID = passenger.getPassengerID();
			
			for(Order o: orders) {
				if(passengerID == o.getPassengerID()) {
					System.out.println("Your orders are as follows:");
					System.out.printf("%15s%15s%15s%15s%15s","PassengerID","FlightID","Seat","CreateDate","Status");
             											
//??how to show all the order						
						System.out.printf("%15s%15s%15s%15s%15s\n",passenger.passengerID,o.getFlight(),o.getSeat(),o.getDate(),o.getStatus());
				
					}
					else
						System.out.println("You haven't booked any ticket yet.");
					
				}
			//warning! lack return!
		}
			
		    	else {
			    	System.out.println("You haven't signed in yet.\nPlease sign in or sign up.\n\tEnter 1 for sign in\n\tEnter 2 for sign up)");
				    Scanner in = new Scanner(System.in);
				    int opt = in.nextInt();
				    switch(opt){
				    case 1:signIn(passengers,passenger);
				    	break;
				    case 2:signUp(passengers,passenger);
				    	break;
				    default:System.out.println("No such command!");
				    	break;
				    }
		    	}
            } 
       
	//	query Flight
	public static void queryFlight(ArrayList<Passenger> passengers,ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders,Date creatDate){
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome to query flight information:");
			System.out.println("1:query by flight ID\n2:query be start city, arrival city and date\n3:for return");
			int opt = in.nextInt();
			switch(opt){
			case 1: queryByID(flights);
			break;
			case 2: queryByInfo(flights);
			break;
			case 3: showMenu(passenger);
			break;
			default:System.out.println("No such command!");	
			}
			
			//Ìø×ª reserveFlight
			System.out.println();
			System.out.println("1:reserve the flight\n2:return to main menu");
			opt = in.nextInt();
			switch (opt){
			case 1:
				reserveFlight(passengers,flights,passenger,orders,creatDate);
				break;
			case 2:
				action(passengers, flights,passenger,orders,creatDate);
				break;
			//warning! no return!
			default:
				System.out.println("No such command!");
			}
			
	}
	
	
	public static void queryByID(ArrayList<Flight> flights){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight ID:");
		String ID = in.next();
		int s = -1;
		for (Flight f: flights){
			if (ID.equals(f.getFlightID())){
				System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
			else{
				s = f.getFlightID().indexOf(ID);
				
				//Ä£ºý²éÕÒ
				if (s!=-1){
					System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
				}
				else{
					System.out.println("No such flight!");
			
				}
			}	
		}
		
	}
	
	public static void queryByInfo(ArrayList<Flight> flights){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight Infomation:");
		String info = in.next();
		for (Flight f: flights){
			if (info.equals(f.getStartCity())){
				System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
			else{
				if (info.equals(f.getArrivalCity())){
					System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
				}
				else{
					if (info.equals(f.getDepartureDate())){
						System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
					}
					else{
						System.out.println("No such flight!");
					}
				}
			}
		}
		
	}
	
	private static void showMenu(Passenger passenger) {
	      for (int i = 0; i < 50; i++) System.out.printf("_");
	      if(passenger.ifSignIn){
	    	  System.out.printf("\nWelcome to book ticket!\n%s%s%s%s",
	    			  			"0:Exit\n",
	    			  			"1:query the flight\n",
	    			  			"2:reserve the flight\n",
	    			  			"3:unsubscribe the flight\n");
	      }
	      else{
	    	  System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s%s",
			  			"0:Exit\n",
			  			"1:Sign in\n",
			  			"2:Sign up\n",
			  			"3:query the flight\n",
			  			"4:reserve the flight\n",
			  			"5:unsubscribe the flight\n");
	      }
	  }   
	
	public static void action(ArrayList<Passenger> passengers,ArrayList<Flight> flights, Passenger passenger,ArrayList<Order> orders,Date creatDate){
		Scanner in = new Scanner(System.in);
	    int opt = 0;
	    boolean commandGet = false;
	    while (!commandGet) {
	    	showMenu(passenger);
	        opt = in.nextInt();
	          
	        if(passenger.ifSignIn){
	        	if (opt > 3) 
	        		System.out.println("no such command!");
	            else commandGet = true;
	        	  
	            do 
	            {
	                switch (opt) {
	                    case 0: continue;
	          			case 1:
	          				queryFlight(passengers,flights,passenger,orders,creatDate);
	      					break;
	          			case 2:
	          				reserveFlight(passengers,flights,passenger,orders,creatDate);
	          				break;
	          			case 3:
	          				unsubscribeFlight(passengers, flights,passenger,orders, creatDate);
	          				break;
	                  }    
	                  showMenu(passenger);
	                  opt = in.nextInt();
	              }while (opt != 0);
	          }
	          else{
	        	  if (opt > 5) 
	                  System.out.println("no such command!");
	              else commandGet = true;
	        	  
	              do 
	              {
	                  switch (opt) {
	                    case 0: continue;
	        			case 1:
	    				signUp(passengers,passenger);
	    				break;
	    			case 2:
	    				signIn(passengers,passenger);
	    				break;
	    			case 3:
	    				queryFlight(passengers,flights,passenger,orders,creatDate);
	    				break;
	    			case 4:
	    				reserveFlight(passengers,flights,passenger,orders,creatDate);
	    				break;	
	    			case 5:
	    				unsubscribeFlight(passengers, flights,passenger,orders, creatDate);
	    				break;
	                  }
	                  showMenu(passenger);
	                  opt = in.nextInt();
	              }while (opt != 0);    
	          }
	      }
	 }

	public static void reserveFlight(ArrayList<Passenger> passengers,ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders,Date creatDate){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to reserve the flight!");
		boolean flag = true;
		do{
		if(passenger.getIfSignIn()){
			System.out.println("Please enter the flight ID:");
			String ID = in.next();
			int opt = 0;
			for (Flight f:flights){
				if (ID.equals(f.getFlightID())){
					System.out.println("Do you want to book this flight?");
					System.out.printf("%15f%15f%15f%15f%15f%15f%10d%10d",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
					System.out.println("1: Yes\n2: No");
					opt = in.nextInt();
					switch(opt){
					case 1:
						reserveFlightConfirm(ID,passengers,flights,passenger,orders,f,creatDate);
						flag = false;
						break;
					case 2:
						break;
					default:
						System.out.println("No such command!");
						break;
					}
				}
				else{
					System.out.println("Sorry!No such flight!\nPlease reenter");
				}
			}
		}
		else{
			System.out.println("Sorry!You haven't sign in!\nPlease sign in.");
			signIn(passengers,passenger);
		}
		}while(flag);
		
	}
	
	//reserve confirm
	public static void reserveFlightConfirm(String ID,ArrayList<Passenger> passengers,ArrayList<Flight> flights, Passenger passenger,ArrayList<Order> orders,Flight f,Date creatDate){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your password:");
		String password = in.next();
		if (password.equals(passenger.getPassword())){
			passenger.passenger = passenger;
			//seat problem!
			String seat = "";
			Order o = new Order(passenger,seat,f,creatDate);
			orders.add(o);
			//warning! lack add into OrderClass!
		}
		else{
			System.out.println("Sorry!Wrong password!");
		//lack retry!
		}
	}

	
	public static void unsubscribeFlight(ArrayList<Passenger> passengers,ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders,Date creatDate){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to unsubscribe the flight!");
		boolean flag = true;
		do{
		if(passenger.getIfSignIn()){
			System.out.println("Please enter the flight ID:");
			String ID = in.next();
			int opt = 0;
			for (Order o:orders){
				if (ID.equals(o.getFlight().getFlightID())){
					System.out.println("Do you want to unsubscrib this flight?");
					System.out.printf("%15f%15f%15f%15f%15f%15f%15f%15f%15f",
							o.getFlight().getFlightID(),o.getFlight().getStartTime(),o.getFlight().getArrivalTime(),o.getFlight().getStartCity(),o.getFlight().getArrivalCity(),o.getFlight().getDepartureDate(),o.getFlight().getFlightStatus(),o.getDate());
					System.out.println("1: Yes\n2: No");
					opt = in.nextInt();
					switch(opt){
					case 1:
						unsubscribeFlightConfirm(ID,passengers,flights,passenger,orders,creatDate);
						flag = false;
						break;
					case 2:
						break;
					default:
						System.out.println("No such command!");
						break;
					}
				}
				else{
					System.out.println("Sorry!No such flight!\nPlease reenter");
				}
			}
		}
		else{
			System.out.println("Sorry!You haven't sign in!\nPlease sign in.");
			signIn(passengers,passenger);
		}
		}while(flag);
		
	}
	
	public static void unsubscribeFlightConfirm(String ID,ArrayList<Passenger> passengers,ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders,Date creatDate){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your password:");
		String password = in.next();
		if (password.equals(passenger.getPassword())){
			//not correct!
			orders.remove(o);
			//warning! lack add into OrderClass!
		}
		else{
			System.out.println("Sorry!Wrong password!");
		//lack retry!
		}
	
	}
}


	



		
		

	
	
	
	
			


