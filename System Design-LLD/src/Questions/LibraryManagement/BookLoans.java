package Questions.LibraryManagement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class BookLoans {
    private final UUID loanId;
    private Member member;
    private BookCopy bookCopy;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private boolean active = true;
    private static final int MAX_BORROW_DAYS = 7;

    BookLoans(Member member, BookCopy bookCopy) {
        this.loanId = UUID.randomUUID();
        this.member = member;
        this.bookCopy = bookCopy;
        this.loanDate = LocalDateTime.now();
        this.returnDate = loanDate.plusDays(MAX_BORROW_DAYS);
    }

    public Member getMember() {
        return member;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public void closeLoan() {
        bookCopy.markReturned();
        member.removeLoan(this);
        active = false;
    }

}
