package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@PageTitle("Tic-Tac-Toe")
@Route("/tic-tac-toe")
public class TicTacToe extends VerticalLayout {
    private final Environment environment;

    private final String player1;
    private final String player2;

    private final String none;
    private final String[][] board; // the game board
    private String currentPlayer; // the current player (X or O)
    private boolean gameOver; // flag to indicate if the game is over

    @Autowired
    public TicTacToe(Environment environment) {
        this.environment = environment;
        this.player1 = this.environment.getProperty("tic-tac-toe.player1");
        this.player2 = this.environment.getProperty("tic-tac-toe.player2");
        this.none = " ";
        // initialize the board as a 3x3 array of empty spaces
        board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = this.none;
            }
        }

        // set the first player to X
        currentPlayer = this.player1;

        // create a layout for the board
        HorizontalLayout[] rows = new HorizontalLayout[3];
        for (int i = 0; i < 3; i++) {
            rows[i] = new HorizontalLayout();
            add(rows[i]);
        }

        // create buttons for each cell of the board
        Button[][] buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this.none);
                buttons[i][j].setHeight("100px");
                buttons[i][j].setWidth("100px");
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addClickListener(e -> move(finalI, finalJ, buttons)); // add a click listener to handle the move
                rows[i].add(buttons[i][j]);
            }
        }

        HorizontalLayout resetButtonLayout = new HorizontalLayout();
        Button resetButton = new Button("Reset");
        resetButton.addClickListener(e -> resetGame(buttons));
        resetButtonLayout.add(resetButton);
        add(resetButtonLayout);
    }

    private void resetGame(Button[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = this.none;
            }
        }
        this.currentPlayer = this.player1;
        gameOver = false;
        // update the button text to empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(this.none);
            }
        }
    }

    // this method handles a move by the current player at the given row and column
    private void move(int row, int col, Button[][] buttons) {
        if (!gameOver && board[row][col] .equals(this.none)) { // if the game is not over and the cell is empty
            board[row][col] = currentPlayer; // mark the cell with the current player's symbol
            buttons[row][col].setText(String.valueOf(currentPlayer)); // update the button text
            if (isWinningMove(row, col)) { // check if the move is a winning move
                gameOver = true; // set the game over flag to true
                Notification.show(currentPlayer + " wins!", 3000, Notification.Position.MIDDLE); // show a notification
            } else if (isBoardFull()) { // check if the board is full
                gameOver = true; // set the game over flag to true
                Notification.show("It's a tie!", 3000, Notification.Position.MIDDLE); // show a notification
            } else {
                // switch the current player
                currentPlayer = (currentPlayer.equals(this.player1)) ? this.player2 : this.player1;
            }
        }
    }

    // this method checks if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // this method checks if the move at the given row and column is a winning move
    private boolean isWinningMove(int row, int col) {
        // check the row
        if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
            return true;
        }
        // check the column
        if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
            return true;
        }
        // check the main diagonal
        if (row == col && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
        }
        // check the secondary diagonal
        if (row + col == 2 && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return true;
        }
        // otherwise, not a winning move
        return false;
    }
}
