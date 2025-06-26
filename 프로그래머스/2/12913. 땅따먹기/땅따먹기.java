import java.util.*;

class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        
        // 초기화
        for (int i=0; i<4; i++) {
            dp[0][i] = land[0][i];
        }
        
        for (int i=1; i<land.length; i++) {
            for (int idx = 0; idx<4; idx++) {
                if (idx == 0) {
                    dp[i][idx] = land[i][idx] + Math.max(dp[i-1][1], Math.max(dp[i-1][2], dp[i-1][3]));
                } 
                else if (idx == 1) {
                    dp[i][idx] = land[i][idx] + Math.max(dp[i-1][0], Math.max(dp[i-1][2], dp[i-1][3]));
                }
                else if (idx == 2) {
                    dp[i][idx] = land[i][idx] + Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][3]));
                }
                else {
                    dp[i][idx] = land[i][idx] + Math.max(dp[i-1][0], Math.max(dp[i-1][2], dp[i-1][1]));
                }
            }
        }
        
        int last = dp.length;
        return Arrays.stream(dp[last-1]).max().getAsInt();
    }
}