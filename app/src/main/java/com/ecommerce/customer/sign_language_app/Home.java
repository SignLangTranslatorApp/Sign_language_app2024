package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Home extends AppCompatActivity {
    private ImageView profile,cam,keyboared1,dictionary,search;
String tt;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile=findViewById(R.id.profile);
        dictionary=findViewById(R.id.dictionary);
        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Keyboared.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
        cam=findViewById(R.id.camera);
        Intent intent=getIntent();
      tt=  intent.getStringExtra("type");
keyboared1=findViewById(R.id.keyboared1);
        search=findViewById(R.id.search);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        keyboared1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,translation.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ChosenTranslator.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,SearchShowImage.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ProfileActivity.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();  // إغلاق النشاط الحالي والعودة إلى النشاط السابق
                return true;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_help:
                Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Home.this,Help.class);
                intent1.putExtra("type",tt);
                startActivity(intent1);
                return true;
            case R.id.bb:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Home.this,ProfileActivity.class);
                intent.putExtra("type",tt);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Action(View view) {


        //finish();
        startActivity(new Intent(this,MainActivity.class));


    }

    public void dictionary(View view) {
        startActivity(new Intent(this,MainActivity.class));

    }



    public void translate(View view) {
        startActivity(new Intent(this,DetectorActivity.class));


    }
}