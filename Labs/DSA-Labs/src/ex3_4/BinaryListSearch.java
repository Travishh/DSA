package ex3_4;

import java.util.*;

/**
 *
  * @author Travis 19056383
 */
public class BinaryListSearch<E extends Comparable> {
    private List<E> elements;
   
   public BinaryListSearch(List<E> elements)
   {  this.elements = elements;
   }
   
   public int search(E target)
   {  if (target == null)
         throw new NullPointerException("search target is null");
      ListIterator itr = elements.listIterator();
      return search(target, 0, elements.size(),itr);
   }
   
   // recursive method which searches through the elements array
   // between start index (inclusive) and end index (exclusive) for the
   // index of specified target, or returns -(insertion)-1 if not found
   private int search(E target, int start, int end,ListIterator itr)
   {  if (start >= end)
         return -start-1; // negative value
      else
      { int midpoint = (start+end)/2; // midpoint of search region

        if(midpoint>itr.nextIndex()){
            for(int i=itr.nextIndex();i<end;i++){
                if(target.compareTo(elements.get(i))==0){
                    return i;
                }else{
                    itr.next();
                }
            }
        }
      }
        return -1;
   }
   
   public static void main(String[] args)
   {  
       List<String> list = new LinkedList<>();
       list.add("ant");       
       list.add("bat");
       list.add("cat");
       list.add("cow");
       list.add("dog");
       list.add("eel");
       list.add("fly");       
       list.add("fox");
       list.add("owl");
       list.add("pig");
       list.add("rat");
       System.out.println(list);
      BinaryListSearch<String> bin = new BinaryListSearch<String>(list);
      String target = "owl";
      int index = bin.search(target);
      if (index >= 0)
         System.out.println(target + " found at index " + index);
      else
         System.out.println(target + " not at index " + (-index-1));
   }
}
