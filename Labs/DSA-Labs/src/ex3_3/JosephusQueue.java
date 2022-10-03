package ex3_3;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
  * @author Travis 19056383
 */
public class JosephusQueue {
    
    public static void josephusProblem(int n, int m){
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> removeQueue = new LinkedList<>();
        for(int i=1;i<=n;i++){
            queue.add(i);
        }
        System.out.println("Adding Soldiers");
        for(Integer i:queue){
            System.out.print(i+", ");
        }
        System.out.println("\n");
        int counter = 1;
        while(!queue.isEmpty()){
            if(counter%m != 0){
                int removeInt = queue.remove();
                queue.add(removeInt);
                counter++;
            }else{
                int removeInt = queue.remove();                
                removeQueue.add(removeInt);
                if(queue.size()==0){
                    System.out.println("The Last Standing soldier is >>: " + removeInt);
                }else{
                    System.out.println("removing soldier >>: "+removeInt);
                }
                counter++;
            }
        }
        
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the number of Soldiers: ");
        int n = scan.nextInt();
        System.out.println("Enter the number to remove the soldier: ");
        int m = scan.nextInt();
        josephusProblem(n, m);
    }
    
}
