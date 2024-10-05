import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] wear : clothes) {
            String kind = wear[1];
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }
        
        int answer = 1;
        
        for (int count : map.values()) {
            answer *= (count + 1);
        }
        
        return answer - 1;
    }
}
