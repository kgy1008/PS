import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int prev = 0;
        
        for (int station : stations) {
            int start = Math.max(1, station - w);
            
            if (prev < start) {
                answer += Math.ceil((double)(start - prev - 1) / (2*w + 1));
            }
            
            prev = Math.min(n,station + w);
        }

        if (prev < n) {
            int remain = n- prev;
            answer += Math.ceil((double)remain / (2*w + 1.0));
        }

        return answer;
    }
}