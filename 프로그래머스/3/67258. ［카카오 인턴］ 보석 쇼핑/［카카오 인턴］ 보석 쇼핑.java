import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int total = set.size(); // 전체 보석 종류 수
        
        int start = 0, end = 0;
        int min = gems.length - 1; 
        int left = 1, right = gems.length;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        while (end < gems.length) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            end++;
            
            while (map.size() == total) { // 모든 보석이 포함될 때
                if (end - start < min) {
                    min = end - start;
                    left = start + 1;
                    right = end;
                }
                
                // 시작점 보석을 하나 제거
                map.put(gems[start], map.get(gems[start]) - 1);
                if (map.get(gems[start]) == 0) {
                    map.remove(gems[start]);
                }
                start++;
            }
        }
        
        return new int[]{left, right};
    }
}
