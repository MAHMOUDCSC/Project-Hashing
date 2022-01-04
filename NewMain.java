
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

/**
 *
 * @author mahmoud
 */



public class NewMain {

    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String[] args) {
    
        
            Scanner myObj1 = new Scanner(System.in);  // Create a Scanner object
             Scanner cs = new Scanner(System.in); 
    System.out.println("Enter size of hash array :");
 
       int size=myObj1.nextInt() ;  
    
               System.out.println ("enter the path text file :   " );
          
     String src_file;
        src_file = cs.nextLine();
      //System.out.println(src_file  );
          Hash_Table hash=new Hash_Table();
           
   
     
        

                hash.allocate(size);
        try {
      File myObj = new File(src_file);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
       String[] arrOfStr = data.split(":", 2);
       
        hash.put(arrOfStr[0], arrOfStr[1]);
       
        //    System.out.println(arrOfStr[0]+ "   " +arrOfStr[1]);
      //  System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
        
        
         int z = 0;
         do{
             System.out.println("1- Enter to disply  all keys and values");
             System.out.println("2- Enter to stores a new key value pair in hash table  :");
            
              System.out.println("3- Enter  to removes all key-value pairs with a matching key:");
              
                System.out.println("0- Enter  to frees all memory  and exit:");
                z=myObj1.nextInt() ;  
                switch(z){
                    case 1: {hash.get();}
                        break;
                    case 2: {      
                         System.out.println ("enter the name movie  : " );
                          String moive = cs.nextLine();
                           System.out.println ("enter the discrption  movie   " );
                          String dis = cs.nextLine();
                        hash.put(moive, dis);
                    
                    }
                        ;break;
                    case 3:{
                         System.out.println ("enter the name movie :  " );
                          String moive = cs.nextLine();
                         hash.erase(moive);
                    }
                        ;break;
                }
             
         }while(z!=0);
       
        hash.deallocate();
 
            myObj1.close();
          cs.close();
    }
   
}
  class Hash_Table{
      //  private String [] array_Hash=new Straing[2];
        private SinglyLinkedList[] list_Array_Hash ;
       // private int size_keys;
     Hash_Table(){}
     Hash_Table(int size){
         allocate( size);
     }
     void  allocate(int size){// initializes a hash table of a given size
           list_Array_Hash=new SinglyLinkedList[size];
           for(int i=0;i<list_Array_Hash.length;i++){
               list_Array_Hash[i]=new SinglyLinkedList();
           }
           //this.size_keys=size;
       } 
 void put(String name,String Ds){//stores a new key value pair in the associative array
     int S=name.length()%list_Array_Hash.length;
     list_Array_Hash[S].addNode(name, Ds);
 //   System.out.print("\n Done ...\n ");
 }
    void get (){
      for(int i=0;i<list_Array_Hash.length;i++){
          System.out.println("The kay is : "+i +" and  values [name,Discrption] is:  "+ list_Array_Hash[i].toString());
           }
    }
  
        void erase (String name){
          int S=name.length()%list_Array_Hash.length;
          System.out.println(S);
           list_Array_Hash[S].deleteKey(name);
        }
    void deallocate(){
            for (SinglyLinkedList list_Array_Hash1 : list_Array_Hash) {
                list_Array_Hash1.deleteList();
            }
            list_Array_Hash=null;
 
}
    }

  class SinglyLinkedList {    
    //Represent a node of the singly linked list    
    class Node{    
        String name; 
        String descrip;
        Node next;    
            
        public Node(String name,String Ds) {    
            this.name = name;
            this.descrip=Ds;
            this.next = null;    
        }    
    }    
     
    //Represent the head and tail of the singly linked list    
    public Node head = null;    
    public Node tail = null;    
        
    //addNode() will add a new node to the list    
    public void addNode(String name,String Ds) {    
        //Create a new node    
        Node newNode = new Node(name,Ds);    
            
        //Checks if the list is empty    
        if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            //newNode will be added after tail such that tail's next will point to newNode    
            tail.next = newNode;    
            //newNode will become new tail of the list    
            tail = newNode;    
        }    
    }    
        
    //display() will display all the nodes present in the list    
   
    @Override
    public String toString(){
            Node current = head;    
            String S="";
        if(head == null) {    
            //System.out.println("List is empty");    
            return "List is empty";    
        }    
       // System.out.println("Nodes of singly linked list: ");    
        while(current != null) {    
            //Prints each node by incrementing pointer    
        S+= "["+current.name + " , "+current.descrip+ "]  ,   "   ;
            current = current.next;    
        }    
      return S;
  }
    
    void deleteKey(String key)
    {
        // Store head node
        Node temp = head, prev = null;
 
        // If head node itself holds the key
        // or multiple occurrences of key
        while (temp != null &&  temp.name.equals(key))
        {
            head = temp.next; // Changed head
            temp = head; // Change Temp
        }
 
        // Delete occurrences other than head
        while (temp != null)
        {
            // Search for the key to be deleted,
            // keep track of the previous node
            // as we need to change 'prev->next'
            while (temp != null && !temp.name.equals(key))
            {
                prev = temp;
                temp = temp.next;
            }
 
            // If key was not present in linked list
            if (temp == null)
                return;
 
            // Unlink the node from linked list
            prev.next = temp.next;
 
            // Update Temp for next iteration of outer loop
            temp = prev.next;
        }
    }
 
   public void deleteList()
    {
        head = null;
    }
    }
    