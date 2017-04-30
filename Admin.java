import java.util.*;

public class Admin {
    String userName = "";
    String password = "";
    boolean ifSignIn = false;


    public static void signUp(ArrayList<Admin> array) {
        System.out.println("please input your username");
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        boolean nameUsed = false;
        while (!nameUsed) {
            nameUsed = true;
            for (Admin a : array) {
                if (a.userName.equals(s1))
                {
                    nameUsed = false;
                    break;
                } 

            }
            if (!nameUsed) {
                System.out.printf("This username has been used, please change!\nPlease input your username:\n");
                s1 = in.next();
            }
            
        }
        
        System.out.printf("Please input your password:");
        String s2 = in.next();
        System.out.printf("Please input your password again:");
        String s3 = in.next();
        if (s2.equals(s3)) {          
            Admin a = new Admin();
            a.password = s2;
            a.userName = s1;
            array.add(a);
            System.out.println("Congratulations, "+a.userName+"!You have signed up as an administrator!");
        }
        else {
            System.out.println("Sorry, the two passwords are not identical");
        }  
    }

    public static void signIn(ArrayList<Admin> admins,ArrayList<Passenger> passengers,
                             ArrayList<Flight> flights,ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
        System.out.print("username:");
        String s1 = in.next();
        boolean success = false;
        for (Admin a : admins) {
            if (a.userName.equals(s1)) {
                System.out.print("password:");
                String s2 = in.next();
                if (a.password.equals(s2)) {
                    System.out.printf("Welcome back, %s!",a.userName);
                    a.ifSignIn = true;
                    success = true;
                    Admin.action(a,passengers,flights,orders);//sign in successfully -> admin action
                }else success = false;    
            }
        }
        if (!success)
            System.out.println("Sorry, wrong password or username, please check!");           
    }

    public static void action(Admin administrator,ArrayList<Passenger> passengers,ArrayList<Flight> flights,ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
        int opt = 0;
        boolean commandGet = false;
        while (!commandGet) {
            showMenu();
            opt = in.nextInt();
            if (opt > 6) 
                System.out.println("no such command!");
            else commandGet = true;

        }
        do 
        {
            switch (opt) {
                case 0: continue;
                case 1: creatFlight(ArrayList<Flight> flights);
                        break;
                case 2: 
                        break;
                case 3: 
                        break;
                case 4: 
                        break;
                case 5: 
                        break;
                case 6: 
                        break;
                default: break;
            }
            showAdminMenu();
            opt = in.nextInt();
        }while (opt != 0);
            
        
        

   }
    
    public static void showAdminMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s%s%s",
                          "0:Exit\n",
                          "1:creat flight\n",
                          "2:update flight\n",
                          "3:delete flight\n",
                          "4:super query\n",
                          "5:user management\n",
                          "6:query flight\n");
    }   



    public static void creatFlight(ArrayList<Flight> flights) {
        Canner in = new Scanner(System.in);
        System.out.println("Please input FlightID:");
        String FlightID = in.next();
        System.out.println("Please input start time:");
        String startTime = in.next();
        System.out.println("Please input arrival time:");
        String arrivalTime = in.next();
        System.out.println("Please input start city:");
        String startCity = in.next();
        System.out.println("Please input arrival city:");
        String arrivalCity = in.next();
        System.out.println("Please input departure date:");
        String departureDate = in.next();
        System.out.println("Please input price:");
        int price = in.nextInt();
        System.out.println("Please input seat capacity:");
        int seatCapacity = in.nextInt();
        System.out.println("Please input flight status:");
        String flightStatus = in.next();
        
        Flight f = new Flight(FlightID, startTime, arrivalTime, startCity, arrivalCity, departureDate, price, seatCapacity, flights);
        flights.add(f);
    }
}
