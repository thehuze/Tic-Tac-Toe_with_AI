package tictactoe.entity;

public enum EntityType {

    HUMAN(Difficulty.USER),
    AI(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD);

    private final Difficulty[] difficulties;

    EntityType(Difficulty... aliases) {
        this.difficulties = aliases;
    }

    public Difficulty[] getDifficulties() {
        return difficulties;
    }

    public static EntityType getByDifficulty(Difficulty difficulty) {
        for (EntityType type : EntityType.values()) {

            for (Difficulty d : type.difficulties) {

                if (d == difficulty) {
                    return type;
                }
            }
        }
        return null;
    }
}
