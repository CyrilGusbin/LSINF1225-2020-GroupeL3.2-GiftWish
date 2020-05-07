//Ceci est l'interface de connexion.


package com.example.wishlist.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.example.wishlist.Backend.Article;
import com.example.wishlist.Backend.DBManager;
import com.example.wishlist.Backend.USER;
import com.example.wishlist.DAO.UserDAO;
import com.example.wishlist.R;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {

    private Button play;
    private TextView lblArticles;
    private DBManager dbManager;
    private final AppCompatActivity activity = MainActivity.this;
    UserDAO userDao;

    @Override
    public void onBackPressed() {
        //doNothing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lorsque l'on clique sur le bouton "S'inscrire", l'application nous déplace vers l'interface d'inscription."
        this.play = findViewById(R.id.inscription2);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Register.class);
                startActivity(otherActivity);
            }
        });

        //Lorsque l'on clique une fois nos coordonnées entrées sur le bouton de connexion, si les données entrées sont correctes, l'application nous déplace vers l'interface du menu principal.
        this.play = findViewById(R.id.connexion);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Menu.class);
                Intent resetActivity = new Intent(getApplicationContext(), MainActivity.class);

                USER user = new USER();
                EditText pseudo = findViewById(R.id.pseudo);
                EditText mdp = findViewById(R.id.mdp);
                String pseudo_bis= pseudo.getText().toString();
                String mdp_bis= mdp.getText().toString();
                Log.e("mdp", mdp_bis);
                boolean ret = userDao.CreateFirstUser(user ,pseudo_bis, mdp_bis);
                Log.e("tentative", "connect");
                if (ret){
                    otherActivity.putExtra(EXTRA_MESSAGE, pseudo_bis);
                    startActivity(otherActivity);
                    finish();
                }
                else{
                    resetActivity.putExtra(EXTRA_MESSAGE, "Pseudo et/ou mot de passe invalides");
                    startActivity(resetActivity);
                    finish();
                }

            }
        });


        Intent intent= getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.error);
        textView.setText(message);
        userDao = new UserDAO(activity);

    }

}
