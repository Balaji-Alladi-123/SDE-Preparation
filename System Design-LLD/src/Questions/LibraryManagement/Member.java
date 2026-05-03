package Questions.LibraryManagement;

import java.util.List;
import java.util.UUID;

public class Member {
    private final UUID memberId;
    private final String name;
    private final String phoneNumber;
    private final String address;
    private List<BookLoans> bookLoans;

    Member(String name, String phoneNumber, String address) {
        this.memberId = UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public synchronized void addLoan(BookLoans loan) {
        bookLoans.add(loan);
    }

    public String getAddress() {
        return address;
    }

    public List<BookLoans> getBookLoans() {
        return bookLoans;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public synchronized void removeLoan(BookLoans loan) {
        bookLoans.remove(loan);
    }
}
