package ex4_1;

// Travis 19056383

import java.util.Enumeration; // old version of an Iterator
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class LinkedBinaryTreeNode<E> implements MutableTreeNode
{
   private E element;
   private MutableTreeNode parent;
   private MutableTreeNode leftChild;   
   private MutableTreeNode rightChild;

   
   public LinkedBinaryTreeNode()
   {  this(null);
   }
   
   public LinkedBinaryTreeNode(E element)
   {  this.element = element;
      parent = null;
      leftChild = null;
      rightChild = null;
   }
   
   // returns an Enumeration of the child nodes
   public Enumeration<MutableTreeNode> children()
   {  List<MutableTreeNode> list = new LinkedList<>();
      if(leftChild != null){
          list.add(leftChild);
      }
      if(rightChild != null){
          list.add(rightChild);
      }
      return (Enumeration<MutableTreeNode>)
         (new Enumerator(list.iterator())); // unchecked
   }
   
   // returns that this node allows children
   public boolean getAllowsChildren()
   {  return true;
   }
   
   //return the child at specified index
   public TreeNode getChildAt(int childIndex)
      throws IndexOutOfBoundsException
   {  
       if(childIndex == 0) //0=left
           return leftChild;
       else if(childIndex == 1) //1=right
           return rightChild;
       else
           throw new IndexOutOfBoundsException();
   }
   
   // returns the number of children of this node
   public int getChildCount()
   {  
       int count = 0;
       if(leftChild !=null)
           count++;      
       if(rightChild != null)
           count++;
       return count;
   }
   
   // returns the index of node or -1 if node not found
   public int getIndex(TreeNode node)
   {  
       //check if node equal left or right and return 0 or 1 or -1 if not found
       if (node==null)
         throw new IllegalArgumentException();
      else if (leftChild.equals(node))
          return 0;
      else if(rightChild.equals(node))
          return 1;
      else return -1;
         
   }
   
   // return the parent node or null if this node is the root
   public TreeNode getParent()
   {  return parent;
   }

   // returns whether this node is a leaf   
   public boolean isLeaf()
   {  return (getChildCount()==0);
   }
   
   // add the child node at specified index, updating this node 
   // and child node to reflect the change
   public void insert(MutableTreeNode child, int index)
      throws IllegalArgumentException
   { 
       if(index == 0 && leftChild != null)
           throw new IllegalArgumentException("Left child is not null");
       else if (index ==1 && rightChild != null)
           throw new IllegalArgumentException("Right child is not null");       
       else if(index == 0){
           child.removeFromParent();  // remove child from its existing parent
           leftChild = child;         // set leftChild to child
           leftChild.setParent(this); // update the child node
       }
       else if (index ==1){
           child.removeFromParent();  // remove child from its existing parent
           rightChild = child;         // set rightChild to child
           rightChild.setParent(this); // update the child node
       }
   }
   
   // removes the child at index from this node, updating this
   // node and child node to reflect the change
   public void remove(int index)
   {  
       if(index == 0 && leftChild != null)
           leftChild.setParent(null);
       else if (index == 1 && rightChild != null)
           rightChild.setParent(null);
   }
   
   // remove the specified child from this node, updating this
   // node and child node to reflect the change
   public void remove(MutableTreeNode node)
   {  
       if(node != null){
           if(node.equals(leftChild)){
                leftChild = null;
                    
           }
           else if(node.equals(rightChild)){
                rightChild = null;
           }
       }
   }
   
   // remove this node from its parent, updating this
   // node and its parent node
   public void removeFromParent()
   {  if (this.parent!=null)
      {  parent.remove(this);
         this.parent = null;
      }
   }
   
   // sets the parent of this node to be newParent
   // but does not update newParent
   public void setParent(MutableTreeNode newParent)
   {  removeFromParent(); // remove this node from its existing parent
      this.parent = newParent;
   }
   
   // set the element held in this node
   public void setUserObject(Object object)
   {  this.element = (E)object; // unchecked, could throw exception
   }
   
   public E getUserObject()
   {  return element;
   }
   
   // returns a string representation of the node and its child nodes
   // in preorder notation
   public String toString()
   {  String output = "" + this.element;
      if (!isLeaf())
      {  output += "[ ";
         output += leftChild;
         output += " ";
         output += rightChild;
         output += " ]";
      }
      return output;
   }
   
   public static void main(String[] args)
   {  // create some sample nodes
      MutableTreeNode root = new LinkedBinaryTreeNode<String>("A");
      MutableTreeNode nodeB = new LinkedBinaryTreeNode<String>("B");
      MutableTreeNode nodeC = new LinkedBinaryTreeNode<String>("C");
      MutableTreeNode nodeD = new LinkedBinaryTreeNode<String>("D");
      MutableTreeNode nodeE = new LinkedBinaryTreeNode<String>("E");
      MutableTreeNode nodeF = new LinkedBinaryTreeNode<String>("F");

      // build the tree
      nodeB.insert(nodeD, 0);
      nodeB.insert(nodeE, 1);
      nodeC.insert(nodeF, 0);
      root.insert(nodeB, 0);
      root.insert(nodeC, 1);
      System.out.println("Original Tree: " + root);
      root.remove(nodeC);
      root.insert(nodeF, 1);
      System.out.println("Modified Tree: " + root);
   }
}

// utility class to wrap an Iterator object as an Enumeration object
class Enumerator<E> implements Enumeration<E>
{
   private Iterator<E> iterator;
   
   public Enumerator(Iterator<E> iterator)
   {  this.iterator = iterator;
   }
   
   public boolean hasMoreElements()
   {  return iterator.hasNext();
   }
   
   public E nextElement()
   {  return iterator.next();
   }
}
