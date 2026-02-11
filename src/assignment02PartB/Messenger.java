/*
 * **********************************************
 * San Francisco State University
 * CSC 220 -  Data Structures
 * File Name: Manager.java
 * Author: Duc Ta
 * Author: <First Name> <Last Name>
 * **********************************************
 */

package assignment02PartB;
// Please organize all the given files in 1 same package

// Please make sure to read the provided "_ListOf-PleaseDoNotChange.txt"

public final class Manager extends Person {

    //
    // Instance Data Fields
    //

    //
    // Constructors
    //
    public Manager() {
        this("Gabe", "Kapler");
    }

    public Manager(String firstName, String lastName) {
        super(firstName, lastName);
        this.role = "Manager";
        this.affiliation = new Club(Messenger.getConfig().getLanguage().getClubPhrase(0));
    }

    //
    // Instance Methods
    //

    //
    // Language
    //

    //
    // Override
    //
}