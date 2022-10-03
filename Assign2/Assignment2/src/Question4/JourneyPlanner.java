package Question4;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Travis Hun 19056383
 */
public class JourneyPlanner {
    private Map<String, Set<BusTrip>> locationMap;
    private Set<BusTrip> busDeparture;
    
    public JourneyPlanner(){
        locationMap = new HashMap<>();        
    }
    
    public boolean add(BusTrip bus){
        //check if the location exist in the map
        if(locationMap.get(bus.getDepartLocation())== null){
            busDeparture = new HashSet<>();
        }else{
            busDeparture = locationMap.get(bus.getDepartLocation());
        }
        busDeparture.add(bus);
        locationMap.put(bus.getDepartLocation(), busDeparture);
        return true;
    }
    
    public List<BusJourney> getPossibleJourneys(String startLocation, LocalTime startTime, String endLocation, LocalTime endTime){
        List<BusJourney> journeyList = new LinkedList<>();
        findPaths(startLocation, startTime, endLocation, endTime, new BusJourney(),journeyList);       
        return journeyList;        
    }
    
    public void findPaths(String currentLocation, LocalTime currentLocalTime, String endLocation, LocalTime endTime, BusJourney currentJourney, List<BusJourney> journeyList){       
        //check if the target location is found
        if(currentLocation.equals(endLocation)){
            journeyList.add(currentJourney.cloneJourney()); // clone the journey and add to the list
        }else{ //if not found
            if(locationMap.containsKey(currentLocation)){ //check for current location key and get the object at that key
                for(BusTrip bus:locationMap.get(currentLocation)){ 
                    //check if arrival location not in current location and current time less than or = to depart time
                    if(!currentLocation.contains(bus.getArrivalLocation()) && currentLocalTime.compareTo(bus.getDepartTime())<=0){
                        if(currentJourney.addBus(bus) == true){ //if can add recursively find the paths
                            findPaths(bus.getArrivalLocation(), bus.getArrivalTime(), endLocation, endTime, currentJourney, journeyList);
                            currentJourney.removeLastTrip();
                        }
                    }
                }
            }
        }
    }       
}
