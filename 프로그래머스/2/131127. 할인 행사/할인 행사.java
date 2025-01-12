import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> target = new HashMap<>();
        
        for (int i=0; i<want.length; i++) {
            target.put(want[i], number[i]);
        }
        
        int idx = 0;
        int end = discount.length - 10;
        int answer = 0;
        
        while (idx <= end) {
            Map<String, Integer> tmp = new HashMap<>();
            for (int i=idx; i<idx+10; i++) {
                tmp.put(discount[i], tmp.getOrDefault(discount[i],0)+1);
            }
            
            if (tmp.equals(target)) {
                answer++;
            }
            idx++;
        }
        return answer;
    }
}