package Question1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Travis 19056383
 */
public class FileSorter implements Runnable {
    
    private int limit;
    private File output;
    private File input;
    private Queue<File> filesList;
    
    FileSorter(int limit, File input, File output){
        this.limit = limit;
        this.input = input;
        this.output = output;
        filesList = new LinkedList<>();
    }
    
    public void splitStage(){
        BufferedReader reader;
        try{
            //Read from file
            reader = new BufferedReader(new FileReader(input));
            String currentLine = reader.readLine();//start reading
            List<String> tempLineRead = new ArrayList<>();
            int lineCount = 0;
            int fileNo = 1;
            while(currentLine != null){ //if not null 
                tempLineRead.add(currentLine);
                currentLine = reader.readLine(); // move to next line
                lineCount++;
                if(lineCount == limit){ 
                    Collections.sort(tempLineRead); //sort the temp files
                    //Write to output file
                    String currentFile = "TempFile" + fileNo + ".txt"; //set file name
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentFile))); // write to new file
                    filesList.offer(new File(currentFile)); // add new file to the list
                    for(String linesRead:tempLineRead){ //write line to the file
                        writer.write(linesRead);
                        writer.write("\n");
                    }
                    writer.close();
                    lineCount = 0; //reset line count to read the rest of the lines
                    tempLineRead = new ArrayList<>(); // reset 
                    fileNo++;
                }
            }
            reader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(filesList.toArray()));

    }
    
    public void mergeStage(){
        
        try {    
            //keep merging until only the output file remain
            while(filesList.size()>1){
                ArraySorter sorter = new ArraySorter();
                
                File file1 = filesList.poll();//get and remove a file from the fileslist
                File file2 = filesList.poll();//get and remove a file from the fileslist

                File combineFile = new File("combine.txt"); //temp combine file
                FileWriter writer = new FileWriter(combineFile);
                
                BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                
                String stringFileOne = br1.readLine();
                String stringFileTwo = br2.readLine();
              
                String[] temp = new String[(int)(file1.length() + file2.length())]; //temp string array for both files' strings
                int tempLength = 0;
                
                //copy the strings for file 1 and 2
                while(stringFileOne != null){
                    temp[tempLength] = stringFileOne;
                    stringFileOne = br1.readLine();
                    tempLength++;
                } 
                br1.close();
                file1.delete();
                
                while(stringFileTwo != null){
                    temp[tempLength] = stringFileTwo;
                    stringFileTwo = br2.readLine();
                    tempLength++;
                }            
                br2.close();
                file2.delete();
                 
                sorter.mergeSort(temp); //sort out both files
               
                for(String line:temp){ //write all the lines from temp to combineFile
                    if(line!= null){
                        writer.write(line + "\n"); //write line to file 
                    }
                }
                filesList.offer(combineFile); //add combineFile to fileList
                writer.close();                         
            }

        } catch (Exception e) {
        } 
        //remove the last remaining file and change it to output file
        File lastFile = filesList.remove();
        lastFile.renameTo(output);
    }
 
    @Override
    public void run() {
        splitStage();
        mergeStage();
    }
    
    public static void main(String[] args) {
        String path = "countries.txt";
        String pathOut = "F:\\NetbeanProjects\\Assignment2\\src\\Question1\\sortOutput.txt";
        FileSorter run = new FileSorter(20,new File(path), new File(pathOut));
        Thread thread = new Thread(run);
        thread.start();
    } 
   
}
