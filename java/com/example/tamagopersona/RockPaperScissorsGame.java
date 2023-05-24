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
    private Button rockButton, paperButton, scissorsButton;

    private int playerScore, computerScore;

    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors_game);

        playerChoiceImageView = findViewById(R.id.player_choice_image_view);
        computerChoiceImageView = findViewById(R.id.computer_choice_image_view);
        resultTextView = findViewById(R.id.result_text_view);
        rockButton = findViewById(R.id.rock_button);
        paperButton = findViewById(R.id.paper_button);
        scissorsButton = findViewById(R.id.scissors_button);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(ROCK);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(PAPER);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(SCISSORS);
            }
        });
    }

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

    private int generateComputerChoice() {
        Random random = new Random();
        return random.nextInt(3);
    }

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

    private int determineResult(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            return 0; // Draw
        } else if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
                (playerChoice == PAPER && computerChoice == ROCK) ||
                (playerChoice == SCISSORS && computerChoice == PAPER)) {
            return 1; // Win
        } else {
            return -1; // Lose
        }
    }

    private void displayScores() {
        Toast.makeText(this, "Player: " + playerScore + " vs Computer: " + computerScore, Toast.LENGTH_SHORT).show();
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");

        if (playerScore == 3) {
            builder.setMessage("Victory!");
            setResult(RESULT_OK);
        } else {
            builder.setMessage("Defeat!");
            setResult(RESULT_CANCELED);
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_OK);
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