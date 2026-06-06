/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     The basic Book class 
 * Author:      Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/6/2026
 * Version:     1.0 
 *              Basic version of Book class 

 * @see ItemStatus
 * @Used by:     The main class - LibraryMain.java
*******************************************************************************/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Book{
    //Attributes
    private String id; //a unique id for the book in the library system
    private String name;
    private ItemStatus status; //CheckedOut, Available, Lost, OnHold
    private String borrower = null; //initialized to no borrower

    // these dates should remain null as when a book is added to a library. It doesn't immediately get checked out.
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    //Setters and getters
    public void setID(String itemID){
        this.id = itemID;
    }
        
    public void setName(String itemName){
        this.name = itemName;
    }
    
    public void setStatus(ItemStatus itemStatus){
        this.status = itemStatus;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {this.checkoutDate = checkoutDate;}
    
    public String getID(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public ItemStatus getStatus(){
        return this.status;
    }

    public LocalDate getCheckinDate() {
        return this.checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }
    
    
    //Constructor
    public Book(String itemID, String itemName, ItemStatus itemStatus){
        setID(itemID);
        setName(itemName);
        setStatus(itemStatus);
    }
    
    /**
	 * Method:	checkIn
	 * Function:    operations when one returns (checks in) an item.
	 *              Check the previous status and update the status to AVAILABLE after a successful checkin
	 * @param	customer the customer that has checked out the item
     * @param  checkinDate the date checked in
	 * @return	true - success; false - failure
	 */
    public boolean checkIn(String customer, LocalDate checkinDate){
        
        if(this.status.equals(ItemStatus.CHECKEDOUT)){ //Make sure the item was checked out
            //Make sure the borrower in the profile is the same person for which this item is returned
            if(borrower.equals(customer)){
                //Change the book status to Available (in default)
                this.status = ItemStatus.AVAILABLE;
                //Reset the borrower to null
                this.borrower = null;
                this.checkinDate = checkinDate;

                long daysBetween = ChronoUnit.DAYS.between(this.checkoutDate, this.checkinDate); // calculate the dates in between

                double fine = 0; // calculate the fine, default case there is no fine.
                if (daysBetween >= 366) {
                    fine = 10.0;
                } else if (daysBetween >= 181) {
                    fine = 5.0;
                } else if (daysBetween >= 31) {
                    fine = 1.0;
                }
                boolean overdue = fine != 0;
                // format the time as documented on W3Schools.com
                DateTimeFormatter niceDateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                String niceCheckinDate = checkinDate.format(niceDateFormatter);

                if (overdue) {
                    System.out.printf("You returned the book on %s when it was OVERDUE by %d. You owe $%.2f in late fees.", niceCheckinDate, daysBetween, fine);
                } else {
                    System.out.println("Thank you for returning the book on time!");
                }
                this.checkoutDate = null; // reset the checkout date after evaluating any overdue fines

                return true;
            }
            else{
                System.out.println("The customer information does not match the record!");
                return false;
            }
        }
        else{// Operation failed if the item was not checked out before returning 
            System.out.println("Error! The book's status is "+ this.status+" before returning!");
            return false;
        }
    }

    /**
     * Method: checkIn
     * Function: CheckIn, but it assumes that the date checked in is the current time.
     * @return true - success; false - failure
     */
    public boolean checkIn(String customer) {
        return checkIn(customer, LocalDate.now());
    }
     /**
	 * Method:	checkOut
	 * Function:    operations when one tries to check out an item.
	 *              Check the previous status and update the status to CHECKEDOUT if successful
      *             Updates the status of the book's checkedOut attribute.
	 * @param	customer the customer that is going to check out the item
	 * @return	true - success; false - failure
	 */
    public boolean checkOut(String customer, LocalDate checkoutDate){
        //Make sure the item is ready for checkout - when its status is AVAILABLE
        if(this.status.equals(ItemStatus.AVAILABLE)){
            this.status = ItemStatus.CHECKEDOUT;
            this.borrower = customer; //record the borrower information
            this.checkoutDate = checkoutDate;
            this.checkinDate = null;
            return true;
        }
        else{
            System.out.println("Error! The book's status is "+ this.status+" before checking out!");
            return false;
        }
        
    }

    /**
     * Method:  checkout
     * Function: operates when one tries to check out an item.
     *           Check the previous status and update the status to CHECKEDOUT if successful
     *           Updates the status of the books checkedOut atrribute.
     * @param customer the customer that is going to check out the item
     * @return true - success; false - failure
     */
    public boolean checkOut(String customer) {
        return checkOut(customer, LocalDate.now());
    }

    /**
     * Method: renew
     * Function: operates when one tries to renew an item
     *           Check the status if the book can be renewed (is checked out by the customer renewing)
     *           Calls checkin to refresh the checking dates and check if the user owes any late fees.
     * @param customer the customer that is renewing the book
     * @param renewDate the date on which the customer is attempting to renew the book
     * @return true - success; false - failure
     */
    public boolean renew(String customer, LocalDate renewDate) {
        if (!(this.getStatus() == ItemStatus.CHECKEDOUT)) { // check if the book is actually checked out
            System.out.println("You can't renew a book that isn't checked out!");
            return false;
        }
        if (!this.borrower.equals(customer)) { // check if the customer owns the book
            System.out.println("You can't renew a book you haven't checked out!");
            return false;
        }
        // the data that would cause checkIn to fail has been cleared by previous parts of this function
        // thus the result can be safely discarded
        checkIn(customer, renewDate);
        // update the attribute parameters as the book has effectively been re-checked out
        this.checkinDate = null;
        this.checkoutDate = renewDate;
        return true;
    }
    /**
     * Method: renew
     * Function: operates when one tries to renew an item
     *           Check the status if the book can be renewed (is checked out by the customer renewing)
     *           Calls checkin to refresh the checking dates and check if the user owes any late fees.
     * @param customer the customer that is renewing the book
     * @return true - success; false - failure
     */
    public boolean renew(String customer) {
        return renew(customer, LocalDate.now());
    }

}