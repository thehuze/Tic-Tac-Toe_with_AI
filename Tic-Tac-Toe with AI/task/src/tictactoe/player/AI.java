package tictactoe.player;

import tictactoe.entity.Difficulty;
import tictactoe.entity.MoveCharacter;
import tictactoe.game.Field;
import tictactoe.game.Minimax;
import tictactoe.game.Pos;

import java.util.Random;

public class AI extends Player {

    Minimax minimax;

    public AI(Field field, Difficulty difficulty, MoveCharacter moveCharacter) {
        super(difficulty, field, moveCharacter);
        this.minimax = new Minimax();
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
            case HARD: {
                moveHard();
                break;
            }
            default: {
                moveEasy();
            }
        }
    }

    /**
     * Обычная сложность ИИ:
     * Оппонент ходит на случайное свободное поле
     */
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

    /**
     * Средняя сложноть ИИ:
     * Если оппонент в шаге от победы, то компьютер мешает ему.
     * Если компьютер в шаге от победы, то компьютер побеждает.
     * Иначе: случайный ход
     */
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

    /**
     * Максимальная сложность ИИ:
     * Компьютер просчитывает несколько шагов вперед и двигается
     * по наиболее выигрышной тактике:
     * Алгоритм Минимакс (How to make your Tic Tac Toe game unbeatable by using the minimax algorithm)
     */
    public void moveHard() {
        Pos pos = minimax.findOptimalMovement(field, moveCharacter);
        if (pos != null) {
            doMove(pos.getRow(), pos.getCol());
        }
        else {
            moveMedium();
        }
    }
}