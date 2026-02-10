# CSC 220 - Assignment 02 Part B: SF Giants Organization System

## Overview

This assignment involves creating a complete object-oriented system to represent the SF Giants baseball organization using Java 15+ features including sealed classes and the `permits` keyword.

## Project Structure

### Package Organization

- **Package**: `assignment02PartB`
- All Java files must be in the same package

### File Categories

#### DO NOT CHANGE (Protected Files)

These files contain core functionality and must NOT be modified:

1. **Greeting.java** - Interface for greeting functionality
2. **Organization.java** - Sealed abstract base class for organizations
3. **Config.java** - Configuration class
4. **SFGiantsCardGenerator.java** - Card generation utility
5. **Messenger.java** - Messaging system (Critical: Do not remove or change existing code)
6. **LogFileDriver** - Logging driver

#### MUST IMPLEMENT/MODIFY (Your Work)

All other `.java` files can be modified or completed:

- Person.java
- Student.java
- Player.java
- President.java
- GeneralManager.java
- Manager.java
- Club.java
- University.java
- OwnerGroup.java
- Language.java
- Card.java
- ChatSession.java
- Color.java
- Directory.java
- FrontOffice.java
- QuestionAnswer.java
- Quiz.java
- Receipt.java
- Timer.java
- Main.java (The entry point)

## Step-by-Step Implementation Guide

### Step 1: Understand the Architecture

**Goal**: Understand the sealed class hierarchy and design patterns

#### 1.1 Class Hierarchy Overview

```
Person (sealed abstract)
├── Student
├── Player
├── President
├── GeneralManager
└── Manager

Organization (sealed abstract)
├── Club
├── University
└── OwnerGroup
```

#### 1.2 Review Sealed Classes

**File**: Person.java

```java
public sealed abstract class Person implements Greeting
    permits Student, Player, President, GeneralManager, Manager {
    // Base class for all people in the organization
}
```

**File**: Organization.java

```java
public sealed abstract class Organization
    permits Club, University, OwnerGroup {
    // Base class for all organizational units
}
```

#### 1.3 Key Concept: Why Sealed Classes?

Sealed classes provide several benefits:

- **Type Safety**: Only specified classes can extend the sealed class
- **Exhaustiveness**: Compiler ensures all permitted subclasses are handled
- **Maintainability**: Clear relationships between parent and child classes
- **Security**: Prevents unauthorized subclassing

#### 1.4 Review Protected Files (DO NOT MODIFY)

Before starting, examine these files to understand the provided functionality:

1. **Greeting.java**
   - Purpose: Interface defining greeting contract
   - Method: `void greet(Language language)`
   - Usage: All Person subclasses must implement this

2. **Messenger.java**
   - Purpose: Core messaging system
   - ⚠️ **Critical**: Do NOT modify or remove existing code
   - Usage: Handle communication between objects

3. **Config.java**
   - Purpose: Configuration constants and settings
   - Contains: Default values, paths, settings for the application

4. **SFGiantsCardGenerator.java**
   - Purpose: Generate baseball cards with player information
   - Input: Player object
   - Output: Formatted card display

5. **LogFileDriver**
   - Purpose: Handle file logging operations
   - Usage: Log events and interactions to files

### Step 2: Implement Core Classes - Hierarchy Level 1

#### 2.1 Implement Person Class

**File**: Person.java

**Purpose**: Base class for all people in the SF Giants organization

**Instance Variables to Add**:

```java
private String firstName;       // Person's first name
private String lastName;        // Person's last name
private String personID;        // Unique identifier
private int age;               // Person's age
private String email;          // Contact email
private String phoneNumber;    // Contact phone
private Language language;     // Preferred language
private Organization organization; // Current organization
```

**Constructor Examples**:

```java
// Default Constructor
public Person() {
    this.firstName = "Unknown";
    this.lastName = "Unknown";
    this.personID = "";
    this.age = 0;
    this.email = "";
    this.phoneNumber = "";
    this.language = Language.ENGLISH;
    this.organization = null;
}

// Parameterized Constructor
public Person(String firstName, String lastName, String personID,
              int age, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.personID = personID;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.language = Language.ENGLISH;
    this.organization = null;
}
```

**Required Methods**:

```java
// Getters
public String getFirstName() { return firstName; }
public String getLastName() { return lastName; }
public String getFullName() { return firstName + " " + lastName; }
public String getPersonID() { return personID; }
public int getAge() { return age; }
public String getEmail() { return email; }
public String getPhoneNumber() { return phoneNumber; }
public Language getLanguage() { return language; }
public Organization getOrganization() { return organization; }

// Setters
public void setFirstName(String firstName) { this.firstName = firstName; }
public void setLastName(String lastName) { this.lastName = lastName; }
public void setAge(int age) { this.age = age; }
public void setEmail(String email) { this.email = email; }
public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
public void setLanguage(Language language) { this.language = language; }
public void setOrganization(Organization organization) { this.organization = organization; }

// Interface Implementation (from Greeting)
@Override
public void greet(Language language) {
    System.out.println("Hello, my name is " + getFullName());
}

// Standard Methods
@Override
public String toString() {
    return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", personID='" + personID + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", language=" + language +
            '}';
}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Person person = (Person) obj;
    return personID.equals(person.personID);
}

@Override
public int hashCode() {
    return personID.hashCode();
}
```

#### 2.2 Implement Student Class

**File**: Student.java (extends Person)

**Purpose**: Represents a student affiliated with the organization

**Additional Instance Variables**:

```java
private String studentID;           // University student ID
private String major;              // Field of study
private boolean isEnrolled;        // Current enrollment status
private University university;     // Associated university
private double gpa;                // Grade point average
```

**Constructor Example**:

```java
public Student(String firstName, String lastName, String personID, int age,
               String email, String phoneNumber, String studentID,
               String major, University university) {
    super(firstName, lastName, personID, age, email, phoneNumber);
    this.studentID = studentID;
    this.major = major;
    this.university = university;
    this.isEnrolled = true;
    this.gpa = 0.0;
}
```

**Required Methods**:

```java
// Getters
public String getStudentID() { return studentID; }
public String getMajor() { return major; }
public boolean isEnrolled() { return isEnrolled; }
public University getUniversity() { return university; }
public double getGPA() { return gpa; }

// Setters
public void setStudentID(String studentID) { this.studentID = studentID; }
public void setMajor(String major) { this.major = major; }
public void setEnrolled(boolean enrolled) { isEnrolled = enrolled; }
public void setGPA(double gpa) { this.gpa = gpa; }

// Student-specific Methods
public void enrollInCourse(String courseName) {
    System.out.println(getFullName() + " enrolled in " + courseName);
}

public void updateGPA(double newGPA) {
    this.gpa = newGPA;
    System.out.println(getFullName() + "'s GPA updated to " + newGPA);
}

// Override greeting
@Override
public void greet(Language language) {
    System.out.println("Hi, I'm " + getFullName() + ", a student at " +
                       (university != null ? university : "a university"));
}

@Override
public String toString() {
    return super.toString() + "\nStudent{" +
            "studentID='" + studentID + '\'' +
            ", major='" + major + '\'' +
            ", isEnrolled=" + isEnrolled +
            ", gpa=" + gpa +
            '}';
}
```

#### 2.3 Implement Player Class

**File**: Player.java (extends Person)

**Purpose**: Represents a baseball player for the SF Giants

**Additional Instance Variables**:

```java
private int jerseyNumber;         // Jersey number (1-99)
private String position;          // Position (pitcher, catcher, etc.)
private int battingAverage;       // Batting statistics (or use double)
private int homeRuns;             // Number of home runs
private Club club;               // Team affiliation
private boolean isActive;        // Currently playing
private String dateJoined;       // When player joined
```

**Constructor Example**:

```java
public Player(String firstName, String lastName, String personID, int age,
              String email, String phoneNumber, int jerseyNumber,
              String position, Club club) {
    super(firstName, lastName, personID, age, email, phoneNumber);
    this.jerseyNumber = jerseyNumber;
    this.position = position;
    this.club = club;
    this.battingAverage = 0;
    this.homeRuns = 0;
    this.isActive = true;
    this.dateJoined = new java.time.LocalDate.now().toString();
}
```

**Required Methods**:

```java
// Getters
public int getJerseyNumber() { return jerseyNumber; }
public String getPosition() { return position; }
public int getBattingAverage() { return battingAverage; }
public int getHomeRuns() { return homeRuns; }
public Club getClub() { return club; }
public boolean isActive() { return isActive; }
public String getDateJoined() { return dateJoined; }

// Setters
public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }
public void setPosition(String position) { this.position = position; }
public void setClub(Club club) { this.club = club; }
public void setActive(boolean active) { isActive = active; }

// Player-specific Methods
public void updateStats(int batAvg, int hrs) {
    this.battingAverage = batAvg;
    this.homeRuns = hrs;
    System.out.println(getFullName() + " stats updated: Avg=" + batAvg + ", HRs=" + hrs);
}

public void recordHomeRun() {
    this.homeRuns++;
    System.out.println(getFullName() + " hit a home run! Total: " + homeRuns);
}

public void generateCard() {
    // Use SFGiantsCardGenerator to create a card
    // This will be handled by the Card class
    System.out.println("Card generated for " + getFullName() +
                       " #" + jerseyNumber + " - " + position);
}

// Override greeting
@Override
public void greet(Language language) {
    System.out.println("Hey! I'm " + getFullName() + ", #" + jerseyNumber +
                       " " + position + " for the SF Giants");
}

@Override
public String toString() {
    return super.toString() + "\nPlayer{" +
            "jerseyNumber=" + jerseyNumber +
            ", position='" + position + '\'' +
            ", battingAverage=" + battingAverage +
            ", homeRuns=" + homeRuns +
            ", isActive=" + isActive +
            '}';
}
```

#### 2.4 Implement President Class

**File**: President.java (extends Person)

**Purpose**: Represents organizational leadership

**Additional Instance Variables**:

```java
private String title;              // President, Vice President, etc.
private Organization organization; // Organization led
private int yearsInPosition;       // Years as president
private String department;         // Responsible department
```

**Constructor Example**:

```java
public President(String firstName, String lastName, String personID, int age,
                 String email, String phoneNumber, String title,
                 Organization organization) {
    super(firstName, lastName, personID, age, email, phoneNumber);
    this.title = title;
    this.organization = organization;
    this.yearsInPosition = 0;
    this.department = "";
}
```

**Required Methods**:

```java
// Getters and Setters
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public Organization getOrganization() { return organization; }
public void setOrganization(Organization organization) { this.organization = organization; }
public int getYearsInPosition() { return yearsInPosition; }
public void setYearsInPosition(int years) { this.yearsInPosition = years; }
public String getDepartment() { return department; }
public void setDepartment(String department) { this.department = department; }

// President-specific Methods
public void makeDecision(String decision) {
    System.out.println(getFullName() + " (" + title + ") decides: " + decision);
}

public void promotePerson(Person person, String newRole) {
    System.out.println(getFullName() + " promotes " + person.getFullName() +
                       " to " + newRole);
}

// Override greeting
@Override
public void greet(Language language) {
    System.out.println("Welcome. I'm " + getFullName() + ", " + title +
                       " of the organization");
}

@Override
public String toString() {
    return super.toString() + "\nPresident{" +
            "title='" + title + '\'' +
            ", yearsInPosition=" + yearsInPosition +
            '}';
}
```

#### 2.5 Implement Manager Classes

**File**: GeneralManager.java (extends Person)

**Purpose**: Manages overall operations and strategy

**Instance Variables**:

```java
private int employeesManaged;    // Number of employees
private String department;        // Department managed
private String yearsExperience;  // Management experience
private Club club;               // Associated club
```

**Key Methods**:

```java
public void hireEmployee(Person employee) { /* ... */ }
public void fireEmployee(String personID) { /* ... */ }
public void makeTradeDecision(Player player, String newTeam) { /* ... */ }
public void setStrategy(String strategy) { /* ... */ }

@Override
public void greet(Language language) {
    System.out.println("I'm " + getFullName() + ", General Manager. " +
                       "I manage " + employeesManaged + " people");
}
```

**File**: Manager.java (extends Person)

**Purpose**: Manages day-to-day operations or specific teams

**Instance Variables**:

```java
private String shift;           // Day, Night, etc.
private String team;            // Team managed
private int yearsWithCompany;  // Tenure
```

**Key Methods**:

```java
public void assignTask(Person person, String task) { /* ... */ }
public void conductMeeting() { /* ... */ }
public void scheduleEvent(String eventName, String date) { /* ... */ }

@Override
public void greet(Language language) {
    System.out.println("Hello, I'm " + getFullName() +
                       ", a manager on the " + shift + " shift");
}
```

### Step 3: Implement Organization Classes - Hierarchy Level 2

#### 3.1 Understand Organization Base

**File**: Organization.java (PROTECTED - Review Only)

Review the abstract methods you must implement in subclasses:

```java
public sealed abstract class Organization
    permits Club, University, OwnerGroup {

    // Likely abstract methods (examine the file):
    public abstract String getOrganizationName();
    public abstract String getOrganizationID();
    public abstract void displayInfo();
    public abstract int getMemberCount();
    // ... other methods
}
```

#### 3.2 Implement Club Class

**File**: Club.java (extends Organization)

**Purpose**: Represents an athletic club (e.g., SF Giants baseball club)

**Instance Variables**:

```java
private String clubName;              // Club name (e.g., "SF Giants")
private String clubID;                // Unique identifier
private List<Player> players;         // Roster of players
private List<Manager> managers;       // Management team
private String foundingDate;          // Establishment date
private String location;              // Club location
private String description;           // Club description
private President president;          // Club president
private GeneralManager generalManager; // Club GM
```

**Constructor Example**:

```java
public Club(String clubName, String clubID, String location) {
    this.clubName = clubName;
    this.clubID = clubID;
    this.location = location;
    this.players = new ArrayList<>();
    this.managers = new ArrayList<>();
    this.foundingDate = new java.time.LocalDate.now().toString();
    this.description = "";
    this.president = null;
    this.generalManager = null;
}
```

**Required Methods**:

```java
// Getters
public String getClubName() { return clubName; }
public String getClubID() { return clubID; }
public String getLocation() { return location; }
public List<Player> getPlayers() { return new ArrayList<>(players); }
public List<Manager> getManagers() { return new ArrayList<>(managers); }
public int getPlayerCount() { return players.size(); }
public President getPresident() { return president; }
public GeneralManager getGeneralManager() { return generalManager; }

// Setters
public void setPresident(President president) { this.president = president; }
public void setGeneralManager(GeneralManager generalManager) {
    this.generalManager = generalManager;
}

// Club-specific Methods
public void addPlayer(Player player) {
    if (!players.contains(player)) {
        players.add(player);
        player.setClub(this);
        System.out.println(player.getFullName() + " joined " + clubName);
    }
}

public void removePlayer(Player player) {
    if (players.remove(player)) {
        System.out.println(player.getFullName() + " left " + clubName);
    }
}

public void addManager(Manager manager) {
    if (!managers.contains(manager)) {
        managers.add(manager);
        System.out.println(manager.getFullName() + " joined as manager");
    }
}

public Player findPlayerByNumber(int jerseyNumber) {
    return players.stream()
        .filter(p -> p.getJerseyNumber() == jerseyNumber)
        .findFirst()
        .orElse(null);
}

public void displayRoster() {
    System.out.println("\n=== " + clubName + " Roster ===");
    for (Player player : players) {
        System.out.println("#" + player.getJerseyNumber() + " - " +
                          player.getFullName() + " (" + player.getPosition() + ")");
    }
}

// Implement abstract methods
@Override
public String getOrganizationName() { return clubName; }

@Override
public String getOrganizationID() { return clubID; }

@Override
public void displayInfo() {
    System.out.println("Club: " + clubName);
    System.out.println("ID: " + clubID);
    System.out.println("Location: " + location);
    System.out.println("Players: " + players.size());
    System.out.println("Managers: " + managers.size());
}

@Override
public int getMemberCount() {
    return players.size() + managers.size() + (president != null ? 1 : 0) +
           (generalManager != null ? 1 : 0);
}

@Override
public String toString() {
    return "Club{" +
            "clubName='" + clubName + '\'' +
            ", location='" + location + '\'' +
            ", players=" + players.size() +
            ", managers=" + managers.size() +
            '}';
}
```

#### 3.3 Implement University Class

**File**: University.java (extends Organization)

**Purpose**: Represents a university organization

**Instance Variables**:

```java
private String universityName;       // University name
private String universityID;         // Unique identifier
private List<Student> students;      // Enrolled students
private String location;             // University location
private String accreditation;        // Accreditation status
private President president;         // University president
private int foundedYear;            // Year founded
```

**Constructor Example**:

```java
public University(String universityName, String universityID,
                  String location, int foundedYear) {
    this.universityName = universityName;
    this.universityID = universityID;
    this.location = location;
    this.students = new ArrayList<>();
    this.foundedYear = foundedYear;
    this.accreditation = "Accredited";
    this.president = null;
}
```

**Required Methods**:

```java
// Getters
public String getUniversityName() { return universityName; }
public String getUniversityID() { return universityID; }
public List<Student> getStudents() { return new ArrayList<>(students); }
public int getStudentCount() { return students.size(); }
public President getPresident() { return president; }
public int getFoundedYear() { return foundedYear; }

// Setters
public void setPresident(President president) { this.president = president; }

// University-specific Methods
public void enrollStudent(Student student) {
    if (!students.contains(student)) {
        students.add(student);
        student.setOrganization(this);
        System.out.println(student.getFullName() +
                          " enrolled at " + universityName);
    }
}

public void dropStudent(Student student) {
    if (students.remove(student)) {
        student.setEnrolled(false);
        System.out.println(student.getFullName() +
                          " dropped from " + universityName);
    }
}

public void displayStudentList() {
    System.out.println("\n=== " + universityName + " Students ===");
    for (Student student : students) {
        System.out.println(student.getFullName() + " - " +
                          student.getMajor() + " (GPA: " + student.getGPA() + ")");
    }
}

// Implement abstract methods
@Override
public String getOrganizationName() { return universityName; }

@Override
public String getOrganizationID() { return universityID; }

@Override
public void displayInfo() {
    System.out.println("University: " + universityName);
    System.out.println("ID: " + universityID);
    System.out.println("Location: " + location);
    System.out.println("Founded: " + foundedYear);
    System.out.println("Students: " + students.size());
    System.out.println("Accreditation: " + accreditation);
}

@Override
public int getMemberCount() {
    return students.size() + (president != null ? 1 : 0);
}

@Override
public String toString() {
    return "University{" +
            "universityName='" + universityName + '\'' +
            ", location='" + location + '\'' +
            ", students=" + students.size() +
            ", foundedYear=" + foundedYear +
            '}';
}
```

#### 3.4 Implement OwnerGroup Class

**File**: OwnerGroup.java (extends Organization)

**Purpose**: Represents the ownership group of an organization

**Instance Variables**:

```java
private String groupName;           // Group name
private String groupID;             // Unique identifier
private List<President> owners;     // List of owners
private Map<String, Double> ownershipPercentage; // Ownership shares
private String contactPerson;       // Primary contact
private String address;             // Contact address
```

**Constructor Example**:

```java
public OwnerGroup(String groupName, String groupID, String address) {
    this.groupName = groupName;
    this.groupID = groupID;
    this.address = address;
    this.owners = new ArrayList<>();
    this.ownershipPercentage = new HashMap<>();
    this.contactPerson = "";
}
```

**Required Methods**:

```java
// Getters
public String getGroupName() { return groupName; }
public String getGroupID() { return groupID; }
public List<President> getOwners() { return new ArrayList<>(owners); }
public String getAddress() { return address; }
public int getOwnerCount() { return owners.size(); }

// Setters
public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
}

// OwnerGroup-specific Methods
public void addOwner(President owner, double ownershipPercent) {
    if (!owners.contains(owner)) {
        owners.add(owner);
        ownershipPercentage.put(owner.getPersonID(), ownershipPercent);
        System.out.println(owner.getFullName() +
                          " added as owner (" + ownershipPercent + "%)");
    }
}

public void removeOwner(President owner) {
    if (owners.remove(owner)) {
        ownershipPercentage.remove(owner.getPersonID());
        System.out.println(owner.getFullName() + " removed as owner");
    }
}

public void displayOwnershipStructure() {
    System.out.println("\n=== " + groupName + " Ownership ===");
    for (President owner : owners) {
        double percent = ownershipPercentage.getOrDefault(
            owner.getPersonID(), 0.0);
        System.out.println(owner.getFullName() + ": " + percent + "%");
    }
}

// Implement abstract methods
@Override
public String getOrganizationName() { return groupName; }

@Override
public String getOrganizationID() { return groupID; }

@Override
public void displayInfo() {
    System.out.println("Owner Group: " + groupName);
    System.out.println("ID: " + groupID);
    System.out.println("Address: " + address);
    System.out.println("Owners: " + owners.size());
    System.out.println("Contact: " + contactPerson);
}

@Override
public int getMemberCount() {
    return owners.size();
}

@Override
public String toString() {
    return "OwnerGroup{" +
            "groupName='" + groupName + '\'' +
            ", owners=" + owners.size() +
            ", address='" + address + '\'' +
            '}';
}
```

### Step 4: Implement Supporting Classes

#### 4.1 Language Support

**File**: Language.java

**Purpose**: Handle multi-language greeting and phrase support

**Implementation Option 1: Enum-based**:

```java
public enum Language {
    ENGLISH("English"),
    SPANISH("Spanish"),
    FRENCH("French"),
    CHINESE("Chinese");

    private String name;
    private Map<String, String> phrases;

    Language(String name) {
        this.name = name;
        this.phrases = new HashMap<>();
        populatePhrases();
    }

    private void populatePhrases() {
        switch(this) {
            case ENGLISH -> populateEnglishPhrases();
            case SPANISH -> populateSpanishPhrases();
            case FRENCH -> populateFrenchPhrases();
            case CHINESE -> populateChinesePhrases();
        }
    }

    private void populateEnglishPhrases() {
        phrases.put("greeting", "Hello");
        phrases.put("farewell", "Goodbye");
        phrases.put("thank_you", "Thank you");
        phrases.put("welcome", "Welcome to SF Giants");
        phrases.put("player_intro", "I'm a player for the team");
        phrases.put("student_intro", "I'm a student");
    }

    private void populateSpanishPhrases() {
        phrases.put("greeting", "Hola");
        phrases.put("farewell", "Adiós");
        phrases.put("thank_you", "Gracias");
        phrases.put("welcome", "Bienvenido a los Gigantes de San Francisco");
        // ... more phrases
    }

    private void populateFrenchPhrases() {
        phrases.put("greeting", "Bonjour");
        phrases.put("farewell", "Au revoir");
        phrases.put("thank_you", "Merci");
        // ... more phrases
    }

    private void populateChinesePhrases() {
        phrases.put("greeting", "你好");
        phrases.put("farewell", "再见");
        phrases.put("thank_you", "谢谢");
        // ... more phrases
    }

    public String getPhrase(String key) {
        return phrases.getOrDefault(key, "Phrase not found");
    }

    public String getLanguageName() {
        return name;
    }
}
```

**Usage in Person class**:

```java
Language currentLanguage = Language.ENGLISH;
String greeting = currentLanguage.getPhrase("greeting");
System.out.println(greeting + "!");
```

#### 4.2 Card System

**File**: Card.java

**Purpose**: Represent baseball cards with player information

**Instance Variables**:

```java
private String cardID;            // Unique card ID
private Player player;            // Associated player
private String cardNumber;        // Card number
private Color cardColor;          // Card color
private String season;            // Season/year
private String rarity;            // Rarity level
private double cardValue;         // Estimated value
private String description;       // Card description
```

**Methods**:

```java
public Card(String cardID, Player player, Color color, String season) {
    this.cardID = cardID;
    this.player = player;
    this.cardNumber = player.getJerseyNumber() + "-" + season;
    this.cardColor = color;
    this.season = season;
    this.rarity = "Common";
    this.cardValue = 0.0;
}

public void displayCard() {
    System.out.println("╔═══════════════════════════╗");
    System.out.println("║  " + player.getFullName() + "        ║");
    System.out.println("║  #" + player.getJerseyNumber() + " - " + player.getPosition() + "       ║");
    System.out.println("║  Season: " + season + "           ║");
    System.out.println("║  Rarity: " + rarity + "          ║");
    System.out.println("╚═══════════════════════════╝");
}

public void setCardValue(double value) { this.cardValue = value; }
public void setRarity(String rarity) { this.rarity = rarity; }
public Player getPlayer() { return player; }
public String getCardID() { return cardID; }
public double getCardValue() { return cardValue; }
```

**File**: Color.java

**Purpose**: Represent card colors

**Simple Implementation**:

```java
public enum Color {
    RED("#FF0000"),
    BLUE("#0000FF"),
    GOLD("#FFD700"),
    SILVER("#C0C0C0"),
    HOLOGRAPHIC("#FF69B4");

    private String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() { return hexCode; }
}
```

#### 4.3 Interactive Components

**File**: ChatSession.java

**Purpose**: Manage conversation sessions

**Instance Variables**:

```java
private String sessionID;
private List<String> conversationHistory;
private Person user;
private LocalDateTime startTime;
private LocalDateTime endTime;
```

**Methods**:

```java
public ChatSession(String sessionID, Person user) {
    this.sessionID = sessionID;
    this.user = user;
    this.conversationHistory = new ArrayList<>();
    this.startTime = LocalDateTime.now();
}

public void addMessage(String message) {
    conversationHistory.add(message);
}

public void displayHistory() {
    System.out.println("\n=== Chat Session " + sessionID + " ===");
    for (String message : conversationHistory) {
        System.out.println(message);
    }
}

public void endSession() {
    this.endTime = LocalDateTime.now();
    System.out.println("Session ended");
}

public List<String> getHistory() {
    return new ArrayList<>(conversationHistory);
}
```

**File**: QuestionAnswer.java

**Purpose**: Store Q&A pairs for quizzes

**Instance Variables**:

```java
private String questionID;
private String question;
private String correctAnswer;
private List<String> options;
private int difficulty;
private String topic;
```

**Methods**:

```java
public QuestionAnswer(String questionID, String question, String answer) {
    this.questionID = questionID;
    this.question = question;
    this.correctAnswer = answer;
    this.options = new ArrayList<>();
    this.difficulty = 1;
}

public void addOption(String option) {
    options.add(option);
}

public boolean isCorreectAnswer(String userAnswer) {
    return userAnswer.equalsIgnoreCase(correctAnswer);
}

public void displayQuestion() {
    System.out.println("\nQuestion: " + question);
    for (int i = 0; i < options.size(); i++) {
        System.out.println((i + 1) + ". " + options.get(i));
    }
}

public String getCorrectAnswer() { return correctAnswer; }
public List<String> getOptions() { return new ArrayList<>(options); }
```

**File**: Quiz.java

**Purpose**: Manage a collection of questions and scoring

**Instance Variables**:

```java
private String quizID;
private String quizTitle;
private List<QuestionAnswer> questions;
private int score;
private int totalQuestions;
private Person taker;
private LocalDateTime startTime;
```

**Methods**:

```java
public Quiz(String quizID, String quizTitle, Person taker) {
    this.quizID = quizID;
    this.quizTitle = quizTitle;
    this.questions = new ArrayList<>();
    this.taker = taker;
    this.score = 0;
    this.totalQuestions = 0;
    this.startTime = LocalDateTime.now();
}

public void addQuestion(QuestionAnswer question) {
    questions.add(question);
    totalQuestions++;
}

public void displayQuiz() {
    System.out.println("\n╔════════════════════════════╗");
    System.out.println("║  Quiz: " + quizTitle);
    System.out.println("║  Taker: " + taker.getFullName());
    System.out.println("║  Questions: " + totalQuestions);
    System.out.println("╚════════════════════════════╝");

    for (int i = 0; i < questions.size(); i++) {
        System.out.println("\n(" + (i + 1) + "/" + totalQuestions + ")");
        questions.get(i).displayQuestion();
    }
}

public void recordAnswer(int questionIndex, String answer) {
    if (questionIndex >= 0 && questionIndex < questions.size()) {
        QuestionAnswer q = questions.get(questionIndex);
        if (q.isCorreectAnswer(answer)) {
            score++;
            System.out.println("✓ Correct!");
        } else {
            System.out.println("✗ Incorrect. The answer is: " + q.getCorrectAnswer());
        }
    }
}

public void displayScore() {
    double percentage = (double) score / totalQuestions * 100;
    System.out.println("\n╔════════════════════════════╗");
    System.out.println("║  Quiz Results");
    System.out.println("║  Score: " + score + "/" + totalQuestions);
    System.out.println("║  Percentage: " + String.format("%.2f", percentage) + "%");
    System.out.println("╚════════════════════════════╝");
}

public int getScore() { return score; }
public int getTotalQuestions() { return totalQuestions; }
```

#### 4.4 Utility Classes

**File**: Timer.java

**Purpose**: Track time intervals and durations

**Methods**:

```java
public class Timer {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isRunning;

    public void start() {
        this.startTime = LocalDateTime.now();
        this.isRunning = true;
        System.out.println("Timer started");
    }

    public void stop() {
        this.endTime = LocalDateTime.now();
        this.isRunning = false;
        System.out.println("Timer stopped");
    }

    public long getElapsedSeconds() {
        if (isRunning) {
            return java.time.temporal.ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
        }
        return java.time.temporal.ChronoUnit.SECONDS.between(startTime, endTime);
    }

    public void reset() {
        this.startTime = null;
        this.endTime = null;
        this.isRunning = false;
    }
}
```

**File**: Receipt.java

**Purpose**: Store transaction and purchase information

**Instance Variables**:

```java
private String receiptID;
private LocalDateTime timestamp;
private Person buyer;
private List<String> items;
private double totalAmount;
private String paymentMethod;
```

**Methods**:

```java
public Receipt(String receiptID, Person buyer) {
    this.receiptID = receiptID;
    this.buyer = buyer;
    this.timestamp = LocalDateTime.now();
    this.items = new ArrayList<>();
    this.totalAmount = 0.0;
}

public void addItem(String item, double price) {
    items.add(item + " - $" + price);
    totalAmount += price;
}

public void displayReceipt() {
    System.out.println("\n╔════════════════════════════╗");
    System.out.println("║  RECEIPT #" + receiptID);
    System.out.println("║  Date: " + timestamp);
    System.out.println("║  Customer: " + buyer.getFullName());
    System.out.println("╟────────────────────────────╢");
    for (String item : items) {
        System.out.println("║  " + item);
    }
    System.out.println("╟────────────────────────────╢");
    System.out.println("║  Total: $" + String.format("%.2f", totalAmount));
    System.out.println("║  Payment: " + paymentMethod);
    System.out.println("╚════════════════════════════╝");
}
```

**File**: Directory.java

**Purpose**: Maintain directory of people and organizations

**Methods**:

```java
public class Directory {
    private Map<String, Person> people;
    private Map<String, Organization> organizations;

    public Directory() {
        this.people = new HashMap<>();
        this.organizations = new HashMap<>();
    }

    public void addPerson(String id, Person person) {
        people.put(id, person);
    }

    public Person findPerson(String id) {
        return people.get(id);
    }

    public void addOrganization(String id, Organization org) {
        organizations.put(id, org);
    }

    public Organization findOrganization(String id) {
        return organizations.get(id);
    }

    public void displayDirectory() {
        System.out.println("\n=== Directory ===");
        System.out.println("People: " + people.size());
        for (Person p : people.values()) {
            System.out.println("  - " + p.getFullName());
        }
        System.out.println("Organizations: " + organizations.size());
        for (Organization o : organizations.values()) {
            System.out.println("  - " + o.getOrganizationName());
        }
    }
}
```

#### 4.5 Front Office

**File**: FrontOffice.java

**Purpose**: Handle customer service and operations

**Instance Variables**:

```java
private String officeName;
private List<Person> staff;
private Directory directory;
private List<Receipt> receipts;
private int ticketsSold;
```

**Methods**:

```java
public FrontOffice(String officeName) {
    this.officeName = officeName;
    this.staff = new ArrayList<>();
    this.directory = new Directory();
    this.receipts = new ArrayList<>();
    this.ticketsSold = 0;
}

public void sellTicket(Person customer, int quantity) {
    ticketsSold += quantity;
    System.out.println(quantity + " tickets sold to " + customer.getFullName());
}

public Receipt generateReceipt(String receiptID, Person customer) {
    Receipt receipt = new Receipt(receiptID, customer);
    receipt.addItem("Game Ticket", 50.00);
    receipts.add(receipt);
    receipt.displayReceipt();
    return receipt;
}

public void displayStats() {
    System.out.println("\n=== Front Office Statistics ===");
    System.out.println("Office: " + officeName);
    System.out.println("Staff: " + staff.size());
    System.out.println("Tickets Sold: " + ticketsSold);
    System.out.println("Receipts: " + receipts.size());
}
```

### Step 5: Implement Main Class

**File**: Main.java

The Main class serves as the entry point and demonstrates all functionality.

**Complete Example**:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║  SF Giants Organization System              ║");
        System.out.println("║  CSC 220 - Assignment 02 Part B             ║");
        System.out.println("╚═════════════════════════════════════════════╝\n");

        // Step 1: Create Organizations
        System.out.println("Step 1: Creating Organizations...\n");

        Club sfGiants = new Club("San Francisco Giants", "SFG001", "San Francisco, CA");
        University sfsu = new University("San Francisco State University", "SFSU001",
                                         "San Francisco, CA", 1899);
        OwnerGroup ownership = new OwnerGroup("Giants Ownership Group", "OWN001",
                                              "San Francisco, CA");

        // Step 2: Create People
        System.out.println("\nStep 2: Creating People...\n");

        // Create President
        President president = new President("John", "Doe", "PRES001", 55,
                                           "john.doe@giants.com", "555-0001",
                                           "President", sfGiants);
        president.setDepartment("Executive");
        sfGiants.setPresident(president);

        // Create General Manager
        GeneralManager gm = new GeneralManager("Jane", "Smith", "GM001", 48,
                                               "jane.smith@giants.com", "555-0002");
        gm.setEmployeesManaged(150);
        gm.setClub(sfGiants);
        sfGiants.setGeneralManager(gm);

        // Create Players
        Player player1 = new Player("Mike", "Wilson", "P001", 28,
                                    "mike.wilson@giants.com", "555-0003",
                                    25, "Pitcher", sfGiants);

        Player player2 = new Player("Alex", "Johnson", "P002", 32,
                                    "alex.johnson@giants.com", "555-0004",
                                    7, "Outfield", sfGiants);

        Player player3 = new Player("Carlos", "Garcia", "P003", 25,
                                    "carlos.garcia@giants.com", "555-0005",
                                    14, "Infield", sfGiants);

        // Create Manager
        Manager manager = new Manager("Tom", "Brown", "M001", 45,
                                      "tom.brown@giants.com", "555-0006");
        manager.setShift("Day");
        sfGiants.addManager(manager);

        // Create Students
        Student student1 = new Student("Emma", "Davis", "S001", 20,
                                       "emma.davis@mail.com", "555-0007",
                                       "SF123456", "Computer Science", sfsu);
        student1.setGPA(3.85);

        Student student2 = new Student("Ryan", "Martinez", "S002", 21,
                                       "ryan.martinez@mail.com", "555-0008",
                                       "SF234567", "Engineering", sfsu);
        student2.setGPA(3.70);

        // Step 3: Build Relationships
        System.out.println("\nStep 3: Building Relationships...\n");

        // Add players to club
        sfGiants.addPlayer(player1);
        sfGiants.addPlayer(player2);
        sfGiants.addPlayer(player3);

        // Enroll students
        sfsu.enrollStudent(student1);
        sfsu.enrollStudent(student2);
        sfsu.setPresident(president);

        // Add owners
        ownership.addOwner(president, 100.0);

        // Step 4: Test Greetings with Different Languages
        System.out.println("\nStep 4: Testing Greetings...\n");

        System.out.println("--- English Greetings ---");
        player1.setLanguage(Language.ENGLISH);
        player1.greet(Language.ENGLISH);

        student1.setLanguage(Language.ENGLISH);
        student1.greet(Language.ENGLISH);

        president.setLanguage(Language.ENGLISH);
        president.greet(Language.ENGLISH);

        System.out.println("\n--- Spanish Greetings ---");
        player2.setLanguage(Language.SPANISH);
        player2.greet(Language.SPANISH);

        System.out.println("\n--- Other Language Greetings ---");
        player3.setLanguage(Language.FRENCH);
        player3.greet(Language.FRENCH);

        // Step 5: Display Organization Information
        System.out.println("\nStep 5: Organization Information...\n");

        sfGiants.displayInfo();
        sfGiants.displayRoster();

        sfsu.displayInfo();
        sfsu.displayStudentList();

        ownership.displayInfo();
        ownership.displayOwnershipStructure();

        // Step 6: Test Card Generation
        System.out.println("\nStep 6: Generating Baseball Cards...\n");

        Card card1 = new Card("CARD001", player1, Color.GOLD, "2026");
        card1.setRarity("Holographic");
        card1.setCardValue(150.00);
        card1.displayCard();

        Card card2 = new Card("CARD002", player2, Color.SILVER, "2026");
        card2.setRarity("Rare");
        card2.setCardValue(75.00);
        card2.displayCard();

        // Step 7: Test Quiz System
        System.out.println("\nStep 7: Running a Quiz...\n");

        Quiz quiz = new Quiz("QUIZ001", "SF Giants Trivia", student1);

        QuestionAnswer q1 = new QuestionAnswer("Q1",
            "What is the jersey number of the pitcher?", "25");
        q1.addOption("25");
        q1.addOption("7");
        q1.addOption("14");
        q1.addOption("32");
        quiz.addQuestion(q1);

        QuestionAnswer q2 = new QuestionAnswer("Q2",
            "What year was SFSU founded?", "1899");
        q2.addOption("1850");
        q2.addOption("1899");
        q2.addOption("1950");
        q2.addOption("2000");
        quiz.addQuestion(q2);

        quiz.displayQuiz();

        System.out.println("\n--- Answering Questions ---");
        quiz.recordAnswer(0, "25");  // Correct
        quiz.recordAnswer(1, "1899"); // Correct

        quiz.displayScore();

        // Step 8: Test Chat Session
        System.out.println("\nStep 8: Chat Session...\n");

        ChatSession chatSession = new ChatSession("CHAT001", player1);
        chatSession.addMessage(player1.getFullName() + ": Hi everyone!");
        chatSession.addMessage("Manager: Welcome to the team!");
        chatSession.addMessage(player1.getFullName() + ": Thanks for having me!");
        chatSession.displayHistory();
        chatSession.endSession();

        // Step 9: Test Receipt Generation
        System.out.println("\nStep 9: Receipt Generation...\n");

        Receipt receipt = new Receipt("REC001", student1);
        receipt.addItem("Game Ticket", 50.00);
        receipt.addItem("Team Jersey", 75.00);
        receipt.addItem("Concessions", 25.50);
        receipt.setPaymentMethod("Credit Card");
        receipt.displayReceipt();

        // Step 10: Display Directory
        System.out.println("\nStep 10: Directory Summary...\n");

        Directory directory = new Directory();
        directory.addPerson("P001", player1);
        directory.addPerson("P002", player2);
        directory.addPerson("S001", student1);
        directory.addOrganization("GIANTS", sfGiants);
        directory.addOrganization("SFSU", sfsu);
        directory.displayDirectory();

        // Step 11: Test Statistics
        System.out.println("\nStep 11: Statistics...\n");

        FrontOffice frontOffice = new FrontOffice("SF Giants Front Office");
        frontOffice.sellTicket(student1, 2);
        frontOffice.sellTicket(student2, 4);
        frontOffice.displayStats();

        // Conclusion
        System.out.println("\n╔═════════════════════════════════════════════╗");
        System.out.println("║  Program Completed Successfully!            ║");
        System.out.println("║  All Features Demonstrated!                 ║");
        System.out.println("╚═════════════════════════════════════════════╝");
    }
}
```

**Test Checklist for Main.java**:

- [ ] Organizations created successfully
- [ ] All Person types instantiated
- [ ] Relationships established
- [ ] Greetings work in multiple languages
- [ ] Organization info displays correctly
- [ ] Cards are generated and displayed
- [ ] Quiz runs and calculates scores
- [ ] Chat sessions work
- [ ] Receipts generate properly
- [ ] Directory maintains entries
- [ ] No compile or runtime errors
- [ ] Output is well-formatted and clear

### Step 6: Verify Output

**File**: SampleOutput-01.pdf

1. Run Main.java
2. Compare output with SampleOutput-01.pdf
3. Ensure all:
   - Greetings are correct
   - Organization info displays properly
   - Cards are generated correctly
   - Quiz/Chat functions work
   - Language switching works (if implemented)

## Implementation Notes

### Important Constraints

- ⚠️ DO NOT modify files in the "DO NOT CHANGE" list
- ⚠️ DO NOT remove existing code in Messenger.java
- ⚠️ Submit complete files, not just added code
- ⚠️ All files must be in the same package: `assignment02PartB`

### Java 15+ Features Used

- **Sealed Classes**: Person and Organization restrict their subclasses
- **Switch Expressions**: Modern switch with `->` syntax (see Language.java)
- **Records** (Optional): Can be used for simple data classes

### Best Practices

1. Use meaningful variable names
2. Add comments explaining complex logic
3. Implement proper encapsulation with private variables
4. Use getters/setters for access
5. Override `toString()` for all classes
6. Implement `equals()` when comparing objects
7. Follow Java naming conventions (camelCase, PascalCase)

## Testing Strategy

### Unit Testing

- Test each class individually
- Verify constructors work correctly
- Check getter/setter functionality

### Integration Testing

- Test Person and Organization interactions
- Verify greetings from all types
- Test language switching

### System Testing

- Run Main.java
- Compare with expected output
- Verify all features work together

## Submission Checklist

Before submitting:

- [ ] All required classes are implemented
- [ ] Code compiles without errors
- [ ] Main.java runs without exceptions
- [ ] Output matches SampleOutput-01.pdf
- [ ] All files are in correct package
- [ ] No changes made to protected files
- [ ] All source files are submitted (not just new code)
- [ ] Code follows Java conventions
- [ ] Comments explain complex logic

## Common Pitfalls to Avoid

1. ❌ Modifying protected files
2. ❌ Submitting incomplete files
3. ❌ Incorrect package organization
4. ❌ Missing method implementations
5. ❌ Not calling `super()` in subclass constructors
6. ❌ Forgetting to implement abstract methods
7. ❌ Not testing with the provided sample output

## Resources

- Java 15 Documentation: https://docs.oracle.com/en/java/javase/15/
- Sealed Classes Tutorial: https://docs.oracle.com/en/java/javase/15/language/sealed-classes.html
- Object-Oriented Design Patterns
- SFSU CSC 220 Course Materials

---

## Additional Implementation Details

### Import Statements Required

Add these imports to your Java files:

```java
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
```

### Interface Implementation Template

When implementing the Greeting interface:

```java
public void greet(Language language) {
    String greeting = language.getPhrase("greeting");
    System.out.println(greeting + "! I'm " + getFullName());
}
```

### Collection Usage Patterns

```java
// ArrayList for dynamic lists
List<Player> players = new ArrayList<>();

// HashMap for key-value pairs
Map<String, Person> directory = new HashMap<>();

// For-each loops
for (Player player : players) {
    System.out.println(player.getFullName());
}

// Stream operations
Player pitcher = players.stream()
    .filter(p -> p.getPosition().equals("Pitcher"))
    .findFirst()
    .orElse(null);
```

### Proper Constructor Chaining

```java
// In subclass constructor
public Player(String firstName, String lastName, String personID,
              int age, String email, String phoneNumber,
              int jerseyNumber, String position, Club club) {
    super(firstName, lastName, personID, age, email, phoneNumber);
    this.jerseyNumber = jerseyNumber;
    this.position = position;
    this.club = club;
    // Initialize other Player-specific fields
}
```

### toString() Implementation Pattern

```java
@Override
public String toString() {
    return "ClassName{" +
            "field1='" + field1 + '\'' +
            ", field2=" + field2 +
            ", field3='" + field3 + '\'' +
            '}';
}
```

### Null Safety Pattern

```java
public void displayInfo() {
    System.out.println("Name: " + (firstName != null ? firstName : "Unknown"));
    System.out.println("Club: " + (club != null ? club.getClubName() : "No club"));
}
```

## Troubleshooting Guide

### Common Compilation Errors

**Error**: "class X cannot extend sealed class Y"

- **Solution**: Check if class X is in the `permits` clause of sealed class Y

**Error**: "does not override abstract method Z"

- **Solution**: Implement all abstract methods from parent class

**Error**: "call to super must be first statement in constructor"

- **Solution**: Move `super()` call to be the first line in subclass constructors

**Error**: "constructor X does not take Y parameters"

- **Solution**: Check constructor parameter types and count match the actual definition

### Common Runtime Errors

**NullPointerException when accessing object property**:

```java
// Wrong
System.out.println(player.getClub().getClubName());  // If player.getClub() is null

// Correct
if (player.getClub() != null) {
    System.out.println(player.getClub().getClubName());
}

// Or use Optional
Optional<Club> club = Optional.ofNullable(player.getClub());
club.ifPresent(c -> System.out.println(c.getClubName()));
```

**Cast exception errors**:

```java
// Avoid casting unless necessary
// If you have a list of Person objects but need Player-specific methods:
if (person instanceof Player) {
    Player player = (Player) person;
    player.recordHomeRun();
}
```

### Output Formatting Issues

**Alignment problems in display**:

```java
// Use String.format for consistent spacing
System.out.println(String.format("%-20s %3d", "Name:", jerseyNumber));
System.out.println(String.format("%-20s %.2f", "GPA:", gpa));
```

**Multiple line outputs**:

```java
System.out.println("\n╔════════════════════════════╗");
System.out.println("║  Section Title             ║");
System.out.println("╟────────────────────────────╢");
System.out.println("║  Content Line 1            ║");
System.out.println("║  Content Line 2            ║");
System.out.println("╚════════════════════════════╝\n");
```

## Code Quality Checklist

Before submitting, verify:

- [ ] All classes follow Java naming conventions (PascalCase for classes, camelCase for variables)
- [ ] All instance variables are private
- [ ] All methods have appropriate access modifiers (public, private, protected)
- [ ] All getters/setters follow standard naming (getFoo(), setFoo())
- [ ] All classes override toString() method
- [ ] Proper use of super() in constructors
- [ ] No code duplication (use inheritance where appropriate)
- [ ] Comments explain complex logic or business rules
- [ ] Code is properly indented (4 spaces or 1 tab)
- [ ] No magic numbers (use named constants instead)
- [ ] Error handling for edge cases

## Performance Considerations

### Efficient List Operations

```java
// Good: Direct iteration
for (Player player : players) {
    // process
}

// Avoid: Repeated get() calls with list
for (int i = 0; i < list.size(); i++) {
    list.get(i);  // Don't do this in a loop if avoidable
}

// Better: Use enhanced for loop or iterator
Iterator<Player> iterator = players.iterator();
while (iterator.hasNext()) {
    Player player = iterator.next();
}
```

### Memory Efficient Pattern

```java
// Return copies to prevent external modification
public List<Player> getPlayers() {
    return new ArrayList<>(players);  // Return copy, not reference
}

// Or use immutable collection
public List<Player> getPlayers() {
    return Collections.unmodifiableList(players);
}
```

## Example Test Cases

### Test Case 1: Student Enrollment

```java
University sfsu = new University("SFSU", "SFSU001", "SF", 1899);
Student student = new Student("John", "Doe", "S001", 20,
                              "john@mail.com", "555-0001",
                              "SF123", "CS", sfsu);
sfsu.enrollStudent(student);
assert sfsu.getStudentCount() == 1;
student.greet(Language.ENGLISH);
```

### Test Case 2: Player Statistics

```java
Club club = new Club("Giants", "SFG", "SF");
Player player = new Player("Mike", "Wilson", "P001", 28,
                           "mike@giants.com", "555-0001",
                           25, "Pitcher", club);
club.addPlayer(player);
player.updateStats(300, 15);
assert club.findPlayerByNumber(25) == player;
```

### Test Case 3: Quiz Functionality

```java
Quiz quiz = new Quiz("Q1", "Trivia", student);
QuestionAnswer qa = new QuestionAnswer("Q1", "Question?", "Answer");
qa.addOption("Answer");
qa.addOption("Wrong");
quiz.addQuestion(qa);
quiz.recordAnswer(0, "Answer");
assert quiz.getScore() == 1;
```

## Module Dependencies

```
Greeting.java (Interface)
  ↓ (implemented by)
Person.java (Abstract Sealed Class)
  ├── Student.java
  ├── Player.java
  ├── President.java
  ├── GeneralManager.java
  └── Manager.java

Organization.java (Abstract Sealed Class)
  ├── Club.java (contains Players, Managers)
  ├── University.java (contains Students)
  └── OwnerGroup.java (contains Presidents)

Language.java (Enum/Class)
  ↓ (used by)
Person subclasses

Card.java + Color.java
  ↓ (generated from)
Player.java

ChatSession.java, Quiz.java, QuestionAnswer.java
  ↓ (used by)
Person subclasses

Timer.java, Receipt.java, Directory.java, FrontOffice.java
  ↓ (utility classes)
Main.java

Config.java, SFGiantsCardGenerator.java, Messenger.java, LogFileDriver.java
  ↓ (provided utilities - DO NOT MODIFY)
Used by main program
```

---

**Assignment Due Date**: Check course syllabus
**Format**: Java project in IntelliJ IDEA or similar IDE
**Good Luck! Happy Coding! 🚀**
