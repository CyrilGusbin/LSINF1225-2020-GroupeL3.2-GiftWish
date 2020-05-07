package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;
import com.example.wishlist.Interface.item_creation;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class wishes_intermediate extends AppCompatActivity {
    private final AppCompatActivity activity = wishes_intermediate.this;
    UserDAO userdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishes_intermediate);
        Intent intent= getIntent();
        String nwl= intent.getStringExtra(WishLists.EXTRA_MESSAGE2);
        TextView nomWL = findViewById(R.id.nwl);
        nomWL.setText(nwl);
        Intent intent2=getIntent();
        String feedback= intent2.getStringExtra(item_creation.EXTRA_MESSAGE4);
        TextView feedB= findViewById(R.id.feed_create_item);
        feedB.setText(feedback);
    }
    public void go_to_items(View view){
        Intent intent= getIntent();
        String idwl= intent.getStringExtra(EXTRA_MESSAGE);
        Intent intent2= new Intent(this, Wishes.class);
        intent2.putExtra(EXTRA_MESSAGE, idwl);
        startActivity(intent2);
        finish();
    }
    public void go_to_item_creation(View view){
        Intent intent= getIntent();
        String idwl= intent.getStringExtra(EXTRA_MESSAGE);
        Intent intent2= new Intent(this, item_creation.class);
        intent2.putExtra(EXTRA_MESSAGE, idwl);
        startActivity(intent2);
        finish();

    }

}
