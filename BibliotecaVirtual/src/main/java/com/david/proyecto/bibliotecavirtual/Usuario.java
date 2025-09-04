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
public class Usuario {
     private String id;
    private String nombre;
    private List<String> isbnsPrestados;
    
    public Usuario(String id, String nombre) {
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ID y nombre no pueden ser nulos o vacíos");
        }
        
        this.id = id;
        this.nombre = nombre;
        this.isbnsPrestados = new ArrayList<>();
    }
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<String> getIsbnsPrestados() { return new ArrayList<>(isbnsPrestados); }
    
    public void agregarIsbnPrestado(String isbn) {
        isbnsPrestados.add(isbn);
    }
    
    public void removerIsbnPrestado(String isbn) {
        isbnsPrestados.remove(isbn);
    }
    
    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "', librosPrestados=" + isbnsPrestados + "}";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
