package com.uas.a082111001_umar_musyaffa;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "your_app_shared_pref";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PHONE = "phone";

    private static SharedPrefManager instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    // Method to store user login session
    public void userLogin(Users user) {
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.apply();
    }

    // Method to check if user is logged in
    public boolean isLoggedIn() {
        return sharedPreferences.getInt(KEY_USER_ID, -1) != -1; // -1 indicates no user logged in
    }

    // Method to retrieve logged in user information
    public Users getUser() {
        int id = sharedPreferences.getInt(KEY_USER_ID, -1);
        String name = sharedPreferences.getString(KEY_USER_NAME, null);
        String email = sharedPreferences.getString(KEY_USER_EMAIL, null);
        String phone = sharedPreferences.getString(KEY_USER_PHONE, null);

        // Return user object based on stored preferences
        return new Users(id, name, email, null,phone); // Replace null with appropriate password handling if needed
    }

    // Method to log out user (clear session)
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
