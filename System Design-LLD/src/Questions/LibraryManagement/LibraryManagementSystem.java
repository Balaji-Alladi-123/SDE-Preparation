package Questions.LibraryManagement;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LibraryManagementSystem {
    private static LibraryManagementSystem instance;
    private final Catalogue catalogue;
    private final Map<UUID, Member> members;
    private final Map<UUID, BookLoans> loans;

    private LibraryManagementSystem() {
        catalogue = new Catalogue();
        members = new ConcurrentHashMap<>();
        loans = new ConcurrentHashMap<>();
    }

    public static synchronized LibraryManagementSystem getInstance() {
        if (instance == null) {
            instance = new LibraryManagementSystem();
        }
        return instance;
    }

    public void addBook(Book book) {
        BookCopy bookCopy = new BookCopy(book);
        catalogue.addBook(bookCopy);
    }

    public Member subscribeMember(String name, String phoneNumber, String address) {
        Member member = new Member(name, phoneNumber, address);
        members.put(member.getMemberId(), member);
        return member;
    }

    public synchronized BookLoans borrowLoan(UUID memberId, String bookName) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new RuntimeException("Not allowed to allocate because no member available");
        }
        List<BookCopy> bookCopyList = catalogue.getBookCopiesByTitle(bookName);
        if (bookCopyList == null) {
            throw new RuntimeException("No books available");
        }
        for (BookCopy bookCopy : bookCopyList) {
            if (bookCopy.isAvailable()) {
                bookCopy.markIssued();
                BookLoans loan = new BookLoans(member, bookCopy);
                member.addLoan(loan);
                loans.put(loan.getLoanId(), loan);
                System.out.println("Book borrowed: " + bookCopy.getBook().name + " by " + member.getName());
                return loan;
            }
        }
        return null;
    }

    public synchronized void returingBook(UUID loanId) {
       BookLoans bookLoans = loans.get(loanId);
       bookLoans.closeLoan();
    }

    public List<Book> searchByTitle(String title) {
        return catalogue.getBooksByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return catalogue.getBooksByAuthor(author);
    }
}
