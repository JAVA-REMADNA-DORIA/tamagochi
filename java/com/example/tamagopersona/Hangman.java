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
    private TextView wordLabel;
    private EditText letterField;
    private Button guessButton;
    private TextView attemptsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

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

    private void guessLetter() {
        String letter = letterField.getText().toString().toLowerCase();
        letterField.getText().clear();

        if (letter.length() != 1) {
            showAlert("Please enter a single letter.");
            return;
        }

        if (!Character.isLetter(letter.charAt(0))) {
            showAlert("Please enter a valid letter.");
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

        wordLabel.setText("Word: " + guessedWord.toString());
    }

    private void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    private void endGame(boolean won) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");

        Intent intent = new Intent();
        if (won) {
            builder.setMessage("Congratulations! You won!");
        } else {
            builder.setMessage("Game over. You lost.");
        }

        setResult(RESULT_CANCELED, intent); // Utilisez RESULT_CANCELED lorsque le joueur perd
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Hangman.this, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void resetGame() {
        secretWord = "donne moi vingt cellya";
        guessedWord = null;
        attemptsLeft = 6;
        updateWordLabel();
        updateAttemptsLabel();
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Input");
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}