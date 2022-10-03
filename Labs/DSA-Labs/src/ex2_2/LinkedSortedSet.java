package ex2_2;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Travis 19056383
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E> {
    
    public LinkedSortedSet(){
        super();
        firstNode = null;
        numElements = 0;
    }
    
    public LinkedSortedSet(Collection<? extends E> c){
        super(c);
    }
    
    @Override
    public boolean add(E o){
        Node<E> newNode = new Node<E>(o);
        
        if(firstNode == null || o.compareTo(firstNode.element) < 0){
            newNode.next = firstNode;
            firstNode = newNode;
            numElements++;
            System.out.println("================================" );
            System.out.println("Adding " +o+ " to the List" );
            System.out.println("Adding " +o+ " to the start of the List" );
            return true;    
       
        }
        else if(firstNode == null || o.compareTo(firstNode.element) == 0){
            System.out.println("================================" );
            System.out.println("Adding " +o+ " to the List" );
            System.out.println("The Element already Exist");
            return false;
        }
        else{
            Node<E> currentNode = firstNode;
            currentNode = firstNode;
            while(currentNode.next != null && o.compareTo(currentNode.next.element) >=0){
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            numElements++;
            System.out.println("================================" );
            System.out.println("Adding " +o+ " to the List" );
            return true;
        }
    }
    
    public static void main(String[] args) {
        Set<String> color = new LinkedSortedSet<String>();
        
        color.add("Orange");
        System.out.println(color);
        
        color.add("Blue");
        System.out.println(color);
        
        color.add("Blue");
        System.out.println(color);
        
        color.add("Red");
        System.out.println(color);
        
        color.add("Yellow");
        System.out.println(color);
        
        color.add("Black");
        System.out.println(color);
        
        color.add("LightBlue");
        System.out.println(color);
        
        color.add("Zunza");
        System.out.println(color);

    }
}
