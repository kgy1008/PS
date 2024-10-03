import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int kind = gemSet.size();  
        
        Map<String, Integer> gemMap = new HashMap<>();
        int[] answer = new int[2];
        int minLen = Integer.MAX_VALUE;  
        int start = 0;  
        
        for (int end = 0; end < gems.length; end++) {
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);
            
            while (gemMap.size() == kind) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    answer[0] = start + 1;  
                    answer[1] = end + 1;    
                }
                
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                if (gemMap.get(gems[start]) == 0) {
                    gemMap.remove(gems[start]);
                }
                start++;  
            }
        }
        
        return answer;
    }
}
