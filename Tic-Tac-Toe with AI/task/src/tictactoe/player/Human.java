package tictactoe.player;

import tictactoe.entity.Difficulty;
import tictactoe.entity.MoveCharacter;
import tictactoe.game.Field;

import java.util.Scanner;

public class Human extends Player {

    public Human(Difficulty difficulty, Field field, MoveCharacter moveCharacter) {
        super(difficulty, field, moveCharacter);
    }

    @Override
    public void move() {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        try {
            System.out.print("Enter the coordinates: > ");
            x = Integer.parseInt(scanner.next()) - 1;
            y = Integer.parseInt(scanner.next()) - 1;
        }
        catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            move();
            return;
        }

        if (x > 2 || y > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            move();
            return;
        }

        switch (field.getField()[x][y]) {
            case 'O':
            case 'X': {
                System.out.println("This cell is occupied! Choose another one!");
                move();
                break;
            }
            case '_': {
                doMove(x, y);
                break;
            }
        }
    }
}