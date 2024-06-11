package com.davidgarcia.login_app_com;

import java.util.Arrays;

public class Game {
    private String[][] board;
    private boolean player1Turn;
    private boolean gameFinished;
   // private int cPlays;
    private int cPlays;
    private int[][] lastPlaysPosX;
    private int[][] lastPlaysPosO;
    private int[] lastButtonO;

    public Game() {
        board = new String[3][3];
       lastPlaysPosX=new int[2][6];
        lastPlaysPosO=new int[2][6];
        cPlays=0;
        player1Turn = true;
        gameFinished = false;

        resetBoard();
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public String getCellContent(int row, int col) {
        return board[row][col];
    }

    public int[] makeMove(int row, int col) {
        int lastRow=-1;
        int lastCol=-1;
        if (board[row][col].equals("") && !gameFinished) {
            board[row][col] = player1Turn ? "X" : "O";
            if (checkForWin()) {
                gameFinished = true;
            } else if (isBoardFull()) {
                gameFinished = true;
            }

            if(cPlays<=2){
            if(player1Turn){

                    lastPlaysPosX[0][cPlays]=row;
                    lastPlaysPosX[1][cPlays]=col;


            }else{

                    lastPlaysPosO[0][cPlays]=row;
                    lastPlaysPosO[1][cPlays]=col;

            }
                cPlays++;
            }else{
                if(cPlays>=6) {
                    if (player1Turn) {
                        lastRow = lastPlaysPosX[0][0];
                        lastCol = lastPlaysPosX[1][0];
                        board[lastRow][lastCol] = "";
                        lastPlaysPosX[0] = Arrays.copyOfRange(lastPlaysPosX[0], 1, lastPlaysPosX[0].length);
                        lastPlaysPosX[1] = Arrays.copyOfRange(lastPlaysPosX[1], 1, lastPlaysPosX[1].length);
                        lastPlaysPosX[0][lastPlaysPosX[0].length - 1] = row;
                        lastPlaysPosX[1][lastPlaysPosX[1].length - 1] = col;

                    } else {
                        lastRow = lastPlaysPosO[0][0];
                        lastCol = lastPlaysPosO[1][0];
                        board[lastRow][lastCol] = "";
                        lastPlaysPosO[0] = Arrays.copyOfRange(lastPlaysPosO[0], 1, lastPlaysPosO[0].length);
                        lastPlaysPosO[1] = Arrays.copyOfRange(lastPlaysPosO[1], 1, lastPlaysPosO[1].length);
                        lastPlaysPosO[0][lastPlaysPosO[0].length - 1] = row;
                        lastPlaysPosO[1][lastPlaysPosO[1].length - 1] = col;

                    }
                }
            }
            player1Turn=!player1Turn;



        }
        return new int[]{lastRow, lastCol};
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
        player1Turn = true;
        gameFinished = false;
    }

    private boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}