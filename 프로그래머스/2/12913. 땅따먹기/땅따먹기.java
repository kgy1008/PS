import java.util.*;

class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int[][] answer = new int[n][4];
        
        for (int i=0; i<4; i++) {
            answer[0][i] = land[0][i];
        }
        
        for (int i=1; i<n; i++) {
            for (int j=0; j<4; j++) {
                int max = 0;
                for (int k=0; k<4; k++) {
                    if (k != j) {
                        max = Math.max(max, answer[i-1][k]);
                    }
                }
                answer[i][j] = max + land[i][j];
            }
        }
        
        return Arrays.stream(answer[n-1]).max().getAsInt();
    }
}
