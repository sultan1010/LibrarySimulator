package LibrarySystem;


//Sultan bin Khalid bin Abdulwahid
// ID number: 446102116



import java.util.Scanner;
import java.io.Console;
public class Library {
	
	static Scanner input = new Scanner(System.in);
	
	
	// User data arrays
	static int[] ids = {101, 102, 103};
    static String[] names = {"Ahmed", "Sultan", "Fahad"};
    static int[] borrowed = {0, 0, 0};
    
 // Totals for admin view
    static int totalBorrowed = 0;
    static int totalReturned = 0;
    static double totalFee = 0.0;
    
	public static void main(String[] args) {
		boolean exitProgram = false;
		// Main program loop
        while (!exitProgram) {
            System.out.println("\n=== Welcome to Library Simulator ===");
            System.out.println("1) Select User Account");
            System.out.println("2) Login as Administrator");
            System.out.println("3) Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
         // Navigate based on user choice
            switch (choice) {
                case 1 -> userLogin();
                case 2 -> adminMenu();
                case 3 -> exitProgram = true;
                default -> System.out.println("Invalid choice! Please enter a number from the menu.");
            }
        }

        System.out.println("Thank you for using Library Simulator. Goodbye!");
		
		

	}
	
	public static void userLogin() {
		// Show list of users
		System.out.println("\nSelect an account:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d) ID: %d Name: %s\n", i + 1, ids[i], names[i]);
        }
        System.out.println((names.length + 1) + ") Back to Main Menu");
        System.out.print("Enter choice: ");
        int accountChoice = input.nextInt();

        if (accountChoice < 1 || accountChoice > names.length + 1) {
            System.out.println("Invalid choice! Returning to main menu.");
            return;
        }

        if (accountChoice == names.length + 1) return;

        int currentIndex = accountChoice - 1;
        System.out.println("Logged in as: " + names[currentIndex] + " (ID: " + ids[currentIndex] + ")");

        int sessionBorrowed = 0;
        int sessionReturned = 0;
        double sessionFee = 0.0;

        boolean exitUserMenu = false;
        while (!exitUserMenu) {
        	// User operations menu
            System.out.println("\n--- User Operations Menu ---");
            System.out.println("1) View Borrowed Books Count");
            System.out.println("2) Borrow Book");
            System.out.println("3) Return Book");
            System.out.println("4) View Session Summary");
            System.out.println("5) Exit to Main Menu");
            System.out.print("Enter choice: ");
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1 -> System.out.printf("You currently have %d books borrowed.\n", borrowed[currentIndex]);
                case 2 -> {
                	// Borrow book logic
                    if (borrowed[currentIndex] >= 5) {
                        System.out.println("You can't borrow more than 5 books at once.");
                    } else {
                        borrowed[currentIndex]++;
                        sessionBorrowed++;
                        sessionFee += 0.5;
                        totalBorrowed++;
                        totalFee += 0.5;
                        System.out.println("Borrow operation successful.");
                        System.out.printf("Current borrowed books: %d\n", borrowed[currentIndex]);
                    }
                }
                case 3 -> {
                	// Return book logic
                    if (borrowed[currentIndex] > 0) {
                        borrowed[currentIndex]--;
                        sessionReturned++;
                        totalReturned++;
                        System.out.println("Return operation successful.");
                        System.out.printf("Current borrowed books: %d\n", borrowed[currentIndex]);
                    } else {
                        System.out.println("You don't have books borrowed.");
                    }
                }
                case 4 -> {
                	// Show session summary
                    System.out.printf("\n--- Session Summary for %s (ID: %d) ---\n", names[currentIndex], ids[currentIndex]);
                    System.out.printf("Books borrowed this session: %d\n", sessionBorrowed);
                    System.out.printf("Books returned this session: %d\n", sessionReturned);
                    System.out.printf("Fees this session: %.2f SAR\n", sessionFee);
                }
                case 5 -> exitUserMenu = true;
                default -> System.out.println("Invalid choice! Please enter a number from the menu.");
            }
        }
	}
	
	public static void adminMenu() {
 		final String adminUsername = "admin"; //  ADMIN USERNAME
		final String adminPassword = "admin123"; // ADMIN PASSWORD
		Console console = System.console();
		
		System.out.print("Enter admin username: ");
		String username = input.next();
		
		String password;
		if (console != null) {
			char[] passArray = console.readPassword("Enter admin password: ");
			password = new String(passArray);
		} else {
			System.out.print("Enter admin password: ");
			password = input.next(); // Fallback for IDEs without Console
		}
		
		// Check credentials
		if (!username.equalsIgnoreCase(adminUsername) || !password.equals(adminPassword)) {
			System.out.println("Invalid admin credentials. Access denied.");
			return;
		}
		
		boolean exitAdmin = false;
        while (!exitAdmin) {
        	// Admin menu
            System.out.println("\nLogged in as Administrator");
            System.out.println("--- Administrator Menu ---");
            System.out.println("1) View Total Revenue");
            System.out.println("2) Most Frequent Operation (Borrow or Return)");
            System.out.println("3) Exit to Main Menu");
            System.out.print("Enter choice: ");
            int adminChoice = input.nextInt();

            switch (adminChoice) {
                case 1 -> System.out.printf("Total revenue collected from all borrow operations: %.2f SAR\n", totalFee);
                case 2 -> {
                	// Determine most frequent operation
                    System.out.println("Most frequent operation:");
                    if (totalBorrowed > totalReturned) {
                        System.out.println("BORROW");
                    } else if (totalReturned > totalBorrowed) {
                        System.out.println("RETURN");
                    } else {
                        System.out.println("TIED");
                    }
                    System.out.printf("Total borrow operations: %d\n", totalBorrowed);
                    System.out.printf("Total return operations: %d\n", totalReturned);
                }
                case 3 -> exitAdmin = true;
                default -> System.out.println("Invalid choice! Please enter a number from the menu.");
            }
        }
	}
}
