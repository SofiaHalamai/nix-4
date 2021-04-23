package ua.com.alevel.db;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBInCSV {

    private final String fileAuthors = "authors.csv";
    private final String fileBooks = "books.csv";
    private int sizeAuthors = 1;
    private int sizeBooks = 1;

    private static DBInCSV db;

    private DBInCSV() {
        List<String[]> csvData = new ArrayList<>();
        String[] headerForAuthors = {"ID", "FirstName", "LastName", "Books", "Visible"};
        try (CSVWriter writerA = new CSVWriter(new FileWriter(fileAuthors))) {
            csvData.add(headerForAuthors);
            writerA.writeAll(csvData);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
        csvData.clear();
        String[] headerForBooks = {"ID", "Name", "Authors", "Visible"};
        try (CSVWriter writerB = new CSVWriter(new FileWriter(fileBooks))) {
            csvData.add(headerForBooks);
            writerB.writeAll(csvData);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    public static DBInCSV getInstance() {
        if (db == null) {
            db = new DBInCSV();
        }
        return db;
    }

    public void createAuthor(Author author) {
        String[] addAuthor = new String[5];
        author.setId(this.sizeAuthors++);
        addAuthor[0] = author.getId().toString();
        addAuthor[1] = author.getFirstName();
        addAuthor[2] = author.getLastName();
        for (String books : author.getBooks()) {
            if (addAuthor[3] != null)
                addAuthor[3] += ", " + books;
            else
                addAuthor[3] = books;
        }
        author.setVisible(true);
        addAuthor[4] = author.getVisible().toString();
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors, true))) {
            writer.writeNext(addAuthor);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }

    public void createBook(Book book) {
        String[] addBook = new String[4];
        book.setId(this.sizeBooks++);
        addBook[0] = book.getId().toString();
        addBook[1] = book.getName();
        for (String authors : book.getAuthors()) {
            if (addBook[2] != null)
                addBook[2] += ", " + authors;
            else
                addBook[2] = authors;
        }
        book.setVisible(true);
        addBook[3] = book.getVisible().toString();
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileBooks, true))) {
            writer.writeNext(addBook);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        }
    }


    public void updateBook(Book book) {
        int id = book.getId();
        try {
            CSVReader reader = new CSVReader(new FileReader(fileBooks));
            List<String[]> allBook = reader.readAll();
            reader.close();
            if (allBook.size() < id - 1 || allBook.isEmpty() || allBook.get(id)[3].equals("false")) {
                System.out.println("No such book!");
                return;
            }
            allBook.get(id)[1] = book.getName();
            allBook.get(id)[2] = null;
            for (String authors : book.getAuthors()) {
                if (allBook.get(id)[2] != null)
                    allBook.get(id)[2] += ", " + authors;
                else
                    allBook.get(id)[2] = authors;
            }
            CSVWriter writer = new CSVWriter(new FileWriter(fileBooks));
            writer.writeAll(allBook);
            writer.close();
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
    }

    public void updateAuthor(Author author) {
        int id = author.getId();
        try {
            CSVReader reader = new CSVReader(new FileReader(fileAuthors));
            List<String[]> allAuthors = reader.readAll();
            reader.close();
            if (allAuthors.size() < id - 1 || allAuthors.isEmpty() || allAuthors.get(id)[4].equals("false")) {
                System.out.println("No such author!");
                return;
            }
            allAuthors.get(id)[1] = author.getFirstName();
            allAuthors.get(id)[2] = author.getLastName();
            allAuthors.get(id)[3] = null;
            for (String books : author.getBooks()) {
                if (allAuthors.get(id)[3] != null)
                    allAuthors.get(id)[3] += ", " + books;
                else
                    allAuthors.get(id)[3] = books;
            }
            CSVWriter writer = new CSVWriter(new FileWriter(fileAuthors));
            writer.writeAll(allAuthors);
            writer.close();
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
    }

    public void deleteBook(int id) {
        deleteSomeFromLibrary(id, fileBooks);
    }

    public void deleteAuthor(int id) {
        deleteSomeFromLibrary(id, fileAuthors);
    }

    private void deleteSomeFromLibrary(int id, String file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> allEntities = reader.readAll();
            reader.close();
            if (allEntities.size() < id - 1 || allEntities.isEmpty() || allEntities.get(id)[3].equals("false")) {
                System.out.println("Incorrect ID! No such ID!");
                return;
            }
            if (file.equals(fileAuthors)) {
                allEntities.get(id)[4] = "false";
            }else{
                allEntities.get(id)[3] = "false";
            }
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(allEntities);
            writer.close();
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
    }

    public List<Book> findAllBook() {
        List<Book> allBooks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileBooks))) {
            reader.readNext();
            List<String[]> books;

            books = reader.readAll();
            for (String[] b : books) {
                if (b[3].equals("true")) {
                    Book book = new Book();
                    book.setId(Integer.valueOf(b[0]));
                    book.setName(b[1]);
                    book.setAuthors(b[2]);
                    allBooks.add(book);
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
        return allBooks;
    }

    public List<Author> findAllAuthor() {
        List<Author> allAuthors = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileAuthors))) {
            reader.readNext();
            List<String[]> authors;

            authors = reader.readAll();
            for (String[] a : authors) {
                if (a[4].equals("true")) {
                    Author author = new Author();
                    author.setId(Integer.valueOf(a[0]));
                    author.setFirstName(a[1]);
                    author.setLastName(a[2]);
                    author.setBooks(a[3]);
                    allAuthors.add(author);
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
        return allAuthors;
    }

    public Book findBookById(int id) {
        List<Book> books = findAllBook();
        Book current = books.stream().filter(b -> id == b.getId()).findFirst().orElse(null);
        if (current == null) {
            System.out.println("Book not found!");
            return null;
        }
        return current;
    }

    public Author findAuthorById(int id) {
        List<Author> authors = findAllAuthor();
        Author current = authors.stream().filter(a -> id == a.getId()).findFirst().orElse(null);
        if (current == null) {
            System.out.println("Author not found!");
            return null;
        }
        return current;
    }

    public List<Author> findAuthorsByBook(Book book) {
        List<Author> authorsList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(fileAuthors))) {
            reader.readNext();

            List<String[]> authors;
            authors = reader.readAll();

            for (String[] a : authors) {
                for (String tmp : a[3].split(", ")) {
                    if (tmp.equals(book.getName()) && a[4].equals("true")) {
                        Author author = new Author();
                        author.setId(Integer.valueOf(a[0]));
                        author.setFirstName(a[1]);
                        author.setLastName(a[2]);
                        author.setBooks(a[3]);
                        authorsList.add(author);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
        return authorsList;
    }

    public List<Book> findBooksByAuthor(Author author) {
        List<Book> booksList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(fileBooks))) {
            reader.readNext();

            List<String[]> books;
            books = reader.readAll();

            for (String[] b : books) {
                for (String tmp : b[2].split(", ")) {
                    if (tmp.equals(author.getFirstName() + " " + author.getLastName())
                            && b[3].equals("true")) {
                        Book book = new Book();
                        book.setId(Integer.valueOf(b[0]));
                        book.setName(b[1]);
                        book.setAuthors(b[2]);
                        booksList.add(book);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            System.out.println("Invalid file path");
        }
        return booksList;
    }
}
