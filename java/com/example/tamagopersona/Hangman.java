package com.example.tamagopersona;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hangman extends AppCompatActivity {

    private String secretWord = "donne moi vingt cellya";
    private StringBuilder guessedWord;
    private int attemptsLeft = 6;
    int nino;
    int nino2;
    private TextView wordLabel;
    private EditText letterField;
    private Button guessButton;
    private TextView attemptsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        nino = MainActivity.hungryValue;
        nino2 = MainActivity.happinessValue;
        wordLabel = findViewById(R.id.wordLabel);
        letterField = findViewById(R.id.letterField);
        guessButton = findViewById(R.id.guessButton);
        attemptsLabel = findViewById(R.id.attemptsLabel);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessLetter();
            }
        });

        updateWordLabel();
        updateAttemptsLabel();
    }

    //trouver la lettre
    private void guessLetter() {
        String letter = letterField.getText().toString().toLowerCase();
        letterField.getText().clear();

        if (letter.length() != 1) {
                showAlert("N'entrez qu'une lettre");
            return;
        }

        if (!Character.isLetter(letter.charAt(0))) {
            showAlert("Entrez une lettre.");
            return;
        }

        char guessedLetter = letter.charAt(0);
        boolean found = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (Character.toLowerCase(secretWord.charAt(i)) == guessedLetter) {
                guessedWord.setCharAt(i, secretWord.charAt(i));
                found = true;
            }
        }

        if (!found) {
            attemptsLeft--;
        }

        updateWordLabel();
        updateAttemptsLabel();

        if (attemptsLeft == 0) {
            endGame(false);
        } else if (guessedWord.toString().equals(secretWord)) {
            endGame(true);
        }
    }

    //apparition des lettres dans la phrase
    private void updateWordLabel() {
        if (guessedWord == null) {
            guessedWord = new StringBuilder(secretWord.length());
            for (int i = 0; i < secretWord.length(); i++) {
                if (Character.isLetter(secretWord.charAt(i))) {
                    guessedWord.append('_');
                } else {
                    guessedWord.append(secretWord.charAt(i));
                }
            }
        }

        wordLabel.setText("Phrase: " + guessedWord.toString());
    }

    //tentatives restantes
    private void updateAttemptsLabel() {
        attemptsLabel.setText("Chances: " + attemptsLeft);
    }

    //fin du jeu
    private void endGame(boolean won) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Partie finie");

        if (won) {
            nino2 += 2;
            Wallet.addMoney(10);
            builder.setMessage("Félicitation ! Du coup c'est oui ou c'est non ? ^^");
        } else {
            nino2 -= 2;
            builder.setMessage("Perdu.");
        }

        MainActivity.happinessValue = nino2;
        MainActivity.updateMoney();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.hungryValue = nino;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //reinitialisation de la boucle de jeu
    private void resetGame() {
        secretWord = "donne moi vingt cellya";
        guessedWord = null;
        attemptsLeft = 6;
        updateWordLabel();
        updateAttemptsLabel();
    }

    //si erreur du joueur
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrée invalide");
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}