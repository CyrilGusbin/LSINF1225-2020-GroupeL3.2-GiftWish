package com.example.wishlist.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.example.wishlist.Backend.USER;
import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {
    private final AppCompatActivity activity = MainActivity.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.error);
        textView.setText(message);
        userDao = new UserDAO(activity);

    }


    public void goregister(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void connect(View view){
        Intent intent2 = new Intent(this, Menu.class);
        Intent intent = new Intent(this, MainActivity.class);
        USER user = new USER();
        EditText pseudo = findViewById(R.id.pseudo);
        EditText mdp = findViewById(R.id.mdp);
        String pseudo_bis= pseudo.getText().toString();
        String mdp_bis= mdp.getText().toString();
        Log.e("mdp", mdp_bis);
        boolean ret = userDao.CreateFirstUser(user ,pseudo_bis, mdp_bis);
        Log.e("tentative", "connect");
        if (ret){
            startActivity(intent2);
        }
        else{
            intent.putExtra(EXTRA_MESSAGE, "Pseudo et/ou mot de passe invalides");
            startActivity(intent);
        }

    }
}
