package com.davidgarcia.login_app_com;

public class Game {
    private String[][] board;
    private boolean player1Turn;
    private boolean gameFinished;

    public Game() {
        board = new String[3][3];
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

    public boolean makeMove(int row, int col) {
        if (board[row][col].equals("") && !gameFinished) {
            board[row][col] = player1Turn ? "X" : "O";
            if (checkForWin()) {
                gameFinished = true;
            } else if (isBoardFull()) {
                gameFinished = true;
            }
                player1Turn = !player1Turn;

            return true;
        }
        return false;
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