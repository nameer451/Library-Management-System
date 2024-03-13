import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Library {

    //Text files to store permanently library data
    File libraryBooksFile = new File("librarybooks.txt");
    File libraryUsersFile = new File("libraryusers.txt");

    //arrays being used to navigate through the library data within the program
    ArrayList <book> libraryBooksArray = new ArrayList<>();
    ArrayList <user> libraryUsersArray = new ArrayList<>();

    Scanner sc = new Scanner(System.in);


    //function which will load the data from  the libraryBooks text file into the libraryBooks array
    public void loadBooks() {

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(libraryBooksFile))) {
            while ((line = br.readLine()) != null) {
                book newBook = new book();

                //each book takes up 5 lines in the text file

                newBook.bookid = line;
                newBook.title = br.readLine();
                newBook.genre = br.readLine();
                newBook.author = br.readLine();
                newBook.availability = br.readLine();

                libraryBooksArray.add(newBook); // add the book into the array
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading library books data.");
        }
    }


    //function which will load the data from  the libraryUsers text file into the libraryUsers array
    public void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(libraryUsersFile))) {
           
            String line;
    
            while ((line = br.readLine()) != null) {
                user newUser = new user();
                newUser.userid = line;
                newUser.name = br.readLine();
                newUser.contact = br.readLine();
                String borrowedBooksLine = br.readLine(); // Read the line containing borrowed books
                String[] borrowedBooksArray = borrowedBooksLine.split(",");
                // Populate the borrowedBooks array accordingly
                for (int i = 0; i < 5; i++) {
                    if (i < borrowedBooksArray.length) {
                        newUser.borrowedBooks[i] = borrowedBooksArray[i];
                    } else {
                        newUser.borrowedBooks[i] = "";
                    }
                }
                libraryUsersArray.add(newUser);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading library books data.");
        }
    }
    
    
    

    //function which will save the data from  the libraryBooks array into the libraryBooks text file
    public void saveBooks() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(libraryBooksFile))) {
            for (book b : libraryBooksArray) {
                // Check if any essential data is null before writing
                if (b.bookid != null && b.title != null && b.genre != null && b.author != null && b.availability != null) {
                    bw.write(b.bookid);
                    bw.newLine();
                    bw.write(b.title);
                    bw.newLine();
                    bw.write(b.genre);
                    bw.newLine();
                    bw.write(b.author);
                    bw.newLine();
                    bw.write(b.availability);
                    bw.newLine(); // Write a newline after each book entry
                }
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing library books data.");
        }
    }
    
    


    //function which will save the data from  the libraryUsers array into the libraryUsers text file
    public void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(libraryUsersFile))) {
            for (user u : libraryUsersArray) {
                bw.write(u.userid);
                bw.newLine();
                bw.write(u.name);
                bw.newLine();
                bw.write(u.contact);
                bw.newLine();
                bw.write(u.borrowedBooks[0]+","+u.borrowedBooks[1]+","+u.borrowedBooks[2]+","+u.borrowedBooks[3]+","+u.borrowedBooks[4]);
                bw.newLine();

            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing library books data.");
            e.printStackTrace();
        }
    }




    public void addBook()
    {

        book newBook = new book();

        System.out.println("Enter the book's name: ");
        newBook.title = sc.nextLine();

        System.out.println("Enter an ID for identifying the book with: ");
        newBook.bookid = sc.nextLine();

        System.out.println("Enter the book's genre: ");
        newBook.genre = sc.nextLine();

        System.out.println("Enter the book's author: ");
        newBook.author = sc.nextLine();

        newBook.availability = "available";

        libraryBooksArray.add(newBook);

        System.out.println("Book Added Successfully!");

        saveBooks();

    }


    public void addUser()
    {

        user newUser = new user();
        
        System.out.println("Enter the user's name: ");
        newUser.name = sc.nextLine();

        System.out.println("Enter an ID for identifying the user with: ");
        newUser.userid = sc.nextLine();

        System.out.println("Enter the user's contact info: ");
        newUser.contact = sc.nextLine();

        libraryUsersArray.add(newUser);

        System.out.println("User Added Succesfully!");

        saveUsers();

    }

    public void borrowBook() {

        System.out.println("Enter the userID of the person borrowing the book: ");
        String userid = sc.nextLine();
    
        System.out.println("Enter the ID of the book that the user would like: ");
        String bookid = sc.nextLine();

        boolean bookFound = false, userFound = false, userLimit = true;

        for (book b : libraryBooksArray) {
            if (b.bookid.equalsIgnoreCase(bookid)) {
                bookFound = true;

                for (user u : libraryUsersArray) {
                    if (u.userid.equalsIgnoreCase(userid)) {
                        userFound =  true;

                        if (b.availability.equalsIgnoreCase("available")) {
                            for (int i = 0; i < 5; i++) {
                                if (u.borrowedBooks[i].equalsIgnoreCase("")) {
                                    u.borrowedBooks[i] = b.title;
                                    userLimit = false;
                                    b.availability = "unavailable";
                                    System.out.println("Book Borrowed Successfully!");
                                    saveBooks();
                                    saveUsers();
                                    return;
                                }
                            }
                            
                        }else
                        {
                            System.out.println("This book is not available for borrowing.");
                            return;
                        }
                    }
                }
                
            }
        }

        if (bookFound == false) {
            System.out.println("This book does not exist.");
            return;
        }

        if (userFound == false) {
            System.out.println("This user does not exist");
            return;
        }

        if (userLimit == true) {
            System.out.println("The user has already borrowed the max number of books.");
            return;
            
        }

    }


    public void returnBook() {

        System.out.println("Enter the userID of the person borrowing the book: ");
        String userid = sc.nextLine();
    
        System.out.println("Enter the ID of the book that the user would like: ");
        String bookid = sc.nextLine();

        boolean bookFound = false, userFound = false, userOwnedBook = false;

        for (book b : libraryBooksArray) {
            if (b.bookid.equalsIgnoreCase(bookid)) {
                bookFound = true;

                for (user u : libraryUsersArray) {
                    if (u.userid.equalsIgnoreCase(userid)) {
                        userFound =  true;

                        if (b.availability.equalsIgnoreCase("unavailable")) {
                            for (int i = 0; i < 5; i++) {
                                if (u.borrowedBooks[i].equalsIgnoreCase(b.title)) {
                                    userOwnedBook = true;
                                    u.borrowedBooks[i] = "";
                                    b.availability = "available";
                                    System.out.println("Book Returned Successfully!");
                                    saveBooks();
                                    saveUsers();
                                    return;
                                }
                            }
                            
                        }else
                        {
                            System.out.println("This book was not borrowed.");
                            return;
                        }
                    }
                }
                
            }
        }

        if (bookFound == false) {
            System.out.println("This book does not exist.");
            return;
        }

        if (userFound == false) {
            System.out.println("This user does not exist");
            return;
        }

        if (userOwnedBook == false) {
            System.out.println("This user is not in posession of this book.");
        }

    }

    
    public void displayBooks()
    {
        for (book b : libraryBooksArray) {
            System.out.println("\nBook ID: "+b.bookid+"\nTitle: "+b.title+"\nAuthor: "+b.author+"\nGenre: "+b.genre+"\nAvailability: "+b.availability +"\n");
        }
    }


    public void searchBook(char option){

        int count = 0;

        switch (option) {

            case 't' :
                System.out.println("Enter the name of the book you are looking for: ");
                String targetBook = sc.nextLine();

                for (book b : libraryBooksArray) {
                    if (b.title.equalsIgnoreCase(targetBook)) {
                        System.out.println("Book has been found! BookID: "+ b.bookid + " Genre: "+b.genre);
                        return;
                    }
                    System.out.println("This book does not exist in this library.");

                }
                
                break;

            case 'a':

                System.out.println("Enter the name of the author who's books you are looking for: ");
                String targetAuthor = sc.nextLine();


                for (book b : libraryBooksArray) {
                    if (b.author.equalsIgnoreCase(targetAuthor)) {
                        System.out.println(b.title);
                        count++;
                    }
                }

                if (count == 0) {
                    System.out.println("No books from the author in question exist in this library.");
                }
                break;

            case 'g':
                System.out.println("Enter the genre of books you are looking for: ");
                String targetGenre = sc.nextLine();

                for (book b : libraryBooksArray) {
                    if (b.genre.equalsIgnoreCase(targetGenre)) {
                        System.out.println(b.title);
                        count++;
                    }
                }

                if (count == 0) {
                    System.out.println("No books from that genre exist in this library.");
                }
                break;

            case 'l':
                System.out.println("Here is a list of all the books that are currently borrowed: ");

                for (book b : libraryBooksArray) {
                    if (b.availability.equalsIgnoreCase("unavailable")) {
                        for (user u : libraryUsersArray) {
                            for (int i = 0; i < 5; i++) {
                                if (u.borrowedBooks[i].equalsIgnoreCase(b.title)) {
                                    System.out.println("Book: "+b.title+"  Borrowed by: "+u.name+ "  UserID: "+u.userid);
                                }
                            }
                        }
                        
                    }
                }
                break;
                
        
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    } 

    public void seachByUserID(String userid){
        boolean foundUser = false;
        for (user u : libraryUsersArray) {
            if (u.userid.equalsIgnoreCase(userid)) {
                foundUser = true;
                for (int i = 0; i < 5; i++) {
                    if (u.borrowedBooks[i] != "") {
                        System.out.println(u.borrowedBooks[i]);
                    }
                    
                }
                
            }
        }

        if (foundUser == false) {
            System.out.println("This user does not exist.");
            
        }

    }
    

    public static void main(String[] args) {

    }
}