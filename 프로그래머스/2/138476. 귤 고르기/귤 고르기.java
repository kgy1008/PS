import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        List<Integer> sizes = new ArrayList<>(map.values());
        sizes.sort(Collections.reverseOrder());
        int answer = 0;
        int tmp = 0;
        for (int s : sizes) {
            answer++;
            tmp += s;
            if (tmp >= k) {
                return answer;
            }
        }
        
        return answer;
    }
}