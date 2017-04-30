import java.util.*;

public class Project {
    public static void main(String[] args) {
        ArrayList<Admin> Admins = new ArrayList<Admin>();
  //      ArrayList<Passenger> Passengers = new ArrayList<Passenger>();

        Scanner in = new Scanner(System.in);
        int opt = 0;
      
        boolean commandGet = false;
        showMenu();
        while (!commandGet) {
         //   showMenu();
            opt = in.nextInt();
            if (opt > 4) 
                System.out.println("no such command!");
            else commandGet = true;

        }
        do 
        {
            switch (opt) {
                case 0: continue;
                case 1: Admin.signUp(Admins);
                        break;
//              case 2: Passengers.SignUp(Passengers);
 //                     break;
                case 3: Admin.signIn(Admins);
            //          Admin.action(Admins, Passengers, Flights, Orders);
                        break;
        //      case 4: Passengers.SignIn(Passengers);
        //              Passengers.action(Passengers, Flights, Orders);
        //              break;
           
                default: break;
            }
            showMenu();
            opt = in.nextInt();
        }while (opt !=0);
            
    }
    public static void showMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s",
                          "0:Exit\n",
                          "1:Sign up as Administrators\n",
                          "2:Sign up as Passengers\n",
                          "3:Sign in as Administrators\n",
                          "4:Sign in as Passengers\n"); 
                          
    }
}