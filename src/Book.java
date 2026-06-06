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
import java.time.temporal.ChronoUnit;

public class Book{
    //Attributes
    private String id; //a unique id for the book in the library system
    private String name;
    private ItemStatus status; //CheckedOut, Available, Lost, OnHold
    private String borrower = null; //initialized to no borrower

    // these dates should remain null as when a book is added to a library it doesnt immediately get checked out
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
	 * Function:    operations when one return (check in) an item. 
	 *              Check the previous status and update the status to AVAILABLE after a successful checkin
	 * @param	customer the customer that has checked out the item
	 * @return	true - success; false - failure
	 */
    public Boolean checkIn(String customer){
        
        if(this.status.equals(ItemStatus.CHECKEDOUT)){ //Make sure the item was checked out
            //Make sure the borrower in the profile is the same person for which this item is returned
            if(borrower.equals(customer)){
                //Change the book status to Available (in default)
                this.status = ItemStatus.AVAILABLE;
                //Reset the borrower to null
                this.borrower = null;
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
	 * Method:	checkOut
	 * Function:    operations when one try to check out an item. 
	 *              Check the previous status and update the status to CHECKEDOUT if successful
	 * @param	customer the customer that is going to check out the item
	 * @return	true - success; false - failure
	 */
    public Boolean checkOut(String customer){
        //Make sure the item is ready for checkout - when its status is AVAILABLE
        if(this.status.equals(ItemStatus.AVAILABLE)){
            this.status = ItemStatus.CHECKEDOUT;
            this.borrower = customer; //record the borrower information
            return true;
        }
        else{
            System.out.println("Error! The book's status is "+ this.status+" before checking out!");
            return false;
        }
        
    }
}