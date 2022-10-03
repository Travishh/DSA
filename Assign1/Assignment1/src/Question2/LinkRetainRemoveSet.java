package Question2;

import java.util.Iterator;

/**
 *
 * @author Travis
 * 
 */
public class LinkRetainRemoveSet<E extends Comparable<E>> extends LinkedSet<E> {
    public LinkRetainRemoveSet(){
        super();
        firstNode = null;
    }
    
    @Override
    public boolean add(E o){
        Node<E> newNode = new Node<E>(o);
        //add to start of the list if firstNode is null
        if(firstNode == null || o.compareTo(firstNode.element) < 0){
            newNode.next = firstNode;
            firstNode = newNode;
            return true;    
        }
        //check for duplicates
        else if(contains(o)){
            return false;
        }
        //add to the list
        else{
            Node<E> currentNode = firstNode;
            currentNode = firstNode;
            while(currentNode.next != null && o.compareTo(currentNode.next.element) >0){
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            return true;
        }
    }
    //Remove Method
    public LinkRetainRemoveSet<E> remove(E start, E end){
        //set for storing the remove set
        LinkRetainRemoveSet<E> remove_Set = new LinkRetainRemoveSet<E>();
        Node<E> currentNode = firstNode;
        Node<E> previousNode = null;
        
        if(start != null){
            while(currentNode != null && currentNode.element.compareTo(start) !=0 ){
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        //if the first node is not null and is not at the end point
        while(currentNode != null && currentNode.element.compareTo(end) != 0){
            remove_Set.add(currentNode.element);
            currentNode = currentNode.next;
            if(previousNode != null){
                previousNode.next = currentNode;
            }else{
                firstNode = firstNode.next;
            }
        }
        
        while(currentNode != null){
            currentNode = currentNode.next;
        }
        
        //printing
        System.out.println("remove (" + start+ ", "+end+")");
        System.out.print("returned set = ");
        
        return remove_Set;
    }
    //Retain Method
    public LinkRetainRemoveSet<E> retain(E start, E end){
        LinkRetainRemoveSet<E> retain_Set = new LinkRetainRemoveSet<>();
        Node<E> currentNode = firstNode;
        Node<E> previousNode;
        
        if(contains(start)){
            while(currentNode != null && currentNode.element.compareTo(start)!=0){
                retain_Set.add(currentNode.element);
                currentNode = currentNode.next;
                firstNode = firstNode.next;
            }
        }
        
        previousNode = currentNode;
        while(currentNode != null && currentNode.element.compareTo(end) != 0){
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        previousNode = null;
        
        while(currentNode != null){
            retain_Set.add(currentNode.element);
            currentNode = currentNode.next;
        }
        //printing
        System.out.println("retain (" + start+ ", "+end+")");
        System.out.print("returned set = ");
        
        return retain_Set;
    }
    
    //for printing elements from the Set 
    public static void printSet(LinkRetainRemoveSet<Integer> element){
        System.out.print("{");
        Iterator i = element.iterator();
        if(i.hasNext()){
            System.out.print(i.next());
        }
        while(i.hasNext()){
            System.out.print("; " + i.next());
        }
        System.out.println("}");
    }
    
    public static void main(String[] args) {
        LinkRetainRemoveSet<Integer> element = new LinkRetainRemoveSet<>();
        LinkRetainRemoveSet<Integer> retainElement = new LinkRetainRemoveSet<>();
        LinkRetainRemoveSet<Integer> removeElement = new LinkRetainRemoveSet<>();
        element.add(1);        
        element.add(2);
        element.add(3);
        element.add(5);
        element.add(6);
        element.add(7);
        element.add(1);   
        element.add(4);
        
        //case: retain(null, 4)
        System.out.print("Set = ");
        printSet(element);
        retainElement = element.retain(2,6);
        printSet(retainElement);

        element.add(1);        
        element.add(2);
        element.add(3);
        element.add(5);
        element.add(6);
        element.add(7);
        element.add(1);   
        element.add(4);
        //case: remove(4,5)
        System.out.print("\nSet = ");
        printSet(element);
        removeElement = element.remove(4,5);
        printSet(removeElement);
        System.out.print("Set = ");
        printSet(element);
    }
}
