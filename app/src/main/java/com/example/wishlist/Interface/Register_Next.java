//TODO : RENDRECETTE PARTIE FONCTIONNELLE.
package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Register_Next extends AppCompatActivity {

    //UserDAO userDao;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__next);

        this.play = findViewById(R.id.create_account);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Menu.class);
                startActivity(otherActivity);
            }
        });

    }

    //TODO : INSERER DANS LA BASE DE DONNEE SON NOM PRENOM ET AGE LORSQU'IL CREE SON COMPTE.
    //public void CreateData(View view){
        //Intent intent = new Intent(this, Menu.class);

        //EditText nom = findViewById(R.id.set_nom);
        //EditText prenom = findViewById(R.id.set_prenom);
        //EditText age = findViewById(R.id.setupAge);
        //String nom_bis= nom.getText().toString();
        //String prenom_bis= prenom.getText().toString();
        //String age_bis= age.getText().toString();

        //userDao.AddDataDB("test", "test", "test");

        //startActivity(intent);
    //}
}


