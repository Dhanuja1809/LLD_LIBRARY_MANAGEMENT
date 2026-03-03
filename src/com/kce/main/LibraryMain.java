package com.kce.main;

import java.util.Scanner;

import com.kce.service.LibraryService;

public class LibraryMain {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryService service = new LibraryService();

        while (true) {



            System.out.println("\n1.Add Book");

            System.out.println("2.Register Member");

            System.out.println("3.Borrow Book");

            System.out.println("4.Return Book");

            System.out.println("5.Search Book");

            System.out.println("6.Member Report");

            System.out.println("7.Book Report");

            System.out.println("8.Top Borrowers");

            System.out.println("9.Exit");



            int choice = sc.nextInt();



            switch (choice) {



            case 1:

                System.out.println("Enter Book ID:");

                int bookId = sc.nextInt();



                System.out.println("Enter Title:");

                String title = sc.next();



                System.out.println("Enter Author:");

                String author = sc.next();



                System.out.println("Enter Total Copies:");

                int copies = sc.nextInt();



                service.addBook(bookId, title, author, copies);

                break;



            case 2:

                System.out.println("Enter Member ID:");

                int memberId = sc.nextInt();



                System.out.println("Enter Member Name:");

                String name = sc.next();



                service.registerMember(memberId, name);

                break;



            case 3:

                System.out.println("Enter Member ID:");

                int mId = sc.nextInt();



                System.out.println("Enter Book ID:");

                int bId = sc.nextInt();



                service.borrowBook(mId, bId);

                break;



            case 4:

                System.out.println("Enter Member ID:");

                int rmId = sc.nextInt();



                System.out.println("Enter Book ID:");

                int rbId = sc.nextInt();



                service.returnBook(rmId, rbId);

                break;



            case 5:

                System.out.println("Enter Title or Author to Search:");

                String keyword = sc.next();



                service.searchBook(keyword);

                break;



            case 6:

                System.out.println("Enter Member ID:");

                int reportMemberId = sc.nextInt();



                service.memberReport(reportMemberId);

                break;



            case 7:

                System.out.println("Enter Book ID:");

                int reportBookId = sc.nextInt();



                service.bookReport(reportBookId);

                break;



            case 8:

                service.topBorrowers();

                break;



            case 9:

                System.out.println("Exiting...");

                System.exit(0);



            default:

                System.out.println("Invalid Choice!");

        }

        }

    }

	}