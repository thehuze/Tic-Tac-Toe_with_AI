package tictactoe.entity;

import java.util.Locale;

public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    USER("user");

    private final String alias;

    Difficulty(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public static Difficulty getByAlias(String userInput) {
        return Difficulty.valueOf(userInput.toUpperCase(Locale.ROOT));
    }
}
