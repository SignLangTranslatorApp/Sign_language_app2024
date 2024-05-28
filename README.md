# Sign Language to English Translation App

## Overview

This project is an Android application that translates sign language gestures into English text using a machine learning model. The application leverages Firebase Firestore for backend services and requires configuration of the `google-services.json` file.

## Features

- Real-time sign language recognition and translation
- User-friendly interface
- Firebase Firestore integration for data storage and retrieval
- شحح

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Android Studio installed
- Firebase account and project created
- Basic understanding of Android development and machine learning models

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/sign-language-translation-app.git
   cd sign-language-translation-app
   ```

2. **Open the project in Android Studio:**

   - Launch Android Studio.
   - Select "Open an existing Android Studio project".
   - Navigate to the cloned repository and select it.

3. **Set up Firebase:**

   - Go to the [Firebase Console](https://console.firebase.google.com/).
   - Create a new project (or use an existing one).
   - Add an Android app to your Firebase project.
     - Register your app with your package name.
     - Download the `google-services.json` file.
     - Place the `google-services.json` file in the `app` directory of your Android project.

4. **Replace `google-services.json`:**

   Ensure that the `google-services.json` file is correctly placed in the `app` directory.

## Firebase Integration Steps

1. **Add Firebase SDK:**

   Add the following dependencies to your `build.gradle` files.

   - **Project-level `build.gradle` file:**

     ```groovy
     buildscript {
         dependencies {
             // Add this line
             classpath 'com.google.gms:google-services:4.3.10'
         }
     }
     ```

   - **App-level `build.gradle` file:**

     ```groovy
     apply plugin: 'com.android.application'
     apply plugin: 'com.google.gms.google-services'

     dependencies {
         // Add these lines
         implementation 'com.google.firebase:firebase-auth:21.0.3'
         implementation 'com.google.firebase:firebase-firestore:24.0.0'
     }
     ```

2. **Initialize Firebase in your project:**

   In your main activity or application class, initialize Firebase:

   ```java
   import com.google.firebase.FirebaseApp;

   public class MainActivity extends AppCompatActivity {
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           FirebaseApp.initializeApp(this);
           setContentView(R.layout.activity_main);
       }
   }
   ```

3. **Authentication Setup:**

   Set up Firebase Authentication to allow users to sign in and manage their accounts:

   ```java
   import com.google.firebase.auth.FirebaseAuth;
   import com.google.firebase.auth.FirebaseUser;

   private FirebaseAuth mAuth;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       mAuth = FirebaseAuth.getInstance();
   }

   @Override
   public void onStart() {
       super.onStart();
       FirebaseUser currentUser = mAuth.getCurrentUser();
       if (currentUser != null) {
           // User is signed in
       } else {
           // No user is signed in
       }
   }

   private void signIn(String email, String password) {
       mAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this, task -> {
               if (task.isSuccessful()) {
                   // Sign in success
                   FirebaseUser user = mAuth.getCurrentUser();
               } else {
                   // If sign in fails
               }
           });
   }
   ```

4. **Firestore Integration:**

   Use Firestore to store and retrieve translation data:

   ```java
   import com.google.firebase.firestore.FirebaseFirestore;
   import com.google.firebase.firestore.QueryDocumentSnapshot;
   import com.google.firebase.firestore.QuerySnapshot;

   private FirebaseFirestore db;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       db = FirebaseFirestore.getInstance();
   }

   private void addTranslation(String userId, String translation) {
       Map<String, Object> translationData = new HashMap<>();
       translationData.put("userId", userId);
       translationData.put("translation", translation);
       translationData.put("timestamp", new Date());

       db.collection("translations")
           .add(translationData)
           .addOnSuccessListener(documentReference -> {
               // Document added successfully
           })
           .addOnFailureListener(e -> {
               // Error adding document
           });
   }

   private void getTranslations(String userId) {
       db.collection("translations")
           .whereEqualTo("userId", userId)
           .get()
           .addOnCompleteListener(task -> {
               if (task.isSuccessful()) {
                   for (QueryDocumentSnapshot document : task.getResult()) {
                       // Process each document
                   }
               } else {
                   // Error getting documents
               }
           });
   }
   ```

## Usage

1. **Run the application:**

   - Connect your Android device or start an emulator.
   - Click on the "Run" button in Android Studio.

2. **Access the application:**

   - Open the app on your device or emulator.
   - Sign in and start translating sign language gestures into English.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Feel free to reach out if you have any questions or need further assistance. Happy coding!
