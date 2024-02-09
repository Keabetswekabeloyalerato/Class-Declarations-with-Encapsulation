public class Register{
 private Ticket[] tickets;
 private int numTickets;

 public Register(){
  tickets= new Ticket[100];
  numTickets=0;
  }
 public void add(Ticket ticket){
  if (numTickets >= 100){
  return;}
  
  tickets[numTickets]= ticket;
  numTickets++;
  }
 public boolean contains(String ticketID){
 for (int i = 0; i < numTickets; i++) {
     if (tickets[i].ID().equals(ticketID)) {
         return true;
            }
        }
         return false;
 }
 public Ticket retrieve(String ticketID){
   for (int i = 0; i < numTickets; i++) {
        if (tickets[i].ID().equals(ticketID)) {
            return tickets[i];
            }
        }
        return null; 
 }

}