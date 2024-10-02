import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[] dp1 = new int[n];
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        for (int i=2; i<n-1; i++){
            dp1[i] = Math.max(dp1[i-2] + money[i], dp1[i-1]);
        }
        
        int[] dp2 = new int[n];
        dp2[1] = money[1];
        for (int i=2; i<n; i++) {
            dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
        }
        
        int max1 = Arrays.stream(dp1).max().getAsInt();
        int max2 = Arrays.stream(dp2).max().getAsInt();
        
        return Math.max(max1, max2);
    }
}