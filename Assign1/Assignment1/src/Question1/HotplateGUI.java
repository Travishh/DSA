package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Travis
 */
public class HotplateGUI extends JPanel implements MouseListener, MouseMotionListener, ActionListener, ChangeListener {
    private Element[][] elements;
    private DrawPanel drawPanel;
    
    private int rows = 20;
    private int columns = 20;
    
    private int rectWidth, rectHeight;
    
    private JSlider tempSlider,heatSlider;
    
    private int xcoord, ycoord;
    private double startTemp = 1000;
    private double heatConstant;
    
    private Timer timer;
    //Constructor
    public HotplateGUI(){
        super(new BorderLayout());
        
        //instantiate elements
        elements = new Element[rows][columns];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                elements[i][j] = new Element(0.0,0.1);
            }
        }
        //Main Panel
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        
        //slilders
        //tempSlider
        tempSlider = new JSlider();
        tempSlider.setMaximum(1000);
        tempSlider.setValue(1000);
        tempSlider.setMajorTickSpacing(100);
        tempSlider.setMinorTickSpacing(10);
        tempSlider.setPaintTicks(true);
        //heatSlider
        heatSlider = new JSlider();
        heatSlider.setMaximum(100);
        heatSlider.setMinimum(1);
        heatSlider.setMajorTickSpacing(10);
        heatSlider.setMinorTickSpacing(1);
        heatSlider.setPaintTicks(true);

        //panel for sliders
        JPanel sliderPanel = new JPanel();
        //panel for temperature
        JPanel tempPanel = new JPanel();
        tempPanel.setBorder(BorderFactory.createTitledBorder("Temperature slider 0-1000"));
        tempPanel.add(tempSlider);
        sliderPanel.add(tempPanel);
        //panel for heat constant
        JPanel heatPanel = new JPanel();
        heatPanel.setBorder(BorderFactory.createTitledBorder("HeatConstant slider 0.01 - 1.0"));
        heatPanel.add(heatSlider);
        sliderPanel.add(heatPanel);
        
        add(sliderPanel, BorderLayout.SOUTH);
        
        addElements();
        
        //add events Listener
        tempSlider.addChangeListener(this);
        heatSlider.addChangeListener(this);
        drawPanel.addMouseMotionListener(this);
        drawPanel.addMouseListener(this);
        
        //refresh Panel
        timer = new Timer(25, this);
        timer.start();
    }

    //Change event method to change values
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        
        if(source == tempSlider){
            startTemp = source.getValue();
        }else{
            heatConstant = source.getValue();
            heatConstant = heatConstant/100;
            Element.heatConstant = heatConstant;
        }
    }
    public class DrawPanel extends JPanel
    {
        public DrawPanel()
        {
            super.setPreferredSize(new Dimension(600,600));
            super.setBackground(Color.RED);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            //use Graphics object to draw grid 20x20
            rectWidth = (getWidth()/rows);
            rectHeight = (getHeight()/columns);
            for(int i=0; i<rows;i++){
                for(int j=0; j<columns;j++){ 
                    elements[i][j].drawElement(g, i *rectWidth, j*rectHeight, rectWidth - 1, rectHeight -1);
                }
            }
        }
    }
     
    //adding elements to neighbour list
     public void addElements(){
         for(int i = 0; i<rows;i++){
             for(int j = 0; j<columns; j++){
                 //if column is more than 0, add element to neighbour to the left
                 if(j>0){
                     elements[i][j].addNeighbour(elements[i][j-1]);
                     elements[i][j].start();
                 }
                 //if column is less than 19, add element to neighbour to the right
                 if(j < 19){
                     elements[i][j].addNeighbour(elements[i][j+1]);
                     elements[i][j].start();
                 }
                 //if row if more than 0, add element to neighbour to the top
                 if(i>0){
                     elements[i][j].addNeighbour(elements[i-1][j]);
                     elements[i][j].start();
                 }
                 //if row if less than 19, add element to neighbour to the bottom
                 if(i<19){
                     elements[i][j].addNeighbour(elements[i+1][j]);
                     elements[i][j].start();
                 }
             }
         }
     }
 
    
    //Main method
    public static void main(String[] args) {
        //Create frame
        JFrame frame = new JFrame ("HotPlate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HotplateGUI());
        frame.pack();
        frame.setVisible(true);
    }

    //Event Listener Methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        xcoord = ((e.getX()/rectWidth));
        ycoord = ((e.getY()/rectHeight));
        
        //try catch if the mouse dragged furthur than rows/columns
        try{
            elements[xcoord][ycoord].applyTempToElement(startTemp);
        }catch(ArrayIndexOutOfBoundsException error){
            System.err.println(error.getMessage());
        }
        //draw rects when mouse pressed
        drawPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == timer){
            drawPanel.repaint();
        }
    }
    //End Event Listener Methods
}
