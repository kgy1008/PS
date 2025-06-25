import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Map<Integer, Integer> map = new HashMap<>(); // 인용 횟수 - 인용 횟수 이상 인용된 논문의 개수
        
        for (int i=0; i<= 10000; i++) {
            map.put(i,0);
        }
        
        for (int c : citations) {
            for (int key : map.keySet()) {
                if (key <= c) {
                    map.put(key, map.get(key) + 1);
                }
            }
        }
        
        int answer = 0;
        
        for (int h=0; h<10000; h++) {
            int v = map.get(h); // h번 이상 인용된 논문의 개수
            
            if (v >= h && citations.length - v <= h) { // h번 이상 인용된 논문이 h편 이상일 때
                answer = Math.max(answer, h);
            }
        }
        return answer;
    }
}