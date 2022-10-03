package ex3_6;

import java.util.Random;

/**
 *
 * @author Travis 19056383
 */
public class BinSort {
    public static void binSort(int[] list, int maxValue){
        int[] bins = new int[maxValue+1];
        for(int i=0;i<list.length;i++){
            bins[list[i]]++;
        }
        
        for(int i=0;i<bins.length;i++){
            if(bins[i] > 0){
                for(int j=bins[i];j>0;j--){
                    System.out.print(" "+i);
                }
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        final int maxValue = 100;
        int[] list = new int[50];
        
        System.out.println("UnSorted List:");
        for(int i=0;i<list.length;i++){
            list[i]=rand.nextInt(maxValue+1);
        }
        for(int i=0;i<list.length;i++)
            System.out.print(" "+list[i]);
        System.out.println();
        
        System.out.println("\nSorted List: ");
        long begin = System.nanoTime()/1000;
        binSort(list, maxValue);
        long end = System.nanoTime()/1000;
        System.out.println("\nTime Taken: "+(end-begin)+" microS");
    }
}
