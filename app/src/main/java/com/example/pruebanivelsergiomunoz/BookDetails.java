package com.example.pruebanivelsergiomunoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetails extends AppCompatActivity {

    TextView id, title, isbn, genre, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        String bookID;

        this.id = findViewById(R.id.id);
        this.title = findViewById(R.id.title);
        this.isbn = findViewById(R.id.isbn);
        this.genre = findViewById(R.id.genre);
        this.description = findViewById(R.id.description);

        Intent intent = getIntent();
        getBook(intent.getIntExtra("bookID", 0));


    }

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
                    id.setText(String.valueOf(book.getId()));
                    title.setText(book.getTitle());
                    isbn.setText(String.valueOf(book.getISBN()));
                    if(book.getGenre()!=null)
                        genre.setText(book.getGenre());
                    else
                        genre.setText(R.string.UNKNOWN);

                    description.setText(book.getDescription());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(R.string.NO_DATA),Toast.LENGTH_SHORT).show();
            }
        });
    }
}