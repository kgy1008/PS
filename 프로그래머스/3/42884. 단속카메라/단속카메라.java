import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1,o2) -> Integer.compare(o1[1], o2[1]));
        
        int answer = 0;
        int cam = Integer.MIN_VALUE;
        
        for(int[] route : routes) {
            if(cam < route[0]) {
                cam = route[1];
                answer++;
            }
        }
        return answer;
    }
}