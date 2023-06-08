package Libros;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
import java.io.*;
import java.util.*;

// Clase que representa un libro
class Book {
    private String title;
    private String[] authors;
    private String id;
    private String category;
    private int recommendedAge;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String[] authors, String id, String category, int recommendedAge) {
        this.title = title;
        this.authors = authors;
        this.id = id;
        this.category = category;
        this.recommendedAge = recommendedAge;
        this.isAvailable = true; // Por defecto, el libro está disponible
    }

    // Métodos getter y setter

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Clase que representa un usuario
class User {
    private String name;
    private String lastName;
    private String dateOfBirth;
    private String dni;

    // Constructor
    public User(String name, String lastName, String dateOfBirth, String dni) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
    }

    // Métodos getter y setter

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDni() {
        return dni;
    }
}

// Clase que representa la biblioteca y administra los libros y usuarios
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Método para dar de alta un libro
    public void addBook(Book book) {
        books.add(book);
    }

    // Método para dar de baja un libro
    public void removeBook(Book book) {
        books.remove(book);
    }

    // Método para dar de alta un usuario
    public void addUser(User user) {
        users.add(user);
    }

    // Método para dar de baja un usuario
    public void removeUser(User user) {
        users.remove(user);
    }

    // Método para prestar un libro
    public void lendBook(Book book, User user) {
        if (!book.isAvailable()) {
            System.out.println("El libro no está disponible actualmente.");
            return;
        }

        int userAge = calculateUserAge(user.getDateOfBirth());
        if (userAge < 8) {
            System.out.println("El usuario debe tener al menos 8 años para pedir prestado un libro.");
            return;
        }

        if (book.getRecommendedAge() > userAge) {
            System.out.println("El usuario no cumple con la edad recomendada para este libro.");
            return;
        }

        book.setAvailable(false);
        System.out.println("El libro '" + book.getTitle() + "' ha sido prestado a " + user.getName() + ".");
    }

    // Método para devolver un libro
    public void returnBook(Book book, User user) {
        if (book.isAvailable()) {
            System.out.println("El libro no está en préstamo actualmente.");
            return;
        }

        book.setAvailable(true);
        System.out.println("El libro '" + book.getTitle() + "' ha sido devuelto por " + user.getName() + ".");
    }

    // Método para listar libros por título
    public void listBooksByTitle() {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
        System.out.println("Libros ordenados por título:");
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    // Método para listar libros por categoría
    public void listBooksByCategory(String category) {
        System.out.println("Libros en la categoría '" + category + "':");
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                System.out.println(book.getTitle());
            }
        }
    }

    // Método para listar libros prestados
    public void listBorrowedBooks() {
        System.out.println("Libros prestados:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book.getTitle());
            }
        }
    }

    // Método para listar libros disponibles
    public void listAvailableBooks() {
        System.out.println("Libros disponibles:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle());
            }
        }
    }

    // Método para listar libros de un usuario
    public void listBooksByUser(User user) {
        System.out.println("Libros prestados a " + user.getName() + ":");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book.getTitle());
            }
        }
    }

    // Método para calcular la edad actual a partir de la fecha de nacimiento
    private int calculateUserAge(String dateOfBirth) {
        // Implementa aquí la lógica para calcular la edad a partir de la fecha de nacimiento
        // ...
        return 0;
    }

    // Método para almacenar el estado de la biblioteca en un archivo
    public void saveLibraryState(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(books);
            oos.writeObject(users);
            System.out.println("El estado de la biblioteca ha sido guardado en el archivo '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Error al guardar el estado de la biblioteca: " + e.getMessage());
        }
    }
}
