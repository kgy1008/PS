import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[]{1, gems.length};
        
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        
        int total = set.size();
        int len = gems.length;
        
        HashMap<String, Integer> map = new HashMap<>();
        int right = 0;
        for (int left=0; left<gems.length; left++) {
            while (right < gems.length && map.size() != total) {
                String gem = gems[right];
                map.put(gem, map.getOrDefault(gem, 0) + 1);
                right++;
            }
            
            right = Math.min(right, gems.length);
            if (map.size() == total && len > right - left) {
                len = right - left;
                answer[0] = left+1;
                answer[1] = right;
            }
            
            String remove = gems[left];
            map.put(remove, map.get(remove) - 1);
            if (map.get(remove) == 0) {
                map.remove(remove);
            }
        }
        
        return answer;
    }
}