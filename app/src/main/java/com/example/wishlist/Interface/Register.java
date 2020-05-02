package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wishlist.Backend.USER;
import com.example.wishlist.R;
import com.example.wishlist.DAO.UserDAO;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Register extends AppCompatActivity {
    private final AppCompatActivity activity = Register.this;
    UserDAO userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent= getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.error);
        textView.setText(message);
        userDao=new UserDAO(activity);
    }
    public void CreateUser(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Intent intent2 = new Intent(this, Register.class);
        EditText pseudo = (EditText) findViewById(R.id.pseudo);
        EditText mdp = (EditText) findViewById(R.id.MDP);
        String pseudo_bis= pseudo.getText().toString();
        String mdp_bis= mdp.getText().toString();
        if(userDao.AddUserDB(pseudo_bis, mdp_bis)){
            startActivity(intent);
        }
        else{
            intent2.putExtra(EXTRA_MESSAGE, "Pseudo déjà utilisé");
            startActivity(intent2);

        }

}
}
