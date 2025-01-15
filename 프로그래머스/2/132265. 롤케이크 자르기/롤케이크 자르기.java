import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int top : topping) {
            map.put(top, map.getOrDefault(top,0) + 1);
        }
        
        int size = map.keySet().size();
        int answer = 0;
        Set<Integer> tmp = new HashSet<>();
        
        for (int i=0; i<topping.length; i++) {
            tmp.add(topping[i]);
            map.put(topping[i], map.get(topping[i]) - 1);
            if (map.get(topping[i]) == 0) {
                map.remove(topping[i]);
            }
            
            if (tmp.size() == map.keySet().size()) {
                answer++;
            }
        }
        return answer;
    }
}