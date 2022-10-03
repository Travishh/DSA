package ex1_4;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Travis 19056383
 */
public class RandomObtainableList<E> extends ArrayList implements RandomObtainable{
    private final Random rand;
    
    public RandomObtainableList(){
        super();
        rand = new Random();
    }
    
    public static void main(String[] args) {
        RandomObtainableList<String> list = new RandomObtainableList<>();
        
        System.out.println("Adding Objects to the list...\n");
        list.add("Travis");
        list.add("Monday");
        list.add("DSA");
        list.add("R&D");
        list.add("Web Development");        
        list.add("Thread");

        System.out.println("Print all the objects in the list: ");
        for(Object obj:list){
            System.out.print("[" + obj + "]");
        }
        
        System.out.println("\n\nGet Random object from the list: ");
        System.out.println(list.getRandom());
        
        System.out.println("\nRemove Random object from the list: ");
        System.out.println(list.removeRandom());
        
        System.out.println("\nThe remaining objects in the list: ");
        for(Object obj:list){
            System.out.print("[" + obj + "]");
        }
        System.out.println();
        
    }
    
    @Override
    public Object getRandom() throws NoSuchElementException {
        return this.get(rand.nextInt(this.size()));
    }

    @Override
    public boolean removeRandom() throws UnsupportedOperationException {
        return this.remove(this.get(rand.nextInt(this.size())));
 
    }
    
}
