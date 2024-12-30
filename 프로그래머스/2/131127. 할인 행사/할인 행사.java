import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> target = new HashMap<>();
        
        for (int i=0; i<want.length; i++) {
            target.put(want[i], number[i]);
        }
        
        int answer = 0;
        for (int i=0; i<=discount.length-10; i++) {
            Map<String, Integer> basket = new HashMap<>();
            for (int j=i; j<i+10; j++) {
                if (target.containsKey(discount[j])) {
                    basket.put(discount[j], basket.getOrDefault(discount[j], 0) + 1);
                }
            }
            
            if (basket.equals(target)) {
                answer++;
            }
        }
        return answer;
    }
}