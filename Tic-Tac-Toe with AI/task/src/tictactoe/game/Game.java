package tictactoe.game;

import tictactoe.entity.Difficulty;
import tictactoe.entity.MoveCharacter;
import tictactoe.player.AI;
import tictactoe.player.Human;
import tictactoe.player.Player;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    private Field field;
    private Player player_one;
    private Player player_two;

    /**
     * Начать игру. Создает поле и вызывает прием команд пользователя
     */
    public void start() {

        field = new Field();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input command: > ");

        // Кто играет?
        String command = scanner.nextLine();
        String[] args = command.split(" ");

        if (Objects.equals(args[0], "exit")) {
            return;
        }

        if (args.length != 3) {
            badSettingInput();
            return;
        }

        if (!args[0].equals("start")) {
            badSettingInput();
            return;
        }

        Difficulty firstPlayerDif;
        Difficulty secondPlayerDif;

        // Создаем игроков
        try {
            firstPlayerDif = Difficulty.getByAlias(args[1]);
            player_one = firstPlayerDif == Difficulty.USER ? new Human(firstPlayerDif, field, MoveCharacter.X) : new AI(field, firstPlayerDif, MoveCharacter.X);

            secondPlayerDif = Difficulty.getByAlias(args[2]);
            player_two = secondPlayerDif == Difficulty.USER ? new Human(firstPlayerDif, field, MoveCharacter.O) : new AI(field, secondPlayerDif, MoveCharacter.O);
        }
        catch (IllegalArgumentException exception) {
            badSettingInput();
            return;
        }

        // Заполняю поле
        for (char[] chars : field.getField()) {
            Arrays.fill(chars, '_');
        }

        field.print();
        nextMove();
    }

    /**
     * Продолжить игру. Вызывает ход следующего игрока
     */
    public void nextMove() {
        if (field.amountOfMoves % 2 == 0) {
            player_one.move();
        }
        else {
            player_two.move();
        }
    }

    private void badSettingInput() {
        System.out.println("Bad parameters!");
        start();
    }
}