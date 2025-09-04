/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.david.proyecto.bibliotecavirtual;
import java.util.*;
/**
 *
 * @author WILLIANS
 */
public class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final String categoria;
    
    public Libro(String isbn, String titulo, String autor, String categoria) {
        if (isbn == null || isbn.trim().isEmpty() || 
            titulo == null || titulo.trim().isEmpty() || 
            autor == null || autor.trim().isEmpty() || 
            categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Ningún campo puede ser nulo o vacío");
        }
        
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }
    
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    
    @Override
    public String toString() {
        return "Libro{isbn='" + isbn + "', titulo='" + titulo + "', autor='" + autor + "', categoria='" + categoria + "'}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
