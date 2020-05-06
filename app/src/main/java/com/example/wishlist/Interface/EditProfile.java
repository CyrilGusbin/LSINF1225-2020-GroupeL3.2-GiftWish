package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.wishlist.Backend.FeedReaderContract;
import com.example.wishlist.R;

import java.text.SimpleDateFormat;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.loadPseudo);
        textView.setText("Votre Pseudo actuel :" +message);
    }
}