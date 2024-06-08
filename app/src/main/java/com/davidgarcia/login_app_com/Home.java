package com.davidgarcia.login_app_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {
    TextView helloText;
    String username;
    private boolean player1Turn = true;
    private Button[][] buttons = new Button[3][3];
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponents();
        checkIntents();
        helloUser( this.username);

        game = new Game();
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" +String.valueOf(i+1)+String.valueOf(j+1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }
    }
    private void initComponents(){
        helloText=findViewById(R.id.lbl_home_hello);
    }
    private void checkIntents(){
        Intent intent = getIntent();
        if(intent!=null && intent.getExtras()!=null){
            this.username=intent.getStringExtra("EXTRA_USERNAME");;
        }
    }
    private void helloUser(String username){
        String hello = getString(R.string.msg_home_hello)+" "+username+".";
        helloText.setText(hello);
    }
    public void onGridButtonClick(View view) {
        Button button = (Button) view;
        String buttonID = getResources().getResourceEntryName(button.getId());
        int row = Character.getNumericValue(buttonID.charAt(buttonID.length() - 2)) - 1;
        int col = Character.getNumericValue(buttonID.charAt(buttonID.length() - 1)) - 1;

        if (game.makeMove(row, col)) {
            button.setText(game.isPlayer1Turn() ? "O" : "X");
            if (game.isGameFinished()) {
                if (game.isBoardFull()) {
                    showToast("Draw!");
                } else {
                    showToast(game.isPlayer1Turn() ? "Player 2 (O) wins!" : "Player 1 (X) wins!");
                }
                //disableButtons();
            }
        }
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void resetGame(View view) {
        game.resetBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}