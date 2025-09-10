import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int min = 1;
        int max = distance;
        int answer = 0;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            
            if (isPossible(mid, rocks, n, distance)) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(int minDistance, int[] rocks, int n, int distance) {
        int removedRocks = 0;
        int lastPosition = 0;
        
        for (int rockPosition : rocks) {
            if (rockPosition - lastPosition < minDistance) {
                removedRocks++;
            } else {
                lastPosition = rockPosition;
            }
        }
        
        // 마지막 돌과 도착점(distance) 사이의 거리 확인
        if (distance - lastPosition < minDistance) {
            removedRocks++;
        }
        
        return removedRocks <= n;
    }
}