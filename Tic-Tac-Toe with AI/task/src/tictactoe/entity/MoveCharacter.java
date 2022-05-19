package tictactoe.entity;

public enum MoveCharacter {

    X('X'),
    O('O');

    private final char symbol;

    MoveCharacter(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
