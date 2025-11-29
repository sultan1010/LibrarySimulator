package LibrarySimulator;

public class Member {
//	 private var
	private int id;
	private String name;
	private int borrowedCount;
	private int numViewBorrowed;
	private int numBorrows;
	private int numReturns;
	private double sessionFees;

//	public var

	public static double TotalRevenue;
	public static int TotalViewBorrowed;
	public static int TotalBorrows;
	public static int TotalReturns;

	
	// Constructor
	public Member(int id, String name, int borrowedCount) {
		this.id = id;
		this.name = name;
		this.borrowedCount = borrowedCount;
	}
	// Checks if member can borrow (max 5 books)
	private boolean canBorrow() {
		return borrowedCount < 5;
	}
	// Checks if member can return a book
	private boolean canReturn() {
		return borrowedCount > 0;
	}
	// Displays current borrowed books for this member
	public void viewBorrowedCount() {
		System.out.println(name + " currently has " + borrowedCount + " books borrowed.");
		numViewBorrowed++;
		TotalViewBorrowed++;
	}
	
	
	// Borrow one book, update counters and fees
	public boolean borrowOne() {
		if (!canBorrow())
			return false;

		borrowedCount++;
		numBorrows++;
		TotalBorrows++;
		sessionFees += 0.5;
		TotalRevenue += 0.5;

		return true;

	}
	
	
	// Return one book, update counters
	public boolean returnOne() {
		if (!canReturn())
			return false;

		borrowedCount--;
		numReturns++;
		TotalReturns++;

		return true;
	}
	// Display detailed session statistics for this member
	public void displayStatistics() {
		System.out.println("\n--- Session Statistics for " + name + " (ID: " + id + ") ---");
		System.out.println("Books currently borrowed: " + borrowedCount);
		System.out.println("Times viewed borrowed count: " + numViewBorrowed);
		System.out.println("Borrow operations: " + numBorrows);
		System.out.println("Return operations: " + numReturns);
		System.out.printf("Session fees: %.2f SAR\n", sessionFees);

	}
	
	
	// Reset session statistics to zero
	public void reset() {
	    numViewBorrowed = 0;
	    numBorrows = 0;
	    numReturns = 0;
	    sessionFees = 0.0;
	    System.out.println("Session statistics have been reset.");
	}
	
	// Get member name
	public String getName() {
		return name;
	}
	// Get member ID
	public int getId() {
		return id;
	}

}
