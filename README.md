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
# Creating a Sign Language Recognition Model and Integrating it with an Android Application

## Overview

This guide outlines the steps to create a machine learning model for recognizing sign language and integrating it with an Android application. The process involves training the model, converting it into a format compatible with Android, and incorporating it into an Android app that uses Firebase Firestore for backend services.

## Step 1: Data Collection and Preprocessing

1. **Data Collection:**
   - Collect a large dataset of sign language gestures. Each gesture should have multiple samples recorded in consistent lighting and background conditions.
   - Label each gesture appropriately for training.

2. **Data Preprocessing:**
   - Normalize the images by resizing them to a consistent shape (e.g., 64x64 or 128x128 pixels).
   - Convert images to grayscale if necessary to reduce complexity.
   - Augment the data to increase the diversity of the training set by applying transformations like rotation, flipping, and zooming.

## Step 2: Model Training

1. **Choose a Model Architecture:**
   - Convolutional Neural Networks (CNNs) are effective for image recognition tasks. You can start with a simple architecture and gradually increase complexity.

2. **Training the Model:**
   - Split your dataset into training, validation, and test sets.
   - Use a deep learning framework like TensorFlow or PyTorch to build and train the model.
   - Monitor the model's performance using metrics like accuracy and loss.

3. **Save the Model:**
   - Once the model is trained to a satisfactory level of accuracy, save it in a format suitable for mobile deployment. TensorFlow Lite is commonly used for Android applications.

   ```python
   import tensorflow as tf

   # Example: Saving a TensorFlow model
   model.save('sign_language_model.h5')

   # Convert the model to TensorFlow Lite
   converter = tf.lite.TFLiteConverter.from_keras_model(model)
   tflite_model = converter.convert()

   # Save the converted model
   with open('sign_language_model.tflite', 'wb') as f:
       f.write(tflite_model)
   ```

## Step 3: Integrating the Model with an Android App

1. **Add TensorFlow Lite to Your Android Project:**
   - Include the TensorFlow Lite dependency in your `build.gradle` file.

   ```groovy
   dependencies {
       implementation 'org.tensorflow:tensorflow-lite:2.7.0'
   }
   ```

2. **Load the Model in the Android App:**
   - Place the `model.tflite` file in the `assets` directory of your Android project.
   - Write code to load and run the model.

   ```java
   import org.tensorflow.lite.Interpreter;

   public class SignLanguageClassifier {

       private Interpreter interpreter;

       public SignLanguageClassifier(Context context) throws IOException {
           interpreter = new Interpreter(loadModelFile(context));
       }

       private MappedByteBuffer loadModelFile(Context context) throws IOException {
           AssetFileDescriptor fileDescriptor = context.getAssets().openFd("model.tflite");
           FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
           FileChannel fileChannel = inputStream.getChannel();
           long startOffset = fileDescriptor.getStartOffset();
           long declaredLength = fileDescriptor.getDeclaredLength();
           return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
       }

       public float[] classify(float[] input) {
           float[][] output = new float[1][1]; // Adjust dimensions based on model output
           interpreter.run(input, output);
           return output[0];
       }
   }
   ```

3. **Capture and Preprocess Input in the Android App:**
   - Use the device camera to capture real-time video or images.
   - Preprocess the captured images to match the input requirements of the model (e.g., resizing, normalizing).

   ```java
   private Bitmap preprocessImage(Bitmap bitmap) {
       // Resize and normalize the bitmap as required by your model
       return Bitmap.createScaledBitmap(bitmap, 64, 64, true);
   }
   ```

4. **Run the Model and Display Results:**
   - Use the `SignLanguageClassifier` class to run the model on the preprocessed input and display the results to the user.

   ```java
   Bitmap bitmap = ...; // Capture or load your image
   bitmap = preprocessImage(bitmap);

   float[] input = ...; // Convert bitmap to appropriate input format for the model

   float[] output = signLanguageClassifier.classify(input);

   // Display the result
   String result = interpretOutput(output);
   textView.setText(result);
   ```

## Step 4: Firebase Firestore Integration

1. **Add Firebase to Your Android Project:**
   - Follow the steps mentioned in the earlier section to set up Firebase in your project, including adding the `google-services.json` file.

2. **Store and Retrieve Translation Data:**
   - Use Firebase Firestore to store user data and translation results.

   ```java
   import com.google.firebase.firestore.FirebaseFirestore;

   FirebaseFirestore db = FirebaseFirestore.getInstance();

   private void saveTranslation(String userId, String translation) {
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

## Conclusion

By following these steps, you can create a sign language recognition model and integrate it into an Android application. The integration with Firebase Firestore allows you to store and manage translation data efficiently. This approach provides a robust solution for real-time sign language translation on mobile devices.
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
