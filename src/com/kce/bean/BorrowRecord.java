package com.kce.bean;

import java.time.LocalDate;

public class BorrowRecord {

    private int bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowRecord(int bookId) {
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(7);
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
