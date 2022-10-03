package ex5_1;

/**
 *
// Travis 19056383
 */
public class HashTable<E> {
    private final int INITIAL_CAPACITY = 5;
    protected Node<E>[] table;
    private int numElements;
    private final float loadFactor = 0.75f;

    public HashTable(){
        numElements = 0;
        table = new Node[INITIAL_CAPACITY];
    }
    
    public boolean isEmpty(){
        return (numElements==0);
    }
    
    public int size(){
        return table.length;
    }
    
    public void expandCapacity(){
        Node<E>[] newTable = new Node[INITIAL_CAPACITY * 2];
        for(int i = 0; i<table.length; i++){
            if(table[i] != null){
                newTable[i] = table[i];
            }
        }
        table = newTable;
    }
    
    public void add(E o){
        if((numElements+1)/(float)table.length > loadFactor){ //prevent collision
            System.out.println("LOAD FACTOR EXCEEDED, EXPANDING CAPACITY...");
            expandCapacity();
        }
        int index = o.hashCode()%table.length;
        if(index<0){
            index = index * -1;
        }
        Node<E> newNode = new Node<E>(o);
        newNode.next = table[index];
        table[index] = newNode;
        numElements++;
    }
    
    @Override
    public String toString(){
        String output = "";
        for(int i = 0; i<table.length;i++){
            output += "Row " + i +": ";
            
            if(table[i] != null){
                Node current = table[i];
                int count = 0;
                while(current != null){
                    if(count != 0){
                        output += " ";
                    }
                    output += current.element;
                    current = current.next;
                    count++;
                }

            }
            output+= "\n";
        }
        return output;
    }
       
    public static void main(String[] args) {
        HashTable<Student> table = new HashTable<>();
        Student student1 = new Student("Randy");
        Student student2 = new Student("Seth");        
        Student student3 = new Student("Tash");
        Student student4 = new Student("Stan");
        Student student5 = new Student("Leo");
        Student student6 = new Student("Cushla");
        Student student7 = new Student("John");

        table.add(student1);
        table.add(student2);
        table.add(student3);
        table.add(student4);
        table.add(student5);
        table.add(student6);        
        table.add(student7);
        
        System.out.println(table.toString());
        System.out.println("SIZE:" + table.size());
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
