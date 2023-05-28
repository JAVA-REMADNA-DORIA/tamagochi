package com.example.tamagopersona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tamagopersona.Hangman;
import com.example.tamagopersona.RockPaperScissorsGame;
import com.example.tamagopersona.Shop;
import com.example.tamagopersona.Wallet;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private static TextView money;
    private ImageView tamago;
    private static TextView happiness;
    private TextView hungry;
    private TextView state;
    private ImageButton shopButton;
    private ImageButton gameOneButton;
    private ImageButton gameTwoButton;

    static int happinessValue = 10;
    public static int hungryValue = 10;
    static String stateValue = "Morgana va bien";
    private static final int HANGMAN_REQUEST_CODE = 1;
    private static final int ROCKPAPERSCISSORS_REQUEST_CODE = 2;
    private static final int SHOP_REQUEST_CODE = 3;

    private Wallet wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tamago = findViewById(R.id.tamago);
        this.happiness = findViewById(R.id.happiness);
        this.hungry = findViewById(R.id.hungry);
        this.money = findViewById(R.id.money);
        this.state = findViewById(R.id.state);
        this.shopButton = findViewById(R.id.shop);
        this.gameOneButton = findViewById(R.id.gameOne);
        this.gameTwoButton = findViewById(R.id.gameTwo);

        //initialisation du porte monnaie
        wallet = new Wallet(0);
        updateMoney();

        //ouverture du shop
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop();
            }
        });

        //ouverture du hangman
        gameOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseHungry(2); // Décrémente la faim avant le démarrage de l'activité
                openGameOne();
            }
        });

        //ouverture du pierre papier ciseaux
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

    //fct pour ouvrir le shop
    private void openShop() {
        Intent intent = new Intent(MainActivity.this, Shop.class);
        startActivityForResult(intent, SHOP_REQUEST_CODE);
    }

    //fct pour ouvrir le hangman
    private void openGameOne() {
        Intent intent = new Intent(MainActivity.this, Hangman.class);
        startActivityForResult(intent, HANGMAN_REQUEST_CODE);
    }

    //ouverture du pierre papier ciseaux
    private void openGameTwo() {
        Intent intent = new Intent(MainActivity.this, RockPaperScissorsGame.class);
        startActivityForResult(intent, ROCKPAPERSCISSORS_REQUEST_CODE);
    }

    //fct pour diminuer la jauge de faim
    private void increaseHungry(int value) {
        hungryValue += value;
        if (hungryValue > 20) {
            hungryValue = 20;
        }
        updateHungry();
        updateState();
    }

    //fct pour augmenter la jauge faim
    private void decreaseHungry(int value) {
        hungryValue -= value;
        if (hungryValue < 0) {
            hungryValue = 0;
        }
        updateHungry();
        updateState();
    }

    //fct pour mettre à jour la jauge de faim
    private void updateHungry() {
        String updatedHungryMsg = getString(R.string.hungry_msg, hungryValue);
        hungry.setText(updatedHungryMsg);
    }

    //fct pour augmenter la jauge de bonheur
    void increaseHappiness(int value) {
        happinessValue += value;
        if (happinessValue > 20) {
            happinessValue = 20;
        }
        updateHappiness();
        updateState();
    }

    //fct pour diminuer la jauge de bonheur
    void decreaseHappiness(int value) {
        happinessValue -= value;
        if (happinessValue < 0) {
            happinessValue = 0;
        }
        updateHappiness();
        updateState();
    }

    //fct pour mettre à jour la jauge de bonheur
    static void updateHappiness() {
        String updatedHappinessMsg = "Jauge de Bonheur: " + happinessValue + "/20";
        happiness.setText(updatedHappinessMsg);
    }

    //fct pour mettre à jour le porte-monnaie
    static void updateMoney() {
        String updatedMoneyMsg = Wallet.getMoney() + "ћ";
        money.setText(updatedMoneyMsg);
    }

    //message d'état
    private void updateState() {
        String stateMsg;

        if (hungryValue == 0 && happinessValue > 0 && happinessValue <= 20) {
            stateMsg = "Morgana est mort de faim";
        } else if (hungryValue <= 5 && happinessValue > 5 && happinessValue < 20) {
            stateMsg = "Morgana est affamé";
        } else if (hungryValue > 5 && hungryValue < 15 && happinessValue > 5 && happinessValue < 15) {
            stateMsg = "Morgana va bien";
        } else if (hungryValue >= 15 && happinessValue > 5 && happinessValue < 20) {
            stateMsg = "Morgana a bien mangé";
        } else if (hungryValue == 20 && happinessValue > 5 && happinessValue < 20) {
            stateMsg = "Morgana est boulimique";
        } else if (happinessValue == 0 && hungryValue > 0 && hungryValue <= 20) {
            stateMsg = "Morgana s'est suicidé";
        } else if (happinessValue <= 5 && hungryValue > 5 && hungryValue < 20) {
            stateMsg = "Morgana est dépressif";
        } else if (happinessValue >= 15 && happinessValue < 20 && hungryValue > 0 && hungryValue < 15) {
            stateMsg = "Morgana est heureux";
        } else if (happinessValue == 20 && hungryValue > 5 && hungryValue < 20) {
            stateMsg = "Morgana est au septième ciel";
        } else if (hungryValue >= 0 && hungryValue <= 5 && happinessValue >= 0 && happinessValue <= 5) {
            stateMsg = "Morgana est affamé et dépressif";
        } else if (hungryValue >= 15 && hungryValue < 20 && happinessValue >= 15 && happinessValue < 20) {
            stateMsg = "Morgana a bien mangé et est au septième ciel";
        } else if (hungryValue == 20 && happinessValue == 20) {
            stateMsg = "Morgana est boulimique et au septième ciel";
        } else if (hungryValue == 0 && happinessValue == 0) {
            stateMsg = "Vous avez assassiné Morgana";
        } else {
            stateMsg = getString(R.string.state_msg);
        }

        state.setText(stateMsg);
    }

    //condition de modification des jauges et du porte-monnaie selon la victoire ou la défaite lors d'un jeu ainsi que la modification des jauges si l'item a un effet
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == HANGMAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                increaseHappiness(2);
                updateHappiness();
                wallet.addMoney(10);
                updateMoney();

                int hungerChange = data.getIntExtra("HUNGER_CHANGE", 0);
                decreaseHungry(hungerChange);
            } else if (resultCode == RESULT_CANCELED && data != null) {
                decreaseHappiness(2);
                updateHappiness();
            }
        } else if (requestCode == ROCKPAPERSCISSORS_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                increaseHappiness(2);
                updateHappiness();
                wallet.addMoney(10);
                updateMoney();

                int hungerChange = data.getIntExtra("HUNGER_CHANGE", 0);
                decreaseHungry(hungerChange);
            } else if (resultCode == RESULT_CANCELED && data != null) {
                decreaseHappiness(2);
                updateHappiness();
            }
        } else if (requestCode == SHOP_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                String itemName = data.getStringExtra("ITEM_NAME");
                String itemImgName = data.getStringExtra("ITEM_IMGNAME");

                if (itemName.equals("Miam")) {
                    increaseHungry(1);
                    updateHungry();
                } else if (itemName.equals("Xanax")) {
                    increaseHappiness(1);
                    updateHappiness();
                } else if (itemName.equals("Truck Sama à la rescousse")) {
                    happinessValue = 10;
                    hungryValue = 10;
                    updateHappiness();
                    updateHungry();
                }
            }
        }
    }

}
