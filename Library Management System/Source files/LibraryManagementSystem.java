import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //creating an object of the library
        Library library = new Library();

        //loading the books and user's text files into the arrays
        library.loadBooks();
        library.loadUsers();

        System.out.println("Welcome to Library Management System");
        
        int option = 0;

        //running the program whilst the user chooses not to terminate it
        while (option != 8) {
            System.out.println("Press '1' to add a book, \n'2' to add a user, \n'3' to show all books in the library, \n'4' to borrow a book, \n'5' to return a book, \n'6' to search for a book using different filters \n'7' to search for books borrowed by a specific user\n'8' to exit the program.=>");

            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    library.addUser();
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    library.borrowBook();
                    break;
                case 5:
                    library.returnBook();
                    break;
                case 6:
                    sc.nextLine(); // Consume newline character
                    System.out.println("Enter 'T' to search by title, 'A' to search by author or G to search by genre, Enter 'L' for a list of currently borrowed book: ");
                    char filter = sc.nextLine().charAt(0);
                    filter = Character.toLowerCase(filter);
                    library.searchBook(filter);
                    break;
                case 7:
                    sc.nextLine();
                    System.out.println("Enter the ID of the user who's borrowed books you want to check: ");
                    String userid = sc.nextLine();
                    library.seachByUserID(userid);
                    break;
                default:
                    System.out.println("Incorrect input. Please try again");
                    break;
            }
        }


    }
    
}
