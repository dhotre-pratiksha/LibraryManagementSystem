package librarymanagementsystem;

import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        System.out.println("-----------------------------------------------");
        System.out.println("---------- LIBRARY MANAGEMENT SYSTEM ----------");
        System.out.println("-----------------------------------------------");

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice : ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title : ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name : ");
                    String author = sc.nextLine();

                    System.out.print("Enter Quantity : ");
                    int qty = sc.nextInt();

                    Book book = new Book(id, title, author, qty);
                    library.addBook(book);
                    break;

                case 2:
                    library.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to search : ");
                    id = sc.nextInt();
                    Book found = library.searchBook(id);
                    if (found != null) {
                        System.out.printf("%-5s %-25s %-20s %-10s%n", "ID", "Title", "Author", "Qty");
                        System.out.println("-------------------------------------------------------------");
                        found.displayInfo();
                    } else {
                        System.out.println(" Book not found!!!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to update : ");
                    id = sc.nextInt();
                    library.updateBook(id, sc);
                    break;

                case 5:
                    System.out.print("Enter Book ID to delete : ");
                    id = sc.nextInt();
                    library.deleteBook(id);
                    break;

                case 6:
                    System.out.print("Enter Book ID to issue : ");
                    id = sc.nextInt();
                    library.issueBook(id);
                    break;

                case 7:
                    System.out.print("Enter Book ID to return : ");
                    id = sc.nextInt();
                    library.returnBook(id);
                    break;

                case 8:
                    System.out.println(" Exiting the system... Thank You!");
                    break;

                default:
                    System.out.println(" Invalid choice! Please try again...");
            }
        } while (choice != 8);

        sc.close();
    }
}
