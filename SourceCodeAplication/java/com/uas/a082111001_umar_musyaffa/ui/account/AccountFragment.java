package com.uas.a082111001_umar_musyaffa.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.uas.a082111001_umar_musyaffa.MainActivity;
import com.uas.a082111001_umar_musyaffa.RegisterActivity;
import com.uas.a082111001_umar_musyaffa.RequestHandler;
import com.uas.a082111001_umar_musyaffa.Routes;
import com.uas.a082111001_umar_musyaffa.SharedPrefManager;
import com.uas.a082111001_umar_musyaffa.Users;
import com.uas.a082111001_umar_musyaffa.databinding.FragmentAccountBinding;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private TextInputEditText editTextEmail, editTextPassword;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // views
        editTextEmail = binding.Emailinput;
        editTextPassword = binding.Passwordinput;
        button = binding.tombol;
        Button register = binding.register;

        register.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegisterActivity.class);
            startActivity(intent);
        });

        // Set click listener
        button.setOnClickListener(v -> userAuth());



        // Check if user is already logged in
        if (SharedPrefManager.getInstance(requireContext()).isLoggedIn()) {
            startMain();

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void userAuth() {
        // Disable the button to prevent multiple clicks
        button.setEnabled(false);

        new Thread(() -> {
            try {
                RequestHandler requestHandler = new RequestHandler();
                String email = Objects.requireNonNull(editTextEmail.getText()).toString();
                String password = Objects.requireNonNull(editTextPassword.getText()).toString();
                String responseHandler;

                // Creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                // Sending the POST request and getting the response
                responseHandler = requestHandler.sendPost(Routes.LOGIN_URL, params);

                // Converting response to JSON object
                JSONObject obj = new JSONObject(responseHandler);
                JSONObject response = obj.getJSONObject("response");
                final String message = response.getString("message");

                requireActivity().runOnUiThread(() -> {
                    // Show a toast message with the server response
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    // Enable the button again
                    button.setEnabled(true);
                });

                // If response is successful
                if (response.getString("status").equals("success")) {
                    JSONObject data = response.getJSONObject("data");

                    // Creating a new user object
                    Users user = new Users(
                            data.getInt("id"),
                            data.getString("name"),
                            data.getString("email"),
                            data.getString("password"),
                            data.getString("phone")
                    );

                    // Save user details and transition to the main activity
                    SharedPrefManager.getInstance(requireContext()).userLogin(user);
                    requireActivity().runOnUiThread(this::startMain);
                }
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> {
                    // Enable the button again
                    button.setEnabled(true);
                    Toast.makeText(getContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
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

                if (response.getString("status").equals("success")) {
                    String data = response.getString("data");

                    // Starting the main activity
                    Intent main = new Intent(getContext(), MainActivity.class);

                    main.putExtra("data_json", data);
                    startActivity(main);
                    requireActivity().finish();
                }
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Permission Failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

}