package tictactoe.player;

import tictactoe.entity.Difficulty;
import tictactoe.entity.MoveCharacter;
import tictactoe.game.Field;

import java.util.Random;

public class AI extends Player {

    public AI(Field field, Difficulty difficulty, MoveCharacter moveCharacter) {
        super(difficulty, field, moveCharacter);
    }

    @Override
    public void move() {

        if (field.getAmountOfMoves() >= 9) return;

        switch (difficulty) {
            case EASY: {
                moveEasy();
                break;
            }
            case MEDIUM: {
                moveMedium();
                break;
            }
            default: {
                moveEasy();
            }
        }
    }

    public void moveEasy() {
        Random random = new Random();

        int x = random.nextInt(3);
        int y = random.nextInt(3);

        switch (super.field.getField()[x][y]) {
            case 'O':
            case 'X': {
                move();
                break;
            }
            case '_': {
                doMove(x, y);
            }
        }
    }

    public void moveMedium() {
        // Перебираем все возможные варианты победы
        for (int[][] win : Field.win_conditions) {

            int x1 = win[0][0];
            int x2 = win[1][0];
            int x3 = win[2][0];
            int y1 = win[0][1];
            int y2 = win[1][1];
            int y3 = win[2][1];

            char f1 = field.getField()[x1][y1];
            char f2 = field.getField()[x2][y2];
            char f3 = field.getField()[x3][y3];

            if (f1 != '_' && f1 == f2 && f3 == '_') {
                doMove(x3, y3);
                break;
            }
            else if (f1 != '_' && f1 == f3 && f2 == '_') {
                doMove(x2, y2);
                break;
            }
            else if (f2 != '_' && f2 == f3 && f1 == '_') {
                doMove(x1, y1);
                break;
            }
        }
        moveEasy();
    }
}