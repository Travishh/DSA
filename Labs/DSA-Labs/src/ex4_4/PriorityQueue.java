package ex4_4;

import java.util.NoSuchElementException;

/**
 *
// Travis 19056383
 */
public class PriorityQueue implements QueueADT<Task<?>>{
    
    private final ArrayHeap<Task<?>> heap;
    
    public PriorityQueue(){
        heap = new ArrayHeap<>();
    }

    @Override
    public void enqueue(Task<?> element) {
       heap.add(element);
    }

    @Override
    public Task<?> dequeue() throws NoSuchElementException {
        Task removeTask = heap.removeMin();
        return removeTask;
    }

    @Override
    public Task<?> first() throws NoSuchElementException {
        Task getTask = heap.getMin();
        return getTask;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }
    
    public static void main(String[] args) {
        QueueADT<Task<?>> queue = new PriorityQueue();
        queue.enqueue(new Task<>("SKA work", 3));        
        queue.enqueue(new Task<>("Have a Sleep", 8));
        queue.enqueue(new Task<>("Feed the Cat and Dog", 1));
        queue.enqueue(new Task<>("Have Coffee", 1));
        queue.enqueue(new Task<>("Have 2nd Coffee", 2));
        queue.enqueue(new Task<>("Have 3rd Coffee", 4));
        
        while(!queue.isEmpty()){
            System.out.print("Processing Task >> ");
            System.out.println(queue.first());
            queue.dequeue();
        }

    }
    
}
