import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] arr = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        
        int[] score = new int[3];
        
        for(int i = 0; i < answers.length; i++) {
            for (int j = 0; j < 3; j ++) {
                if (arr[j][i % arr[j].length] == answers[i]) {
                    score[j]++;
                }
            }
        }
        
        int maxScore = Arrays.stream(score).max().getAsInt();
        ArrayList<Integer> result = new ArrayList<>();
    
        for (int i=0; i<3; i++) {
            if (maxScore == score[i]) {
                result.add(i+1);
            }
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}