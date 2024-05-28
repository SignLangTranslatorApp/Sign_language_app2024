package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChosenTranslator extends AppCompatActivity {
ImageView cam,tra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_translator);
        cam=findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChosenTranslator.this,ShotingVideo.class));
            }
        });
        tra=findViewById(R.id.tra);
        tra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChosenTranslator.this,SearchShowImage.class));
            }
        });
    }
}