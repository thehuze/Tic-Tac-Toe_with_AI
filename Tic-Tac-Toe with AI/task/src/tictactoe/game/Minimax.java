package tictactoe.game;

import tictactoe.entity.MoveCharacter;

public class Minimax {

    private static class Line {

        private final Pos[] line = new Pos[3];

        /**
         * Сюда передаем координаты 3-х позиций,
         * образующих линию
         *
         * @param pos1 Позиция 1
         * @param pos2 Позиция 2
         * @param pos3 Позиция 3
         */
        Line(Pos pos1, Pos pos2, Pos pos3) {
            line[0] = pos1;
            line[1] = pos2;
            line[2] = pos3;
        }

        /**
         * Получить координаты позиции, составляющей линию
         *
         * @param index от 0 до 2
         * @return Соответствующая позиция с координатами row, col
         */
        Pos getPos(int index) {
            return line[index];
        }
    }

    // Линнии бывают разные: 3 горизонтальных, 3 вертикальных и 2 диагональных линии
    private static final Line[] lines = new Line[]
            {
                    new Line(new Pos(0, 0), new Pos(0, 1), new Pos(0, 2)),// строка 0
                    new Line(new Pos(1, 0), new Pos(1, 1), new Pos(1, 2)),// строка 1
                    new Line(new Pos(2, 0), new Pos(2, 1), new Pos(2, 2)),// строка 2
                    new Line(new Pos(0, 0), new Pos(1, 0), new Pos(2, 0)),// столбец 0
                    new Line(new Pos(0, 1), new Pos(1, 1), new Pos(2, 1)),// столбец 1
                    new Line(new Pos(0, 2), new Pos(1, 2), new Pos(2, 2)),// столбец 2
                    new Line(new Pos(0, 0), new Pos(1, 1), new Pos(2, 2)),// диагональ
                    new Line(new Pos(0, 2), new Pos(1, 1), new Pos(2, 0)),// другая диаг.
            };

    private MoveCharacter firstPlayerChar;
    private MoveCharacter secondPlayerChar;

    /**
     * Запускаем мощь интеллекта для поиска оптимальной позиции
     *
     * @param field Игровая доска с крестиками и ноликами
     * @param character  кто ходит: крестики или нолики
     * @return Позиция, куда надо ставить символ
     */
    public Pos findOptimalMovement(Field field, MoveCharacter character) {
        firstPlayerChar = character;
        secondPlayerChar = character == MoveCharacter.O ? MoveCharacter.X : MoveCharacter.O;
        Score score = miniMax(field, firstPlayerChar, 4);
        return score.getPos();
    }

    /**
     * Реализация алгоритма Minimax
     *
     * @param field Игровая доска с крестиками и ноликами
     * @param character  Чей сейчас ход: крестик или нолик
     * @param depth Максимальная глубина рекурсии
     * @return Ответ содержит оценку для самой оптимальной позиции и саму эту позицию для символа
     */
    private Score miniMax(Field field, MoveCharacter character, int depth) {
        // Ищем наиболее благоприятный исход для нас и наиболее плохой для противника
        int bestScore = (character == firstPlayerChar) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Pos bestPos = null;
        if (depth == 0 || field.isSomeoneWin(false)) {
            // Конец игры или достигнут предел глубины рекурсии
            // Вычисляем оценку игровой ситуации на доске
            bestScore = evaluate(field);
        } else {
            // Клонируем игровую доску со всеми уже поставленными крестиками и ноликами
            // и используем эту копию в рекурсивных вызовах miniMax в теле цикла
            Field clonedBoard = field.copy();
            // Проходим в цикле по позициям, НЕ ЗАНЯТЫМ символами
            for (Pos freePos : field.getFreePositions()) {
                // Делаем ход
                clonedBoard.setCharAtPosition(freePos, character);
                if (character == firstPlayerChar) {  // Если сыграли мы

                    int currentScore = miniMax(clonedBoard, secondPlayerChar, depth - 1).getScorePoints();

                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestPos = freePos;
                    }
                } else {  // Если сыграл оппонент

                    int currentScore = miniMax(clonedBoard, firstPlayerChar, depth - 1).getScorePoints();

                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestPos = freePos;
                    }
                }
                // Отменяем ход чтобы восстановить игровую ситуацию перед следующей итерацией цикла
                clonedBoard.setCharAtPosition(freePos, MoveCharacter.EMPTY);
            }
        }
        return new Score(bestPos, bestScore);
    }

    /**
     * Функция оценки ситуации для игровой доски.
     * Хорошо работает при глубине рекурсии 4 и более.
     *
     * @param field - игровая доска
     * @return Сумма эвристик по всем возможным линиям
     */
    private int evaluate(Field field) {
        int score = 0;
        // Вычисление суммарной эвристики по всем 8 линиям доски: 3 строки, 3 стобца, 2 диагонали
        for (Line line : lines) {
            score += evaluateLine(field, line);
        }
        return score;
    }

    /**
     * Эвристическая функция для заданной тремя позициями линии
     *
     * @param field - игровая доска
     * @return +100, +10, +1 для 3-, 2-, 1- поставленных в линию наших символов.
     * -100, -10, -1 для 3-, 2-, 1- поставленных в линию символов противника.
     */
    private int evaluateLine(Field field, Line line) {
        int score = 0;

        char cell1 = field.getCharAtPosition(line.getPos(0));
        char cell2 = field.getCharAtPosition(line.getPos(1));
        char cell3 = field.getCharAtPosition(line.getPos(2));

        // cell1
        if (cell1 == firstPlayerChar.getSymbol()) {
            score = 1;
        } else if (cell1 == secondPlayerChar.getSymbol()) {
            score = -1;
        }

        // cell2
        if (cell2 == firstPlayerChar.getSymbol()) {
            if (score == 1) {   // cell1 это мы
                score = 10;
            } else if (score == -1) {  // cell1 это противник
                return 0;
            } else {  // cell1 пустая
                score = 1;
            }
        } else if (cell2 == secondPlayerChar.getSymbol()) {
            if (score == -1) { // cell1 это мы
                score = -10;
            } else if (score == 1) { // cell1 это противник
                return 0;
            } else {  // cell1 пустая
                score = -1;
            }
        }

        // cell3
        if (cell3 == firstPlayerChar.getSymbol()) {
            if (score > 0) {  // cell1 и/или cell2 это мы
                score *= 10;
            } else if (score < 0) {  // cell1 и/или cell2 это противник
                return 0;
            } else {  // cell1 и cell2 пустые
                score = 1;
            }
        } else if (cell3 == secondPlayerChar.getSymbol()) {
            if (score < 0) {  // cell1 и/или cell2 это мы
                score *= 10;
            } else if (score > 0) {  // cell1 и/или cell2 это противник
                return 0;
            } else {  // cell1 и cell2 пустые
                score = -1;
            }
        }
        return score;
    }
}
