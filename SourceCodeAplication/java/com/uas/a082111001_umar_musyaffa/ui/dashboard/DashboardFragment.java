package com.uas.a082111001_umar_musyaffa.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uas.a082111001_umar_musyaffa.Items;
import com.uas.a082111001_umar_musyaffa.ItemsAdapter;
import com.uas.a082111001_umar_musyaffa.MainActivity;
import com.uas.a082111001_umar_musyaffa.R;
import com.uas.a082111001_umar_musyaffa.RequestHandler;
import com.uas.a082111001_umar_musyaffa.Routes;
import com.uas.a082111001_umar_musyaffa.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private List<Items> itemsList;
    private ItemsAdapter itemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button buttonLogout = root.findViewById(R.id.logout);
        GridView gridViewData = root.findViewById(R.id.gridViewData);
        itemsList = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(requireContext(), itemsList);
        gridViewData.setAdapter(itemsAdapter);

        fetchData();
        buttonLogout.setOnClickListener(v -> {

            // Perform logout
            SharedPrefManager.getInstance(requireContext()).logout();

            // Show a toast message
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Navigate to the login activity
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return root;
    }

    private void fetchData() {
        new Thread(() -> {
            try {
                RequestHandler requestHandler = new RequestHandler();
                String responseHandler = requestHandler.sendGet(Routes.ITEMS_URL);

                // Converting response to JSON object
                JSONObject obj = new JSONObject(responseHandler);
                JSONObject response = obj.getJSONObject("response");

                // If response is successful
                if (response.getString("status").equals("success")) {
                    JSONArray dataArray = response.getJSONArray("data");
                    itemsList.clear();
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject data = dataArray.getJSONObject(i);
                        Items items = new Items(
                                data.getInt("iditems"),
                                data.getString("cpuName"),
                                data.getString("namaBarang"),
                                data.getString("hargaBarang"),
                                data.getString("deskripsiBarang"),
                                data.getString("gambarBarang")
                        );
                        itemsList.add(items);
                    }

                    requireActivity().runOnUiThread(() -> itemsAdapter.notifyDataSetChanged());
                } else {
                    requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Connection Failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
