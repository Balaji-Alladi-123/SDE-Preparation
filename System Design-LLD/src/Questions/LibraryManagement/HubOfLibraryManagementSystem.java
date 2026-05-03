package Questions.LibraryManagement;

import java.util.Date;
import java.util.List;

 class LibrarySystem {
    public static void run() {
        LibraryManagementSystem libraryManagementSystem = LibraryManagementSystem.getInstance();
        //adding books
        libraryManagementSystem.addBook(new Book("Java Programming", "James", "ISBN1", new Date()));
        libraryManagementSystem.addBook(new Book("Python Programming", "Narasimha", "ISBN2", new Date()));
        libraryManagementSystem.addBook(new Book("Artificial Intelligence", "Jon Doe", "ISBN3", new Date()));
        libraryManagementSystem.addBook(new Book("Machine Learning", "David", "ISBN4", new Date()));

        // addding members
        Member member1 = libraryManagementSystem.subscribeMember("Balaji Satya", "9347793857", "Velpur");
        Member member2 = libraryManagementSystem.subscribeMember("Ram Charan", "32423434355", "Hyderabad");
        Member member3 = libraryManagementSystem.subscribeMember("Imaneaul", "893554234223", "Bhemadolu");

        // adding loans
        BookLoans loan1 = libraryManagementSystem.borrowLoan(member1.getMemberId(),"Python Programming");
        BookLoans loan2 = libraryManagementSystem.borrowLoan(member3.getMemberId(),"Python Programming");

        libraryManagementSystem.returingBook(loan1.getLoanId());
        List<Book> searchResults = libraryManagementSystem.searchByAuthor("Author 1");
        System.out.println("Search Results:");
        for (Book book : searchResults) {
            System.out.println(book.getName() + " by " + book.getAuthor());

        }
    }
}

 public class HubOfLibraryManagementSystem {
    public static void main(String[] args) {
        LibrarySystem.run();
    }
}
