package Question3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
// @author Travis Hun 19056383
 */
public class HashSetWithChaining<E> implements Set<E>{
    private int initialCapacity;
    protected Node<E>[] table;
    private int numElements;
    private float loadFactor;
    
    public HashSetWithChaining(){
        this.initialCapacity = 10;
        this.loadFactor = 0.75f;
        numElements = 0;
        table = new Node[this.initialCapacity];
    }

    public HashSetWithChaining(int initialCapacity, float loadFactor){
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        numElements = 0;
        table = new Node[this.initialCapacity];
    }
    
    
    public int hashCode(E e){
        return e.hashCode()%table.length;     
    }
    
    public void expandCapacity(){
        Node<E>[] newTable = new Node[initialCapacity * 2];
        for(int i = 0; i<table.length; i++){
            if(table[i] != null){
                newTable[i] = table[i];
            }
        }
        table = newTable;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean found = false;
        if(isEmpty() || o == null){
            found = false;
        }

        int index = hashCode((E) o);
        if(index<0){
            index = index * -1;
        }
        if(table[index] == null){
            found = false;
        }
        Node<E> checkNode = table[index];
        while(checkNode!= null){
            if(checkNode.element.equals(o)){
                found = true;
            }
            checkNode = checkNode.next;
        }
       return found; 
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) Arrays.stream(table).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(table).toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node(e);
        if(contains(e)){ //if element already exist dont add
            return false;
        }
        if((numElements+1)/(float)table.length > loadFactor){ //prevent collision
            System.out.println("LOAD FACTOR EXCEEDED, EXPANDING CAPACITY...");
            expandCapacity();
        }
        int index = hashCode((E) e); //get hash code
        
        if(table[index] == null){ //if table at index = null add the new node
            table[index] = newNode;
            numElements++;
            return true;
        }
        
        //go through table to check if the index is existed
        Node<E> currentNode = table[index];
        while(currentNode.next != null) {
            if(currentNode.element.equals(newNode.element)){
                return false;
            }
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        numElements++;
        return true;          
    }

    @Override
    public boolean remove(Object o) {
        boolean found = false; //flag
        if(isEmpty() || o == null){
            found = false;
        }
        int index = hashCode((E) o); //get the hash code of o
        if(table[index] == null){ //if element at index = null 
            found = false;
        }
        //remove the recent node
        Node<E> recentNode = table[index];
        if(recentNode.element.equals(o)){
            table[index] = recentNode.next;
            numElements--;
            found = true;
        }
        //remove the chain node
        Node<E> chainNode = recentNode;
        while(recentNode.next != null) {
            recentNode = recentNode.next;
            if(recentNode.element.equals(o)) {
                chainNode.next = recentNode.next;
                numElements--;
                found = true;
            }
            chainNode = recentNode;
        }                    
        return found;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object element:c){
            if(contains(element)){
                return true;
            }         
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(Object element:c){
            add((E) element);
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Object element:c){
            if(contains(element)){
                add((E) element);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object element:c){
            remove(element);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        table = new Node[initialCapacity];
        numElements =0;
    }
    
    @Override
    public String toString(){
        System.out.println("Size is:" +numElements);
        String output = "";
        for(int i = 0; i<table.length;i++){
            output += "Row " + i +": ";
            
            if(table[i] != null){
                Node<E> currentNode = table[i];
                int count = 0;
                while(currentNode != null){
                    if(count != 0){
                        output += "-->";
                    }
                    output += currentNode.element;
                    currentNode = currentNode.next;
                    count++;
                }
            }
            output+= "\n";
        }
        return output;
    }
       
    public static void main(String[] args) {
        HashSetWithChaining<String> hashtable = new HashSetWithChaining<>(6,0.75f);
        System.out.println("Create Set, initial capacity=6");
        System.out.println("Adding Seth, Bob, Adam, Ian");
        hashtable.add("Seth");       
        hashtable.add("Bob");
        hashtable.add("Adam");
        hashtable.add("Ian");
        System.out.println(hashtable);

        System.out.println("Adding Jill, Amy, Nat, Seth, Bob, Simon");
        hashtable.add("Jill");       
        hashtable.add("Amy");
        hashtable.add("Nat");
        hashtable.add("Seth");
        hashtable.add("Bob");
        hashtable.add("Simon");
        System.out.println(hashtable);
       
        System.out.print("Contain Seth?" + hashtable.contains("Seth") + " ");
        System.out.print("Contain Nat?" + hashtable.contains("Nat") + " ");
        System.out.print("Contain Gary?" + hashtable.contains("Gary") + " ");
        
        System.out.println("Adding Amy, Simon, Seth, Bob, Andy, Nat, Ian, Adam, Jill");
        System.out.println("REMOVING Seth, Bob");
       
        hashtable.add("Amy");       
        hashtable.add("Simon");
        hashtable.add("Seth");
        hashtable.add("Bob");
        hashtable.add("Andy");
        hashtable.add("Nat");
        hashtable.add("Jill");
        hashtable.remove("Seth");  
        hashtable.remove("Bob");
        System.out.println(hashtable);
        
    }
    
   // inner class which represents a node in a singly-linked list
   private class Node<E>
   {  
      public E element;
      public Node<E> next;
      
      public Node(E element)
      {  this.element = element;
         next = null;
      }
   }
}
