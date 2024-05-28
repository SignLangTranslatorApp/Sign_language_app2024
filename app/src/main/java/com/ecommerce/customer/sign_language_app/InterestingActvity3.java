package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

import mquinn.sign_language.Adapter.Adapter;

public class InterestingActvity3 extends AppCompatActivity {
    RecyclerView recyclerView;
String tt;
ImageView next;
    // Using ArrayList to store images data
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.m1, R.drawable.m2, R.drawable.m3,
            R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7));

    //////

    ArrayList images1 = new ArrayList<>(Arrays.asList(R.drawable.s1, R.drawable.s2, R.drawable.s3,
            R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7,R.drawable.s8,R.drawable.s8,R.drawable.s10,R.drawable.s9));

    ArrayList images2 = new ArrayList<>(Arrays.asList(R.drawable.f1, R.drawable.f2, R.drawable.f3,
            R.drawable.f4, R.drawable.f5, R.drawable.f6, R.drawable.f7,R.drawable.f8,R.drawable.f8,R.drawable.f10));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting_actvity3);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Intent intent=getIntent();
        next=findViewById(R.id.next);
        tt=  intent.getStringExtra("type");
        // Setting the layout as Staggered Grid for vertical orientation

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),Home.class);
                intent1.putExtra("type",tt);
                startActivity(intent1);
            }
        });
if(tt.equals("Medicine"))
{
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(staggeredGridLayoutManager);

    // Sending reference and data to Adapter
    Adapter adapter = new Adapter(InterestingActvity3.this, images);

    // Setting Adapter to RecyclerView
    recyclerView.setAdapter(adapter);
}else if(tt.equals("Hajj and Umrah services"))
{
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(staggeredGridLayoutManager);

    // Sending reference and data to Adapter
    Adapter adapter = new Adapter(InterestingActvity3.this, images);

    // Setting Adapter to RecyclerView
    recyclerView.setAdapter(adapter);
}
else if(tt.equals("Security field"))
{
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(staggeredGridLayoutManager);

    // Sending reference and data to Adapter
    Adapter adapter1 = new Adapter(InterestingActvity3.this, images1);

    // Setting Adapter to RecyclerView
    recyclerView.setAdapter(adapter1);
}
else
{
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(staggeredGridLayoutManager);

    // Sending reference and data to Adapter
    Adapter adapter2 = new Adapter(InterestingActvity3.this, images2);

    // Setting Adapter to RecyclerView
    recyclerView.setAdapter(adapter2);
}

    }
}