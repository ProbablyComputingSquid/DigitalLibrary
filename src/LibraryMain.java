/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Digital Library App
 * Author:        Jacob bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/6/2026
 * Version:     1.0 
 *              Basic version that test the basic Book class

 * @see Book, ItemStatus

*******************************************************************************/


import java.util.*;

public class LibraryMain
{
    public static void main(String args[]){
        Scanner myScanner = new Scanner(System.in);
        
        //Ask input from the keyboard for book information 
        System.out.println("What is the name of the book?");
        String bookName = myScanner.nextLine();
        System.out.println("What is the book ID?");
        String bookID = myScanner.nextLine();
        ItemStatus bookStatus = ItemStatus.AVAILABLE;
        //Create a  book Object
        Book book1 = new Book(bookID, bookName, bookStatus);
        
        /*Test Case 1: Check out the book and then check in immediately*/
        System.out.println("============== Test Case 1 =================");
        //Ask for the customer name
        System.out.println("What is the customer's name?");
        String customerName = myScanner.nextLine();
        //Check out the book
        System.out.println("Trying to check out the book " +book1.getName());
        if(book1.checkOut(customerName)){ //if succeed
            System.out.println(customerName+" now have this book checked out!");
        }

        //Check in the book
        System.out.println("Trying to check in the book ");
        if(book1.checkIn(customerName)){ //if checkin succeed
            System.out.println(" The book "+book1.getName()+ "is checked in!");
        }
        System.out.println("============== Test Case 1 End ================= \n");

        /*Test Case 2: Check in a book that was listed as available */
        //Check in the book
        System.out.println("============== Test Case 2 =================");
        System.out.println("Trying to check out the book " +book1.getName() + " that was already returned");
        if(book1.checkIn(customerName)){ //if checkin succeed
            System.out.println(" The book "+book1.getName()+ "is checked in!");
        }
        System.out.println("============== Test Case 2 End ================= \n ");

        /*Test Case 3: Check out a book, then try to check out again */
        System.out.println("============== Test Case 3 =================");
        //Check out the first time
        System.out.println("Trying to check out the book " +book1.getName());
        if(book1.checkOut(customerName)){ //if succeed
            System.out.println(customerName+" now have this book checked out!");
        }
        
        //Try to Check out again
        System.out.println("Trying to check out the book " +book1.getName()+ " again.");
        if(book1.checkOut(customerName)){ //if succeed
            System.out.println(customerName+" now have this book checked out!");
        }
        
        System.out.println("============== Test Case 3 End =================");

    }
    
}