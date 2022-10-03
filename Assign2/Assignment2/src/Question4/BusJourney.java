package Question4;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Travis Hun 19056383
 */
public class BusJourney {
    private List<BusTrip> busList;
    
    public BusJourney(){
        busList = new LinkedList<>();
    }
    public BusJourney(List<BusTrip> list){
        busList = new LinkedList<>();
        for(BusTrip trip:list){
            busList.add(trip);
        }
    }
    
    public boolean addBus(BusTrip bus){
        //check if empty
        if(busList.isEmpty()){
            busList.add(bus);
            return true;
        }
        //check conditions
        else if(getDestination().equals(bus.getDepartLocation()) &&
                getDestinationTime().compareTo(bus.getDepartTime()) <=0  &&
                !containsLocation(bus.getArrivalLocation())){
            busList.add(bus);
            return true;
        }
        
        return false;       
    }
    
    public boolean removeLastTrip(){
        if(!busList.isEmpty()){
            busList.remove(busList.get(busList.size()-1));
            return true;
        }
        return false;
    }
    
    public boolean containsLocation(String location){
        for(int i =0; i<busList.size();i++){
            if(busList.get(i).getDepartLocation().equals(location) || busList.get(i).getArrivalLocation().equals(location)){
                return true;
            }
        }
        return false;
    }
    
    public String getOrigin(){
        //if the list is not empty get the first depart location in the list
        if(!busList.isEmpty()){
            String origin = busList.get(0).getDepartLocation();
            return origin;
        }
        return null;
    }
    
    public String getDestination(){
        //if the list is not empty get the last depart location in the list
        if(!busList.isEmpty()){
            String destionation = busList.get(busList.size()-1).getArrivalLocation();
            return destionation;
        }
        return null;
    }
    
    public LocalTime getOriginTime(){
        //if the list is not empty get the first depart time in the list
        if(!busList.isEmpty()){
            LocalTime originTime = busList.get(0).getDepartTime();
            return originTime;
        }
        return null;      
    }
    
    public LocalTime getDestinationTime(){
        //if the list is not empty get the last depart time in the list
         if(!busList.isEmpty()){
            LocalTime originTime = busList.get(busList.size()-1).getDepartTime();
            return originTime;
        }
        return null;
    }
    
    public BusJourney cloneJourney(){
        //create a new journey with existing bus list
        BusJourney newJourney = new BusJourney(busList);
        return newJourney;
    }
    
    public int getNumberOfBusTrips(){
        int tripCounter =0;
        for(int i =0;i<busList.size();i++){
            tripCounter++;
        }
        return tripCounter;
    }
    
    public double getTotalCost(){
        double totalCost = 0;
        for(int i=0;i<getNumberOfBusTrips();i++){
            totalCost += busList.get(i).getCost();
        }
        return totalCost;
    }
    
    public String toString(){
        String output = "";
        output += "TOTAL COST: $" + getTotalCost();
        output += "!!!\n";
        for(int i=0;i<busList.size();i++){
            output +=  busList.get(i) + "\n";
        }
        output += "\n";
        return output;
    }    
}
