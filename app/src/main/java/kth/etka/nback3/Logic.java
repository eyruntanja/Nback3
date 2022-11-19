package kth.etka.nback3;

import android.os.Bundle;

import java.util.Random;

public class Logic {

    public static final int SIZE=3;

    public enum Board {BOX, NONE}

    private Board[][] board;
    private int rounds;


    public void reset(){
        // Method to reset the board back to starting state
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col<SIZE; col++){
                board[row][col] = Board.NONE;
            }
        }
        rounds = 0;
    }


    public boolean isEmpty(int row, int col){return board[row][col] == Board.NONE;}


    public Board[][] getCopyOfBoard() {
        Board[][] copy = new Board[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col <SIZE; col++){
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }

    //Singleton
    public static Logic getInstance(){
        if (logic == null){
            logic = new Logic();
        }
        return logic;
    }

    private static Logic logic = null;

    private Logic(){
        board = new Board[SIZE][SIZE];
        reset();
    }
}

