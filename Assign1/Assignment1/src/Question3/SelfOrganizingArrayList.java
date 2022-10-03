package Question3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Travis
 */
public class SelfOrganizingArrayList<E> implements List<E> {
    private final int INITIAL_CAPACITY = 20;
    private int numElements;
    private E[] elements;
    private int[] count;
    
    public SelfOrganizingArrayList(){
        super();
        numElements = 0;
        elements = (E[])(new Object[INITIAL_CAPACITY]);
        count = new int[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        if(numElements == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        E temp;
        for(int i = 0; i<numElements;i++){
            if(o.equals(elements[i])){
                count[i]++;
                System.out.println(o+ ":found");
                System.out.println(o+" counter: "+count[i]);
              
                return true;
            }
            
        }     
        System.out.println(o+ ":Not found");
        return false;
    }
    
     @Override
    public int indexOf(Object o) {
        for(int i=0; i<numElements;i++){
            if(o.equals(elements[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(E e) {
        if(numElements >= elements.length){
            expandCapacity();
        }
        elements[numElements] = e;
        numElements++;
        System.out.println(e+" is Added");
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(numElements > 0){
            for(int i = 0; i<numElements;i++){
                if(elements[i] == o){
                    elements[i] = null;
                    numElements--;
                    System.out.println(o+" is Removed");
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public E get(int index) {
        if(index >=0 && index<numElements){
            System.out.println("Get Index "+index+": "+elements[index]);
            return elements[index];
        }else{
            throw new NoSuchElementException("Index out of bounds");
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < numElements && elements[currentIndex] != null;
            }

            @Override
            public E next() {
                return elements[currentIndex++];
            }
            
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        for(int i=0;i<numElements;i++){
            elements[i] = null;
        }
        numElements = 0;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void expandCapacity(){
        E[] newElementsArray = (E[])(new Object[elements.length*2]);
        int[] newCountArray = new int[count.length*2];
        for(int i=0;i<numElements;i++){
            newElementsArray[i] = elements[i];
            newCountArray[i] = count[i];
        }
        elements = newElementsArray;
        count = newCountArray;
    }

    //Main Method
    public static void main(String[] args) {
        SelfOrganizingArrayList<String> arrayList = new SelfOrganizingArrayList<>();
        //test add
        arrayList.add("A");
        arrayList.add("B");
 
        System.out.println("Elements Size: "+arrayList.size());        
        //test get
        arrayList.get(0);
        //test contains
       
        arrayList.contains("B");
        arrayList.contains("B");
        arrayList.indexOf("B");

        //test remove
        arrayList.remove("A");
        

        System.out.println("Elements Size: "+arrayList.size());
    }
}
