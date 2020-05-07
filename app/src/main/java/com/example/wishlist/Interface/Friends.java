package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wishlist.Backend.MyAdapterFriend;
import com.example.wishlist.Backend.MyAdapterWishes;
import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Friends extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = Friends.this;
    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_friendlist(pseudo);
        recyclerView = findViewById(R.id.my_recycler_view3);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterFriend(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
}