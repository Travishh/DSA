package ex3_2;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Travis 19056383
 */
public class ArrayQueue<E> implements QueueADT<E> {
    
    private final int INITIAL_CAPACITY = 5;
    private int numElements;
    private E[] elements;
    private int head;
    private int tail;
    
    public ArrayQueue(){
        super();
        numElements = 0;
        elements = (E[])(new Object[INITIAL_CAPACITY]);
        head = 0;
        tail = 0;
    }
    
    public ArrayQueue(Collection<? extends E> c){
        this();
        for(E element:c){
            enqueue(element);
        }
    }
    
    @Override
    public void enqueue(E element) {
        if(numElements==elements.length){
            expandCapacity();
        }
        elements[tail]=element;
        tail = (tail+1) % elements.length; 
        numElements++;
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        if(numElements>0){
            E headElement = elements[head];
            elements[head] = null;
            head = (head+1)%elements.length;
            numElements--;
            return headElement;
        }else throw new NoSuchElementException();
    }

    @Override
    public E first() throws NoSuchElementException {
        if(numElements>0){
            return elements[head];
        }else
            throw new NoSuchElementException();
    }

    @Override
    public boolean isEmpty() {
        return (numElements==0);
    }

    @Override
    public int size() {
        return numElements;
    }
    
    public void expandCapacity(){
        E[] largerArray = (E[])(new Object[elements.length*2]);
        for(int i = 0; i<= elements.length;i++){
            largerArray[i] = elements[head];
            head = (head+1)%elements.length;
        }
        head = 0;
        tail = numElements;
        elements = largerArray;    
    }
    
    public String toString(){
        String output = "[";
        for(int i=0;i<tail;i++){
            output += elements[i];        
            if(i<tail-1){
                output += ", ";
            }         
        }
        output += "]";
        return output;
    }
    
    public static void main(String[] args) {
        QueueADT<Integer> q = new ArrayQueue<>();

        System.out.println("Adding to queue");
        q.enqueue(1);        
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);        
        System.out.println(q.toString());
        
        System.out.println("Removing from queue");
        q.dequeue();        
        q.dequeue();        
        System.out.println(q.toString());
        
        System.out.println("Adding to queue");
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);
        q.enqueue(13);
        q.enqueue(14);
        System.out.println(q.toString());
    }
    
}
