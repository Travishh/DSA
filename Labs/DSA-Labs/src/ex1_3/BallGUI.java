package ex1_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Travis 19056383
 */
public class BallGUI extends JPanel implements ActionListener
{
    
    private JButton addButton;
    private JButton removeButton;
    private List<Ball> balls;
    private DrawPanel drawPanel;
    private Timer timer;
    
    
    public BallGUI(){
        super(new BorderLayout());
        balls = new ArrayList<>();
        
        //create button panel and add/remove button
        addButton = new JButton("Add Ball");
        removeButton = new JButton("Remove Ball");
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        
        //refresh panel
        timer = new Timer(25, this);
        timer.start();

    }
    
    
    
    public static void main(String[] args) {
        //create frame
        JFrame frame = new JFrame("Bouncy Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BallGUI());
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private class DrawPanel extends JPanel{
        public DrawPanel(){
            setPreferredSize(new Dimension(500,500));
            setBackground(Color.WHITE);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //set width and height
            Ball.World_H = getHeight();
            Ball.World_W = getWidth();
            //draw ball
            for(Ball ball:balls){
                ball.drawBall(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Ball ball = new Ball();

        if(source == addButton){
            System.out.println("Add Ball to the list");
            //add ball to List
            Thread thread = new Thread(ball);
            thread.start();
            balls.add(ball);
        }
        if(source == removeButton){
            System.out.println("Remove Ball from the list");
            //remove ball from first index in the List if not empty
            if(!balls.isEmpty()){
                balls.remove(0);
            }
        }
        
        if(source == timer){
            drawPanel.repaint();
        }
    }
}
