package ex2_1;

import java.util.Random;
import java.util.Set;

/**
 *
  * @author Travis 19056383
 */
public class TimeComplexityArraySet {
    
    public static void main(String[] args) {
        Set<Integer> set = new ArraySet<>();
        Random rand = new Random();
        int n = 100000;
        
        //Add
        long startTimeAdd = System.nanoTime();
        for(int i=0;i<n;i++){
            set.add(rand.nextInt());
        }
        long endTimeAdd = System.nanoTime();
        double addTimeDifference = endTimeAdd - startTimeAdd;
        System.out.println("Time complexity it takes to add to ArraySet: \n" + addTimeDifference + " Nanoseconds");
        
        //Find
        long startTimeFind = System.nanoTime();
        boolean find = set.contains(set.size()/2);
        long endTimeFind = System.nanoTime();
        double timeDifferenceFind = endTimeFind - startTimeFind; 
        System.out.println("\nTime complexity it takes to find element n\\2 from ArraySet: \n" + timeDifferenceFind + " Nanoseconds");
        
        //Remove
        long startTimeRemove = System.nanoTime();
        for(int i=0;i<n;i++){
            set.remove(i);
        }
        long endTimeRemove = System.nanoTime();
        double removeTimeDifference = endTimeRemove - startTimeRemove;
        System.out.println("\nTime complexity it takes to remove from ArraySet: \n" + removeTimeDifference + " Nanoseconds");      
    }  
}
