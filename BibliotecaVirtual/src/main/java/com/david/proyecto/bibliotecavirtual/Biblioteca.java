/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.david.proyecto.bibliotecavirtual;
import java.util.*;
import java.util.stream.Collectors;
/**
 *
 * @author WILLIANS
 */
public class Biblioteca {
    private Map<String, Libro> catalogoPorIsbn;
    private Map<String, Usuario> usuariosPorId;
    private Set<String> isbnsPrestados;
    
    public Biblioteca() {
        catalogoPorIsbn = new HashMap<>();
        usuariosPorId = new HashMap<>();
        isbnsPrestados = new HashSet<>();
    }
    
    // Añadir libro al catálogo
    public boolean añadirLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }
        
        if (catalogoPorIsbn.containsKey(libro.getIsbn())) {
            return false; // El libro ya existe
        }
        
        catalogoPorIsbn.put(libro.getIsbn(), libro);
        return true;
    }
    
    // Quitar libro del catálogo
    public boolean quitarLibro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN no puede ser nulo o vacío");
        }
        
        if (isbnsPrestados.contains(isbn)) {
            return false; // No se puede quitar un libro prestado
        }
        
        return catalogoPorIsbn.remove(isbn) != null;
    }
    
    // Registrar usuario
    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        
        if (usuariosPorId.containsKey(usuario.getId())) {
            return false; // El usuario ya existe
        }
        
        usuariosPorId.put(usuario.getId(), usuario);
        return true;
    }
    
    // Dar de baja usuario
    public boolean darBajaUsuario(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID no puede ser nulo o vacío");
        }
        
        Usuario usuario = usuariosPorId.get(id);
        if (usuario == null) {
            return false; // El usuario no existe
        }
        
        if (!usuario.getIsbnsPrestados().isEmpty()) {
            return false; // El usuario tiene libros prestados
        }
        
        usuariosPorId.remove(id);
        return true;
    }
    
    // Prestar libro
    public boolean prestarLibro(String idUsuario, String isbn) {
        if (idUsuario == null || idUsuario.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de usuario e ISBN no pueden ser nulos o vacíos");
        }
        
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            return false; // Usuario no existe
        }
        
        if (!catalogoPorIsbn.containsKey(isbn)) {
            return false; // Libro no existe en el catálogo
        }
        
        if (isbnsPrestados.contains(isbn)) {
            return false; // Libro ya está prestado
        }
        
        // Realizar el préstamo
        isbnsPrestados.add(isbn);
        usuario.agregarIsbnPrestado(isbn);
        return true;
    }
    
    // Devolver libro
    public boolean devolverLibro(String idUsuario, String isbn) {
        if (idUsuario == null || idUsuario.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de usuario e ISBN no pueden ser nulos o vacíos");
        }
        
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            return false; // Usuario no existe
        }
        
        if (!isbnsPrestados.contains(isbn)) {
            return false; // Libro no está prestado
        }
        
        if (!usuario.getIsbnsPrestados().contains(isbn)) {
            return false; // Este usuario no tiene prestado este libro
        }
        
        // Realizar la devolución
        isbnsPrestados.remove(isbn);
        usuario.removerIsbnPrestado(isbn);
        return true;
    }
    
    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Texto de búsqueda no puede ser nulo");
        }
        
        String textoLower = texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(textoLower))
                .collect(Collectors.toList());
    }
    
    // Buscar libros por autor
    public List<Libro> buscarPorAutor(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Texto de búsqueda no puede ser nulo");
        }
        
        String textoLower = texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getAutor().toLowerCase().contains(textoLower))
                .collect(Collectors.toList());
    }
    
    // Buscar libros por categoría
    public List<Libro> buscarPorCategoria(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Texto de búsqueda no puede ser nulo");
        }
        
        String textoLower = texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(libro -> libro.getCategoria().toLowerCase().contains(textoLower))
                .collect(Collectors.toList());
    }
    
    // Listar libros prestados de un usuario
    public List<Libro> listarPrestados(String idUsuario) {
        if (idUsuario == null || idUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo o vacío");
        }
        
        Usuario usuario = usuariosPorId.get(idUsuario);
        if (usuario == null) {
            return Collections.emptyList(); // Usuario no existe
        }
        
        return usuario.getIsbnsPrestados().stream()
                .map(catalogoPorIsbn::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    // Métodos adicionales para obtener información
    public Collection<Libro> getTodosLosLibros() {
        return catalogoPorIsbn.values();
    }
    
    public Collection<Usuario> getTodosLosUsuarios() {
        return usuariosPorId.values();
    }
    
    public Set<String> getIsbnsPrestados() {
        return new HashSet<>(isbnsPrestados);
    }
}
