package tictactoe;

import tictactoe.game.Game;

public class Main {

    public static Game game;
    public static void main(String[] args) {
        game = new Game();
        game.start();
    }
}
