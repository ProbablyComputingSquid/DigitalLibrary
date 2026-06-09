/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Digital Library App
 * Author:        Jacob bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/6/2026
 * Version:     1.1
 *              The Digital Library interface which allows users to check out, return, or renew a book.

 * @see Book, ItemStatus

*******************************************************************************/

import java.util.*;
import java.util.ArrayList;

public class LibraryMain
{
    static ArrayList<Book> library = new ArrayList<>();
    public static Book findBook(String name) {
        name = name.strip().toLowerCase();
        for (Book book : library) {
            // strip any possible whitespace and lowercase the book name for better matching.
            // not really feeling like doing a search
            String bookName = book.getName().strip().toLowerCase();
            if (bookName.equals(name)) {
                return book;
            }
        }
        System.out.println("No book found!");
        return null;
    }
    public static Book findBook (int id) {
        for (Book book : library) {
            if (book.getID() == id) {
                return book;
            }
        }
        //System.out.printf("No book with id %d", id);
        return null;
    }
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        //Ask input from the keyboard for book information
        System.out.println("What is the name of the book?");
        String bookName = scanner.nextLine();
        System.out.println("What is the book ID?");
        int bookID = scanner.nextInt();
        if (findBook(bookID) != null) {
            System.out.println("A book already exists with that ID!");
            return;
        }
        ItemStatus bookStatus = ItemStatus.AVAILABLE;
        //Create a book Object
        Book book = new Book(bookID, bookName, bookStatus);
        library.add(book);
        System.out.println("Just added book " + book + " to the library");

    }
    private static void deleteBook(Book book) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm deletion of " + book.getName() + " by typing 'YES'");
        String userResponse = scanner.nextLine();
        if (userResponse.strip().equalsIgnoreCase("yes")) {
            library.remove(book);
            System.out.println("Deleting book...");
        } else {
            System.out.println("Cancelling deletion...");
        }
    }
    public static void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose whether to search by (1) book name or (2) id");
        int choice2 = scanner.nextInt();
        if (choice2 == 1) {
            System.out.print("Please type the book name: ");
            String bookSearchName = scanner.nextLine();
            Book foundBook = findBook(bookSearchName);
            if (foundBook == null) {return;}
            deleteBook(foundBook);

        } else if (choice2 == 2) {
            System.out.println("Please type the book's ID");
            int bookSearchId = scanner.nextInt();
            Book foundBook = findBook(bookSearchId);
            if (foundBook == null) {return;}
            deleteBook(foundBook);

        }
    }

    public static void main(String[] args) {
        /*add, delete, update*/
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        String username = "DEFAULT USER";
        while (running) {
            boolean running_book = true;
            System.out.println("""
                --- Welcome to the Digital Library Mainframe, %s ---
                    Your choices are:
                    (1) Add a book to the library.
                    (2) Delete a book from the library.
                    (3) Update a book.
                    (4) See all books.
                    (5) Sign Out (exit)""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case (1): // add a book
                    addBook();
                    break;
                case (2):
                    deleteBook();
                    break;
                case (3):
                    System.out.println("Please type the book's ID");
                    int bookSearchId3 = scanner.nextInt();
                    Book theBook = findBook(bookSearchId3);
                    if (theBook == null) {break;}
                    while (running_book) {
                        System.out.printf(""" 
                    \n
                    --- Welcome to the Digital Library Mainframe, %s ---
                    Your choices are:
                    (1) Check out the book
                    (2) Check in (return) the book
                    (3) Renew the book
                    (4) Return to main menu (exit)
                    """, username);
                        int userChoice = scanner.nextInt();
                        // switch statement to easily evaluate the user's input.
                        switch (userChoice) {
                            case (1):
                                boolean checkOutSuccess = theBook.checkOut(username);
                                System.out.printf("You %sSUCCESSFULLY checked out %s", checkOutSuccess ? "" : "UN", theBook );
                                break;
                            case (2):
                                boolean checkInSuccess = theBook.checkIn(username);
                                System.out.printf("You %sSUCCESSFULLY checked in %s", checkInSuccess ? "" : "UN", theBook );
                                break;
                            case (3):
                                boolean renewSuccess = theBook.renew(username);
                                System.out.printf("You %sSUCCESSFULLY checked out %s", renewSuccess ? "" : "UN", theBook );
                                break;
                            case (4):
                                System.out.println("Returning to menu");
                                running_book = false;
                                break;
                            default:
                                System.out.println("That's not a valid option...");
                        }
                    }
                case (4):
                    for (Book book : library) {
                        System.out.println(book.getInfo());
                    }
                    break;
                case (5):
                    running = false;
                    break;
            }




        }




    }
    
}