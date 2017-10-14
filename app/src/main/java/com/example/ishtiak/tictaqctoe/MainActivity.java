package com.example.ishtiak.tictaqctoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int[][] winnerLocations = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},
        {2,4,6}};
    String msg = "";
    boolean gameOver = false;
    LinearLayout gameOverLayout;
    TextView msgtext;
    Button playAgainBtn;
    GridLayout gridLayout;
    final Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gameOverLayout = (LinearLayout) findViewById(R.id.linearLayoutForGameOver);
        msgtext = (TextView) findViewById(R.id.msgText);
        playAgainBtn = (Button) findViewById(R.id.playButton);
        gameOverLayout.setVisibility(View.INVISIBLE);




        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOverLayout.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.VISIBLE);
                gameOver = false;

                for (int i=0; i<gameState.length; i++){
                    gameState[i] = 2;

                }
                for (int i =0; i<gridLayout.getChildCount(); i++){
                    ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

                }
            }
        });



   }


    public void actionMethod(View view) {
        ImageView imageView = (ImageView) view;
        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if (gameState[tappedLocation] == 2 && gameOver == false) {
                gameState[tappedLocation] = activePlayer;
                imageView.setTranslationY(-3000f);
                if (activePlayer == 0) {
                    ((ImageView) view).setImageResource(R.drawable.green);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    ((ImageView) view).setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
            imageView.animate().translationYBy(3000f).setDuration(300);

        }
        for(int[] winningPosition : winnerLocations){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]  &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] !=2 ){

                if (activePlayer == 0){

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            msg = "The red is winner";
                            gridLayout.setVisibility(View.INVISIBLE);
                            gameOverLayout.setVisibility(View.VISIBLE);
                            msgtext.setText(msg);
                        }
                    }, 1000);

                }
                else if(activePlayer == 1){

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            msg = "The green is winner";
                            gridLayout.setVisibility(View.INVISIBLE);
                            gameOverLayout.setVisibility(View.VISIBLE);
                            msgtext.setText(msg);
                        }
                    }, 1000);

                }
               gameOver = true;
            }

        }



    }



}



