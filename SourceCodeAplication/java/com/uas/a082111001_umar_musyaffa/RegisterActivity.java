package com.uas.a082111001_umar_musyaffa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.uas.a082111001_umar_musyaffa.databinding.ActivityRegisterBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextPassword, editTextName, editTextPhone;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize views
        editTextName = binding.NameInput;
        editTextEmail = binding.Emailinput;
        editTextPassword = binding.Passwordinput;
        editTextPhone = binding.Phoneinput;
        button = binding.tombolRegist;

        // Set click listener for register button
        button.setOnClickListener(v -> userRegister());

        // If the user is already logged in, start the main activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startMain();
            finish();
        }
    }

    private void userRegister() {
        // Disable the button to prevent multiple clicks
        button.setEnabled(false);

        new Thread(() -> {
            try {
                RequestHandler requestHandler = new RequestHandler();
                String name = Objects.requireNonNull(editTextName.getText()).toString();
                String email = Objects.requireNonNull(editTextEmail.getText()).toString();
                String password = Objects.requireNonNull(editTextPassword.getText()).toString();
                String phone = Objects.requireNonNull(editTextPhone.getText()).toString();
                String responseHandler;

                // Creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);

                // Sending the POST request and getting the response
                responseHandler = requestHandler.sendPost(Routes.REGISTER_URL, params);

                // Converting response to JSON object
                JSONObject obj = new JSONObject(responseHandler);
                JSONObject response = obj.getJSONObject("response");
                final String message = response.getString("message");

                runOnUiThread(() -> {
                    // Show a toast message with the server response
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    // Enable the button again
                    button.setEnabled(true);
                });

                // If response is successful
                if (response.getString("status").equals("success")) {
                    JSONObject data = response.getJSONObject("data");
                    Log.e("data", "data is " + data);

                    // Creating a new user object
                    Users user = new Users(
                            data.getInt("id"),
                            data.getString("name"),
                            data.getString("email"),
                            data.getString("password"),
                            data.getString("phone")
                    );

                    // Save user details and transition to the main activity
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                    runOnUiThread(() -> {
                        startMain();
                        finish();
                    });
                }
            } catch (Exception e) {
                Log.e("printStackTrace userRegister", Log.getStackTraceString(e));
                runOnUiThread(() -> {
                    // Enable the button again
                    button.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void startMain() {
        new Thread(() -> {
            try {
                RequestHandler requestHandler = new RequestHandler();
                String responseHandler;

                // Sending the GET request
                responseHandler = requestHandler.sendGet(Routes.DATA_URL);

                JSONObject obj = new JSONObject(responseHandler);
                JSONObject response = obj.getJSONObject("response");

                // If response is successful
                if (response.getString("status").equals("success")) {
                    String data = response.getString("data");
                    Log.e("data", "data is " + data);


                    Intent main = new Intent(RegisterActivity.this, MainActivity.class);

                    main.putExtra("data_json", data);
                    startActivity(main);
                    finish();
                }
            } catch (Exception e) {
                Log.e("printStackTrace startMain", Log.getStackTraceString(e));
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Permission Failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

}
