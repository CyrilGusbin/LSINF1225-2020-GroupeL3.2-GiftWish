package com.example.wishlist.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Backend.wishlist_intermediate;
import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class wishes_intermediate extends AppCompatActivity {
    public static final String EXTRA_MESSAGE2 = "";
    private final AppCompatActivity activity = wishes_intermediate.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userDao=new UserDAO(activity);
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
        Intent intent3=getIntent();
        String pseudo =intent3.getStringExtra(WishLists.EXTRA_MESSAGE3);
        Intent intent2= new Intent(this, item_creation.class);
        intent2.putExtra(EXTRA_MESSAGE, pseudo);
        startActivity(intent2);
        finish();

    }
    public void delete_wl(View view){
        Intent intent=getIntent();
        String pseudo= intent.getStringExtra(WishLists.EXTRA_MESSAGE3);
        String idwl= intent.getStringExtra(EXTRA_MESSAGE);
        Intent intent2=new Intent(this, wishlist_intermediate.class);
        intent2.putExtra(EXTRA_MESSAGE, pseudo);
        Log.e("id", idwl);
        intent2.putExtra(EXTRA_MESSAGE2, "Suppression effectuée");
        userDao.delete_wl(idwl);
        startActivity(intent2);
        finish();

    }

}
