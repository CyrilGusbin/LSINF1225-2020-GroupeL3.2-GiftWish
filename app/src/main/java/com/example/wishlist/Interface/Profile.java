//Ceci est l'interface du profil.

package com.example.wishlist.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.wishlist.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Profile extends AppCompatActivity {

    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Affiche le pseudo de l'user connecté.
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.pseudo_user);
        textView.setText(message);

        //Permet à l'utilisateur de modifier son profil.
        this.play = findViewById(R.id.edit_profile);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(otherActivity);
            }
        });
    }
}
