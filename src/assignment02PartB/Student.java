/*
 * **********************************************
 * San Francisco State University
 * CSC 220 -  Data Structures
 * File Name: Student.java
 * Author: Duc Ta
 * Author: <First Name> <Last Name>
 * **********************************************
 */

package assignment02PartB;
// Please organize all the given files in 1 same package

// Please make sure to read the provided "_ListOf-PleaseDoNotChange.txt"

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Student extends Person {

    //
    // Instance Data Fields
    //
    private final List<Card> cards;
    private int quizScore;

    //
    // Constructors
    //
    public Student() {
        this.cards = new ArrayList<>();
        this.quizScore = 0;
        this.role = "Student";
        this.affiliation = new University(Messenger.getConfig().getLanguage().getUniversityPhrase(0));
    }

    //
    // Instance Methods
    //
    public List<Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getQuizScore() {
        return this.quizScore;
    }

    public void setQuizScore(int score) {
        this.quizScore = score;
    }

    public void introduce(Scanner scanner) {
        System.out.print("SF Giants: Your first name and last name, please: ");
        String fullName = scanner.nextLine().trim();
        String[] parts = fullName.split("\\s+", 2);
        this.firstName = parts[0];
        this.lastName = (parts.length > 1) ? parts[1] : "";

        System.out.print("SF Giants: Your school email address, please: ");
        this.email = scanner.nextLine().trim();

        System.out.printf("%s: Welcome to my university!%n", getFullName());
    }

    //
    // Additional Methods
    //

    //
    // Language
    //

    //
    // Override
    //
}