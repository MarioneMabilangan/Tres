package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameIA extends AppCompatActivity {
    boolean gameActive = true;

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;

            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                status.setText("O's Turn - Tap to play");

            }
            img.animate().translationYBy(1000f).setDuration(300);
            checkGameState();
            if(gameActive && activePlayer == 1) {
                aiPlay();
            }
        }
    }

    private void aiPlay() {
        int aiMove = (int) (Math.random() * 9);
        while (gameState[aiMove] != 2) {
            aiMove = (int) (Math.random() * 9);
        }

        gameState[aiMove] = activePlayer;

        ImageView aiImg = findViewById(getResources().getIdentifier("imageView" + aiMove, "id", getPackageName()));
        aiImg.setImageResource(R.drawable.o);
        aiImg.setTranslationY(-1000f);
        aiImg.animate().translationYBy(1000f).setDuration(300);

        activePlayer = 0;
        TextView status = findViewById(R.id.status);

        status.setText("Te toca");

        checkGameState();
    }

    private void checkGameState() {
        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                String winnerStr;

                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    startActivity(new Intent(MainGameIA.this, WinPlayer.class));
                } else {
                    startActivity(new Intent(MainGameIA.this, WinIA.class));
                }
                TextView status =
                        findViewById(R.id.status);

            }
        }
        if (counter == 9 && flag == 0) {
            TextView status = findViewById(R.id.status);
            startActivity(new Intent(MainGameIA.this, Tutorial.class));
        }
    }



    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Te toca");
    }

    public void makeAIMove() {
        int aiMove = (int) (Math.random() * 9);
        while (gameState[aiMove] != 2) {
            aiMove = (int) (Math.random() * 9);
        }

        gameState[aiMove] = activePlayer;

        ImageView aiImg = findViewById(getResources().getIdentifier("imageView" + aiMove, "id", getPackageName()));
        aiImg.setImageResource(R.drawable.o);
        aiImg.animate().translationYBy(1000f).setDuration(300);

        activePlayer = 1;
        TextView status = findViewById(R.id.status);
        status.setText("Te toca");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        getSupportActionBar().hide();
    }
}