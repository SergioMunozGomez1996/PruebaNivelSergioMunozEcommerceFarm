package com.example.pruebanivelsergiomunoz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pruebanivelsergiomunoz.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 */
public class FilterFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    List<Book> books = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FilterFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FilterFragment newInstance(int columnCount) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.filter_list);
        // Set the adapter
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyFilteredBookRecyclerViewAdapter myFilteredBookRecyclerViewAdapter = new MyFilteredBookRecyclerViewAdapter(books);
        recyclerView.setAdapter(myFilteredBookRecyclerViewAdapter);

        Spinner genreSpinner = view.findViewById(R.id.genre_spinner);
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getFilteredPosts(myFilteredBookRecyclerViewAdapter, parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void getFilteredPosts(MyFilteredBookRecyclerViewAdapter myFilteredBookRecyclerViewAdapter, String genre) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-pruebas-nivel.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService postService = retrofit.create(BookService.class);
        Call<List<Book>> call = postService.getFilteredBooks(genre);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books.clear();
                for(Book book : response.body()) {
                    books.add(book);
                }
                myFilteredBookRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getContext(),"Sin datos",Toast.LENGTH_SHORT).show();
            }
        });
    }
}