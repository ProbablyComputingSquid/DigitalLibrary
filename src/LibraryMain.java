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
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        //Ask input from the keyboard for book information 
        System.out.println("What is the name of the book?");
        String bookName = scanner.nextLine();
        System.out.println("What is the book ID?");
        String bookID = scanner.nextLine();
        ItemStatus bookStatus = ItemStatus.AVAILABLE;
        //Create a book Object
        Book book1 = new Book(bookID, bookName, bookStatus);

        String username = "DEFAULT USER";
        while (true) {
            System.out.printf(""" 
                    \n
                    --- Welcome to the Digital Library Mainframe, %s ---
                    Your choices are:
                    (1) Check out our only book
                    (2) Check in (return) our only book
                    (3) Renew out only book
                    (4) Sign Out (exit)
                    """, username);
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case (1):
                    boolean checkOutSuccess = book1.checkOut(username);
                    System.out.printf("You %sSUCCESSFULLY checked out %s", checkOutSuccess ? "" : "UN", book1 );
                    break;
                case (2):
                    boolean checkInSuccess = book1.checkIn(username);
                    System.out.printf("You %sSUCCESSFULLY checked in %s", checkInSuccess ? "" : "UN", book1 );
                    break;
                case (3):
                    boolean renewSuccess = book1.renew(username);
                    System.out.printf("You %sSUCCESSFULLY checked out %s", renewSuccess ? "" : "UN", book1 );
                    break;
                case (4):
                    System.out.println("See you next time!");
                    return;
                default:
                    System.out.println("That's not a valid option...");
            }
        }
    }
    
}