package ex1_3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Travis 19056383
 */
public class Ball implements Runnable {

    public static int World_W, World_H;
    //positions
    private int x,y;
    //movements
    private int mx,my;
    //size
    private int size;
    //color
    private Color colour;
    private Random rand;
    private boolean stopRequested;
    
    public Ball(){
        
        rand = new Random();

        x = World_W/2;
        y = World_H/2;

        mx = rand.nextInt(5)-5;
        my = rand.nextInt(5)-5;
        
        colour = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        size = rand.nextInt(30)+15;
    }
    
    //move the ball
    private void moveBall(){
        x += mx;
        y += my;
        
        if(x<0 || (x + size) > World_W){
            mx = -mx;
        }
        if(y<0 || (y + size) > World_H){
            my = -my;
        }
        
    }
    //draw the ball
    
    //stop the thread
    
    @Override
    public void run() {
        stopRequested = false;
        
        while(!stopRequested){
            moveBall();
            try{
                Thread.sleep(30);
            }catch(Exception e){
                
            }
        }
    }
    
    public void drawBall(Graphics g){
        g.setColor(colour);
        g.fillOval(x, y, size, size);
    }
    
}
