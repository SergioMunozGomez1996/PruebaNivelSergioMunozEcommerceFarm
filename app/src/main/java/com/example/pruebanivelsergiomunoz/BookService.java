package com.example.pruebanivelsergiomunoz;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

//interfaz la cual nos servirá para gestionar cada una de nuestras peticiones a la API
public interface BookService {
    String API_ROUTE = "/getMessages";
    @GET(API_ROUTE)
    Call< List<Book> > getBooks();
}