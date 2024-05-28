package mquinn.sign_language;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateNewAccount extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText,Confrimpassword;
    private EditText Name,LNAME;
    private ImageView save;
    private TextView signupText;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        Name = findViewById(R.id.firstname);
        save=findViewById(R.id.loginButton);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        LNAME=findViewById(R.id.lastname);
        Confrimpassword=findViewById(R.id.Confrimpassword);

        signupText=findViewById(R.id.signupText);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateNewAccount.this,Login.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });


    }
    private void createUser(String email, String password, String name, String last, String cpass){
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("first name", name);
        user.put("last name", last);

        // Add a new document with a generated ID
        db.collection("USER")
                .document(email)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateNewAccount.this, "User created successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SignUpActivity", "Error adding document", e);
                        Toast.makeText(CreateNewAccount.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Assuming you have a method to handle sign up button click
    public void signUpClicked () {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String name = Name.getText().toString().trim();
        String last = LNAME.getText().toString().trim();
        String cpass=Confrimpassword.getText().toString().trim();

        // Validate email and password here if needed

        createUser(email, password,name,last,cpass);
        createAccount(email, password,cpass);
    }

    private void createAccount(String email, String password, String cpass) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cpass))
        {
            Toast.makeText(getApplicationContext(), "Please enter Confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!cpass.equals(password))
        {
            Toast.makeText(getApplicationContext(), "Please re-enter  password Confirm", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SignUpActivity", "createUserWithEmail:success");
                            Toast.makeText(CreateNewAccount.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(CreateNewAccount.this,InterestingActvity.class);
                            startActivity(intent);
                            // You can add further actions here, like navigating to another activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignUpActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateNewAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in.
            Toast.makeText(this, "User is logged in", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(CreateNewAccount.this,InterestingActvity.class);
            startActivity(intent);
            // You can add further actions here, like navigating to the home screen.
        } else {
            // No user is signed in.
            Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show();
            // You can add further actions here, like navigating to the login screen.
        }
    }

}