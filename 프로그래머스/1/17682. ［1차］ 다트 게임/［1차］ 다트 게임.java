import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String dartResult) {
        int[] scores = new int[3];
        int turn = -1;
        int i = 0;
        
        while (i < dartResult.length()) {
            char c = dartResult.charAt(i);

            if (Character.isDigit(c)) {
                turn++;
                if (c == '1' && dartResult.charAt(i + 1) == '0') {
                    scores[turn] = 10;
                    i++;
                } else {
                    scores[turn] = c - '0';
                }
            } else if (c == 'S') {
                scores[turn] = scores[turn];
            } else if (c == 'D') {
                scores[turn] = scores[turn] * scores[turn];
            } else if (c == 'T') {
                scores[turn] = scores[turn] * scores[turn] * scores[turn];
            } else if (c == '*') {
                scores[turn] *= 2;
                if (turn > 0) {
                    scores[turn - 1] *= 2;
                }
            } else if (c == '#') {
                scores[turn] *= -1;
            }
            i++;
        }
        
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        
        return totalScore;
    }
}