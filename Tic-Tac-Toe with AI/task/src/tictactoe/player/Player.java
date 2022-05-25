package tictactoe.player;

import tictactoe.Main;
import tictactoe.entity.Difficulty;
import tictactoe.entity.EntityType;
import tictactoe.entity.MoveCharacter;
import tictactoe.game.Field;

public abstract class Player {

    protected EntityType entity;
    protected final Difficulty difficulty;
    protected final Field field;
    protected final MoveCharacter moveCharacter;

    public Player(Difficulty difficulty, Field field, MoveCharacter moveCharacter) {
        this.entity = difficulty == Difficulty.USER ? EntityType.HUMAN : EntityType.AI;
        this.moveCharacter = moveCharacter;
        this.difficulty = difficulty;
        this.field = field;
    }

    /**
     * Имплементация родительского абстрактного метода
     */
    public abstract void move();

    /**
     * Сделать ход
     * @param x строка игрового поля
     * @param y колонка игрового поля
     */
    protected void doMove(int x, int y) {

        if (field.isSomeoneWin(true)) {
            return;
        }

        if (entity == EntityType.AI) System.out.println("Making move level \"" + difficulty.name().toLowerCase() + "\"");
        field.increaseMoves();
        field.getField()[x][y] = getMoveCharacter().getSymbol();
        field.print();

        if (!field.isSomeoneWin(true)) {
            Main.game.nextMove();
        }
    }

    public MoveCharacter getMoveCharacter() {
        return moveCharacter;
    }


}