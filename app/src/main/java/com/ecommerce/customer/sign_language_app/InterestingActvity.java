package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InterestingActvity extends AppCompatActivity {
ImageView a1,a2,a3,a4;
String tt=null;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting_actvity);
        a1=findViewById(R.id.g1);
        a2=findViewById(R.id.g2);
        a3=findViewById(R.id.g3);
        a4=findViewById(R.id.g4);
        save=findViewById(R.id.save);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tt="Security field";
                Toast.makeText(InterestingActvity.this,"Security field",Toast.LENGTH_LONG).show();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt="Medicine";
                Toast.makeText(InterestingActvity.this,"Medicine",Toast.LENGTH_LONG).show();
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt="Hajj and Umrah services";
                Toast.makeText(InterestingActvity.this,"Hajj and Umrah services",Toast.LENGTH_LONG).show();
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt="Education field";
                Toast.makeText(InterestingActvity.this,"Education field",Toast.LENGTH_LONG).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),InterestingActvity2.class);
                intent.putExtra("type",tt);
                startActivity(intent);
            }
        });
    }
}