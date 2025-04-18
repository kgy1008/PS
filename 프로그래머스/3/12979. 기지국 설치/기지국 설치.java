import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;  // 처음 아파트 번호
        
        for (int station : stations) {
            int left = station - w;  // 현재 기지국 커버 시작
            if (start < left) {
                int gap = left - start;
                answer += Math.ceil((double)gap / (2 * w + 1));
            }
            start = station + w + 1;  // 다음 커버되지 않은 구간의 시작
        }
        
        // 마지막 기지국 이후 구간
        if (start <= n) {
            int gap = n - start + 1;
            answer += Math.ceil((double)gap / (2 * w + 1));
        }
        
        return answer;
    }
}
