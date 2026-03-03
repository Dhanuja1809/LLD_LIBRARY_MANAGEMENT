package com.kce.bean;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private int memberId;
    private String name;

    private List<BorrowRecord> borrowedBooks = new ArrayList<>();

    private int totalBooksBorrowed = 0;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<BorrowRecord> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getTotalBooksBorrowed() {
        return totalBooksBorrowed;
    }

    public void incrementTotalBorrowed() {
        totalBooksBorrowed++;
    }
}
