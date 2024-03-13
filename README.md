# Library Management System

This Java program implements a simple library management system allowing users to manage books and users in a library. The system provides functionalities such as adding books and users, displaying all books in the library, borrowing and returning books, searching for books by various filters (title, author, genre), and checking books borrowed by a specific user. It permanently stores data in text files and transfers that data into arrays when any manipulation is needed.

## Features

- **Add Book**: Allows adding a new book to the library.
- **Add User**: Allows adding a new user to the library.
- **Display Books**: Displays all books currently available in the library.
- **Borrow Book**: Enables users to borrow a book from the library.
- **Return Book**: Allows users to return a borrowed book to the library.
- **Search Book**: Provides options to search for books by title, author, genre, or to list currently borrowed books.
- **Search by User ID**: Allows users to check which books are borrowed by a specific user.

## Usage

1. Compile the Java files using a Java compiler.
2. Run the `LibraryManagementSystem` class.
3. Follow the on-screen prompts to perform desired operations such as adding books, adding users, borrowing books, returning books, or searching for books.

## Classes

- `book`: Represents a book with attributes such as book ID, title, author, genre, and availability.
- `user`: Represents a user with attributes such as user ID, name, contact information, and an array to store borrowed books.
- `Library`: Contains methods to handle operations related to loading, saving, adding, borrowing, and returning books, as well as managing users.
- `LibraryManagementSystem`: Main class that serves as an entry point to the library management system.

## Note

- The program utilizes text files (`librarybooks.txt` and `libraryusers.txt`) to store library data permanently. Ensure these files are present in the same directory as the Java files.
