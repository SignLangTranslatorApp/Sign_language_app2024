package mquinn.sign_language;

// MainActivity.java


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mquinn.sign_language.Adapter.ImageAdapter;
import mquinn.sign_language.model.ImageModel;

public class SearchShowImage extends AppCompatActivity {

    private List<ImageModel> imageItemList;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_show_image);

        imageItemList = new ArrayList<>();
        // Add your images and titles here
        imageItemList.add(new ImageModel(R.drawable.na1, "Don’t Know"));
        imageItemList.add(new ImageModel(R.drawable.n2, "Thank you"));
        imageItemList.add(new ImageModel(R.drawable.n3, "You’re welcome"));
        imageItemList.add(new ImageModel(R.drawable.n4, "Good morning"));
        imageItemList.add(new ImageModel(R.drawable.n5, "Good night"));



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ImageAdapter(imageItemList);
        recyclerView.setAdapter(adapter);

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String text) {
        List<ImageModel> filteredList = imageItemList.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        adapter.updateList(filteredList);
    }
}