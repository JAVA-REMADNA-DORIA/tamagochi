package com.example.tamagopersona;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RockPaperScissorsGame extends AppCompatActivity {

    private ImageView playerChoiceImageView, computerChoiceImageView;
    private TextView resultTextView;
    int nino2,money;
    private Button rockButton, paperButton, scissorsButton;

    private int playerScore, computerScore;

    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors_game);
        nino2 = MainActivity.happinessValue;
        money = Wallet.getMoney();
        playerChoiceImageView = findViewById(R.id.player_choice_image_view);
        computerChoiceImageView = findViewById(R.id.computer_choice_image_view);
        resultTextView = findViewById(R.id.result_text_view);
        rockButton = findViewById(R.id.rock_button);
        paperButton = findViewById(R.id.paper_button);
        scissorsButton = findViewById(R.id.scissors_button);

        //bouton pierre
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(ROCK);
            }
        });

        //bouton papier
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(PAPER);
            }
        });

        //bouton ciseaux
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(SCISSORS);
            }
        });
    }

    //boucle du jeu
    private void playGame(int playerChoice) {
        int computerChoice = generateComputerChoice();

        setChoiceImage(playerChoiceImageView, playerChoice);
        setChoiceImage(computerChoiceImageView, computerChoice);

        int result = determineResult(playerChoice, computerChoice);

        if (result == 0) {
            resultTextView.setText(R.string.result_draw);
        } else if (result == 1) {
            resultTextView.setText(R.string.result_win);
            playerScore++;
        } else {
            resultTextView.setText(R.string.result_lose);
            computerScore++;
        }

        displayScores();

        if (playerScore == 3 || computerScore == 3) {
            endGame();
        }
    }

    //choix aléatoire de l'ordinateur
    private int generateComputerChoice() {
        Random random = new Random();
        return random.nextInt(3);
    }

    //image apparaissant lors du choix de chaque joueur
    private void setChoiceImage(ImageView imageView, int choice) {
        switch (choice) {
            case ROCK:
                imageView.setImageResource(R.drawable.rock);
                break;
            case PAPER:
                imageView.setImageResource(R.drawable.paper);
                break;
            case SCISSORS:
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
    }

    //condition de victoire
    private int determineResult(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            return 0;
        } else if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
                (playerChoice == PAPER && computerChoice == ROCK) ||
                (playerChoice == SCISSORS && computerChoice == PAPER)) {
            return 1; // victoire
        } else {
            return -1; // défaite
        }
    }

    //score
    private void displayScores() {
        Toast.makeText(this, "Joueur: " + playerScore + " vs Morgana: " + computerScore, Toast.LENGTH_SHORT).show();
    }

    //fin de jeu si 3 victoires et réinitialisation
    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fin de partie");

        if (playerScore == 3) {
            nino2 += 2;
            Wallet.addMoney(10);
            builder.setMessage("Gagné!");
        } else {
            nino2 -= 2;
            builder.setMessage("Perdu!");

        }
        MainActivity.happinessValue = nino2;
        MainActivity.updateHappiness();
        MainActivity.updateMoney();
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        playerScore = 0;
        computerScore = 0;
    }
}