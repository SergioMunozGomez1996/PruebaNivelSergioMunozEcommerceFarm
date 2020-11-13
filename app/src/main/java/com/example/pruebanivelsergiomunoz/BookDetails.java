package com.example.pruebanivelsergiomunoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Clase encargada de gestionar el comportamiento del activity que muestra los detalles del libro seleccionado
public class BookDetails extends AppCompatActivity {

    private TextView id, title, isbn, genre, description, idTitle, titleTitle, isbnTitle, genreTitle, descriptionTitle;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        this.id = findViewById(R.id.id);
        this.title = findViewById(R.id.title);
        this.isbn = findViewById(R.id.isbn);
        this.genre = findViewById(R.id.genre);
        this.description = findViewById(R.id.description);
        this.idTitle = findViewById(R.id.id_title);
        this.titleTitle = findViewById(R.id.title_title);
        this.isbnTitle = findViewById(R.id.isbn_title);
        this.genreTitle = findViewById(R.id.genre_title);
        this.descriptionTitle = findViewById(R.id.description_title);
        this.progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        getBook(intent.getIntExtra("bookID", 0));
    }
    //Peticion http para obtener y mostrar la información del libro seleccionado
    private void getBook(int bookID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-pruebas-nivel.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService postService = retrofit.create(BookService.class);
        Call<List<Book>> call = postService.getBook(bookID);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                for(Book book : response.body()) {
                    progressBar.setVisibility(View.GONE);
                    id.setText(String.valueOf(book.getId()));
                    title.setText(book.getTitle());
                    isbn.setText(String.valueOf(book.getISBN()));
                    if(book.getGenre()!=null)
                        genre.setText(book.getGenre());
                    else
                        genre.setText(R.string.UNKNOWN);

                    description.setText(book.getDescription());

                    idTitle.setVisibility(View.VISIBLE);
                    titleTitle.setVisibility(View.VISIBLE);
                    isbnTitle.setVisibility(View.VISIBLE);
                    genreTitle.setVisibility(View.VISIBLE);
                    descriptionTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(R.string.NO_DATA),Toast.LENGTH_SHORT).show();
            }
        });
    }
}