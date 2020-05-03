//Ceci est l'interface du menu

package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wishlist.R;

public class Menu extends AppCompatActivity {

    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Lorsque  l'on clique sur le bouton "Mes WishLists", l'application nous déplace vers l'interface "Mes WishLists".
        this.play = findViewById(R.id.wishlist);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), WishLists.class);
                startActivity(otherActivity);
             }
        });

        //Lorsque l'on clique sur le bouton "Mes amis", l'application nous déplace vers l'interface "Mes amis".
        this.play = findViewById(R.id.amis);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Friends.class);
                startActivity(otherActivity);
            }
        });

        //Lorsque l'on clique sur le bouton "Mon Profil", l'application nous déplace vers l'interface "Mon profil".
        this.play = findViewById(R.id.profil);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Profile.class);
                startActivity(otherActivity);
            }
        });

        //Lorsque l'on clique sur le bouton "Déconnexion", l'application nous déplace vers l'interface "Connexion".
        this.play = findViewById(R.id.deconnexion);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
};
