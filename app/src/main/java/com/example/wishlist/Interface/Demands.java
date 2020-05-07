package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.wishlist.Backend.MyAdapterDemands;
import com.example.wishlist.Backend.MyAdapterWishes;
import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Demands extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private final AppCompatActivity activity = Demands.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demands);
        userDao= new UserDAO(activity);
        Intent intent= getIntent();
        final String pseudo = intent.getStringExtra(EXTRA_MESSAGE);
        String[] myDataset= userDao.get_demands(pseudo);
        recyclerView = findViewById(R.id.my_recycler_view4);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterDemands(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
}
