package com.kce.bean;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private int availableCopies;

    private List<Integer> borrowedByMembers = new ArrayList<>();

    public Book(int bookId, String title, String author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availableCopies = copies;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseCopy() {
        availableCopies--;
    }

    public void increaseCopy() {
        availableCopies++;
    }

    public List<Integer> getBorrowedByMembers() {
        return borrowedByMembers;
    }
}
