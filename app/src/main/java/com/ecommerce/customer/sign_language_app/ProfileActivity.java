package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView emailTextView;
    private TextView nameTextView,nameTy;
    String email;
    Button logout;
    String tt;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public ProfileActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        nameTy=findViewById(R.id.nameTy);
        tt=  intent.getStringExtra("type");
        nameTy.setText(tt);
        emailTextView = findViewById(R.id.Emailp);
        nameTextView = findViewById(R.id.nameP);
        logout=findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                // Redirect to the login screen or any other screen after logout
                Intent intent = new Intent(ProfileActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); //
            }
        });
        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        // Get the currently signed-in user
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in, get their email
            email = user.getEmail();
            // Update UI with user's email
            emailTextView.setText(email);
        } else {
            // No user is signed in
            Log.d("UserProfileActivity", "No user signed in");
        }

        // Assuming you have the user's ID, you can fetch the user's data
        String userId = "user_id_here";

        getUserData(userId);
    }
    private void getUserData(String userId) {
        db.collection("USER")
                .document(email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String email = document.getString("email");
                                String name = document.getString("first name");
                                String lname = document.getString("last name");

                                // Update UI with user data
                                emailTextView.setText(email);
                                nameTextView.setText(name+" "+lname);
                            } else {
                                Log.d("UserProfileActivity", "No such document");
                            }
                        } else {
                            Log.d("UserProfileActivity", "get failed with ", task.getException());
                        }
                    }
                });
    }

}