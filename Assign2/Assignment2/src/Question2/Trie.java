package Question2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Travis Hun 19056383
 */
public class Trie {
    private TrieNode root;
    
    public Trie(){
        root = new TrieNode();
    }
    
    public boolean add(String element){
        TrieNode currentNode = root;
        
        boolean added = false;
        for(int i=0;i<element.length();i++){
            //handle null when add character to children map
            TrieNode newChildNode = new TrieNode();
            currentNode = currentNode.childNodes.computeIfAbsent(element.charAt(i), c -> newChildNode);
            added = true;
        }     
        return added;
    }
    
//    public boolean remove(String element){
//        TrieNode currentNode = root;
//        boolean deleted = false;
//        
//    }
    
    public boolean contains(String element){
        TrieNode currentNode = root;
        boolean found = false;
        for(int i=0;i<element.length();i++){
            char c = element.charAt(i);
            //if c exist in the map -> found
            if(currentNode.childNodes.containsKey(c)){
                found = true;
            }
        }
        return found;
    }
    
//    public boolean removeAll(String prefix){
//        
//    }
//    
    public boolean startsWtih(String prefix){
        TrieNode currentNode = root;
        boolean found = false;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(currentNode.childNodes.containsKey(c)){
                found = true;
            }
        }
        return found;
    }
//    
//    public Set<String> suggestions(String prefix){
//        
//    }
    
     @Override
    public String toString()
    {
        return recursiveString(root, 0);
    }
    
    private String recursiveString(TrieNode current, int level)
    {
        String levelString = "";
        if(current.childNodes.size() > 0)
        {   
            Set<Character> chars = current.childNodes.keySet();
            String tabs="";
            for(int i=0;i<level;i++)
                tabs += "\t";
            
            for(Character c : chars)
            {   TrieNode child = current.childNodes.get(c);
                levelString+=tabs+" ["+c+"]";
                if(child.element != null) 
                    levelString+=" >> "+child.element;
                levelString +="\n";
                levelString += recursiveString(child, level+1);
            }   
        }
        return levelString;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("then");
        trie.add("hi");
        trie.add("hit");
        trie.add("her");
        trie.add("him");
        trie.add("tea");
        trie.add("a");
        trie.add("apple");
        trie.add("there");
        trie.add("seth");
        trie.add("tart");

        System.out.println(trie);
        System.out.println("Words Start with t? "+trie.startsWtih("t"));
        System.out.println("Words Start with z? "+trie.startsWtih("z"));          
    }
         
   protected class TrieNode
   {  
      Map<Character, TrieNode> childNodes;
      String element;
      
      public TrieNode()
      {  
          element = null;
          childNodes = new HashMap<>();
      }
   }
}
