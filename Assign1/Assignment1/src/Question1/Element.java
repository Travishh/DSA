package Question1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Travis
 */
public class Element implements Runnable {
    private List<Element> neighbours;
    private double currentTemp;
    public static double heatConstant;
    private boolean stopRequested;
    
    //Constructor
    public Element(double startTemp, double heatConstant){
        neighbours = new ArrayList<>();
        this.currentTemp = startTemp;
        //heatConstant Range(0.0,1.0)
        if(heatConstant>0.0 && heatConstant<=1.0){
             this.heatConstant = heatConstant;
        }else{
            throw new IllegalArgumentException("Heat Constant("+heatConstant+") out of Range(0.0,1.0)");
        }
    }
    
    //start method running Element as a thread
    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
    
    //add method: adding element to neighbours list
    public void addNeighbour(Element element){
       neighbours.add(element);
    }
    
    @Override
    public void run() {
        stopRequested = false;
        while(!stopRequested){
            double sumNeighbourElements = 0.0;
            double averageTemp;
            
            //Get the total sum of temperature of neighbour list
            for(int i=0; i<neighbours.size();i++){
                sumNeighbourElements += neighbours.get(i).getTemperature();
            }
            //get the average temp of the neighbours list
            averageTemp = sumNeighbourElements/neighbours.size();
            //adjust the temp of the current temperature  
            currentTemp += (averageTemp - currentTemp)*heatConstant;
            
            try{
                Thread.sleep(20);
            }catch(Exception e){}    
        }   
    }
   
    public  void requestStop(){
        stopRequested = true;
    }
    
    public double getTemperature(){
        return this.currentTemp;
    }
    
    //synchronized method for applying temperature to elements
    public synchronized void applyTempToElement(double appliedTemp){
        currentTemp += (appliedTemp-currentTemp)*heatConstant;
    }
    
    public static void main(String[] args) {
        //adjust temperature by 0.1
        Element firstElement = new Element(100.0, 0.1);       
        Element secondElement = new Element(0.0, 0.1);
        
        //connect elements
        firstElement.addNeighbour(secondElement);
        secondElement.addNeighbour(firstElement);
        //Threads start
        firstElement.start();
        secondElement.start();
        
        //periodically print out both elements temperatures
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                //printing both elements until equal
                System.out.println("First Element Current Temperature: "+firstElement.getTemperature());
                System.out.println("Second Element Current Temperature: "+secondElement.getTemperature());
                
                //compare elements temperate until 2dp
                if((firstElement.getTemperature()-secondElement.getTemperature())<0.01){
                    timer.cancel();
                    firstElement.requestStop();
                    secondElement.requestStop();
                }
            }
        },0,500);//excute task every half a second
  
    }
    
   public void drawElement(Graphics g, int xcoord, int ycoord, int rectWidth, int rectHeight){
       int hot =  Math.min(255, (int)getTemperature());
       
       g.setColor(new Color(hot, 0, 255 - hot));
       g.fillRect(xcoord, ycoord, rectWidth, rectHeight);
   }
}
