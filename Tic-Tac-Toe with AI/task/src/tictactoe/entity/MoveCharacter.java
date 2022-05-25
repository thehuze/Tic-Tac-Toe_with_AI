package tictactoe.entity;

public enum MoveCharacter {

    X('X'),
    EMPTY('_'),
    O('O');

    private final char symbol;

    MoveCharacter(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
