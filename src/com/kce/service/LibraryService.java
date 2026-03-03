package com.kce.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kce.bean.Book;
import com.kce.bean.BorrowRecord;
import com.kce.bean.Member;

public class LibraryService {

    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    // Add Book
    public void addBook(int id, String title, String author, int copies) {

        if (books.containsKey(id)) {
            System.out.println("Book id already exists");
            return;
        }

        books.put(id, new Book(id, title, author, copies));
        System.out.println("Book added successfully");
    }

    // Register Member
    public void registerMember(int memberId, String name) {

        if (members.containsKey(memberId)) {
            System.out.println("Member id already exists");
            return;
        }

        members.put(memberId, new Member(memberId, name));
        System.out.println("Member registered successfully");
    }

    // Borrow Book
    public void borrowBook(int memberId, int bookId) {

        if (!members.containsKey(memberId) || !books.containsKey(bookId)) {
            System.out.println("Book ID or Member ID not found");
            return;
        }

        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member.getBorrowedBooks().size() >= 3) {
            System.out.println("A member can't borrow more than 3 books");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("No copies available");
            return;
        }

        for (BorrowRecord br : member.getBorrowedBooks()) {
            if (br.getBookId() == bookId) {
                System.out.println("You already borrowed this book");
                return;
            }
        }

        BorrowRecord record = new BorrowRecord(bookId);

        member.getBorrowedBooks().add(record);
        member.incrementTotalBorrowed();

        book.decreaseCopy();
        book.getBorrowedByMembers().add(memberId);

        System.out.println("Book borrowed successfully");
    }

    // Return Book
    public void returnBook(int memberId, int bookId) {

        if (!members.containsKey(memberId) || !books.containsKey(bookId)) {
            System.out.println("Book ID or Member ID not found");
            return;
        }

        Member member = members.get(memberId);
        Book book = books.get(bookId);

        BorrowRecord found = null;

        for (BorrowRecord br : member.getBorrowedBooks()) {
            if (br.getBookId() == bookId) {
                found = br;
                break;
            }
        }

        if (found == null) {
            System.out.println("Book not borrowed");
            return;
        }

        long overdueDays =
                ChronoUnit.DAYS.between(found.getDueDate(), LocalDate.now());

        if (overdueDays > 0) {
            System.out.println("Fine: Rs." + (overdueDays * 2));
        }

        member.getBorrowedBooks().remove(found);

        book.increaseCopy();
        book.getBorrowedByMembers().remove(Integer.valueOf(memberId));

        System.out.println("Book returned successfully!");
    }

    // Search Book
    public void searchBook(String keyword) {

        keyword = keyword.toLowerCase();

        for (Book book : books.values()) {

            if (book.getTitle().toLowerCase().contains(keyword)
                    || book.getAuthor().toLowerCase().contains(keyword)) {

                System.out.println(book.getBookId() + " - " + book.getTitle());
            }
        }
    }

    // Member Report
    public void memberReport(int memberId) {

        Member member = members.get(memberId);

        if (member == null) {
            System.out.println("Member not found");
            return;
        }

        for (BorrowRecord br : member.getBorrowedBooks()) {

            System.out.println("Book ID: " + br.getBookId()
                    + " | Borrow Date: " + br.getBorrowDate()
                    + " | Due Date: " + br.getDueDate());
        }
    }

    // Book Report
    public void bookReport(int bookId) {

        Book book = books.get(bookId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Available Copies: " + book.getAvailableCopies());
        System.out.println("Borrowed by Members: " + book.getBorrowedByMembers());
    }

    // Top Borrowers
    public void topBorrowers() {

        List<Member> list = new ArrayList<>(members.values());

        list.sort((a, b) ->
                b.getTotalBooksBorrowed() - a.getTotalBooksBorrowed());

        for (Member m : list) {
            System.out.println(m.getName() + " - " + m.getTotalBooksBorrowed());
        }
    }
}
