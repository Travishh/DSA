package ex4_4;

/**
 *
// Travis 19056383
 */
public class Task<E extends Comparable> implements Comparable<Task>{
    
    private int priority;
    private E element;
    
    public Task(E element, int priority){
        this.element = element; 
        this.priority = priority;
    }
    
    public Task(E element){
        this(element, 1);
    }
    
    @Override
    public String toString(){
        return element.toString()+" ("+priority+")";
    }


    @Override
    public int compareTo(Task o) {
        if(priority == o.priority){
            return element.compareTo(o.element);
        }else{
            return priority - o.priority;
        }
    }
    
}
