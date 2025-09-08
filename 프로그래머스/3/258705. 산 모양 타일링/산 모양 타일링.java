class Solution {
    public int solution(int n, int[] tops) {
        int[][] map = new int[2][2*n+1];
        
        for (int i=0; i<2*n+1; i++) {
            map[0][i] = 1;
        }
        
        for (int i=0; i<tops.length; i++) {
            if (tops[i] == 1) {
                int idx = i*2 + 1;
                map[1][idx] = 1;
            }
        }

        int[] dp = new int[2*n+1];
        dp[0] = 1;
        if (map[1][1] == 1) {
            dp[1] = 3;
        } else {
            dp[1] = 2;
        }
        
        for (int i=2; i<2*n+1; i++) {
            if (map[1][i] == 1) {
                dp[i] = (dp[i-1] * 2 + dp[i-2]) % 10007;
            } else {
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }
        }
        
        return dp[2*n];
    }
}