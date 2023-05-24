package com.example.tamagopersona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView money;
    private ImageView tamago;
    private TextView happiness;
    private TextView hungry;
    private ImageButton shopButton;
    private ImageButton gameOneButton;
    private ImageButton gameTwoButton;

    private int happinessValue = 10;
    private int hungryValue = 10;
    private static final int HANGMAN_REQUEST_CODE = 1;
    private static final int ROCKPAPERSCISSORS_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tamago = findViewById(R.id.tamago);
        this.happiness = findViewById(R.id.happiness);
        this.hungry = findViewById(R.id.hungry);
        this.money = findViewById(R.id.money);
        this.shopButton = findViewById(R.id.shop);
        this.gameOneButton = findViewById(R.id.gameOne);
        this.gameTwoButton = findViewById(R.id.gameTwo);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseHungry(2); // Décrémente la faim avant le démarrage de l'activité
                openShop();
            }
        });

        gameOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseHungry(2); // Décrémente la faim avant le démarrage de l'activité
                openGameOne();
            }
        });

        gameTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseHungry(2); // Décrémente la faim avant le démarrage de l'activité
                openGameTwo();
            }
        });

        updateHappiness();
        updateHungry();
    }

    private void openShop() {
        Intent intent = new Intent(MainActivity.this, Shop.class);
        startActivity(intent);
    }

    private void openGameOne() {
        Intent intent = new Intent(MainActivity.this, Hangman.class);
        startActivityForResult(intent, HANGMAN_REQUEST_CODE);
    }

    private void openGameTwo() {
        Intent intent = new Intent(MainActivity.this, RockPaperScissorsGame.class);
        startActivityForResult(intent, ROCKPAPERSCISSORS_REQUEST_CODE);
    }

    private void decreaseHungry(int value) {
        hungryValue -= value;
        if (hungryValue < 0) {
            hungryValue = 0;
        }
        updateHungry();
    }

    private void updateHungry() {
        String updatedHungryMsg = getString(R.string.hungry_msg, hungryValue);
        hungry.setText(updatedHungryMsg);
    }

    private void increaseHappiness(int value) {
        happinessValue += value;
        if (happinessValue > 20) {
            happinessValue = 20;
        }
        updateHappiness();
    }

    private void decreaseHappiness(int value) {
        happinessValue -= value;
        if (happinessValue < 0) {
            happinessValue = 0;
        }
        updateHappiness();
    }

    private void updateHappiness() {
        String updatedHappinessMsg = getString(R.string.happiness_msg, happinessValue);
        happiness.setText(updatedHappinessMsg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == HANGMAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // Le jeu Hangman a été terminé avec succès
                increaseHappiness(2);
                updateHappiness();

                // Mettre à jour la faim en fonction de la valeur de HUNGER_CHANGE
                int hungerChange = data.getIntExtra("HUNGER_CHANGE", 0);
                decreaseHungry(hungerChange);
            } else if (resultCode == RESULT_CANCELED && data != null) {
                // Le jeu Hangman a été annulé ou terminé sans succès
                decreaseHappiness(2);
                updateHappiness();
            }
        } else if (requestCode == ROCKPAPERSCISSORS_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // Le jeu Rock Paper Scissors a été terminé avec succès
                increaseHappiness(2);
                updateHappiness();

                // Mettre à jour la faim en fonction de la valeur de HUNGER_CHANGE
                int hungerChange = data.getIntExtra("HUNGER_CHANGE", 0);
                decreaseHungry(hungerChange);
            } else if (resultCode == RESULT_CANCELED && data != null) {
                // Le jeu Rock Paper Scissors a été annulé ou terminé sans succès
                decreaseHappiness(2);
                updateHappiness();
            }
        }
    }
}
