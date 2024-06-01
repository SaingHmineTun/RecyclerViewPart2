package it.saimao.recyclerview2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.saimao.recyclerview2.adapters.FriendItemClickListener;
import it.saimao.recyclerview2.adapters.MainAdapter;
import it.saimao.recyclerview2.databinding.ActivityMainBinding;
import it.saimao.recyclerview2.model.FriendRequest;

public class MainActivity extends AppCompatActivity {

    private FriendRequest[] friendRequests = {
            new FriendRequest("Athen Cho Swe", R.drawable.athen_cho_swe),
            new FriendRequest("Chan Chan", R.drawable.chan_chan),
            new FriendRequest("D Phyo", R.drawable.d_phyo),
            new FriendRequest("Moe Moe", R.drawable.moe_moe),
            new FriendRequest("Phyu Phyu Kyaw Thein", R.drawable.phyu_phyu_kyaw_thein),
    };

    private ActivityMainBinding binding;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListener();
    }

    private void initListener() {
        binding.etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<FriendRequest> filterList = List.of(friendRequests);
                if (!s.toString().isBlank()) {
                    /*
                    filter
                    map
                     */
                    filterList = filterList.stream().filter(friendRequest ->
                            friendRequest.name().contains(s.toString())
                    ).collect(Collectors.toList());
                }
                mainAdapter.setFriendRequests(filterList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mainAdapter.setItemClickListener(new FriendItemClickListener() {
            @Override
            public void onConfirm(FriendRequest friendRequest) {
                Toast.makeText(MainActivity.this, friendRequest.name() + "\nOn Confirm Clicked!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDelete(String friendName) {
                Toast.makeText(MainActivity.this, friendName + "\nOn Delete Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUi() {
        mainAdapter = new MainAdapter();
        mainAdapter.setFriendRequests(List.of(friendRequests));
        binding.rvFriends.setAdapter(mainAdapter);
        binding.rvFriends.setLayoutManager(new LinearLayoutManager(this));
    }
}