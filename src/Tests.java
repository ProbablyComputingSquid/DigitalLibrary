import java.time.LocalDate;
import java.util.Scanner;

public class Tests {
    public static void main(String args[]){
        Scanner myScanner = new Scanner(System.in);

        //Ask input from the keyboard for book information
        System.out.println("What is the name of the book?");
        String bookName = myScanner.nextLine();
        System.out.println("What is the book ID?");
        String bookID = myScanner.nextLine();
        ItemStatus bookStatus = ItemStatus.AVAILABLE;
        //Create a book Object
        Book book1 = new Book(bookID, bookName, bookStatus);

        /*Test Case 1: Check out the book and then check in immediately*/
        System.out.println("============== Test Case 1 =================");
        //Ask for the customer name
        System.out.println("What is the customer's name?");
        String customerName = myScanner.nextLine();
        //Check out the book
        System.out.println("Trying to check out the book " +book1.getName());
        if(book1.checkOut(customerName)){ //if succeed
            System.out.println("SUCCESS: " + customerName+" now has this book checked out!");
        }

        //Check in the book
        System.out.println("Trying to check in the book ");
        if(book1.checkIn(customerName)){ //if checkin succeed
            System.out.println("SUCCESS: The book "+book1.getName()+ " is checked in!");
        }
        System.out.println("============== Test Case 1 End ================= \n");

        /*Test Case 2: Check in a book that was listed as available */
        //Check in the book
        System.out.println("============== Test Case 2 =================");
        System.out.println("Trying to check in the book " +book1.getName() + " that was already returned. This should FAIL");
        if(book1.checkIn(customerName)){ //if checkin succeed
            System.out.println("SUCCESS: The book "+book1.getName()+ "is checked in!");
        } else {
            System.out.println("Operation SUCCESSFULLY failed.");
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
        System.out.println("Trying to check out the book " +book1.getName()+ " again. This should FAIL");
        if(book1.checkOut(customerName)){ //if succeed
            System.out.println(customerName+" now have this book checked out!");
        } else {
            System.out.println("Operation SUCCESSFULLY failed.");
        }

        System.out.println("============== Test Case 3 End =================");

        System.out.println("============== Test Case N =================");
        System.out.println("============== Test Case N End =================");
        /* Test Case 4: Renew a book that you've already checked out*/
        System.out.println("============== Test Case 4 =================");
        book1.checkIn(customerName);
        System.out.println("Trying to check out the book " + book1.getName());
        LocalDate today = LocalDate.of(2026, 6, 7);
        LocalDate fakeToday = LocalDate.of(2026,6,29);
        if(book1.checkOut(customerName, today)){ //if succeed
            System.out.println(customerName+" now has this book checked out!");
        }
        System.out.println("Trying to renew the book " + book1.getName() + " 22 days after it was checked out.");

        if (book1.renew(customerName, fakeToday)) {
            System.out.println("Book has been SUCCESSfully renewed!");
        } else {
            System.out.println("ERROR!");
        }
        System.out.println("============== Test Case 4 End =================");
        /* Test Case 5: Renew a book you don't own*/
        System.out.println("============== Test Case 5 =================");

        System.out.println(customerName + "'s sister is trying to renew the book " + book1.getName());

        if (book1.renew(customerName + "'s sister")) {
            System.out.println("Book has been SUCCESSfully renewed!");
        } else {
            System.out.println("ERROR! This SHOULD HAPPEN");
        }
        System.out.println("============== Test Case 5 End =================");
        /* Test Case 6: Renew a book that's available*/
        System.out.println("============== Test Case 6 =================");
        /* */
        book1.checkIn(customerName, fakeToday);

        System.out.println("trying to renew the book " + book1.getName() + " that is available.");

        if (book1.renew(customerName)) {
            System.out.println("Book has been SUCCESSfully renewed!");
        } else {
            System.out.println("ERROR! This SHOULD HAPPEN");
        }
        System.out.println("============== Test Case 6 End =================");
        /* Test Case 7: Return a book that's 30 days overdue*/
        System.out.println("============== Test Case 7 =================");

        LocalDate thirtyDays = LocalDate.of(2026,11, 4);
        book1.checkOut(customerName, today);

        System.out.println("trying to renew the book " + book1.getName() + " 150 days after checkout. It should be overdue without a fine.");

        if (book1.renew(customerName, thirtyDays)) {
            System.out.println("Book has been SUCCESSfully renewed!");
        } else {
            System.out.println("ERROR! This SHOULD HAPPEN");
        }
        System.out.println("============== Test Case 7 End =================");
        /* Test Case 8: Return a book 2 years after it was checked out*/
        System.out.println("============== Test Case 8 =================");

        LocalDate manyDays = LocalDate.of(2028,6, 7);
        book1.checkOut(customerName, today);

        System.out.println("trying to renew the book " + book1.getName() + " 2 years after checkout. It should be overdue with a $10 fine.");

        if (book1.renew(customerName, manyDays)) {
            System.out.println("Book has been SUCCESSfully renewed!");
        } else {
            System.out.println("ERROR! This SHOULD HAPPEN");
        }
        System.out.println("============== Test Case 8 End =================");
        /* Test Case 8: Return a book that's -1 days overdue*/
    }
}
