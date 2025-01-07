import java.util.*;

public class Solution {

    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        
        for (int i=1; i<triangle.length; i++) {
            int[] tmp = triangle[i];
            for (int j=0; j<tmp.length; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i-1][0] + tmp[0];
                } else if (j == tmp.length - 1) {
                    dp[i][j] = tmp[j] + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j], tmp[j] + dp[i-1][j]);
                    dp[i][j] = Math.max(dp[i][j], tmp[j] + dp[i-1][j-1]);
                }
            }
        }
        int[] answer = dp[n-1];
        
        return Arrays.stream(answer).max().getAsInt();
    }

}