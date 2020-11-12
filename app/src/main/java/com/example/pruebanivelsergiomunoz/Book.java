package com.example.pruebanivelsergiomunoz;

//Clase que representa la estructura de los libros que recibimos en el Json
public class Book {
    private int id;
    private String title;
    private long isbn;
    private String description;
    private String genre;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getISBN() {
        return isbn;
    }
    public void setISBN(long isbn) {
        this.isbn = isbn;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
