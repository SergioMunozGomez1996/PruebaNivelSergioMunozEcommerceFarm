package com.example.pruebanivelsergiomunoz;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//interfaz la cual nos servirá para gestionar cada una de nuestras peticiones a la API
public interface BookService {
    //Query para obtener todos los libros
    String API_ROUTE = "/getMessages";
    @GET(API_ROUTE)
    Call<List<Book>> getBooks();

    //Query para obtener todos los libros que tienen el genero indicado
    String API_FILTERED_ROUTE = "/getMessages?";
    @GET(API_FILTERED_ROUTE)
    Call<List<Book>> getFilteredBooks(@Query("genre") String genre);

    //Query para obtener la info de un libro seleccionado
    String API_BOOK_ROUTE = "/getMessage?";
    @GET(API_BOOK_ROUTE)
    Call<List<Book>> getBook(@Query("messageId") int bookID);
}