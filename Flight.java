import java.util.*;
public class Flight
{
    // id for flight which is alloted everytime a new Flight Object is created
    static int id = 0;
    int flightID;
    //Availabel number of tickets currently in flight
    int Businesstickets;
    int Ecotickets;
    //current price
    int Businessprice;
    int Ecoprice;
    //string list of all passenger details for printing
    ArrayList<String> BusinesspassengerDetails;
    ArrayList<String> EcopassengerDetails;
    //list of all passenger IDs
    ArrayList<Integer> BusinesspassengerIDs;
    ArrayList<Integer> EcopassengerIDs;
    //list of number of tickets booked by every passenger ID
    ArrayList<Integer> BusinessbookedTicketsPerPassenger;
    ArrayList<Integer> EcobookedTicketsPerPassenger;
    //list of cost paid by every passenger ID, used to calculate refund while cancelling
    ArrayList<Integer> passengerCost;

    //constructor to set values
    public Flight()
    {
        Businesstickets = 50;
        Businessprice = 2000;
        Ecotickets = 50;
        Ecoprice = 1000;
        id = id + 1;
        flightID = id;
        BusinesspassengerDetails = new ArrayList<String>();
        EcopassengerDetails = new ArrayList<String>();
        BusinesspassengerIDs = new ArrayList<Integer>();
        EcopassengerIDs = new ArrayList<Integer>();
        BusinessbookedTicketsPerPassenger = new ArrayList<Integer>();
        EcobookedTicketsPerPassenger = new ArrayList<Integer>();
        passengerCost = new ArrayList<Integer>();
    }

    //add passenger details to flight
    public void addPassengerDetails(String passengerDetail,int numberOfTickets,int passengerID,String cls)
    {  
        if(cls.equalsIgnoreCase("Business")){
            BusinesspassengerDetails.add(passengerDetail);
            BusinesspassengerIDs.add(passengerID);
            passengerCost.add(Businessprice * numberOfTickets);
            String fea = cls+" Class";
            BusinesspassengerDetails.add(fea);


        //updating price using logic in the problem statement
            Businessprice+=200 * numberOfTickets;

        //updating available number of tickets
            Businesstickets-= numberOfTickets;
            BusinessbookedTicketsPerPassenger.add(numberOfTickets);
            System.out.println("Business classBooked Successfully!");
        }

        else if(cls.equalsIgnoreCase("Economic")){
            EcopassengerDetails.add(passengerDetail);
            EcopassengerIDs.add(passengerID);
            passengerCost.add(Businessprice * numberOfTickets);
            String fea = cls+" Class";
            EcopassengerDetails.add(fea);


        //updating price using logic in the problem statement
            Ecoprice+=100 * numberOfTickets;

        //updating available number of tickets
            Ecotickets-= numberOfTickets;
            EcobookedTicketsPerPassenger.add(numberOfTickets);
            System.out.println("Economic class Booked Successfully!");
        }
        else{
            System.out.println("Class you have mentioned above didn't present");
        }
        

    }

    //cancel tickets booked by a passenger ID
    public void cancelTicket(int passengerID,String cls1)
    {
        if(cls1.equals("1")){
            int indexToRemove = BusinesspassengerIDs.indexOf(passengerID);
            if(indexToRemove < 0)
            {
                System.out.println("Passenger ID not found!");
                return;
            }
            int ticketsToCancel = BusinessbookedTicketsPerPassenger.get(indexToRemove);

            //increase number of available tickets
            Businesstickets+=ticketsToCancel;
            //change price to new value after cancellation
            Businessprice-= 200 * ticketsToCancel;

            //calculate refund
            System.out.println("Refund Amount after cancel : " + (passengerCost.get(indexToRemove)-(200*Businessprice)));

            //remove details of passenger from all lists
            BusinessbookedTicketsPerPassenger.remove(indexToRemove);
            BusinesspassengerIDs.remove(Integer.valueOf(passengerID));
            BusinesspassengerDetails.remove(indexToRemove);
            passengerCost.remove(indexToRemove);

            System.out.println("Cancelled Booked Tickets Successfully!");
        }
        else if(cls1.equals(2)){
            int indexToRemove = EcopassengerIDs.indexOf(passengerID);
            if(indexToRemove < 0)
            {
                System.out.println("Passenger ID not found!");
                return;
            }
            int ticketsToCancel = EcobookedTicketsPerPassenger.get(indexToRemove);

            //increase number of available tickets
            Ecotickets+=ticketsToCancel;
            //change price to new value after cancellation
            Ecoprice-= 200 * ticketsToCancel;
            
            //calculate refund
            System.out.println("Refund Amount after cancel : " + passengerCost.get(indexToRemove));

            //remove details of passenger from all lists
            EcobookedTicketsPerPassenger.remove(indexToRemove);
            EcopassengerIDs.remove(Integer.valueOf(passengerID));
            EcopassengerDetails.remove(indexToRemove);
            passengerCost.remove(indexToRemove);

            System.out.println("Cancelled Booked Tickets Successfully!");
        }
                //find the index to remove from all lists
     
        
                //find the index to remove from all lists
        

    }

    //functions to print details about flights and passengers
    public void flightSummary()
    {
        System.out.println("Flight ID " + flightID + " --" + "Remaining Business Tickets " + Businesstickets + " --"+" Current Business Price:"+Businessprice+ " Economic tickets:"+Ecotickets+" Current Economic Price:"+Ecoprice);
    }
    public void printDetails()
    {
       System.out.println("Flight ID " + flightID + "->");
        for(String detail : BusinesspassengerDetails)
            System.out.println(detail);
        for(String detail: EcopassengerDetails)
            System.out.println(detail);
    }


}