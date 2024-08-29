import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer,Integer> orange = new HashMap<>();
        int answer = 0;
        
        for (int cnt : tangerine) {
            orange.put(cnt,orange.getOrDefault(cnt,0) + 1);
        }
        
        List<Integer> count = new ArrayList<>(orange.values());
        Collections.sort(count, Comparator.reverseOrder());
        
        
        for(int o : count) {
            if (k <= 0) break;
            k -= o;
            answer++;
        }
        
        return answer;
    }
}