import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Integer> sortedCount = new ArrayList<>(map.values());
        sortedCount.sort((o1,o2) -> Integer.compare(o2,o1));
        
        int answer = 0;
        int sum = 0;
        
        for (int count : sortedCount) {
            sum += count;
            answer++;
            
            if (sum >= k) {
                break;
            }
        }
        
        return answer;
    }
}