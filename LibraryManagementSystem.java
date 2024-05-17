import java.util.ArrayList;
import java.util.Scanner;

//Create Book class.
class Book {
    String Title;
    String Author;
    String ISBN;
    boolean IsAvailable;

    //Create constructors. 

    public Book (String Title,String Author,String ISBN){
    this.Title = Title;
    this.Author =Author;
    this.ISBN = ISBN;
    this.IsAvailable = true;
    }

    //Getters .
    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean IsAvailable() {
        return IsAvailable;
    }

    //Setters.
    public void setAvailable(boolean available){
        IsAvailable = available;
    }

    //Display Book details.
    public void displayBookDetails() {
        System.out.println("Title: "+Title);
        System.out.println("Author: "+Author);
        System.out.println("ISBN: "+ISBN);
        System.out.println("Availability: " + (IsAvailable ? "Available" : "Not Available"));

    }

}

//Create Library class.
class Library {
    private ArrayList<Book> bookList =new ArrayList<>();

    //Method to add new book to the library.

    public void addBook(Book book) {
        bookList.add(book);
        System.out.println("Book added to the library.");
    }

    //Display the available book list.

    public void displayAvailableBookList() {
        System.out.println("List of aAvailable Books.");
        for (Book book: bookList) {
            if (book.IsAvailable()) {
                book.displayBookDetails();
                System.out.println("........................");
            }
        }

    }

    //Borrow a Book.
    public void borrowBook(String ISBN) {
        for (Book book: bookList) {
            if (book.getISBN().equals(ISBN) && book.IsAvailable()) {
                book.setAvailable(false);
                System.out.println("Book borrowed successfully.");
                return;
            }
        }
        System.out.println("Book not available at library.");


    }

    //Return a book.
    public void returnBook(String ISBN){
        for (Book book:bookList) {
            if (book.getISBN().equals(ISBN) && !book.IsAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Invalid ISBN or the book is already available in the library.");

    }

    }

//Create user class.
class User {
    private static int userCount= 0;
    private int userId;
    private ArrayList<String> borrowedBooks = new ArrayList<>();

    //Constructor
    public User() {
        userCount++;
        this.userId = userCount;

    }

    //Getter Method
    public int getuserId() {
        return userId;
    }

    //Method to borrow a book.
    public void borrowBook(String ISBN){
        borrowedBooks.add(ISBN);
    }

    //Method to return book.
    public void returnBook(String ISBN){
        borrowedBooks.remove(ISBN);
    }

    //Display user Details.
    public void displayUserDetails(){
        System.out.println("UserId: "+userId);
        System.out.println("Borrowed books: "+borrowedBooks);

    }
}

//Fiction book inherit from book.
class FictionBook extends Book {
    private String genre;

    //Constructor
    public FictionBook(String Title,String Author,String ISBN,String genre) {
        super(Title, Author, ISBN);
        this.genre = genre;
    }

    //Display fiction book details.
    public void displayBook(){
        super.displayBookDetails();
        System.out.println("Genre: "+genre);
    }

}

//NonFIction book inherits from book
class NonFictionBook extends Book {
    private String topic;

    //Constructor
    public NonFictionBook(String Title,String Author,String ISBN,String topic) {
        super(Title, Author, ISBN);
        this.topic = topic;
    }

    //Display fiction book details.
    public void displayBook(){
        super.displayBookDetails();
        System.out.println("Topic: "+topic);
    }

}

//Create main class -LibraryManagementSystem
public class LibraryManagementSystem {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

       //Adding sample books to the library.
       library.addBook(new FictionBook("The lord of the Rings Trilogy", "J.R.R.Tolkien","12345","Fantasty")); 
       library.addBook(new NonFictionBook( "Carmen Maria Machado", "In the Dream House","12346","Memoir"));
       library.addBook(new FictionBook("Frankenstein", "Mary Shelly", "123457", "Sceince fiction"));

       //Creating a user.
       User user =new User();

       //Create main menu
       int choice;

       do{
        System.out.println("\n .....Library Management System.....");
        System.out.println("1.Display Available Books.");
        System.out.println("2.Borrow a Book.");
        System.out.println("3.Return a Book.");
        System.out.println("4.Dispaly user details.");
        System.out.println("5.Exit");
        System.out.println("Enter your choice: ");
        choice =sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
            library.displayAvailableBookList();
            break;

            case 2:
            System.out.println("Enter the ISBN of the book you choosen: ");
            String borrowISBN =sc.nextLine();
            user.borrowBook(borrowISBN);
            break;

            case 3:
            System.out.println("Enter the ISBN of the book you want to return: ");
            String returnISBN =sc.nextLine();
            user.returnBook(returnISBN);
            library.returnBook(returnISBN);
            break;

            case 4:
            user.displayUserDetails();
            break;

            case 5:
            System.out.println("Exiting the Library Management System");
            break;

            default:
            System.out.println("Invalid choice.Please enter valid choice");
        }
       }
       while (choice !=5);
       sc.close();
    }
}