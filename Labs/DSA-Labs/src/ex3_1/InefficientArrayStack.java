package ex3_1;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Travis 19056383
 */
public class InefficientArrayStack<E> implements StackADT<E> {
    
    private int numElements;
    private E[] elements;
    private final int INITIAL_CAPACITY= 20;
    
    public InefficientArrayStack(){
        super();
        numElements = 0;
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }
    
    public InefficientArrayStack(Collection<? extends E> c){
        this();
        for(E element:c){
            push(element);
        }
    }
    
    @Override
    public void push(E element) {
        if(numElements >= elements.length-1)
            expandCapacity();
        //shifting elements to the right by one
        for(int i=numElements + 1; i>0;i--)
            elements[i] = elements[i-1];
        
        elements[0] = element;
        numElements++;
        
    }

    @Override
    public E pop() throws NoSuchElementException {
        if(numElements > 0){
            E topElement = elements[0];
            //shifting elements to the left by one
            for(int i = 0; i<numElements-1;i++ )
                elements[i] = elements[i+1];
            numElements--;
            return topElement;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public E peek() throws NoSuchElementException {
        if(numElements > 0){
            return elements[0];
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    @Override
    public int size() {
        return numElements;
    }
    
    private void expandCapacity(){
        E[] largerArray = (E[])(new Object[elements.length*2]);
        for(int i = 0; i<numElements;i++){
            largerArray[i] = elements[i];
        }
        elements = largerArray;
    }
    
    public String toString(){
        String output = "[";
        for(int i = 0; i<numElements;i++){
            output += elements[i];
            if(i<numElements-1)
                output += ",";
            
        }
        output += "]";
        return output;
    }
    
    public static void main(String[] args) {
        int numElements = 100000;
        StackADT<Integer> stackGood = new ArrayStack<>();
        StackADT<Integer> stackBad = new InefficientArrayStack<>();
        
        System.out.println("Pushing to Good Stack");
        long begin = System.currentTimeMillis();
        for(int i= 0; i<numElements;i++){
            stackGood.push(i);
        }
        System.out.println("Pop All elements from Good Stack");
        while(!stackGood.isEmpty()){
            stackGood.pop();
        }
        System.out.println("Total Time: "+ (System.currentTimeMillis()- begin)+" ms");
        
        
        System.out.println("\nPushing to Bad Stack");
        long begin2 = System.currentTimeMillis();
        for(int i= 0; i<numElements;i++){
            stackBad.push(i);
        }
        System.out.println("Pop All elements from Bad Stack");
        while(!stackBad.isEmpty()){
            stackBad.pop();
        }
        System.out.println("Total Time: "+ (System.currentTimeMillis()- begin2)+" ms");

    }
}
