import java.util.Scanner;
/**
 * The CarParkSim class contains the main car park simulation method.
 * It creates and manipulates the main objects, and handles user I/O.
 *
 * @author Stephan Jamieson and ...
 * @version 14/7/2019
 */
public class CarParkSim {
        
    public static void main(final String[] args) {
        final Scanner keyboard = new Scanner(System.in);
        // YOUR CODE HERE.
        // Declare variables to store a Clock and a Register object, create the relevant objects and assign them.
        Register r = new Register(); 
        Clock c = new Clock(Time.MIDNIGHT);
        TariffTable tariffTable = createTariffTable();
        String ticketID;
        
        System.out.println("Car Park Simulator");
        // YOUR CODE HERE.
        // Print current time.
        System.out.println("The current time is "+c.examine()+".");
        System.out.println("Commands: tariffs, advance {minutes}, arrive, depart, quit.");
        System.out.print(">");
        String input = keyboard.next().toLowerCase();
        
        while (!input.equals("quit")) {
            if (input.equals("advance")) {
                // YOUR CODE HERE.
                // Advance the clock, print the current time.
               int minutesToAdvance = keyboard.nextInt();
                Duration advanceDuration = new Duration("minute", minutesToAdvance);
                c.advance(advanceDuration); 
                System.out.println("The current time is "+c.examine().toString()+".");
             }
            else if (input.equals("arrive")) {
                // YOUR CODE HERE.
                // Create a new ticket, add it to the register, print details of ticket issued.
               
                Time issueTime = c.examine();
                Ticket ticket = new Ticket(issueTime); 
                ticketID = ticket.ID();
                r.add(ticket);
                System.out.println("Ticket issued: "+ticket+"."); }          
                
                else if (input.equals("tariffs")) {
               System.out.println(tariffTable);
               }

            else if (input.equals("depart")) {
                // YOUR CODE HERE.
                // Determine if ticket is valid, i.e. in the register.
                // If not, print error message.
                // If yes, retreive it, calculate duration of stay and print it.
                ticketID=keyboard.next();
                if (r.contains(ticketID)) {
                Ticket ticket =r.retrieve(ticketID);
                System.out.println("Ticket details: " + ticket + ".");
                System.out.println("Current time: " + c.examine() + ".");
                Duration difference = ticket.age(c.examine());
                System.out.println("Duration of stay: " + Duration.format(difference, "hours", "minutes") + "."); 
                 Money cost = tariffTable.getTariff(difference);
               if (cost != null) {
                        System.out.println("Cost of stay : " + cost + ".");
                    } }       
                else{
                System.out.println("Invalid ticket ID.");
                  }
                
                }
            else {
                System.out.println("That command is not recognised.");
                System.out.println("Commands: advance <minutes>, arrive, depart, quit.");
            }            
            System.out.print(">");
            input = keyboard.next().toLowerCase();
        }    
               
        System.out.println("Goodbye.");
     
     }
    
    private static TariffTable createTariffTable() {
    TariffTable tariffTable = new TariffTable(10);
    Currency currency = new Currency("R", "ZAR", 100);

    // Time Period 1: [0 minutes .. 30 minutes] : R10.00
    TimePeriod period1 = new TimePeriod(new Duration("minute", 0), new Duration("minute", 30));
    Money cost1 = new Money(1000L, currency);
    tariffTable.addTariff(period1, cost1);

    // Time Period 2: [30 minutes .. 1 hour] : R15.00
    TimePeriod period2 = new TimePeriod(new Duration("minute", 30), new Duration("hour", 1));
    Money cost2 = new Money(1500L, currency);
    tariffTable.addTariff(period2, cost2);

    // Time Period 3: [1 hour .. 3 hours] : R20.00
    TimePeriod period3 = new TimePeriod(new Duration("hour", 1), new Duration("hour", 3));
    Money cost3 = new Money(2000L, currency);
    tariffTable.addTariff(period3, cost3);

    // Time Period 4: [3 hours .. 4 hours] : R30.00
    TimePeriod period4 = new TimePeriod(new Duration("hour", 3), new Duration("hour", 4));
    Money cost4 = new Money(3000L, currency);
    tariffTable.addTariff(period4, cost4);

    // Time Period 5: [4 hours .. 5 hours] : R40.00
    TimePeriod period5 = new TimePeriod(new Duration("hour", 4), new Duration("hour", 5));
    Money cost5 = new Money(4000L, currency);
    tariffTable.addTariff(period5, cost5);

    // Time Period 6: [5 hours .. 6 hours] : R50.00
    TimePeriod period6 = new TimePeriod(new Duration("hour", 5), new Duration("hour", 6));
    Money cost6 = new Money(5000L, currency);
    tariffTable.addTariff(period6, cost6);

    // Time Period 7: [6 hours .. 8 hours] : R60.00
    TimePeriod period7 = new TimePeriod(new Duration("hour", 6), new Duration("hour", 8));
    Money cost7 = new Money(6000L, currency);
    tariffTable.addTariff(period7, cost7);

    // Time Period 8: [8 hours .. 10 hours] : R70.00
    TimePeriod period8 = new TimePeriod(new Duration("hour", 8), new Duration("hour", 10));
    Money cost8 = new Money(7000L, currency);
    tariffTable.addTariff(period8, cost8);

    // Time Period 9: [10 hours .. 12 hours] : R90.00
    TimePeriod period9 = new TimePeriod(new Duration("hour", 10), new Duration("hour", 12));
    Money cost9 = new Money(9000L, currency);
    tariffTable.addTariff(period9, cost9);

    // Time Period 10: [12 hours .. 1 day] : R100.00
    TimePeriod period10 = new TimePeriod(new Duration("hour", 12), new Duration("day", 1));
    Money cost10 = new Money(10000L, currency);
    tariffTable.addTariff(period10, cost10);

    return tariffTable;
}
}

       
       




