import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long max = ((long) times[times.length - 1]) * n;
        long min = 0L;

        while (min < max) {
            long mid = (max + min) / 2L;
            long count = 0L;

            for (int time : times) {
                count += (mid / time);
            }

            if (count < n) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return max;
    }
}