import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;

        ArrayList<Integer> num = new ArrayList<>();
        
        while (n != 1) {
            num.add(n);
            n /= 2;
        }
        
        int current = 1;
        
        for (int step : num) {
            if (step % 2 == 1) ans++;
        }

        return ans;
    }
}