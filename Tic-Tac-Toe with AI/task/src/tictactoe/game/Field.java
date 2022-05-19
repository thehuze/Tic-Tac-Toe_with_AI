package tictactoe.game;

import java.util.Arrays;

public class Field {

    public static int[][][] win_conditions;
    protected int amountOfMoves;
    protected char[][] field;

    public Field() {
        this.amountOfMoves = 0;
        field = new char[3][3];
        declareConditions();
    }

    public boolean isSomeoneWin() {
        for (int[][] win_condition : win_conditions) {
            int x_counter = 0;
            int o_counter = 0;
            for (int[] into : win_condition) {
                x_counter = field[into[0]][into[1]] == 'X' ? x_counter + 1 : 0;
                o_counter = field[into[0]][into[1]] == 'O' ? o_counter + 1 : 0;

                if (getAmountOfMoves() >= 9) {
                    System.out.println("Draw");
                    return true;
                }
                if (x_counter >= 3) {
                    System.out.println("X wins");
                    return true;
                }
                if (o_counter >= 3) {
                    System.out.println("O wins");
                    return true;
                }
            }
        }
        return false;
    }

    public void print() {
        System.out.println("---------");
        System.out.println("| " + Arrays.toString(field[0]).replace(",", "").replace("_", " ").replace("[", "").replace("]", "") + " |");
        System.out.println("| " + Arrays.toString(field[1]).replace(",", "").replace("_", " ").replace("[", "").replace("]", "") + " |");
        System.out.println("| " + Arrays.toString(field[2]).replace(",", "").replace("_", " ").replace("[", "").replace("]", "") + " |");
        System.out.println("---------");

    }

    public char[][] getField() {
        return field;
    }

    public void increaseMoves() {
        amountOfMoves++;
    }

    public int getAmountOfMoves() {
        return amountOfMoves;
    }

    private void declareConditions() {
        win_conditions = new int[8][3][];
        win_conditions[0][0] = new int[]{0, 0}; // верхняя горизонталь
        win_conditions[0][1] = new int[]{0, 1};
        win_conditions[0][2] = new int[]{0, 2};

        win_conditions[1][0] = new int[]{1, 0}; // средняя горизонталь
        win_conditions[1][1] = new int[]{1, 1};
        win_conditions[1][2] = new int[]{1, 2};

        win_conditions[2][0] = new int[]{2, 0}; // нижняя горизонталь
        win_conditions[2][1] = new int[]{2, 1};
        win_conditions[2][2] = new int[]{2, 2};

        win_conditions[3][0] = new int[]{0, 0}; // первая вертикаль
        win_conditions[3][1] = new int[]{1, 0};
        win_conditions[3][2] = new int[]{2, 0};

        win_conditions[4][0] = new int[]{0, 1}; // вторая вертикаль
        win_conditions[4][1] = new int[]{1, 1};
        win_conditions[4][2] = new int[]{2, 1};

        win_conditions[5][0] = new int[]{0, 2}; // третья вертикаль
        win_conditions[5][1] = new int[]{1, 2};
        win_conditions[5][2] = new int[]{2, 2};

        win_conditions[6][0] = new int[]{0, 0}; // левая диагональ
        win_conditions[6][1] = new int[]{1, 1};
        win_conditions[6][2] = new int[]{2, 2};

        win_conditions[7][0] = new int[]{0, 2}; // правая диагональ
        win_conditions[7][1] = new int[]{1, 1};
        win_conditions[7][2] = new int[]{2, 0};
    }

}