/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.david.proyecto.bibliotecavirtual;
/**
 *
 * @author WILLIANS
 */
public class BibliotecaVirtual {

    public static void main(String[] args) {
       Biblioteca biblioteca = new Biblioteca();
        
        // Registrar usuarios
        biblioteca.registrarUsuario(new Usuario("U1", "Ana"));
        biblioteca.registrarUsuario(new Usuario("U2", "Luis"));
        
        // Añadir libros
        biblioteca.añadirLibro(new Libro("ISBN-001", "Clean Code", "Robert C. Martin", "Software"));
        biblioteca.añadirLibro(new Libro("ISBN-002", "Effective Java", "Joshua Bloch", "Java"));
        biblioteca.añadirLibro(new Libro("ISBN-003", "Design Patterns", "Erich Gamma", "Software"));
        
        // Prestar libros
        biblioteca.prestarLibro("U1", "ISBN-001");
        biblioteca.prestarLibro("U2", "ISBN-002");
        
        // Listar libros prestados de U1
        System.out.println("Libros prestados a Ana (U1):");
        biblioteca.listarPrestados("U1").forEach(System.out::println);
        
        // Devolver libro
        biblioteca.devolverLibro("U1", "ISBN-001");
        
        // Buscar por autor
        System.out.println("\nLibros de Joshua Bloch:");
        biblioteca.buscarPorAutor("Bloch").forEach(System.out::println);
        
        // Buscar por categoría
        System.out.println("\nLibros de categoría Software:");
        biblioteca.buscarPorCategoria("Software").forEach(System.out::println);
        
        // Mostrar estado final
        System.out.println("\n--- ESTADO FINAL DE LA BIBLIOTECA ---");
        System.out.println("Todos los libros:");
        biblioteca.getTodosLosLibros().forEach(System.out::println);
        
        System.out.println("\nTodos los usuarios:");
        biblioteca.getTodosLosUsuarios().forEach(System.out::println);
        
        System.out.println("\nISBNs prestados: " + biblioteca.getIsbnsPrestados());
    }
}
