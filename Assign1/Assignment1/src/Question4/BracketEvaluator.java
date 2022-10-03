package Question4;

import java.util.Stack;

/**
 *
 * @author Travis
 */
public class BracketEvaluator {
    
    //evaluate method accepts string
    public static void evaluate(String string){
        Stack<Character> stack = new Stack<>();
        boolean found = true;
        
        //looping through the string to check individual character
        for(int i = 0; i<string.length();i++){
            //set char at index i
            char c = string.charAt(i);

            //if (,{,<,[ add to stack
            if( c== '(' || c == '{' || c == '<' || c == '['){
                stack.push(c);
            }
            //check for closing bracket
            else{
               // get closing bracket out from stack if opening bracket is found
                if(c == ')'){
                    if(stack.peek() == '('){
                        stack.pop();
                    }else{
                        found  = false;
                    }
                }
                if(c == '}'){
                    if(stack.peek() == '{'){
                        stack.pop();
                    }else{
                        found  = false;  
                    }
                }
                if(c == '>'){
                    if(stack.peek() == '<'){
                        stack.pop();
                    }else{
                        found  = false;
                    }
                }
                if(c == ']'){
                    if(stack.peek() == '['){
                        stack.pop();
                    }else{
                        found  = false;
                    }
                }
            }
        }
        
        //if not found print..
        if(!found || !stack.empty()){
            System.out.print("String: \""+string+"\"");
            System.out.println(" - will not evaluate");
        }
        //if found print..
        else{
            System.out.print("String: \""+string+"\"");
            System.out.println(" - will evaluate successfully");
        }
    }
    
    //Main Method
    public static void main(String[] args) {
        evaluate("(39)");
        evaluate("(39}");
        evaluate("{((2 x 5) + (3*-2 + 5))}");
        evaluate("{ (2 x 5) + (3*-2 + 5))}");
        evaluate("List<List<E>>");
        evaluate("List<List<E>");
        evaluate("{(<<eeeek>>) {} {...} (e(e)e) {hello}}");
        evaluate("{(< eeeek>>) {} {...} e(e)e) {hello} ");
    }
}
