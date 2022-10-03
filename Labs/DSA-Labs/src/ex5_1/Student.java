package ex5_1;

/**
 *
// Travis 19056383
 */
public class Student {
    private String studentName;
    
    public Student(String studentName){
        this.studentName = studentName;
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i<studentName.length();i++){
            hash += studentName.charAt(i);
        }
        return hash;    
    }
    @Override 
    public String toString(){
        return studentName;
    }
    
}
