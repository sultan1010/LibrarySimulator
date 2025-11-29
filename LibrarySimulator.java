package LibrarySimulator;

import java.util.Scanner;

public class LibrarySimulator {

	static Member[] members = { new Member(101, "Sultan", 0), new Member(102, "Ahmed", 0),
			new Member(103, "Fahad", 0) };

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		boolean exitProgram = false;

		while (!exitProgram) {
			System.out.println("\n=== Welcome to Library Simulator ===");
			System.out.println("1) Select User Account");
			System.out.println("2) Login as Administrator");
			System.out.println("3) Exit");
			System.out.print("Enter choice: ");

			int choice = input.nextInt();

			switch (choice) {
			case 1 -> userLogin();
			case 2 -> adminMenu();
			case 3 -> exitProgram = true;
			default -> System.out.println("Invalid choice! Please enter a number from the menu.");
			}
		}

		System.out.println("Thank you for using Library Simulator. Goodbye!");
	}
	// Handles user login and menu operations
	public static void userLogin() {
		System.out.println("\nSelect an account:");

		for (int i = 0; i < members.length; i++) {
			System.out.printf("%d) ID: %d Name: %s\n", i + 1, members[i].getId(), members[i].getName());
		}

		System.out.println((members.length + 1) + ") Back to Main Menu");
		System.out.print("Enter choice: ");
		int accountChoice = input.nextInt();

		if (accountChoice < 1 || accountChoice > members.length + 1) {
			System.out.println("Invalid choice! Returning to main menu.");
			return;
		}

		if (accountChoice == members.length + 1)
			return;

		int currentIndex = accountChoice - 1;

		Member current = members[currentIndex];

		System.out.println("Welcome, " + current.getName() + "! Your ID is " + current.getId());

		boolean exitUserMenu = false;

		while (!exitUserMenu) {

			System.out.println("\n--- User Operations Menu ---");
			System.out.println("1) View Borrowed Books Count");
			System.out.println("2) Borrow Book");
			System.out.println("3) Return Book");
			System.out.println("4) View Session Summary");
			System.out.println("5) Reset Session Statistics");
			System.out.println("6) Exit to Main Menu");
			System.out.print("Enter choice: ");

			int userChoice = input.nextInt();

			switch (userChoice) {
			case 1 -> current.viewBorrowedCount();
			case 2 -> {
				if (current.borrowOne()) {
					System.out.println("Borrow operation successful.");
				} else {
					System.out.println("Borrow limit reached! You cannot borrow more than 5 books at once.");
				}
			}
			case 3 -> {
				if (current.returnOne()) {
					System.out.println("Return operation successful.");
				} else {
					System.out.println("No books to return. You haven't borrowed any books yet.");
				}
			}
			case 4 -> {
				current.displayStatistics();
			}
			case 5 -> {
				current.reset();
			}
			case 6 -> exitUserMenu = true;

			default -> System.out.println("Invalid choice! Please enter a number from the menu.");
			}
		}
	}
	// Handles admin login and menu operations
	public static void adminMenu() {

		final String adminUsername = "admin"; // ADMIN USERNAME
		final String adminPassword = "admin123"; // ADMIN PASSWORD

		System.out.print("Enter admin username: ");

		String username = input.next();

		System.out.print("Enter admin password: ");

		String password = input.next();

		if (!username.equalsIgnoreCase(adminUsername) || !password.equals(adminPassword)) {
			System.out.println("Invalid admin credentials. Access denied.");
			return;
		}

		boolean exitAdmin = false;

		while (!exitAdmin) {
			System.out.println("\nLogged in as Administrator");
			System.out.println("--- Administrator Menu ---");
			System.out.println("1) View Total Revenue");
			System.out.println("2) View Total Operation Statistics");
			System.out.println("3) Exit to Main Menu");
			System.out.print("Enter choice: ");

			int adminChoice = input.nextInt();

			switch (adminChoice) {
			case 1 -> {
				System.out.printf("Total revenue collected: %.2f SAR\n", Member.TotalRevenue);
			}
			case 2 -> {
				System.out.println("Total times borrowed: " + Member.TotalBorrows);
				System.out.println("Total times returned: " + Member.TotalReturns);
				System.out.println("Total times viewed borrowed count: " + Member.TotalViewBorrowed);

				if (Member.TotalBorrows > Member.TotalReturns) {
					System.out.println("Most frequent operation: BORROW");
				} else if (Member.TotalReturns > Member.TotalBorrows) {
					System.out.println("Most frequent operation: RETURN");
				} else {
					System.out.println("Most frequent operation: TIED");
				}

			}
			case 3 -> exitAdmin = true;

			default -> System.out.println("Invalid choice! Please enter a number from the menu.");
			}
		}

	}
}
