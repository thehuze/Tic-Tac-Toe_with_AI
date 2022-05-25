# О проекте #
### Простенькие консольные крестики-нолики с применением многомерных массивов, ООП и [Minimax алгоритма](https://www.freecodecamp.org/news/how-to-make-your-tic-tac-toe-game-unbeatable-by-using-the-minimax-algorithm-9d690bad4b37/)</br> 


### Существует 3 уровня сложности: ###
+ `easy`: ИИ делает ход, полагаясь на псевдорандом;

+ `medium`: Если противник в шаге от победы, то ИИ мешает ему. 
Побеждает сам, если он в шаге от победы. В противном случае ход происходит на основе псевдорандома;

+ `hard`: ИИ с самого начала анализирует и просчитывает все ходы наперед, не оставляя шансов противнику.

Также, можно поиграть против игрока, указав сложность `user`.
____
# Как запустить приложение #
Для запуска приложения потребуется **Java 11** или выше.
1. Скачайте приложение (доступно в релизах);
2. Запустите командную строку (WIN + R > cmd)
3. `java -jar <путь до файла приложения>`
____

# Доступные команды #
- `start <сложность> <сложность>` - запустить игру </br> **Например:** `start user hard`
- `exit` - закрыть приложение