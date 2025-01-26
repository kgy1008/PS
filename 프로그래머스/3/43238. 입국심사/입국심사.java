import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long end = times[times.length-1] * (long)n;
        long start = 0;
        long answer = end;
        
        while (start <= end) {
            long mid = (end + start) / 2;
            long person = 0;
            
            for (int time : times) {
                person += (mid / time);
            }
            
            if (person >= n) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
}