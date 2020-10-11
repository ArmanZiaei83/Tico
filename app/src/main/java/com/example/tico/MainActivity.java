package com.example.tico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button [][] buttons = new Button[3][3];

    private Button resetGame ;
    private TextView pl1 ;
    private TextView pl2 ;
    private boolean player1Turn = true ;
    private TextView playerOneScore ;
    private TextView playerTwoScore ;
    private TextView status ;
    private int playerOnePoint = 0 ;
    private int playerTwoPoint = 0 ;
    private int roundCount ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pl1 = (TextView) findViewById(R.id.pl1);
        pl2 = (TextView) findViewById(R.id.pl2) ;
        status = (TextView) findViewById(R.id.status);
        resetGame = (Button) findViewById(R.id.refreshGame);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);

        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ;  j < 3 ; j++){
                String buttonsID = "btn_" + i + j ;
                int resID = getResources().getIdentifier(buttonsID , "id" , getPackageName());
                buttons [i][j] = findViewById(resID);
                buttons [i][j].setOnClickListener(this);
            }
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGam();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
        }else {
            ((Button) v).setText("O");
        }
        roundCount++ ;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
                status.setText("Winner : Player 1 !");
            }else{
                player2Wins();
                status.setText("Winner : Player 2");
            }
        }else if (roundCount == 9){
            draw();
        }else{
            player1Turn = !player1Turn ;
        }
    }
    private  boolean checkForWin (){
        String [][] field = new String[3][3];

        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                field [i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0 ; i < 3 ; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
            && !field[i][0].equals("")){
                return true ;
            }
        }
        for (int i = 0 ; i < 3 ; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true ;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true ;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true ;
        }
        return false;
    }
    private void player1Wins(){
        playerOnePoint++ ;
        updatePointsText();
        status.setText("Winner : Player 1 !");
        Toast.makeText(this , "Player One Wins !!" , Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void player2Wins(){
        playerTwoPoint++ ;
        updatePointsText();
        status.setText("Winner : Player 2");
        Toast.makeText(this , "Player Two Wins !!" , Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private  void draw(){
        Toast.makeText(this , "Draw !" , Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText () {
        playerOneScore.setText(String.valueOf(playerOnePoint));
        playerTwoScore.setText(String.valueOf(playerTwoPoint));
    }
    private void resetBoard (){
        for (int i=  0 ; i < 3 ; i++){
            for (int j = 0 ; j <3 ; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0 ;
        player1Turn = true ;
        status.setText("");
    }
    private  void resetGam(){
        
        playerOnePoint =0 ;
        playerTwoPoint =0 ;
        updatePointsText();
        resetBoard();

    }
}