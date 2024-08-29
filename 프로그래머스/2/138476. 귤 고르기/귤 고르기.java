import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer,Integer> orange = new HashMap<>();
        int answer = 0;
        
        for (int cnt : tangerine) {
            orange.put(cnt,orange.getOrDefault(cnt,0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(orange.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        for(Map.Entry<Integer, Integer> entry : entryList) {
            if (k <= 0) break;
            k -= entry.getValue();
            answer++;
        }
        
        return answer;
    }
}