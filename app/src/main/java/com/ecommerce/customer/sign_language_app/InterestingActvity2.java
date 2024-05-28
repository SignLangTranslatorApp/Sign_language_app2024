package mquinn.sign_language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InterestingActvity2 extends AppCompatActivity {
ImageView take;
String tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting_actvity2);
        take=findViewById(R.id.skip3);
        Intent intent=getIntent();
        tt=  intent.getStringExtra("type");
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), InterestingActvity3.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
    }
}