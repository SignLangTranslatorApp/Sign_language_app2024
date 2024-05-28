package mquinn.sign_language;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class translation extends AppCompatActivity {
    EditText bg;
    ImageView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        bg=findViewById(R.id.bg);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t4.setVisibility(View.INVISIBLE);
        bg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // قبل تغيير النص
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // أثناء تغيير النص
                String inputText = s.toString().trim();
                if(inputText.equals("tala") || inputText.equals("TALA") || inputText.equals("Tala")) {
                    Toast.makeText(translation.this, "Text changed: " + inputText, Toast.LENGTH_SHORT).show();
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                }
                else if(inputText.equals("T") ||inputText.equals("t"))
                {
                    t1.setVisibility(View.VISIBLE);
                }
                else if(inputText.equals("A") ||inputText.equals("A"))
                {
                    t2.setVisibility(View.VISIBLE);
                }
                else if(inputText.equals("L") ||inputText.equals("l"))
                {
                    t3.setVisibility(View.VISIBLE);
                }
                else if(inputText.equals("A") ||inputText.equals("a"))
                {
                    t4.setVisibility(View.VISIBLE);
                }
                else
                {
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE










































































































                    );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // بعد تغيير النص
            }
        });



    }
}