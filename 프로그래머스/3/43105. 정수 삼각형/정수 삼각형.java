class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];
        dp[1][1] = dp[0][0] + triangle[1][1];
        
        for (int i=2; i < n ; i++) {
            for (int j=0; j<i+1; j++) {
                if (j == 0) dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if (j == i) dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
            }
        }
        
        int answer = 0;
        
        for (int a : dp[n-1]) {
            if (a > answer) answer = a;
        }
        
        return answer;
    }
}