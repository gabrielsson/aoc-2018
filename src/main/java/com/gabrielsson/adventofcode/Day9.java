package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

public class Day9 {

    public Object part1(int players, int marbles) {

        CountMap<Integer> playerScores = playGame(players, marbles);

        long max = playerScores.get(playerScores.getMaxKey());
        return max;
    }

    private CountMap<Integer> playGame(int players, int marbles) {
        CountMap<Integer> playerScores = new CountMap<>();
        CircleDeque<Integer> gamePlan = new CircleDeque<>();

        gamePlan.add(0);
        int currentPlayer = 0;

        for (int i = 1; i <= marbles; i++) {
            if (currentPlayer + 1 > players) {
                currentPlayer = 1;
            } else {
                currentPlayer++;
            }
            if (i % 23 == 0) {
                gamePlan.rotate(-7);
                playerScores.increment(currentPlayer, i + gamePlan.pop());
            } else {
                gamePlan.rotate(2);
                gamePlan.addLast(i);
            }
        }
        return playerScores;
    }
}
