package Question1;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author Travis Hun 19056383
 */
public class FileSorterGUI extends JPanel implements ActionListener{
    private JLabel numItems, input, output, mergeLabel, splitLabel;
    private JTextField stringLimit;
    private JProgressBar mergeProgressBar, splitProgressBar;
    private JButton processTask, enqueueTask;
    
    private Queue<FileSorter> task = new LinkedList();
    
    public FileSorterGUI(){
        super(new GridLayout(7,1)); //using grid layout for 7 components
        
        //number of items
        numItems = new JLabel("Number of items in queue: " + task.size());
        
        //Max String
        stringLimit = new JTextField();
        stringLimit.setBorder(BorderFactory.createTitledBorder("Max Strings in memory: "));

        //File input
        input = new JLabel();
        input.setBorder(BorderFactory.createTitledBorder("Input File: "));
        input.setText("No File");
        
        //File output
        output = new JLabel();
        output.setBorder(BorderFactory.createTitledBorder("Output File: "));
        output.setText("No File");
        
        //merge panel
        JPanel mergePanel = new JPanel(new GridLayout(2,1));     
        mergeLabel = new JLabel("       Merge Progress: ");
        mergeProgressBar = new JProgressBar(0, 100);
        mergeProgressBar.setValue(0);
        mergePanel.add(mergeLabel);
        mergePanel.add(mergeProgressBar);
        //split panel
        JPanel splitPanel = new JPanel(new GridLayout(2,1));     
        splitLabel = new JLabel("       Split Progress: ");
        splitProgressBar = new JProgressBar(0, 100);
        splitProgressBar.setValue(0);
        splitPanel.add(splitLabel);
        splitPanel.add(splitProgressBar);
        
        //buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        processTask = new JButton("Process Task");
        enqueueTask = new JButton("Enqueue Task");
        buttonsPanel.add(processTask);
        buttonsPanel.add(enqueueTask);
        
        //adding component to the frame
        add(numItems);
        add(stringLimit);
        add(input);
        add(output);
        add(mergePanel);        
        add(splitPanel);
        add(buttonsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    }
    
    public static void main(String[] args) {
        //Create frame
        JFrame frame = new JFrame ("File Sorter");
        frame.setMinimumSize(new Dimension(350,350));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FileSorterGUI());
        frame.pack();       
        frame.setVisible(true);
    }
    
}
