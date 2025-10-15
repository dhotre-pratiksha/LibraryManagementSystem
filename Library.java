package librarymanagementsystem;

import java.util.*;
import java.io.*;

public class Library {

    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
        loadFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(" Book added successfully...!");
        saveToFile();
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println(" No books available!!!");
            return;
        }

        System.out.printf("%-5s %-25s %-20s %-10s%n", "ID", "Title", "Author", "Qty");
        System.out.println("-------------------------------------------------------------");
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public Book searchBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void updateBook(int id, Scanner sc) {
        Book book = searchBook(id);
        if (book == null) {
            System.out.println(" Book not found!!!");
            return;
        }

        System.out.println("Enter new title (leave blank to keep same): ");
        sc.nextLine();
        String title = sc.nextLine();
        if (!title.isEmpty()) book.setTitle(title);

        System.out.println("Enter new author (leave blank to keep same) : ");
        String author = sc.nextLine();
        if (!author.isEmpty()) book.setAuthor(author);

        System.out.println("Enter new quantity (or -1 to keep same) : ");
        int qty = sc.nextInt();
        if (qty != -1) book.setQuantity(qty);

        System.out.println(" Book updated successfully...");
        saveToFile();
    }

    public void deleteBook(int id) {
        Book book = searchBook(id);
        if (book == null) {
            System.out.println(" Book not found!!!");
            return;
        }
        books.remove(book);
        System.out.println(" Book deleted successfully...!");
        saveToFile();
    }

    public void issueBook(int id) {
        Book book = searchBook(id);
        if (book == null) {
            System.out.println(" Book not found!!!");
            return;
        }
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            System.out.println(" Book issued successfully...!");
        } else {
            System.out.println(" No copies available!!!");
        }
        saveToFile();
    }

    public void returnBook(int id) {
        Book book = searchBook(id);
        if (book == null) {
            System.out.println(" Book not found!!!");
            return;
        }
        book.setQuantity(book.getQuantity() + 1);
        System.out.println(" Book returned successfully...!");
        saveToFile();
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                pw.println(book.toString());
            }
        } catch (IOException e) {
            System.out.println(" Error saving file : " + e.getMessage());
        }
    }

    public void loadFromFile() {
        books.clear();
        File file = new File("books.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int qty = Integer.parseInt(parts[3]);
                    books.add(new Book(id, title, author, qty));
                }
            }
        } catch (IOException e) {
            System.out.println(" Error loading file : " + e.getMessage());
        }
    }
}
