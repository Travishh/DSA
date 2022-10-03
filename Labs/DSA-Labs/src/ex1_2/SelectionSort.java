package ex1_2;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Travis 19056383
 */
public class SelectionSort {
    
    public static void Selection_Sort(int[] array){
        
        for(int i = 0; i<array.length - 1; i++){
            int smallest_Num = array[i];
            int smallest_Numindex = i;
            for(int j = i+1; j < array.length; j++){
                if(array[j] < smallest_Num){
                    smallest_Num = array[j];
                    smallest_Numindex = j;
                }
            }
            array[smallest_Numindex] = array[i];
            array[i] = smallest_Num;
        }
    }

    public static void main(String[] args){
        System.out.println("Selection Sort Program");
        
        int Max_array = 20;
        int[] array = new int[Max_array];
        
        Random randomNum = new Random();
        for(int i=0;i<array.length;i++){
            array[i]=randomNum.nextInt(100);
        }
        
        System.out.println("\nUNSORTED Numbers: ");
        System.out.println(Arrays.toString(array));
        Selection_Sort(array);
        System.out.println("\nSORTED Numbers: ");
        System.out.print(Arrays.toString(array));
        System.out.println();
    }
}
